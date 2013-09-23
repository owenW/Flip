package eecs285.proj4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.TreeSet;

public class test285 {

  public static void main(String[] arg) {

    try {
      Connection conn = getConnection();
      matchingData matchData = new matchingData(conn);
      matchData.getRecords("easy_records");
      TreeSet<Record> records = matchData.getData();
      Iterator<Record> it = records.descendingIterator();
      matchData.readRecords("test2", 200, "easy_records");
      while (it.hasNext()) {
        Record aRecord = it.next();
        System.out.println(aRecord.user_id);
        System.out.println(aRecord.user_score);
      }
      conn.close();
    } catch (SQLIntegrityConstraintViolationException a) {
      // Do something here. user entered a user_name that already exists
    } catch (SQLException e) {
      System.out.println("??");
      e.printStackTrace();
    }
    /*
     * catch (IOException e) { e.printStackTrace(); }
     */
  }

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    return DriverManager.getConnection(
        "jdbc:oracle:thin:@forktail.dsc.umich.edu:1521:COURSEDB", "owenwang",
        "eecs285");
  }
}