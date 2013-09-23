package eecs285.proj4;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class nameInputDialog extends JDialog{
  BackPanel Background;
  JTextField textField;
  JLabel textLabel;
  JLabel buttonLabel;
  JButton Check;
  
  String userName;
  MatchGameFrame mainframe;
  Integer finalscore;
  String mode;
  
  nameInputDialog(MatchGameFrame mainframe, int finalScore, int Mode){
    super(mainframe, null, true);
    mainframe = mainframe;
    userName = null;
    finalscore=finalScore;
  
    setSize(800, 600);
    Background = new BackPanel("/nameInput.png");
    if(Mode==0) {
      mode="easy_records";
  }
  else if(Mode==1) {
      mode="medium_records";
  }
  else if(Mode==2) {
      mode="hard_records";
  }

    buttonLabel = new JLabel();
    buttonLabel.setLayout(new GridLayout(1, 3));
    buttonLabel.add(new JLabel(""));
    buttonLabel.add(new JLabel(""));
    
    Check = new JButton();
    Check.addActionListener(new getNameActionListener());
    Check.setOpaque(false);
    Check.setContentAreaFilled(false);
    Check.setBorderPainted(false);
    buttonLabel.add(Check);
    
    textLabel = new JLabel();
    textLabel.setLayout(new GridLayout(3, 1));
    textLabel.add(new JLabel(""));
    textField = new JTextField();
    textField.setFont(new Font("Broadway", Font.ITALIC, 24));
    textLabel.add(textField);
    textLabel.add(buttonLabel);
    
    Background.setLayout(new GridLayout(3, 3));
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    Background.add(textLabel);
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    Background.add(new JLabel(""));
    
    add(Background);
    Background.setOpaque(false);
    setUndecorated(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  public class getNameActionListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        
        try{

            Connection conn = getConnection();

            matchingData matchData = new matchingData(conn);

            matchData.readRecords(textField.getText(), finalscore, mode);

             conn.close();
             dispose();

        }

        catch (SQLIntegrityConstraintViolationException a){

            //Do something here. user entered a user_name that already exists

        }

        catch (SQLException e1) {

            System.out.println("??");

            e1.printStackTrace();

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
