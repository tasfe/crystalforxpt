package crystal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.apache.log4j.Logger;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.LightGray;

import crystal.common.data.Log;

/**
 * <p>
 * Title: 主程序
 * </p>
 * <p>
 * Description: 显示LOGO和主窗体
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: Integrity Ltd.
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class MainApp {

	private static Logger logger = Log.getLogger();
	boolean packFrame = false;
	// LOGO窗口定义。作为内部类，要在类的定义位置而不是所在方法的位置进行定义。
	public JWindow window = null;

	Dimension screenSize = null;
	Dimension frameSize = null;

	static String startStr = "程序启动中，请稍候......   ";

	public MainApp() {
		// 主窗口：
		MainFrame frame = new MainFrame();

		// 保证在对输入框输入汉字时，不弹出“输入窗口”的浮动窗口
		System.setProperty("java.awt.im.style","on-the-spot"); 
		
		Logo.progress.setValue(95);
		Logo.progress.setString(startStr + "95%");

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = frame.getSize();
		// 确保窗口大小不超过屏幕大小：
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}

		if (packFrame) {
			frame.pack();
		} else {
			frame.validate();
		}
		
		// 显示主窗口，大小与屏幕相同，留出屏幕底部的显示条。
		// frame.userLogin();
		frame.setSize(screenSize.width, screenSize.height - 30);
		frame.setLocation((screenSize.width - frame.getSize().width) / 2,
				(screenSize.height - frame.getSize().height) / 2 - 15);
		frame.setVisible(true);

		Logo.progress.setValue(99);
		Logo.progress.setString(startStr + "99%");

		// 显示用户登录窗口
		// if (MainFrame.isRestart == false)
		frame.userLogin();
	}

	public static void main(String[] args) {
		logger.info("Starting....");
		logger.info("java.version = " + System.getProperty("java.version"));
		final Logo logo = new Logo("start.gif");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logo.run();
				com.sun.awt.AWTUtilities.setWindowOpacity(logo, 0.8f);
			}
		});

		Logo.progress.setValue(0);
		Logo.progress.setString(startStr + "0%");
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			System.setProperty("sun.awt.noerasebackground", "true");

			PlasticLookAndFeel.setTabStyle("Plastic.borderStyle");
			PlasticLookAndFeel.set3DEnabled(true);
			PlasticLookAndFeel.setPlasticTheme(new LightGray());

			UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");

			UIManager.put("ToolTip.background", new ColorUIResource(16777185));
			Enumeration keys = UIManager.getLookAndFeelDefaults().keys();

			while (keys.hasMoreElements()) {
				Object key = keys.nextElement();
				if (UIManager.get(key) instanceof Font)
					UIManager.put(key, new Font("Dialog", 0, 12));
			}

			Logo.progress.setValue(5);
			Logo.progress.setString(startStr + "5%");

			/*
			 * FontUIResource fontRes = new FontUIResource(new Font("simsun",
			 * Font.PLAIN, 12)); for (Enumeration<Object> keys =
			 * UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
			 * Object key = keys.nextElement(); Object value =
			 * UIManager.get(key); if (value instanceof FontUIResource) {
			 * UIManager.put(key, fontRes); } }
			 */

			/** fix font bug start */
			if (SwingUtilities.isEventDispatchThread()) {
				fixFontBug();
			} else {
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							System.out
									.println(Thread.currentThread().getName());
							fixFontBug();
						}
					});
				} catch (Exception e) {
					// logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Logo.progress.setValue(10);
		Logo.progress.setString(startStr + "10%");

		new MainApp();
		logo.dispose();
	}

	private static void fixFontBug() {
		int sizeOffset = 0;
		Enumeration keys = UIManager.getLookAndFeelDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof Font) {
				Font oldFont = (Font) value;
				Font newFont = new Font("Dialog", oldFont.getStyle(), oldFont
						.getSize()
						+ sizeOffset);
				UIManager.put(key, newFont);
			}
		}
	}
}
