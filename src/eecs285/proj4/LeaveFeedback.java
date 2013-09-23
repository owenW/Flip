package eecs285.proj4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;


public class LeaveFeedback extends JDialog {
	BackPanel leaveFeedback;
	
	
	JTextField feedback;
	
	
	JButton confirm;
	
	
	public LeaveFeedback(MatchGameFrame mainFrame){
		super(mainFrame, null, true);
		feedback=new JTextField(20);
		
		
		confirm=new JButton(new ImageIcon("Path"));
		confirm.addActionListener(new feedbackListener());
		
		leaveFeedback=new BackPanel(null);
		leaveFeedback.setLayout(new FlowLayout());
		leaveFeedback.add(feedback);
		leaveFeedback.add(confirm);
		add(leaveFeedback);
		setVisible(true);
	}

	public class feedbackListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try{

	            Connection conn = getConnection();

	            matchingData matchData = new matchingData(conn);

	            matchData.readRecords(feedback.getText());

	             conn.close();

	        }

	        catch (SQLIntegrityConstraintViolationException a){

	            //Do something here. user entered a user_name that already exists

	        }

	        catch (SQLException e2) {

	            System.out.println("??");

	            e2.printStackTrace();

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