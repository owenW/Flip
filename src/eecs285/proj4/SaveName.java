package eecs285.proj4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

public class SaveName extends JDialog {
	BackPanel name;
	
	
	JTextField getName;
	String mode;
	
	JButton confirm;
	int finalScore;
	
	public SaveName(MatchGameFrame mainFrame, int FinalScore, int Mode){
		super(mainFrame, null, true);
		getName=new JTextField(20);
		finalScore=FinalScore;
		if(Mode==0) {
			mode="easy_records";
		}
		else if(Mode==1) {
			mode="normal_records";
		}
		else if(Mode==2) {
			mode="hard_records";
		}
		
		
		confirm=new JButton(new ImageIcon("Path"));
		confirm.addActionListener(new getNameActionListener());
		
		name=new BackPanel(null);
		name.setLayout(new FlowLayout());
		name.add(getName);
		name.add(confirm);
		add(name);
		setVisible(true);	
		
	}



	public class getNameActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
	        
			try{

	            Connection conn = getConnection();

	            matchingData matchData = new matchingData(conn);

	            matchData.readRecords(getName.getText(), finalScore, mode);

	             conn.close();

	        }

	        catch (SQLIntegrityConstraintViolationException a){

	            //Do something here. user entered a user_name that already exists

	        }

	        catch (SQLException e3) {

	            System.out.println("??");

	            e3.printStackTrace();

	        }

	       /* catch (IOException e) {

	            e.printStackTrace();

	        } */

	    }

	    

	    public Connection getConnection() throws SQLException{

	        try {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } catch (ClassNotFoundException e1) {

	            e1.printStackTrace();

	        }

	        return DriverManager.getConnection("jdbc:oracle:thin:@forktail.dsc.umich.edu:1521:COURSEDB", "owenwang", "eecs285");

	    }
		}
	

	
}