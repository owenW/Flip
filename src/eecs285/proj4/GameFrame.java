/**
 * 
 */
/**
 * @author shilinfeng
 *
 */
package eecs285.proj4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Timer;

import static java.lang.System.out;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JPanel {

  String turnPic;//123  
  ClientServerSocket theServer = new ClientServerSocket("dfdf", 0);
  ClientServerSocket theClient = new ClientServerSocket("gdgaf", 1);
  String[] pictures1 = { "/10.jpg", "/11.jpg", "/12.jpg", "/13.jpg", "/14.jpg",
      "/15.jpg", "/16.jpg", "/17.jpg" };
  String[] pictures2 = { "/20.jpg", "/21.jpg", "/22.jpg", "/23.jpg", "/24.jpg",
      "/25.jpg", "/26.jpg", "/27.jpg", "/28.jpg", "/29.jpg" };
  String[] pictures3 = { "/30.jpg", "/31.jpg", "/32.jpg", "/33.jpg", "/34.jpg",
      "/35.jpg", "/36.jpg", "/37.jpg", "/38.jpg", "/39.jpg", "/40.jpg", "/41.jpg",
      "/42.jpg", "/43.jpg" };
  String[] allPictures;//123
  
  LinkedList<String> pictureNames = new LinkedList<String>();
//  int level = 1;
  boolean ifDisapper[];//123
  int myLast[];
  int heLast[];
  Timer myTimer[];
  boolean ifBeToldStop=false;//
  int myMode;//
  boolean Copen=false;///
  boolean Sopen=false;//
  Timer myTurnTimer[];//123
  Timer myTurnOffTimer[];//123
  
  Timer myClock;
  
  int timeShortShow=100;//123
  int timeNotSee=200;//123
  
  int forever = 600 * 1000;// whl
  int timeShow = 500;
  int totalScore = 0;
  int oneScore = 100;
  boolean continueHit = false;
  boolean notHit = false;
  boolean ifGameOver=false;
  int size;
  int left;
  int click1 = 0;
  JButton prev2 = new JButton();
  JButton prevOne = new JButton();
  JButton currOne = new JButton();
  boolean ifMulti;

  int prevIndex, currIndex;
  int numberOfButton;
  JButton[] grid;
  JLabel timeOut, score, levelPart;
  JButton playingExit;

  JPanel playField, leftPart;
  JPanel whole1;  
  int totalTime = 60;
  int[] pictureNumber;
  MatchGameFrame mainFrame;

  public GameFrame(MatchGameFrame MainFrame, ClientServerSocket Server,
      ClientServerSocket Client, int inSize, int mode, int timeInput) {
    super();

    // whl
    theServer = Server;
    theClient = Client;
    
    myMode=mode;
    Copen=true;
    Sopen=true;
    //123
    if (inSize == 5){
        allPictures=new String[8];
        turnPic="/turn1.jpg";
        allPictures = pictures1; 
    } 
    else if (inSize == 6) {
        allPictures=new String[10];
        turnPic="/turn2.jpg";
        allPictures = pictures2;
    }
    else if (inSize == 7) {
        allPictures=new String[14];
        turnPic="/turn3.jpg";
        allPictures = pictures3;
    }
    

    setSize(805, 625);
    whole1 = new JPanel(new BorderLayout());
    whole1.setPreferredSize(new Dimension(800,600));
    size = inSize;
    numberOfButton = size * size;
    pictureNumber = new int[numberOfButton];
    myLast = new int[numberOfButton];
    heLast = new int[numberOfButton];
    myTimer = new Timer[numberOfButton];
    ifDisapper=new boolean[numberOfButton];//123
    left = size * size;
    if (size % 2 == 1) {
      left = left - 1;
    }
    
    myTurnTimer=new Timer[numberOfButton];  //123   
    myTurnOffTimer=new Timer[numberOfButton];//123
    
    myClock=new Timer();//123
    playField = new JPanel(new GridLayout(size, size));
    playField.setPreferredSize(new Dimension(600, 600));
    grid = new JButton[numberOfButton];
    left = numberOfButton;
    for (int i = 0; i < numberOfButton; i++) {
      grid[i] = new JButton();
      grid[i].setName(Integer.toString(i));
      grid[i].addActionListener(new PictureActionListener());
      URL imageURL = getClass().getResource(allPictures[0]);    
      grid[i].setIcon(new ImageIcon(imageURL));
      
      grid[i].setEnabled(true);
      grid[i].setOpaque(false);
      grid[i].setContentAreaFilled(false);
      grid[i].setBorderPainted(false);
      playField.add(grid[i]);
    }

    for (int i = 0; i < numberOfButton; i++) {
      myLast[i] = 0;
      heLast[i] = 0;
      ifDisapper[i]=false;//whl
      myTimer[i] = new Timer();
      myTimer[i].schedule(new TaskForever(grid[i]), 0);
      myTurnTimer[i]=new Timer();//whl
      myTurnOffTimer[i]=new Timer();//whl
      
    }

    totalTime = timeInput;
    timeOut = new JLabel(Integer.toString(totalTime));
    score = new JLabel("0");
    levelPart = new JLabel("1");

    Random rand = new Random(System.currentTimeMillis());
    LinkedList<String> values = new LinkedList<String>();

    for (int i = 0; i < numberOfButton; i++) {
      int j = 1 + i / 4;
      values.push(Integer.toString(j));
    }

    if (mode != 2) {
      for (int i = 0; i < numberOfButton; i++) {
        int j = Math.abs(rand.nextInt() % (numberOfButton - i));
        pictureNumber[i] = Integer.parseInt(values.get(j));
        values.remove(j);
      }
      if (mode == 1) {
        for (int a : pictureNumber) {
          theServer.sendString(Integer.toString(a));
          
        }
      }
    } else {
      for (int i = 0; i < size * size; i++) {
        pictureNumber[i] = Integer.parseInt(theClient.recvString());
      }
    }
    for (int i = 0; i < numberOfButton; i++) {
      pictureNames.push(allPictures[pictureNumber[i]]);
      
    }
        
    
    
    if (mode == 0) {
      ifMulti = false;
    } else {
      ifMulti = true;
    }

    leftPart = new JPanel(new GridLayout(10, 1));
    leftPart.setPreferredSize(new Dimension(200,600));
    //leftPart.add(new JLabel("Level"));
    //leftPart.add(levelPart);    
    leftPart.add(new JLabel());
    leftPart.add(new JLabel());
    timeOut.setFont(new Font("BroadWay",Font.BOLD,18));
    leftPart.add(timeOut);    
    leftPart.add(new JLabel());
    leftPart.add(new JLabel());
    score.setFont(new Font("BroadWay",Font.BOLD,18));
    leftPart.add(score);
    leftPart.add(new JLabel());
    leftPart.add(new JLabel());
    leftPart.add(new JLabel());
    playingExit = new JButton();
    playingExit.setOpaque(false);
    playingExit.setContentAreaFilled(false);
    playingExit.setBorderPainted(false);
    playingExit.addActionListener(new PlayingExitListener());
    leftPart.add(playingExit);
    playField.setOpaque(false);
    leftPart.setOpaque(false);
    whole1.add(playField, BorderLayout.EAST);
    whole1.add(leftPart, BorderLayout.WEST);
    whole1.setOpaque(false);
    
    BackPanel Background = new BackPanel("/bg4_final.png");
    Background.setLayout(new BorderLayout());
    Background.setPreferredSize(new Dimension(800,600));
    Background.add(whole1,BorderLayout.CENTER);
    
    add(Background);
    out.println("a");
    myClock.schedule(new TaskClock(totalTime), 0, 1000);
    out.println("b");
    mainFrame = MainFrame;
    mainFrame.getContentPane().removeAll();
    mainFrame.setLayout(new BorderLayout());
    mainFrame.add(GameFrame.this, BorderLayout.CENTER);
    mainFrame.repaint();
    mainFrame.revalidate();
    out.println("c");

  }

  public class PlayingExitListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      mainFrame.Difficulty = null;
      mainFrame.Mode = null;
      mainFrame.getContentPane().removeAll();
      mainFrame.setLayout(new BorderLayout());
      mainFrame.add(mainFrame.Page1, BorderLayout.CENTER);
      mainFrame.revalidate();
      mainFrame.repaint();
    }
  }

  public class PictureActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      currOne = (JButton) e.getSource();
      currIndex = Integer.parseInt(currOne.getName());

      // whl
      String sToSend;

      if (click1 == 0) {
        if (notHit) {
          int firstTell = Integer.parseInt(prevOne.getName());
          int secondTell = Integer.parseInt(prev2.getName());
          if (heLast[firstTell] != forever) {
            myTimer[firstTell].cancel();
            myTimer[firstTell] = new Timer();
            myTimer[firstTell].schedule(new TaskForever(grid[firstTell]), 0);
          }
          myLast[firstTell] = 0;
          sToSend = Integer.toString(firstTell + 1);// avoid zero
          if (ifMulti) {
            theServer.sendString(sToSend);
          }

          if (heLast[secondTell] != forever) {
            myTimer[secondTell].cancel();
            myTimer[secondTell] = new Timer();
            myTimer[secondTell].schedule(new TaskForever(grid[secondTell]), 0);
          }
          myLast[secondTell] = 0;
          sToSend = Integer.toString(secondTell + 1);// avoid zero
          if (ifMulti) {
            theServer.sendString(sToSend);
          }
          notHit = false;
        }

        myTimer[currIndex].cancel();
        myTimer[currIndex] = new Timer();
        URL imageURLC0 = getClass().getResource(pictureNames.get(currIndex));  
        currOne.setIcon(new ImageIcon(imageURLC0));
        myTimer[currIndex].schedule(new TaskForever(grid[currIndex]), forever);
        myLast[currIndex] = forever;
        sToSend = Integer.toString(currIndex + 101);// avoid zero
        if (ifMulti) {
          theServer.sendString(sToSend);
        }

        ++click1;
      }
      else if (click1 == 1) {
        currOne.removeAll();

        myTimer[currIndex].cancel();
        myTimer[currIndex] = new Timer();
        URL imageURLC1 = getClass().getResource(pictureNames.get(currIndex));  
        currOne.setIcon(new ImageIcon(imageURLC1));
        myTimer[currIndex].schedule(new TaskForever(grid[currIndex]), forever);
        myLast[currIndex] = forever;
        sToSend = Integer.toString(currIndex + 101);// avoid zero
        if (ifMulti) {
          theServer.sendString(sToSend);
        }

        if (pictureNames.get(prevIndex).equals(pictureNames.get(currIndex))
            && prevIndex != currIndex) {
          if (myLast[prevIndex] != -1 && heLast[prevIndex] != -1
              && myLast[currIndex] != -1 && heLast[currIndex] != -1) {
            click1 = 0;
            continueHit = true;
            totalScore = totalScore + oneScore;
            oneScore = oneScore + 100;

            myTimer[currIndex].cancel();
            myTimer[currIndex] = new Timer();
            myLast[currIndex] = -1;
            heLast[currIndex] = -1;
            sToSend = Integer.toString(currIndex + 201);// avoid zero
            if (ifMulti) {
              theServer.sendString(sToSend);
            }

            myTimer[prevIndex].cancel();
            myTimer[prevIndex] = new Timer();
            myLast[prevIndex] = -1;
            heLast[prevIndex] = -1;
            sToSend = Integer.toString(prevIndex + 201);// avoid zero
            if (ifMulti) {
              theServer.sendString(sToSend);
            }

            prevOne.setEnabled(false);
            currOne.setEnabled(false);
               
            if(ifDisapper[currIndex]==false){
                   myTurnTimer[currIndex].schedule(new TaskTurn(grid[currIndex]),timeShortShow );
                   myTurnOffTimer[currIndex].schedule(new TaskTurnOff(grid[currIndex]),timeNotSee );
                   ifDisapper[currIndex]=true;
                   left = left - 1;
            }
            if(ifDisapper[prevIndex]==false){
                   myTurnTimer[prevIndex].schedule(new TaskTurn(grid[prevIndex]),timeShortShow );
                   myTurnOffTimer[prevIndex].schedule(new TaskTurnOff(grid[prevIndex]),timeNotSee );
                   ifDisapper[prevIndex]=true;
                   left = left - 1;
            }

            
            // slf
            if (left == 0  &&  ifGameOver==false) {
              ifGameOver=true;
              for(int ii=0;ii<size*size;ii++){
                grid[ii].setEnabled(false);
              }
              if (ifMulti){
                 theServer.sendString("0");
              }
              score.setText(Integer.toString(totalScore));
              Score end=new Score(mainFrame, totalScore, size-5);
            }
            
            score.setText(Integer.toString(totalScore));
            whole1.validate();
          }
        }

        else if (prevIndex == currIndex) {
          click1 = 1;
        }

        else {
          click1 = 0;
          if (continueHit) {
            continueHit = false;
            oneScore = 100;
          }
          notHit = true;

          myTimer[currIndex].cancel();
          myTimer[currIndex] = new Timer();
          
          URL imageURLC0 = getClass().getResource(pictureNames.get(currIndex));  
          currOne.setIcon(new ImageIcon(imageURLC0));
          
          if (heLast[currIndex] != forever) {
            myTimer[currIndex].schedule(new TaskForever(grid[currIndex]),
                timeShow);
          }
          myLast[currIndex] = timeShow;
          sToSend = Integer.toString(currIndex + 301);// avoid zero
          if (ifMulti) {
            theServer.sendString(sToSend);
          }

          myTimer[prevIndex].cancel();
          myTimer[prevIndex] = new Timer();
          
          
          URL imageURLPrev = getClass().getResource(pictureNames.get(prevIndex));  
          prevOne.setIcon(new ImageIcon(imageURLPrev));
          
          
          if (heLast[prevIndex] != forever) {
            myTimer[prevIndex].schedule(new TaskForever(grid[prevIndex]),
                timeShow);
          }
          myLast[prevIndex] = timeShow;
          sToSend = Integer.toString(prevIndex + 301);// avoid zero
          if (ifMulti) {
            theServer.sendString(sToSend);
          }
        }
      }
      prev2 = prevOne;
      prevIndex = currIndex;
      prevOne = currOne;
    }
  }

  // whl
  public boolean otherClick(int otherIndex) {
     if(otherIndex==0){
          if(!ifBeToldStop){
              ifBeToldStop=true;//so the sendString"0"can only be use once
              theServer.sendString("0");
              for(int i=0;i<size*size;i++){
                  grid[i].setEnabled(false);
              }
              Timer timeToCloseClient=new Timer();
              Timer timeToCloseServer=new Timer();
              if(myMode==1){//i am server
                  timeToCloseClient.schedule(new TaskCloseC(), 20);
                  timeToCloseServer.schedule(new TaskCloseS(), 40);
              }
              else{//i am client
                  timeToCloseClient.schedule(new TaskCloseC(), 10);
                  timeToCloseServer.schedule(new TaskCloseS(), 30);
              }
          }
          return false;
    }
    else if (otherIndex < 100) {
      otherIndex--;
      heLast[otherIndex] = 0;
    } 
    else if (otherIndex < 200) {
      otherIndex -= 101;
      heLast[otherIndex] = forever;
    } 
    else if (otherIndex < 300) {
      otherIndex -= 201;
      heLast[otherIndex] = -1;
      myLast[otherIndex] = -1;

    } 
    else if (otherIndex < 400) {
      otherIndex -= 301;
      heLast[otherIndex] = timeShow;
    }

    JButton currOtherOne = grid[otherIndex];
    if (heLast[otherIndex] == forever) {
      myTimer[otherIndex].cancel();
      myTimer[otherIndex] = new Timer();
      
      URL imageURLOther1 = getClass().getResource(pictureNames.get(otherIndex));  
      currOtherOne.setIcon(new ImageIcon(imageURLOther1));
      
      myTimer[otherIndex].schedule(new TaskForever(grid[otherIndex]), forever);
    } 
    else if (heLast[otherIndex] == timeShow) {
      if (myLast[otherIndex] != forever) {
        myTimer[otherIndex].cancel();
        myTimer[otherIndex] = new Timer();
        
        URL imageURLOther2 = getClass().getResource(pictureNames.get(otherIndex));  
        currOtherOne.setIcon(new ImageIcon(imageURLOther2));
        
        myTimer[otherIndex].schedule(new TaskForever(grid[otherIndex]),
            timeShow);
      }
    } 
    else if (heLast[otherIndex] == 0) {
      if (myLast[otherIndex] != forever) {
        myTimer[otherIndex].cancel();
        myTimer[otherIndex] = new Timer();
        
        URL imageURLOther3 = getClass().getResource(pictureNames.get(otherIndex));  
        currOtherOne.setIcon(new ImageIcon(imageURLOther3));
        
        myTimer[otherIndex].schedule(new TaskForever(grid[otherIndex]), 0);
      }
    } 
    else if (heLast[otherIndex] == -1) {    
         currOtherOne.setEnabled(false);  
         if(ifDisapper[otherIndex]==false){
               myTurnTimer[otherIndex].schedule(new TaskTurn(grid[currIndex]),timeShortShow );
               myTurnOffTimer[otherIndex].schedule(new TaskTurnOff(grid[currIndex]),timeShortShow );
               ifDisapper[otherIndex]=true;
         }  
         whole1.validate();

         // slf
         left = left - 1;
         if (left == 0&& ifGameOver==false) {
           ifGameOver=true;
           for(int ii=0;ii<size*size;ii++){
             grid[ii].setEnabled(false);
           }
           if (ifMulti){
               theServer.sendString("0");
           }
           score.setText(Integer.toString(totalScore));
           Score end=new Score(mainFrame, totalScore, size-5);
         }
    }
    return true;
}

    public class TaskForever extends TimerTask {
       private JButton button1 = new JButton();
       TaskForever(JButton inButton1) {
       button1 = inButton1;
       }

       public void run() {
         URL imageURLForever = getClass().getResource(allPictures[0]);  
         button1.setIcon(new ImageIcon(imageURLForever));
         whole1.validate();
       }
    }

    public class TaskTurn extends TimerTask {
        private JButton button1=new JButton();
        TaskTurn(JButton inButton1){
            button1=inButton1;
        }
        public void run(){
            URL imageURLTaskTurn = getClass().getResource(turnPic);  
            button1.setIcon(new ImageIcon(imageURLTaskTurn));       
            whole1.validate();
        }
    }
    
    public class TaskTurnOff extends TimerTask {
        private JButton button1=new JButton();
        TaskTurnOff(JButton inButton1) {
            button1=inButton1;
        }
        public void run(){
            button1.setVisible(false);  
            whole1.validate();
        }
    }
    
    public boolean sendCopen(){
        return Copen;
    }
    
    public boolean sendSopen(){
        return Sopen;
    }
    
    public class TaskCloseC extends TimerTask{
        TaskCloseC(){
        }
        public void run(){
            if(Copen){
                Copen=false;
                theClient.close();
            }
        }
    }
    
    public class TaskCloseS extends TimerTask{
        TaskCloseS(){
        }
        public void run(){
            if(Sopen){
                Sopen=false;
                theServer.close();
            }
        }
    }
    public class TaskClock extends TimerTask {
        private int totalLeftTime;
        TaskClock(int totalClock) {
            totalLeftTime=totalClock;//the total time for the level
        }
        public void run(){
          if(ifGameOver==false){
            if(totalLeftTime>0){
                totalLeftTime--;
                timeOut.setText(Integer.toString(totalLeftTime));
            }
            else if (totalLeftTime==0){
                timeOut.setText(Integer.toString(totalLeftTime));
                ifGameOver=true;
                for(int ii=0;ii<size*size;ii++){
                  grid[ii].setEnabled(false);
                }
                if (ifMulti){
                  theServer.sendString("0");
                }
                totalLeftTime=-1;
                score.setText(Integer.toString(totalScore));
                Score end=new Score(mainFrame, totalScore, size-5);
            }
            else if(totalLeftTime==-1){
                //nothing to do
            }
          }
        }
    }   
}