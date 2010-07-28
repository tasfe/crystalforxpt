package crystal;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.borland.jbcl.layout.VerticalFlowLayout;

/**
 * <p>
 * Title: 查询日志，按分类和首次时间。
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003</>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class LogQuerySetupDlg extends JDialog {
	String logDate = "";
	JPanel panelRoot = new JPanel();
	JPanel jPanelDate = new JPanel();
	JPanel jPanelButton = new JPanel();

	VerticalFlowLayout verticalFlowLayoutRoot = new VerticalFlowLayout();

	JLabel jLabel1 = new JLabel();
	JLabel jLabelYear = new JLabel();
	JComboBox jComboBoxYear = new JComboBox(new String[] { "2007", "2008",
			"2009", "2010", "2011", "2012" });
	JLabel jLabelMonth = new JLabel();
	JComboBox jComboBoxMonth = new JComboBox(new String[] { "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "10", "11", "12" });
	JLabel jLabelDay = new JLabel();
	JComboBox jComboBoxDay = new JComboBox(new String[] { "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29", "30", "31" });

	JButton jButtonQuery = new JButton();
	JButton jButtonExit = new JButton();

	public LogQuerySetupDlg(Frame frame) {
		super(frame);
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {
		panelRoot.setLayout(verticalFlowLayoutRoot);
		this.setModal(true);// 对话框不可转移

		jButtonQuery.setText("查询");
		jButtonQuery.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonQuery_actionPerformed(e);
			}
		});
		jButtonExit.setText("取消");
		jButtonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonExit_actionPerformed(e);
			}
		});
		panelRoot.setBorder(BorderFactory.createEtchedBorder());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});
		getContentPane().add(panelRoot);

		jComboBoxYear.setPreferredSize(new Dimension(80, 22));
		jComboBoxMonth.setPreferredSize(new Dimension(50, 22));
		jComboBoxDay.setPreferredSize(new Dimension(50, 22));

		jLabel1.setText("选择日期：");
		jLabelYear.setText("年");
		jLabelMonth.setText("月");
		jLabelDay.setText("日");

		jPanelDate.add(jLabel1, null);
		jPanelDate.add(jComboBoxYear, null);
		jPanelDate.add(jLabelYear, null);
		jPanelDate.add(jComboBoxMonth, null);
		jPanelDate.add(jLabelMonth, null);
		jPanelDate.add(jComboBoxDay, null);
		jPanelDate.add(jLabelDay, null);

		jPanelButton.setBorder(BorderFactory.createEtchedBorder());
		jPanelButton.add(jButtonQuery, null);
		jPanelButton.add(jButtonExit, null);

		panelRoot.add(jPanelDate, null);
		panelRoot.add(jPanelButton, null);

		GregorianCalendar gdate = new GregorianCalendar();
		gdate.setTimeInMillis(System.currentTimeMillis());// 当前时间
		gdate.add(GregorianCalendar.DATE, 0);// 以当日为缺省日期
		String curYear = String.valueOf(gdate.get(GregorianCalendar.YEAR));
		String curMonth = String.valueOf(gdate.get(GregorianCalendar.MONTH) + 1);// 注意月份从0开始计数
		String curDay = String.valueOf(gdate.get(GregorianCalendar.DAY_OF_MONTH));
		// 以当前日期作为缺省：
		jComboBoxYear.setSelectedItem(curYear);
		jComboBoxMonth.setSelectedItem(curMonth);
		jComboBoxDay.setSelectedItem(curDay);
		if(MainFrame.demoMode) {
			jComboBoxYear.setSelectedItem("2007");
			jComboBoxMonth.setSelectedItem("10");
			jComboBoxDay.setSelectedItem("8");
			//logDate = "2007-10-8";
		}
	}

	void jButtonQuery_actionPerformed(ActionEvent e) {
		String year = (String) jComboBoxYear.getSelectedItem();
		String month = (String) jComboBoxMonth.getSelectedItem();
		String day = (String) jComboBoxDay.getSelectedItem();
		// if(month.length() == 1) month = "0" + month;
		logDate = year + "-" + month + "-" + day;
		this.dispose();
	}

	void jButtonExit_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	void this_windowClosing(WindowEvent e) {
		this.dispose();
	}
}
