package crystal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.border.EtchedBorder;

public class Logo
    extends JWindow implements Runnable {
  String filename; //Logo图像文件的文件名

  public static JProgressBar progress = new JProgressBar(0, 100);

  public Logo(String name) {
    filename = name;
  }

  public void run() {
    JPanel jPanelRoot = new JPanel();
    ImageIcon image = new ImageIcon(filename);
    JLabel label = new JLabel(image);

    progress.setStringPainted(true); // 描绘文字
    progress.setBackground(Color.WHITE);
    progress.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.YELLOW));
    //progress.setPreferredSize(new Dimension(image.getIconWidth(), 25));
    //progress.setForeground(Color.BLUE);

    this.getContentPane().add(jPanelRoot); //将显示图片的label加到JPanel里
    jPanelRoot.setLayout(new BorderLayout());
    jPanelRoot.add(label, BorderLayout.CENTER);
    jPanelRoot.add(progress, BorderLayout.SOUTH);

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize(); //获得屏幕的大小
    Dimension frameSize = this.getPreferredSize();
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    //this.setLocation(screenSize.width / 4, screenSize.height / 4); //将Logo窗口显示在屏幕宽的1/4，高的1/4处
    this.setSize(image.getIconWidth(), image.getIconHeight()); //将Logo窗口大小设成图像的大小
    this.toFront(); //将Logo窗口显示为最前面的窗口

    this.setVisible(true); //显示该窗口
  }

  public void setNotVisible() {
    //setVisible(false); //不显示该窗口
    this.dispose();
  }
}
