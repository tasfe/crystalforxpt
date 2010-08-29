package crystal.common;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author memo
 * @version 1.0
 */

public class DatabaseMail extends Thread {
	String mailContent = new String();
	DateFormat dataFormatter = new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss�� EE", Locale.CHINA); 
	String backupPath = "";

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	
	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public DatabaseMail() {
	}

	public void run() {
		sendMail();
	}

	public static void main(String args[]) {
		Thread mail = new DatabaseMail();
		mail.run();
	}
	
	private void sendMail() {
		// ȱʡ��ʱ����Ϊ1���ӡ�
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", Constants.smtpServer);
			props.setProperty("mail.smtp.port", "25");  
			// ��������ʺ����벻�գ�������Ϊ�����֤ģʽ
			Authenticator auth = null;
			if (!Constants.mailSenderPwd.equals("")) {
				auth = new PopupAuthenticator();
				props.put("mail.smtp.auth", "true");
			}

			Session session = Session.getDefaultInstance(props, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(Constants.mailSender));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					Constants.mailAddr1, false));
			if (!Constants.mailAddr2.equals(""))
				msg.setRecipients(Message.RecipientType.CC, InternetAddress
						.parse(Constants.mailAddr2, false));

			msg.setSentDate(new Date());
			//   System.out.println("����ָ����ʽ yyyy��MM��dd�� hhʱmm��ss�� EE ������Ϊ���� : " + df4.format(date)); 
			msg.setSubject("crystalˮ����Ʒ��������ϵͳ-���ݿⱸ��  " + dataFormatter.format(new Date()));
			msg.setText(mailContent);
			
			MimeBodyPart mbp = new MimeBodyPart();
			// mbp.attachFile("sss");
			Multipart mp = new MimeMultipart();
			//�õ�����Դ 
			if(backupPath == null || "".equals(backupPath)) {
				System.out.println("���ñ���·�� " + backupPath + " �����ڣ�");
				return;
			}
			if(!new File(backupPath + "crystal.sql").exists()) {
				System.out.println("���ñ����ļ� " + backupPath + "\\ctystal.sql �����ڣ�");
				return;
			}
			FileDataSource fds = new FileDataSource(backupPath + "crystal.sql");
			//�õ�������������BodyPart 
			mbp.setDataHandler(new DataHandler(fds));
			//�õ��ļ���ͬ������BodyPart 
			mbp.setFileName(fds.getName());
			mp.addBodyPart(mbp);
			msg.setContent(mp);
			
			System.out.println("��ʼ�����ʼ�");
			// msg.saveChanges();
			Transport.send(msg);
			System.out.println("�ʼ�������ϡ�������");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�ʼ����ʹ���" + e);
		}
	}
}

class PopupAuthenticator extends Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(Constants.mailSender,	Constants.mailSenderPwd);
	}
}
