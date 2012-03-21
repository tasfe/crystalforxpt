package com.netblizzard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyEclipseGen extends JFrame {

	/**
	 * Launch the application
	 * @param args
	 */
	
	private static final String LL = "Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself.";
	private JLabel backLabel;
	
	public String getSerial(String userId, String licenseNum) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.add(1, 3);
		cal.add(6, -1);
		java.text.NumberFormat nf = new java.text.DecimalFormat("000");
		licenseNum = nf.format(Integer.valueOf(licenseNum));
		String verTime = new StringBuilder("-").append(
				new java.text.SimpleDateFormat("yyMMdd").format(cal.getTime()))
				.append("0").toString();
		String type = "YE3MP-";
		String need = new StringBuilder(userId.substring(0, 1)).append(type)
				.append("300").append(licenseNum).append(verTime).toString();
		String dx = new StringBuilder(need).append(LL).append(userId)
				.toString();
		int suf = this.decode(dx);
		String code = new StringBuilder(need).append(String.valueOf(suf))
				.toString();
		return this.change(code);
	}

	private int decode(String s) {
		int i;
		char[] ac;
		int j;
		int k;
		i = 0;
		ac = s.toCharArray();
		j = 0;
		k = ac.length;
		while (j < k) {
			i = (31 * i) + ac[j];
			j++;
		}
		return Math.abs(i);
	}

	private String change(String s) {
		byte[] abyte0;
		char[] ac;
		int i;
		int k;
		int j;
		abyte0 = s.getBytes();
		ac = new char[s.length()];
		i = 0;
		k = abyte0.length;
		while (i < k) {
			j = abyte0[i];
			if ((j >= 48) && (j <= 57)) {
				j = (((j - 48) + 5) % 10) + 48;
			} else if ((j >= 65) && (j <= 90)) {
				j = (((j - 65) + 13) % 26) + 65;
			} else if ((j >= 97) && (j <= 122)) {
				j = (((j - 97) + 13) % 26) + 97;
			}
			ac[i] = (char) j;
			i++;
		}
		return String.valueOf(ac);
	}

	
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {  
					MyEclipseGen frame = new MyEclipseGen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame
	 */
	public MyEclipseGen() {
		super();
		setAlwaysOnTop(true);
		getContentPane().setFont(new Font("", Font.PLAIN, 11));
		setIconImages(null);
		setBackground(Color.BLACK);
		setTitle("MyEclipseGen");
		getContentPane().setLayout(null);
		setBounds(100, 100, 459, 162);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel hardwareAddressLabel = new JLabel();
		hardwareAddressLabel.setBackground(SystemColor.control);
		hardwareAddressLabel.setForeground(Color.BLACK);
		hardwareAddressLabel.setFont(new Font("", Font.BOLD, 12));
		hardwareAddressLabel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		hardwareAddressLabel.setText("Serial Number:");
		hardwareAddressLabel.setBounds(10, 12, 94, 18);
		getContentPane().add(hardwareAddressLabel);

		final JLabel activationKeyLabel = new JLabel();
		activationKeyLabel.setBackground(SystemColor.control);
		activationKeyLabel.setForeground(Color.BLACK);
		activationKeyLabel.setFont(new Font("", Font.BOLD, 12));
		activationKeyLabel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		activationKeyLabel.setText("Activation Key:");
		activationKeyLabel.setBounds(10, 58, 94, 18);
		getContentPane().add(activationKeyLabel);

		final JButton keygenButton = new JButton();
		keygenButton.setAutoscrolls(true);
		keygenButton.setBorder(new LineBorder(Color.BLACK, 1, false));
		keygenButton.setFont(new Font("", Font.BOLD, 11));
		
		
		
		
		
		keygenButton.setText("Keygen");
		keygenButton.setBounds(10, 90, 432, 28);
		getContentPane().add(keygenButton);

		final JTextField textField = new JTextField();
		textField.setBackground(SystemColor.control);
		textField.setForeground(SystemColor.activeCaption);
		textField.setBorder(new LineBorder(Color.black, 1, false));
		textField.setBounds(107, 10, 334, 22);
		getContentPane().add(textField);

		final JTextField textField_1 = new JTextField();
		textField_1.setBackground(SystemColor.control);
		textField_1.setForeground(Color.RED);
		textField_1.setBorder(new LineBorder(Color.black, 1, false));
		textField_1.setEditable(false);
		textField_1.setBounds(107, 56, 334, 22);
		getContentPane().add(textField_1);
		
		
		keygenButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					String userId = textField.getText().trim();
					MyEclipseGen myeclipsegen = new MyEclipseGen();
					String res = myeclipsegen.getSerial(userId, "20");
					textField_1.setText(res);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
	}

}
