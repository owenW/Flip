package eecs285.proj4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import com.sun.awt.AWTUtilities;

import static java.lang.System.out;

public class MatchGameFrame extends JFrame {
  public static void main(String args[]) {
    MatchGameFrame MainFrame = new MatchGameFrame();
  }

  JButton ScoreBoard;
  JButton Start1;
  JButton Help;
  JButton Exit;
  JPanel Upper1;
  JPanel Bottom1;
  JPanel Bottom11;
  JPanel Bottom12;
  JPanel Bottom13;
  JPanel Bottom131;
  JPanel Bottom132;
  BackPanel Page1;

  JButton Single;
  JButton Multi;
  JButton Easy;
  JButton Normal;
  JButton Hard;
  JButton Start2;
  JButton GoBack;
  JLabel DifficultyPreview;
  JPanel Left2;
  JPanel Left2Empty1;
  JPanel Left2Empty2;
  JPanel Left21;
  JPanel Left22;
  JPanel Left23;
  JPanel Mid2;
  JPanel Mid21;
  JPanel Mid22;
  JPanel Mid221;
  JPanel Mid222;
  JPanel Mid223;
  JPanel Right2;
  JPanel Right21;
  JPanel Right22;
  JPanel Right221;
  JPanel Right222;
  JPanel Right2221;
  JPanel Right2222;
  BackPanel Page2;

  JLabel Name;
  JLabel Score;
  JButton GoBack2;  
  JPanel Grid;
  JPanel Mid3;
  JPanel Mid31;
  JPanel Mid311;
  JPanel Mid312;
  JPanel Mid313;
  JPanel Upper3;
  JPanel Bottom3;
  JPanel Side31;
  JPanel Side32;
  BackPanel Page3;

  String Difficulty = null;
  String Mode = null;
  String serverIP = null;
  boolean server = false;

