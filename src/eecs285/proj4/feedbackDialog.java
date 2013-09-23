package eecs285.proj4;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class feedbackDialog extends JDialog{
  BackPanel Background;
  JTextField textField;
  JLabel textLabel;
  JLabel buttonLabel;
  JButton Check;
  
  MatchGameFrame mainframe;
  String feedback;
  
  feedbackDialog(MatchGameFrame mainframe){
    super(mainframe, null, true);
    mainframe = mainframe;
    String feedback = null;
    
    setSize(800, 600);
    Background = new BackPanel("/feedbackInput.png");

    buttonLabel = new JLabel();
    buttonLabel.setLayout(new GridLayout(1, 3));
    buttonLabel.add(new JLabel(""));
    buttonLabel.add(new JLabel(""));
    
    Check = new JButton();
    Check.addActionListener(new checkActionListener());
    Check.setOpaque(false);
    Check.setContentAreaFilled(false);
    Check.setBorderPainted(false);
    buttonLabel.add(Check);
    
    textLabel = new JLabel();
    textLabel.setLayout(new GridLayout(3, 1));
    textLabel.add(new JLabel(""));
    textField = new JTextField();
    textField.setFont(new Font("Broadway", Font.ITALIC, 12));
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
  
  public class checkActionListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      feedback = textField.getText();
      dispose();
    }
    
  }
}

