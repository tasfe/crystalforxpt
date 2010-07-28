package crystal;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class AlertMail extends Thread {
	String alertString = new String();
	String title = "";

	public AlertMail() {
	}

	public void run() {
		sendMail();
	}

	public void setAlertMessage(String alertMessage) {
		alertString = alertMessage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private void sendMail() {
		// ȱʡ��ʱ����Ϊ1���ӡ�
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", MainFrame.smtpServer);
			// ��������ʺ����벻�գ�������Ϊ�����֤ģʽ
			Authenticator auth = null;
			if (!MainFrame.mailSenderPwd.equals("")) {
				auth = new PopupAuthenticator();
				props.put("mail.smtp.auth", "true");
			}

			Session session = Session.getDefaultInstance(props, auth);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(MainFrame.mailSender));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
					MainFrame.mailAddr1, false));

			/*if (!MainFrame.mailAddr2.equals(""))
				msg.setRecipients(Message.RecipientType.CC, InternetAddress
						.parse(MainFrame.mailAddr2, false));*/
			if(null==title)
				title = "";
			msg.setSentDate(new Date());
			msg.setSubject("���������ع���ϵͳ����"+title);
			msg.setText(alertString);
			Transport.send(msg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�����ʼ����ʹ���" + e);
		}
	}
}

class PopupAuthenticator extends Authenticator {

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(MainFrame.mailSender,
				MainFrame.mailSenderPwd);
	}

}
