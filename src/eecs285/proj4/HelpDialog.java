package eecs285.proj4;

import javax.swing.*;

import eecs285.proj4.MatchGameFrame.PageOneListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpDialog extends JDialog {
  JButton GoBack;  
  JPanel Upper;
  JPanel Mid;
  JPanel Bottom;
  BackPanel Background;
  
  MatchGameFrame superFrame;
  
  
  public HelpDialog(MatchGameFrame MainFrame) {
    super(MainFrame, null, true);
    setSize(800,600);
    superFrame = MainFrame;
    
    GoBack = new JButton();
    GoBack.setPreferredSize(new Dimension(50, 50));
    GoBack.addActionListener(new myListener());
    GoBack.setOpaque(false);
    GoBack.setContentAreaFilled(false);
    //GoBack.setBorderPainted(false);    
    Upper = new JPanel(new FlowLayout());
    Upper.setPreferredSize(new Dimension(800,420));
    Upper.setOpaque(false);
    Mid = new JPanel(new GridLayout(1,16));
    Mid.setPreferredSize(new Dimension(800,50));
    Mid.setOpaque(false);
    Bottom = new JPanel(new FlowLayout());
    Bottom.setPreferredSize(new Dimension(800,130));
    Bottom.setOpaque(false);
    Background = new BackPanel("/Help.gif");
    Background.setPreferredSize(new Dimension(800,600));
    Background.setLayout(new BorderLayout());
    
    for(int i=0;i<11;i++)
      Mid.add(new JLabel());    
    Mid.add(GoBack);
    for(int i=0;i<4;i++)
      Mid.add(new JLabel());
    Background.add(Upper,BorderLayout.NORTH);
    Background.add(Mid,BorderLayout.CENTER);
    Background.add(Bottom,BorderLayout.SOUTH);
      
    add(Background);
    setUndecorated(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  public class myListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(GoBack)){        
        superFrame.getContentPane().removeAll();
        superFrame.add(superFrame.Page1);
        superFrame.revalidate();
        superFrame.repaint();
        HelpDialog.this.dispose();
      }
      
    }
    
  }
}