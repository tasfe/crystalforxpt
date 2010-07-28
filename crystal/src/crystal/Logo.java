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
  String filename; //Logoͼ���ļ����ļ���

  public static JProgressBar progress = new JProgressBar(0, 100);

  public Logo(String name) {
    filename = name;
  }

  public void run() {
    JPanel jPanelRoot = new JPanel();
    ImageIcon image = new ImageIcon(filename);
    JLabel label = new JLabel(image);

    progress.setStringPainted(true); // �������
    progress.setBackground(Color.WHITE);
    progress.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.YELLOW));
    //progress.setPreferredSize(new Dimension(image.getIconWidth(), 25));
    //progress.setForeground(Color.BLUE);

    this.getContentPane().add(jPanelRoot); //����ʾͼƬ��label�ӵ�JPanel��
    jPanelRoot.setLayout(new BorderLayout());
    jPanelRoot.add(label, BorderLayout.CENTER);
    jPanelRoot.add(progress, BorderLayout.SOUTH);

    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize(); //�����Ļ�Ĵ�С
    Dimension frameSize = this.getPreferredSize();
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    //this.setLocation(screenSize.width / 4, screenSize.height / 4); //��Logo������ʾ����Ļ���1/4���ߵ�1/4��
    this.setSize(image.getIconWidth(), image.getIconHeight()); //��Logo���ڴ�С���ͼ��Ĵ�С
    this.toFront(); //��Logo������ʾΪ��ǰ��Ĵ���

    this.setVisible(true); //��ʾ�ô���
  }

  public void setNotVisible() {
    //setVisible(false); //����ʾ�ô���
    this.dispose();
  }
}
