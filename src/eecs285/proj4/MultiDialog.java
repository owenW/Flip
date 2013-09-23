package eecs285.proj4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiDialog extends JDialog {
  String UserFriendID_str = null;
  //boolean ConnectionStatus = false;

  JButton ServerSelect;
  JButton ClientSelect;
  JButton Confirm;
  JButton Cancel;
  JTextField UserFriendID;

  JPanel Left;
  JPanel Left1;
  JPanel Left2;
  JPanel LeftEmpty1;
  JPanel LeftEmpty2;
  JPanel LeftEmpty3;
  JPanel Right;
  JPanel Right1;
  JPanel Right2;
  JPanel Right21;
  JPanel Right3;
  JPanel Right31;
  JPanel Right32;
  JPanel Side1;
  JPanel Side2;
  JPanel Page;
  BackPanel Background;

  // slf
  int server = 0; // 1 server; 2 client ; 0 no choice

  MatchGameFrame superFrame;

  public MultiDialog(MatchGameFrame MainFrame) {
    super(MainFrame, null, true);
    setSize(600, 450);
    superFrame = MainFrame;

    ServerSelect = new JButton("Server");
    ServerSelect.addActionListener(new MyActionListener());
    ServerSelect.setPreferredSize(new Dimension(160, 80));
    ServerSelect.setOpaque(false);
    ServerSelect.setContentAreaFilled(false);
    // ServerSelect.setBorderPainted(false);
    ClientSelect = new JButton("Client");
    ClientSelect.addActionListener(new MyActionListener());
    ClientSelect.setPreferredSize(new Dimension(160, 80));
    ClientSelect.setOpaque(false);
    ClientSelect.setContentAreaFilled(false);
    // ClientSelect.setBorderPainted(false);
    Confirm = new JButton("Confirm");
    Confirm.addActionListener(new MyActionListener());
    Confirm.setPreferredSize(new Dimension(140, 100));
    Confirm.setOpaque(false);
    Confirm.setContentAreaFilled(false);
    // Confirm.setBorderPainted(false);
    Cancel = new JButton("Cancel");
    Cancel.addActionListener(new MyActionListener());
    Cancel.setPreferredSize(new Dimension(140, 100));
    Cancel.setOpaque(false);
    Cancel.setContentAreaFilled(false);
    // Cancel.setBorderPainted(false);
    UserFriendID = new JTextField();
    UserFriendID.setPreferredSize(new Dimension(280, 50));
    UserFriendID.setFont(new Font("Broadway", Font.BOLD, 30));
    // UserFriendID.setBackground(Color);
    // UserFriendID.setOpaque(false);
    Left = new JPanel(new GridLayout(5, 1));
    Left.setPreferredSize(new Dimension(220, 450));
    Left.setOpaque(false);
    Left1 = new JPanel(new FlowLayout());
    Left1.setPreferredSize(new Dimension(180, 80));
    Left1.setOpaque(false);
    Left2 = new JPanel(new FlowLayout());
    Left2.setPreferredSize(new Dimension(200, 80));
    Left2.setOpaque(false);
    LeftEmpty1 = new JPanel(new FlowLayout());
    LeftEmpty1.setOpaque(false);
    LeftEmpty2 = new JPanel(new FlowLayout());
    LeftEmpty2.setOpaque(false);
    LeftEmpty3 = new JPanel(new FlowLayout());
    LeftEmpty3.setOpaque(false);
    Right = new JPanel(new GridLayout(3, 1));
    Right.setPreferredSize(new Dimension(340, 450));
    Right.setOpaque(false);
    Right1 = new JPanel(new FlowLayout());
    Right1.setPreferredSize(new Dimension(250, 133));
    Right1.setOpaque(false);
    Right2 = new JPanel(new FlowLayout());
    Right2.setPreferredSize(new Dimension(250, 133));
    Right2.setOpaque(false);
    Right21 = new JPanel(new BorderLayout());
    Right21.setPreferredSize(new Dimension(300, 100));
    Right21.setOpaque(false);
    Right3 = new JPanel(new GridLayout(1, 2));
    Right3.setPreferredSize(new Dimension(250, 133));
    Right3.setOpaque(false);
    Right31 = new JPanel(new FlowLayout());
    Right31.setPreferredSize(new Dimension(160, 120));
    Right31.setOpaque(false);
    Right32 = new JPanel(new FlowLayout());
    Right32.setPreferredSize(new Dimension(160, 120));
    Right32.setOpaque(false);
    Side1 = new JPanel(new FlowLayout());
    Side1.setPreferredSize(new Dimension(30, 450));
    Side1.setOpaque(false);
    Side2 = new JPanel(new FlowLayout());
    Side2.setPreferredSize(new Dimension(30, 450));
    Side2.setOpaque(false);

    Left1.add(ServerSelect);
    Left2.add(ClientSelect);
    Left.add(LeftEmpty1);
    Left.add(Left1);
    Left.add(LeftEmpty2);
    Left.add(Left2);
    Left.add(LeftEmpty3);
    Right21.add(UserFriendID, BorderLayout.SOUTH);
    Right2.add(Right21);
    Right31.add(Confirm, SwingConstants.CENTER);
    Right32.add(Cancel, SwingConstants.CENTER);
    Right3.add(Right31);
    Right3.add(Right32);
    Right.add(Right1);
    Right.add(Right2);
    Right.add(Right3);

    Page = new JPanel(new BorderLayout());
    Page.setPreferredSize(new Dimension(540, 450));
    Page.add(Left, BorderLayout.CENTER);
    Page.add(Right, BorderLayout.EAST);
    Page.setOpaque(false);

    Background = new BackPanel("/Background3.png");
    Background.setPreferredSize(new Dimension(600, 450));
    Background.setLayout(new BorderLayout());
    Background.add(Page, BorderLayout.CENTER);
    Background.add(Side1, BorderLayout.EAST);
    Background.add(Side2, BorderLayout.WEST);

    setUndecorated(true);
    setLocationRelativeTo(null);
    add(Background);
    setVisible(true);
  }

  public class MyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(ServerSelect)) {
        server = 1;
        Background.SetImage("/selectServer.png");        
        Background.repaint();
        Background.revalidate();
      }

      else if (e.getSource().equals(ClientSelect)) {
        server = 2;
        Background.SetImage("/selectClient.png");
        Background.repaint();
        Background.revalidate();
      }

      else if (e.getSource().equals(Confirm)) {
        // Establish connection
        superFrame.serverIP = UserFriendID.getText();
        //System.out.println(superFrame.serverIP);
        if (server == 1)
          superFrame.server = true;
        
        MultiDialog.this.dispose();
      }

      else if (e.getSource().equals(Cancel)) {
        superFrame.server = false;
        MultiDialog.this.dispose();
      }
    }
  }
/*
  boolean CheckConnection() {
    return ConnectionStatus;
  }
*/
  /*
   * String getUserFriendID(){ return UserFriendID_str; }
   */
}