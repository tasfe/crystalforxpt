package com.combanc.monitor.alert.alertParser;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import BestMail.smssend;

import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.UserService;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorAlertSmalltypeService;
import com.combanc.monitor.service.MonitorAlertTypeService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.XmlTool;

public class BaseParser {

	private static final Log log = LogFactory.getLog(BaseParser.class);
	
	Map<Integer, UserMobileMsg> userMobileMsgMap = new HashMap<Integer, UserMobileMsg>();
	Map<Integer, UserEmailMsg>  userEmailMsgMap  = new HashMap<Integer, UserEmailMsg>();
	
	protected MonitorSystemParamService monitorSystemParamService;
	// 设备表服务
	protected MonitorDeviceService monitorDeviceService ;
	// 报警表服务
	protected MonitorAlertService monitorAlertService ;
	// 报警类型表服务
	protected MonitorAlertSmalltypeService monitorAlertSmalltypeService ;
	// 报警类型服务
	protected MonitorAlertTypeService monitorAlertTypeService ;
	
	protected UserService userService;
	// 设备表
	protected List<MonitorDevice> deviceList = null;
	// 用户表
	protected List<Users> userList = null;
	// CPU负荷阈值，百分比
	protected int cpuLimen = SystemParamConstants.SYSTEM_CPU_LIMEN_DEFUALT_VALUE;
	// 宽带阈值，百分比
	protected int bandLimen = SystemParamConstants.SYSTEM_BAND_LIMEN_DEFUALT_VALUE;
	// AKCP温度阈值（度）
	protected int tempLimen = SystemParamConstants.SYSTEM_TEMP_LIMEN_DEFUALT_VALUE;
	// 声音报警（ 默认：关）
	protected String soundSwitch="0"; 
	// 短信报警 （默认：关）
	protected String mobileSwitch="0";
	// 邮件报警（ 默认：关）
	protected String emailSwitch="0";
	// 线程互斥量
	public static Integer nSendingShortMsg = 0;
	public static Integer nSendingEmail    = 0;
	
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public BaseParser() {
		 
	}
	
