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
	DateFormat dataFormatter = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 EE", Locale.CHINA); 
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
		// 缺省超时设置为1分钟。
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", Constants.smtpServer);
			props.setProperty("mail.smtp.port", "25");  
			// 如果发送帐号密码不空，则设置为身份验证模式
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
			//   System.out.println("按照指定格式 yyyy年MM月dd日 hh时mm分ss秒 EE ，区域为中文 : " + df4.format(date)); 
			msg.setSubject("crystal水晶饰品买卖管理系统-数据库备份  " + dataFormatter.format(new Date()));
			msg.setText(mailContent);
			
			MimeBodyPart mbp = new MimeBodyPart();
			// mbp.attachFile("sss");
			Multipart mp = new MimeMultipart();
			//得到数据源 
			if(backupPath == null || "".equals(backupPath)) {
				System.out.println("配置备份路径 " + backupPath + " 不存在！");
				return;
			}
			if(!new File(backupPath + "crystal.sql").exists()) {
				System.out.println("配置备份文件 " + backupPath + "\\ctystal.sql 不存在！");
				return;
			}
			FileDataSource fds = new FileDataSource(backupPath + "crystal.sql");
			//得到附件本身并至入BodyPart 
			mbp.setDataHandler(new DataHandler(fds));
			//得到文件名同样至入BodyPart 
			mbp.setFileName(fds.getName());
			mp.addBodyPart(mbp);
			msg.setContent(mp);
			
			System.out.println("开始发送邮件");
			// msg.saveChanges();
			Transport.send(msg);
			System.out.println("邮件发送完毕…………");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "邮件发送错误：" + e);
		}
	}
}

class PopupAuthenticator extends Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(Constants.mailSender,	Constants.mailSenderPwd);
	}
}
