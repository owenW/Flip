package eecs285.proj4;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

class BackPanel extends JPanel {
  private String Image;

  public BackPanel(String image) {
    Image = image;
  }

  public void SetImage(String image) {
    Image = image;
  }

  public String GetImage() {
    return Image;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    URL ImageURL = getClass().getResource(Image);
    // System.out.println(ImageURL.getPath());
    Image img = Toolkit.getDefaultToolkit().getImage(ImageURL);
    g.drawImage(img, 0, 0, null, this);
  }
}