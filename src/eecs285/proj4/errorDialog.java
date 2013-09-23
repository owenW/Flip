package eecs285.proj4;

import javax.swing.JDialog;
import com.sun.awt.AWTUtilities;

public class errorDialog extends JDialog {
  BackPanel e;

  public errorDialog(MatchGameFrame mainframe) {
    super(mainframe, null, true);
    setSize(400, 300);
    e = new BackPanel("/error.png");
    e.setOpaque(false);

    // com.sun.awt.AWTUtilities.setWindowOpacity(this,0.5f);
    add(e);
    setUndecorated(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }
}