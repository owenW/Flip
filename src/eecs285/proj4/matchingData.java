package eecs285.proj4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class matchingData{
    Connection oracleConnection = null;
    private TreeSet<Record> records = new TreeSet<Record>();
    public matchingData(Connection conn){
        oracleConnection = conn;
    }
    public void getRecords(String difficulty) throws SQLException{ 
        
        // Scrollable result set allows us to read forward (using next())
        // and also backward.  
        // This is needed here to support the user of isFirst() and isLast() methods,
        // but in many cases you will not need it.
        // To create a "normal" (unscrollable) statement, you would simply call
        // Statement stmt = oracleConnection.createStatement();
        //
        Statement stmt = oracleConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        
        // For each month, find the number of friends born that month
        // Sort them in descending order of count
        ResultSet rst = stmt.executeQuery("SELECT USER_ID, GAME_SCORE, ID FROM " +
                "(SELECT USER_ID, GAME_SCORE, ID FROM " + difficulty + " ORDER BY 2 DESC)" +
                " WHERE ROWNUM <=5");//SELECT TOP 5 PLAYERS

        // Get the top ten player IDs with the most game score
        //
        while(rst.next()) {
            String user_id = rst.getString(1);
            Integer user_score = rst.getInt(2);
            Integer id = rst.getInt(3);
            Record aRecord = new Record(user_id,user_score,id);
            this.records.add(aRecord);
        }
        
        // Close statement and result set
        rst.close();
        stmt.close();
    }
    
    public void readRecords(String userID, Integer userScore, String difficulty) throws SQLException{
     // Scrollable result set allows us to read forward (using next())
        // and also backward.  
        // This is needed here to support the user of isFirst() and isLast() methods,
        // but in many cases you will not need it.
        // To create a "normal" (unscrollable) statement, you would simply call
        // Statement stmt = oracleConnection.createStatement();
        //
        Statement stmt = oracleConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        
        // For each month, find the number of friends born that month
        // Sort them in descending order of count
        ResultSet rst = stmt.executeQuery("INSERT INTO  " + difficulty +
                "(USER_ID, GAME_SCORE) VALUES (" +
                "'"+userID + "'" + "," + userScore + ")");//insert a record
        
        //Close statement and result set
        rst.close();
        stmt.close();
    }
    public void readRecords(String feedBack) throws SQLException{
        // Scrollable result set allows us to read forward (using next())
           // and also backward.  
           // This is needed here to support the user of isFirst() and isLast() methods,
           // but in many cases you will not need it.
           // To create a "normal" (unscrollable) statement, you would simply call
           // Statement stmt = oracleConnection.createStatement();
           //
           Statement stmt = oracleConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_READ_ONLY);
           
           // For each month, find the number of friends born that month
           // Sort them in descending order of count
           ResultSet rst = stmt.executeQuery("INSERT INTO  FEEDBACK"  +
                   "(feedback) VALUES (" +
                   "'"+feedBack +"')");//insert a record
           
           //Close statement and result set
           rst.close();
           stmt.close();
       }
    public TreeSet<Record> getData() {
        return records;
    }
}