  public MatchGameFrame() {
    super("Match Game");
    setSize(805, 625);

    ScoreBoard = new JButton();
    ScoreBoard.setPreferredSize(new Dimension(230, 180));
    ScoreBoard.addActionListener(new PageOneListener());
    ScoreBoard.setOpaque(false);
    ScoreBoard.setContentAreaFilled(false);
    ScoreBoard.setBorderPainted(false);
    Start1 = new JButton();
    Start1.setPreferredSize(new Dimension(280, 160));
    Start1.addActionListener(new PageOneListener());
    Start1.setOpaque(false);
    Start1.setContentAreaFilled(false);
    Start1.setBorderPainted(false);
    Help = new JButton();
    Help.setPreferredSize(new Dimension(80, 75));
    Help.addActionListener(new PageOneListener());
    Help.setOpaque(false);
    Help.setContentAreaFilled(false);
    Help.setBorderPainted(false);
    Exit = new JButton();
    Exit.setPreferredSize(new Dimension(80, 75));
    Exit.addActionListener(new PageOneListener());
    Exit.setOpaque(false);
    Exit.setContentAreaFilled(false);
    Exit.setBorderPainted(false);

    Upper1 = new JPanel(new FlowLayout());
    Upper1.setPreferredSize(new Dimension(800, 370));
    Upper1.setOpaque(false);
    Bottom1 = new JPanel(new FlowLayout());
    Bottom1.setPreferredSize(new Dimension(800, 230));
    Bottom1.setOpaque(false);
    Bottom11 = new JPanel(new FlowLayout());
    Bottom11.setPreferredSize(new Dimension(300, 230));
    Bottom11.setOpaque(false);
    Bottom12 = new JPanel(new FlowLayout());
    Bottom12.setPreferredSize(new Dimension(340, 230));
    Bottom12.setOpaque(false);
    Bottom13 = new JPanel(new GridLayout(2, 1));
    Bottom13.setPreferredSize(new Dimension(80, 190));
    Bottom13.setOpaque(false);
    Bottom131 = new JPanel(new FlowLayout());
    Bottom131.setOpaque(false);
    Bottom132 = new JPanel(new FlowLayout());
    Bottom132.setOpaque(false);

    Bottom11.add(ScoreBoard);
    Bottom12.add(Start1);
    Bottom131.add(Help, SwingConstants.CENTER);
    Bottom132.add(Exit, SwingConstants.CENTER);
    Bottom13.add(Bottom131);
    Bottom13.add(Bottom132);
    Bottom1.add(Bottom11);
    Bottom1.add(Bottom12);
    Bottom1.add(Bottom13);

    Page1 = new BackPanel("/bg1_final.gif");
    Page1.setPreferredSize(new Dimension(800, 600));
    Page1.setLayout(new BorderLayout());
    Page1.add(Upper1, BorderLayout.CENTER);
    Page1.add(Bottom1, BorderLayout.SOUTH);
    Page1.setOpaque(false);

    setLayout(new BorderLayout());
    add(Page1, BorderLayout.CENTER);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public class PageOneListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(Start1)) {
        Single = new JButton();
        Single.setPreferredSize(new Dimension(205, 120));
        Single.addActionListener(new PageTwoListener());
        Single.setOpaque(false);
        Single.setContentAreaFilled(false);
        Single.setBorderPainted(false);
        Multi = new JButton();
        Multi.setPreferredSize(new Dimension(205, 120));
        Multi.addActionListener(new PageTwoListener());
        Multi.setOpaque(false);
        Multi.setContentAreaFilled(false);
        Multi.setBorderPainted(false);
        Easy = new JButton();
        Easy.setPreferredSize(new Dimension(160, 110));
        Easy.addActionListener(new PageTwoListener());
        Easy.setOpaque(false);
        Easy.setContentAreaFilled(false);
        Easy.setBorderPainted(false);
        Normal = new JButton();
        Normal.setPreferredSize(new Dimension(160, 110));
        Normal.addActionListener(new PageTwoListener());
        Normal.setOpaque(false);
        Normal.setContentAreaFilled(false);
        Normal.setBorderPainted(false);
        Hard = new JButton();
        Hard.setPreferredSize(new Dimension(155, 120));
        Hard.addActionListener(new PageTwoListener());
        Hard.setOpaque(false);
        Hard.setContentAreaFilled(false);
        Hard.setBorderPainted(false);
        Start2 = new JButton();
        Start2.setPreferredSize(new Dimension(200, 140));
        Start2.addActionListener(new PageTwoListener());
        Start2.setOpaque(false);
        Start2.setContentAreaFilled(false);
        Start2.setBorderPainted(false);
        GoBack = new JButton();
        GoBack.setPreferredSize(new Dimension(40, 40));
        GoBack.addActionListener(new PageTwoListener());
        GoBack.setOpaque(false);
        GoBack.setContentAreaFilled(false);
        GoBack.setBorderPainted(false);
        DifficultyPreview = new JLabel(new ImageIcon(getClass().getResource(
            "/preview.png")));
        DifficultyPreview.setPreferredSize(new Dimension(230, 240));
        Left2 = new JPanel(new GridLayout(5, 1));
        Left2.setPreferredSize(new Dimension(315, 600));
        Left2.setOpaque(false);
        Left2Empty1 = new JPanel(new FlowLayout());
        Left2Empty1.setOpaque(false);
        Left2Empty2 = new JPanel(new FlowLayout());
        Left2Empty2.setOpaque(false);
        Left21 = new JPanel(new FlowLayout());
        Left21.setPreferredSize(new Dimension(315, 120));
        Left21.setOpaque(false);
        Left22 = new JPanel(new FlowLayout());
        Left22.setOpaque(false);
        Left22.setPreferredSize(new Dimension(315, 120));
        Left23 = new JPanel(new GridLayout(2, 5));
        Left23.setPreferredSize(new Dimension(315, 120));
        Left23.setOpaque(false);
        Mid2 = new JPanel(new FlowLayout());
        Mid2.setPreferredSize(new Dimension(215, 600));
        Mid2.setOpaque(false);
        Mid21 = new JPanel(new FlowLayout());
        Mid21.setPreferredSize(new Dimension(215, 50));
        Mid21.setOpaque(false);
        Mid22 = new JPanel(new GridLayout(3, 1));
        Mid22.setPreferredSize(new Dimension(215, 550));
        Mid22.setOpaque(false);
        Mid221 = new JPanel(new FlowLayout());
        Mid221.setOpaque(false);
        Mid222 = new JPanel(new FlowLayout());
        Mid222.setOpaque(false);
        Mid223 = new JPanel(new FlowLayout());
        Mid223.setOpaque(false);
        Right2 = new JPanel(new FlowLayout());
        Right2.setPreferredSize(new Dimension(270, 600));
        Right2.setOpaque(false);
        Right21 = new JPanel(new FlowLayout());
        Right21.setPreferredSize(new Dimension(270, 100));
        Right21.setOpaque(false);
        Right22 = new JPanel(new GridLayout(2, 1));
        Right22.setPreferredSize(new Dimension(270, 500));
        Right22.setOpaque(false);
        Right221 = new JPanel(new FlowLayout());
        Right221.setOpaque(false);
        Right222 = new JPanel(new BorderLayout());
        Right222.setOpaque(false);
        Right2221 = new JPanel(new FlowLayout());
        Right2221.setPreferredSize(new Dimension(270, 40));
        Right2221.setOpaque(false);
        Right2222 = new JPanel(new FlowLayout());
        Right2222.setOpaque(false);

        Left21.add(Single, SwingConstants.CENTER);
        Left22.add(Multi, SwingConstants.CENTER);
        for (int i = 0; i < 5; i++) {
          Left23.add(new JLabel());
        }
        Left23.add(GoBack);
        for (int i = 0; i < 4; i++) {
          Left23.add(new JLabel());
        }
        Left2.add(Left2Empty1);
        Left2.add(Left21);
        Left2.add(Left2Empty2);
        Left2.add(Left22);
        Left2.add(Left23);
        Mid221.add(Easy, SwingConstants.CENTER);
        Mid222.add(Normal, SwingConstants.CENTER);
        Mid223.add(Hard, SwingConstants.CENTER);
        Mid22.add(Mid221);
        Mid22.add(Mid222);
        Mid22.add(Mid223);
        Mid2.add(Mid21);
        Mid2.add(Mid22);
        Right221.add(DifficultyPreview, SwingConstants.CENTER);
        Right2222.add(Start2, SwingConstants.CENTER);
        Right222.add(Right2221, BorderLayout.NORTH);
        Right222.add(Right2222, BorderLayout.CENTER);
        Right22.add(Right221);
        Right22.add(Right222);
        Right2.add(Right21);
        Right2.add(Right22);

        Page2 = new BackPanel("/Background2.png");
        Page2.setLayout(new BorderLayout());
        Page2.setPreferredSize(new Dimension(800, 600));
        Page2.add(Left2, BorderLayout.WEST);
        Page2.add(Mid2, BorderLayout.CENTER);
        Page2.add(Right2, BorderLayout.EAST);
        Page2.SetImage("/selectSingle.png");
        Page2.SetImage("/selectSingleEasy.png");
        Page2.SetImage("/selectSingleNormal.png");
        Page2.SetImage("/selectSingleHard.png");
        Page2.SetImage("/selectMulti.png");
        Page2.SetImage("/selectMultiEasy.png");
        Page2.SetImage("/selectMultiNormal.png");
        Page2.SetImage("/selectMultiHard.png");
        Page2.SetImage("/Background2.png");

        MatchGameFrame.this.getContentPane().removeAll();
        MatchGameFrame.this.setLayout(new BorderLayout());
        MatchGameFrame.this.add(Page2, BorderLayout.CENTER);
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(ScoreBoard)) {
        LinkedList<JLabel> EasyNameList = new LinkedList();
        LinkedList<JLabel> NormalNameList = new LinkedList();
        LinkedList<JLabel> HardNameList = new LinkedList();
        LinkedList<JLabel> EasyScoreList = new LinkedList();
        LinkedList<JLabel> NormalScoreList = new LinkedList();
        LinkedList<JLabel> HardScoreList = new LinkedList();
        LinkedList<JPanel> GridList = new LinkedList();
        
        GoBack2 = new JButton();
        GoBack2.addActionListener(new PageThreeListener());
        GoBack2.setPreferredSize(new Dimension(50, 50));
        GoBack2.setOpaque(false);
        GoBack2.setContentAreaFilled(false);
        Mid3 = new JPanel(new BorderLayout());
        Mid3.setPreferredSize(new Dimension(660, 600));
        Mid3.setOpaque(false);
        Mid31 = new JPanel(new BorderLayout());
        Mid31.setPreferredSize(new Dimension(660, 470));
        Mid31.setOpaque(false);
        Mid311 = new JPanel(new GridLayout(2, 11));
        Mid311.setPreferredSize(new Dimension(660, 120));
        Mid311.setOpaque(false);
        Mid312 = new JPanel(new FlowLayout());
        Mid312.setPreferredSize(new Dimension(660, 50));
        Mid312.setOpaque(false);
        Mid313 = new JPanel(new GridLayout(10, 3));
        Mid313.setPreferredSize(new Dimension(660, 300));
        Mid313.setOpaque(false);
        Upper3 = new JPanel(new FlowLayout());
        Upper3.setPreferredSize(new Dimension(660, 70));
        Upper3.setOpaque(false);
        Bottom3 = new JPanel(new FlowLayout());
        Bottom3.setPreferredSize(new Dimension(660, 60));
        Bottom3.setOpaque(false);
        Side31 = new JPanel(new FlowLayout());
        Side31.setPreferredSize(new Dimension(70, 600));
        Side31.setOpaque(false);
        Side32 = new JPanel(new FlowLayout());
        Side32.setPreferredSize(new Dimension(70, 600));
        Side32.setOpaque(false);
        Page3 = new BackPanel("/hallOfFame.png");
        Page3.setLayout(new BorderLayout());
        Page3.setPreferredSize(new Dimension(800, 600));

        try {
          Connection conn = getConnection();
          matchingData matchData = new matchingData(conn);
          matchData.getRecords("easy_records");
          TreeSet<Record> easy_records = matchData.getData();
          
          Iterator<Record> it = easy_records.descendingIterator();
          while (it.hasNext()) {
              Record aRecord = it.next();
              Name = new JLabel(aRecord.user_id);
              Name.setPreferredSize(new Dimension(200, 30));
              Name.setFont(new Font("Broadway", Font.BOLD, 24));
              // Name.setOpaque(false);
              Score = new JLabel(aRecord.user_score.toString());
              Score.setPreferredSize(new Dimension(100, 30));
              Score.setFont(new Font("Broadway", Font.BOLD, 24));
              // Score.setOpaque(false);
              EasyNameList.add(Name);
              EasyScoreList.add(Score);
          }
          conn.close();
      } catch (SQLIntegrityConstraintViolationException a) {
          // Do something here. user entered a user_name that already exists
      } catch (SQLException e1) {
          System.out.println("??");
          e1.printStackTrace();
      }
      
      try {
          Connection conn = getConnection();
          matchingData matchData = new matchingData(conn);
          matchData.getRecords("medium_records");
          TreeSet<Record> medium_records = matchData.getData();
          
          Iterator<Record> it = medium_records.descendingIterator();
          while (it.hasNext()) {
              Record aRecord = it.next();
              Name = new JLabel(aRecord.user_id);
              Name.setPreferredSize(new Dimension(200, 30));
              Name.setFont(new Font("Broadway", Font.BOLD, 24));
              // Name.setOpaque(false);
              Score = new JLabel(aRecord.user_score.toString());
              Score.setPreferredSize(new Dimension(100, 30));
              Score.setFont(new Font("Broadway", Font.BOLD, 24));
              // Score.setOpaque(false);
              NormalNameList.add(Name);
              NormalScoreList.add(Score);
          }
          conn.close();
      } catch (SQLIntegrityConstraintViolationException a) {
          // Do something here. user entered a user_name that already exists
      } catch (SQLException e2) {
          System.out.println("??");
          e2.printStackTrace();
      }
      
      try {
          Connection conn = getConnection();
          matchingData matchData = new matchingData(conn);
          matchData.getRecords("hard_records");
          TreeSet<Record> hard_records = matchData.getData();
          
          Iterator<Record> it = hard_records.descendingIterator();
          while (it.hasNext()) {
              Record aRecord = it.next();
              Name = new JLabel(aRecord.user_id);
              Name.setPreferredSize(new Dimension(200, 30));
              Name.setFont(new Font("Broadway", Font.BOLD, 24));
              // Name.setOpaque(false);
              Score = new JLabel(aRecord.user_score.toString());
              Score.setPreferredSize(new Dimension(100, 30));
              Score.setFont(new Font("Broadway", Font.BOLD, 24));
              // Score.setOpaque(false);
              HardNameList.add(Name);
              HardScoreList.add(Score);
          }
          conn.close();
      } catch (SQLIntegrityConstraintViolationException a) {
          // Do something here. user entered a user_name that already exists
      } catch (SQLException e3) {
          System.out.println("??");
          e3.printStackTrace();
      }
      while(EasyNameList.size()!=5){
          Name = new JLabel(" ");
          Name.setPreferredSize(new Dimension(200, 30));
          Name.setFont(new Font("Broadway", Font.BOLD, 24));
          // Name.setOpaque(false);
          Score = new JLabel(" ");
          Score.setPreferredSize(new Dimension(100, 30));
          Score.setFont(new Font("Broadway", Font.BOLD, 24));
          // Score.setOpaque(false);
          EasyNameList.add(Name);
          EasyScoreList.add(Score);
      }
      
      while(NormalNameList.size()!=5){
          Name = new JLabel(" ");
          Name.setPreferredSize(new Dimension(200, 30));
          Name.setFont(new Font("Broadway", Font.BOLD, 24));
          // Name.setOpaque(false);
          Score = new JLabel(" ");
          Score.setPreferredSize(new Dimension(100, 30));
          Score.setFont(new Font("Broadway", Font.BOLD, 24));
          // Score.setOpaque(false);
          NormalNameList.add(Name);
          NormalScoreList.add(Score);
      }
      
      
      while(HardNameList.size()!=5){
          Name = new JLabel(" ");
          Name.setPreferredSize(new Dimension(200, 30));
          Name.setFont(new Font("Broadway", Font.BOLD, 24));
          // Name.setOpaque(false);
          Score = new JLabel(" ");
          Score.setPreferredSize(new Dimension(100, 30));
          Score.setFont(new Font("Broadway", Font.BOLD, 24));
          // Score.setOpaque(false);
          HardNameList.add(Name);
          HardScoreList.add(Score);
      }
      Mid311.add(GoBack2);
      for (int i = 0; i < 21; i++) {
          Mid311.add(new JLabel());
          
      }
      int j = 0;
      for (int i = 0; i < 30; i++) {
          Grid = new JPanel(new FlowLayout());
          Grid.setPreferredSize(new Dimension(200, 30));
          Grid.setOpaque(false);
          GridList.add(Grid);
      }
        for (int i = 0; i < 30; i = i + 6) {
          GridList.get(i).add(EasyNameList.get(j));
          GridList.get(i + 1).add(NormalNameList.get(j));
          GridList.get(i + 2).add(HardNameList.get(j));
          GridList.get(i + 3).add(EasyScoreList.get(j));
          GridList.get(i + 4).add(NormalScoreList.get(j));
          GridList.get(i + 5).add(HardScoreList.get(j));          
          j++;
          /*
           * GridList.get(i).add(new JButton("aa")); GridList.get(i+1).add(new
           * JButton("bb")); GridList.get(i+2).add(new JButton("cc"));
           * GridList.get(i+3).add(new JButton("dd")); GridList.get(i+4).add(new
           * JButton("ee")); GridList.get(i+5).add(new JButton("ff"));
           */
        }
        for (int i = 0; i < 30; i = i + 6) {
          Mid313.add(GridList.get(i));
          Mid313.add(GridList.get(i + 1));
          Mid313.add(GridList.get(i + 2));
          Mid313.add(GridList.get(i + 3));
          Mid313.add(GridList.get(i + 4));
          Mid313.add(GridList.get(i + 5));
        }

        Mid31.add(Mid311, BorderLayout.NORTH);
        Mid31.add(Mid312, BorderLayout.CENTER);
        Mid31.add(Mid313, BorderLayout.SOUTH);
        Mid3.add(Upper3, BorderLayout.NORTH);
        Mid3.add(Mid31, BorderLayout.CENTER);
        Mid3.add(Bottom3, BorderLayout.SOUTH);
        Page3.add(Side31, BorderLayout.WEST);
        Page3.add(Mid3, BorderLayout.CENTER);
        Page3.add(Side32, BorderLayout.EAST);

        MatchGameFrame.this.getContentPane().removeAll();
        MatchGameFrame.this.add(Page3);
        MatchGameFrame.this.revalidate();
        MatchGameFrame.this.repaint();
      }