	public void init(){
		
		try{
			MonitorSystemParam systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CPU_LIMEN);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				cpuLimen = Integer.parseInt(systemParam.getValue());
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BAND_LIMEN);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				bandLimen = Integer.parseInt(systemParam.getValue());
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TEMP_LIMEN);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				tempLimen = Integer.parseInt(systemParam.getValue());
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SOUND_SWTICH);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				soundSwitch = systemParam.getValue();
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MOBILE_SWTICH);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				mobileSwitch = systemParam.getValue();
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_EMAIL_SWTICH);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				emailSwitch = systemParam.getValue();
			deviceList = monitorDeviceService.findEffectiveList();
			userList = userService.findAllUser();
		}catch(Exception e){
			e.printStackTrace(); 
		}
		 
	}
	/**
	 * 构造针对每个用户的短信报警信息和邮件报警信息
	 * @param sTitle 系统报警标题
	 */
	protected void setUserMsg(String sTitle){
		userMobileMsgMap.clear();
		userEmailMsgMap.clear();
		for( Users us : userList ){
			Integer userId = us.getId();
			userMobileMsgMap.put(userId, new UserMobileMsg(us.getCellphone()));
			userEmailMsgMap.put(userId, new UserEmailMsg(us.getEmail(), sTitle));
			
		}
	}
	
	/**
	 * 设置短信、邮件的标题和内容
	 * @param monitorAlertPolicy 报警策略
	 * @param alertType 报警类型常量
	 * @param title 报警标题
	 * @param msg 报警内容
	 */
	protected void setAlertMsg(MonitorAlertPolicy monitorAlertPolicy,int alertType, String title,String msg){
		// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
		if( checkAlertPolicy(monitorAlertPolicy, alertType) ){
			String recStr = monitorAlertPolicy.getAlertReceivers();
			String receivers[] = null;
			if ( null == recStr || "".equals(recStr))
				return;
			receivers = recStr.split(",");
			if( null == receivers || receivers.length <= 0 )
				return;
			
			for (int j = 0; j < receivers.length; j++) {
				// 解析报警接收人字符串recStr的子串receivers (eg: 1@1@0 )
				String ways[] = receivers[j].split("@");
				if( null  == ways || 3 != ways.length )
					continue;
				// 用户id
				Integer userId = 0;
				try {
					userId = Integer.valueOf(ways[0]);
				} catch (NumberFormatException nfe) {
					userId = 0;
					nfe.printStackTrace();
				}
				// 是否选择“短信报警”且开启开关
				if( "1".equals(ways[1]) && monitorAlertPolicy.getMobileSwitch() 
						&& "1".equals(mobileSwitch) ){
					UserMobileMsg umm = userMobileMsgMap.get(userId);
					if( umm != null ){
						String sMsg[] = umm.alertMsg;
						if( sMsg != null ){
							for( int k=0; k<sMsg.length; k++ ){
								if( sMsg[k] != null && "".equals(sMsg[k]) ){
									sMsg[k] = "<"+title+"> " + msg;
									if( k == 0 ){
										sMsg[k] = "网管报警：" + sMsg[k];
									}
									umm.nCurrMsgCount++;
									break;
								}
							}
						}
					}
				}
				// 是否选择“邮件报警”且开启开关
				if( "1".equals(ways[2]) && monitorAlertPolicy.getEmailSwitch()
						&& "1".equals(emailSwitch) ){
					UserEmailMsg uem = userEmailMsgMap.get(userId);
					if(uem != null) {
						uem.alertMsg += title + ":" + msg;
					}
				}
			}// end for 
		}
		
	}
	
	/**
	 * 检查是否符合报警策略所设置的报警条件
	 * @param ap
	 * @param alertType
	 * @return
	 */
	boolean checkAlertPolicy(MonitorAlertPolicy ap, int alertType) {
		GregorianCalendar now = new GregorianCalendar();
		
		//1、判断监测时间区间
		int hour = now.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = now.get(GregorianCalendar.MINUTE);
		int second = now.get(GregorianCalendar.SECOND);
		int time = 1000 * ((hour - 8) * 3600 + minute * 60 + second);
		//System.out.println(" time  " + time);
		// 当都等于0的时候不判断
		if (!(ap.getDailyStart() == 0 && ap.getDailyEnd() == 0))
			if (time < ap.getDailyStart() || time > ap.getDailyEnd())
				return false;
		
		// 2、判断day of week
		// SUNDAY = 1; MONDAY = 2;
		// dayOfWeek 0123456 对应周一、周二...
		int dayOfWeek = now.get(GregorianCalendar.DAY_OF_WEEK);
		dayOfWeek = (dayOfWeek + 5) % 7;
		if (ap.getDayOfWeek().indexOf(String.valueOf(dayOfWeek)) < 0)
			return false;
		
		if(!isTypeContain(ap.getAlertTypes(), alertType))
			return false;
		
		return true;
	}
	
	private boolean isTypeContain(String alertType,int type) {
		String types[] = alertType.split(",");
		if(types.length == 0)
			return false;
		else {
			for(int i = 0;i<types.length;i++) {
				if(String.valueOf(type).equals(types[i]))
					return true;
			}
			return false;
		}
	}
	
	
	/**
	 *  声音报警:四声
	 * @param sSoundFile 声音文件
	 */
	void soundAlert(String sSoundFile) {
		try {
			// 只支持WAV文件格式。
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(sSoundFile));
			AudioFormat audioFormat = ais.getFormat();

			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(audioFormat);

			byte audioData[] = new byte[128];
			int inBytes = 0;
			sdl.start();
			while (inBytes > -1) {
				inBytes = ais.read(audioData, 0, 128);
				if (inBytes >= 0)
					sdl.write(audioData, 0, 128);
			}

			sdl.drain();
			sdl.close();
			ais.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("声音报警发送错误：", e);
		}
	}
	
	/** 发短信**/
	boolean sendMobileMsg() {
		return true;
	}
	
	/** 发邮件**/
	boolean sendEmail() {
		synchronized (nSendingEmail) {
			String sSMTPServer = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SMTP_SERVER).getValue();
			String sSendEmail = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAIL_SENDER).getValue();
			String sSendPwd = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAIL_SENDER_PWD).getValue();
			
			if( sSMTPServer==null || sSMTPServer.equals("") || sSendPwd==null || sSendPwd.equals("")|| sSendEmail==null|| sSendEmail.equals("") ) {
				return false;
			}
			// 缺省超时设置为1分钟。
			try {
				for( Integer id : userEmailMsgMap.keySet() ){
					// 邮件内容
					String sMsg = userEmailMsgMap.get(id).alertMsg;
					if( sMsg == null || "".equals(sMsg) )
						continue;
					// 接收邮箱地址
					String sRecEmail = userEmailMsgMap.get(id).sEmail;
					if( sRecEmail == null || "".equals(sRecEmail) )
						continue;
					// 标题
					String sTitle = userEmailMsgMap.get(id).sTitle;
					
					// 每发送一条延迟1000ms
					try {
						java.lang.Thread.sleep(1000);
					} catch (Exception t) {
						;
					}
					
					Properties props = System.getProperties();
					props.put("mail.smtp.host", sSMTPServer);
					// 如果发送帐号密码不空，则设置为身份验证模式
					Authenticator auth = null;
					if (!sSendPwd.equals("")) {
						auth = new PopupAuthenticator(sSendEmail, sSendPwd);
						props.put("mail.smtp.auth", "true");
					}

					Session session = Session.getDefaultInstance(props, auth);
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(sSendEmail));
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
							sRecEmail, false));
					
					if(null==sTitle)
						sTitle = "";
					msg.setSentDate(new Date());
					msg.setSubject(MainConstants.BASELINE + "网络监控管理系统报警："+sTitle);
					msg.setText(sMsg);
					Transport.send(msg);
					System.out.println("发送邮件：" + sMsg);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				log.error("报警邮件发送错误：", e);
				//JOptionPane.showMessageDialog(null, "报警邮件发送错误：" + e);
				return false;
			}
		}
	}
	
	/**
	 *  邮件认证
	 * @author Administrator
	 *
	 */
	class PopupAuthenticator extends Authenticator {
		String sSendEmail;
		String sSendPwd;

		public PopupAuthenticator(String sSendEmail, String sSendPwd) {
			this.sSendEmail = sSendEmail;
			this.sSendPwd = sSendPwd;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(sSendEmail, sSendPwd);
		}
	}
	
	/**
	 *  用户短信信息
	 * @author Administrator
	 *
	 */
	class UserMobileMsg {
		String sMobile;
		int	   nCurrMsgCount = 0;
		String alertMsg[] = new String[MainConstants.SHORT_MSG_MAX_NUM];
		
		public UserMobileMsg(String sMobile) {
			this.sMobile = sMobile;
			for( int i=0; i<this.alertMsg.length; i++){
				this.alertMsg[i] = "";
			}
		}
	}
	
	/**
	 *  用户邮件信息
	 * @author Administrator
	 *
	 */
	class UserEmailMsg {
		String sEmail;
		String sTitle;
		String alertMsg;
		
		public UserEmailMsg(String sEmail, String sTitle) {
			this.sEmail = sEmail;
			this.sTitle = sTitle;
			this.alertMsg = "";
		}
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}

	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}

	public MonitorAlertSmalltypeService getMonitorAlertSmalltypeService() {
		return monitorAlertSmalltypeService;
	}

	public void setMonitorAlertSmalltypeService(
			MonitorAlertSmalltypeService monitorAlertSmalltypeService) {
		this.monitorAlertSmalltypeService = monitorAlertSmalltypeService;
	}

	public MonitorAlertTypeService getMonitorAlertTypeService() {
		return monitorAlertTypeService;
	}

	public void setMonitorAlertTypeService(
			MonitorAlertTypeService monitorAlertTypeService) {
		this.monitorAlertTypeService = monitorAlertTypeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


 
	
}
