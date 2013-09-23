package eecs285.proj4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Score extends JDialog{
  BackPanel score;
  JLabel bottom, upper;
  JLabel finalScore;
  JPanel fs;
  
  int Mode;
  JButton saveName, leaveFeedBack, exit;
  JLabel saveNameLabel, leaveFBLabel, exitLabel;
  MatchGameFrame mainframe;
  
  int finalScoreIn;
  
    public Score(MatchGameFrame mainFrame, int FinalScore, int mode){
      super(mainFrame, null, true);
      setSize(800, 600);
      mainframe=mainFrame;
      finalScoreIn=FinalScore;
      Mode=mode;
      
      finalScore = new JLabel(Integer.toString(finalScoreIn), SwingConstants.CENTER);
      finalScore.setFont(new Font("Broadway", Font.BOLD, 52));
      finalScore.setForeground(Color.ORANGE);
  
      upper = new JLabel();
      upper.setLayout(new GridLayout(2, 4));
      upper.add(new JLabel(""));
      upper.add(new JLabel(""));
      upper.add(new JLabel(""));
      upper.add(new JLabel(""));
      upper.add(new JLabel(""));
      upper.add(new JLabel(""));
      upper.add(finalScore);
      upper.add(new JLabel(""));
      
      saveName=new JButton();
      leaveFeedBack=new JButton();
      exit=new JButton();
      saveName.addActionListener(new ScoreActionListener());
      leaveFeedBack.addActionListener(new ScoreActionListener());
      exit.addActionListener(new ScoreActionListener());
      
      exit.setOpaque(false);
      exit.setContentAreaFilled(false);
      exit.setBorderPainted(false);
      saveName.setOpaque(false);
      saveName.setContentAreaFilled(false);
      saveName.setBorderPainted(false);
      leaveFeedBack.setOpaque(false);
      leaveFeedBack.setContentAreaFilled(false);
      leaveFeedBack.setBorderPainted(false);
      
      exitLabel = new JLabel();
      exitLabel.setLayout(new GridLayout(4, 3));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      exitLabel.add(exit);
      exitLabel.add(new JLabel(""));
      exitLabel.add(new JLabel(""));
      

      saveNameLabel = new JLabel();
      saveName.setSize(150, 200);
      saveNameLabel.add(saveName, SwingConstants.CENTER);
      
      leaveFBLabel = new JLabel();
      leaveFeedBack.setSize(150, 200);
      leaveFBLabel.add(leaveFeedBack, SwingConstants.CENTER);
      
      bottom = new JLabel();
      bottom.setLayout(new GridLayout(1, 3));
      bottom.add(exitLabel);
      bottom.add(saveNameLabel);
      bottom.add(leaveFBLabel);
      
      score=new BackPanel("/scoreDisp.png");
      score.setLayout(new GridLayout(2, 1));
      score.add(upper);
      score.add(bottom);
      
      add(score);
      setUndecorated(true);
      setLocationRelativeTo(null);
      setVisible(true);
      //setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
  }
  
  
  public class ScoreActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          if(e.getSource().equals(exit)) {
              dispose();
          }
          else if(e.getSource().equals(saveName)){
              
              nameInputDialog name=new nameInputDialog(mainframe, finalScoreIn, Mode);
              
          }
          
          else if(e.getSource().equals(leaveFeedBack)){
              
              feedbackDialog feedback=new feedbackDialog(mainframe);
              
          }
      }
  }

}