      else if (e.getSource().equals(Help)) {
        HelpDialog MyHelpDialog = new HelpDialog(MatchGameFrame.this);
      }

      else if (e.getSource().equals(Exit)) {
        MatchGameFrame.this.dispose();
      }
    }
  }

  public class PageTwoListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(Single)) {
        Mode = "Single";
        Page2.SetImage("/selectSingle.png");
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(Multi)) {
        Mode = "Multi";
        Page2.SetImage("/selectMulti.png");
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
        MultiDialog MyMultiDialog = new MultiDialog(MatchGameFrame.this);
        /*
        if (!server) {
          Mode = null;
          Difficulty = null;
          Page2.SetImage("/Background2.png");
          MatchGameFrame.this.repaint();
          MatchGameFrame.this.revalidate();
        }*/
        
      }

      else if (e.getSource().equals(Easy)) {
        if (Mode == null) {
          // Provide a warning
        } else if (Mode.equals("Single")) {
          Difficulty = "Easy";
          Page2.SetImage("/selectSingleEasy.png");
        } else if (Mode.equals("Multi")) {
          Difficulty = "Easy";
          Page2.SetImage("/selectMultiEasy.png");
        }
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(Normal)) {
        if (Mode == null) {
          // Provide a warning
        } else if (Mode.equals("Single")) {
          Difficulty = "Normal";
          Page2.SetImage("/selectSingleNormal.png");
        } else if (Mode.equals("Multi")) {
          Difficulty = "Normal";
          Page2.SetImage("/selectMultiNormal.png");
        }
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(Hard)) {
        if (Mode == null) {
          // Provide a warning
        } else if (Mode.equals("Single")) {
          Difficulty = "Hard";
          Page2.SetImage("/selectSingleHard.png");
        } else if (Mode.equals("Multi")) {
          Difficulty = "Hard";
          Page2.SetImage("/selectMultiHard.png");
        }
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(GoBack)) {
        Mode = null;
        Difficulty = null;
        MatchGameFrame.this.getContentPane().removeAll();
        MatchGameFrame.this.setLayout(new BorderLayout());
        MatchGameFrame.this.add(Page1, BorderLayout.CENTER);
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }

      else if (e.getSource().equals(Start2)) {
        if (Mode.equals("Single")) {
          if (Difficulty.equals("Easy")) {
            GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, null,
                null, 5, 0, 3);
            
          } else if (Difficulty.equals("Normal")) {
            GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, null,
                null, 6, 0, 120);
          } else if (Difficulty.equals("Hard")) {
            GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, null,
                null, 7, 0, 200);
          }
        } else if (Mode.equals("Multi")) {
            //MutiplayerMode
          out.println("enter!");
             ClientServerSocket theServer;
             ClientServerSocket theClient;
         //    GameFrame game = null;
             String recvdStr;
             InetAddress addr = null;
             int cInt;
             try {
               addr = InetAddress.getLocalHost();
             } catch (UnknownHostException e3) {
                // TODO Auto-generated catch block
                 out.println("error!");
                  e3.printStackTrace();
              }
             
             out.println(serverIP);
              if (server) {
              out.println(serverIP);
              out.println("dfsdf");
                theServer = new ClientServerSocket(addr.getHostAddress(),17664);
                theServer.startServer();
                recvdStr = theServer.recvString();
                out.println("Recevied message from client: " + recvdStr);
                out.println("difficutl"+Difficulty);
                theClient=new ClientServerSocket(serverIP,17684);
                theClient.startClient();
                theClient.sendString("the Client coming"); 
               
                
                
                if (Difficulty.equals("Easy")) {
                    theServer.sendString("5");
                    cInt = 5;
                    GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                       cInt, 1, 80);
                    String recieveMyStr;
                    int recieveMyInt;
                    boolean ifStop;
                    ifStop = true;// if ifStop ==1 Stop!
                    while (ifStop) {
                        recieveMyStr = theClient.recvString();
                        recieveMyInt = Integer.parseInt(recieveMyStr);
                        ifStop = gamePlaying.otherClick(recieveMyInt);
                   }
                 }
                else if (Difficulty.equals("Normal")) {

                  theServer.sendString("6");
                  cInt = 6;
                  GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                       cInt, 1, 120);
                  String recieveMyStr;
                  int recieveMyInt;
                  boolean ifStop;
                  ifStop = true;// if ifStop ==1 Stop!
                  while (ifStop) {
                      recieveMyStr = theClient.recvString();
                      recieveMyInt = Integer.parseInt(recieveMyStr);
                      ifStop = gamePlaying.otherClick(recieveMyInt);
                 }
                }
               else if (Difficulty.equals("Hard")) {
                  theServer.sendString("7");
                   cInt = 7;
                   GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                       cInt, 1, 200);
                   String recieveMyStr;
                   int recieveMyInt;
                   boolean ifStop;
                   ifStop = true;// if ifStop ==1 Stop!
                   while (ifStop) {
                       recieveMyStr = theClient.recvString();
                       recieveMyInt = Integer.parseInt(recieveMyStr);
                       ifStop = gamePlaying.otherClick(recieveMyInt);
                  }
               }
            } 
           else {
          

              String sStr = null;
              int sInt;
              
              out.println(serverIP);
              
              theClient = new ClientServerSocket(serverIP, 17664);
              theClient.startClient();
              theClient.sendString("Hello to the server!");
              theServer = new ClientServerSocket(addr.getHostAddress(), 17684);
              theServer.startServer();
              sStr=theServer.recvString();
              out.println(sStr);
              
              /*
              GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                  6, 2, 120);
              String recieveMyStr;
              int recieveMyInt;
              boolean ifStop;
              ifStop = true;// if ifStop ==1 Stop!
              while (ifStop) {
                  recieveMyStr = theClient.recvString();
                  recieveMyInt = Integer.parseInt(recieveMyStr);
                  ifStop = gamePlaying.otherClick(recieveMyInt);
             }
              */
              
              sStr=theClient.recvString();
              sInt=Integer.parseInt(sStr);
              //out.println(sInt);
              
              if(sInt==5){
                GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                          5, 2, 80);
                String recieveMyStr;
                int recieveMyInt;
                boolean ifStop;
                ifStop = true;// if ifStop ==1 Stop!
                while (ifStop) {
                    recieveMyStr = theClient.recvString();
                    recieveMyInt = Integer.parseInt(recieveMyStr);
                    ifStop = gamePlaying.otherClick(recieveMyInt);
               }
              }
              else if(sInt==6){
                GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                          6, 2, 120);
                String recieveMyStr;
                int recieveMyInt;
                boolean ifStop;
                ifStop = true;// if ifStop ==1 Stop!
                while (ifStop) {
                    recieveMyStr = theClient.recvString();
                    recieveMyInt = Integer.parseInt(recieveMyStr);
                    ifStop = gamePlaying.otherClick(recieveMyInt);
               }
              }
              else{
                GameFrame gamePlaying = new GameFrame(MatchGameFrame.this, theServer, theClient,
                          7, 2, 200);
                String recieveMyStr;
                int recieveMyInt;
                boolean ifStop;
                ifStop = true;// if ifStop ==1 Stop!
                while (ifStop) {
                    recieveMyStr = theClient.recvString();
                    recieveMyInt = Integer.parseInt(recieveMyStr);
                    ifStop = gamePlaying.otherClick(recieveMyInt);
               }
              }
              
          }

          
        }
      }
    }
  }

  public class PageThreeListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(e.getSource().equals(GoBack2)){        
        MatchGameFrame.this.getContentPane().removeAll();
        MatchGameFrame.this.setLayout(new BorderLayout());
        MatchGameFrame.this.add(Page1,BorderLayout.CENTER);
        MatchGameFrame.this.repaint();
        MatchGameFrame.this.revalidate();
      }
    }
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