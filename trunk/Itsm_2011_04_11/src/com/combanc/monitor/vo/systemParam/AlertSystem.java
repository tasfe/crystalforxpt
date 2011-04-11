package com.combanc.monitor.vo.systemParam;

import com.combanc.monitor.constants.SystemParamConstants;

/**
 * <p>
 * Title:系统设置:报警设置
 * </p>
 * <p>
 * Description:设置邮件报警、短信报警、声音报警参数等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class AlertSystem {

	/** 邮件报警 **/
	private String emailSwitch ;
	private String smtpServer ;
	private String mailSender = "";
	private String mailSenderPwd = "";
	/** 短信报警：默认关 **/
	private String mobileSwitch ;
	private String smsSn ;
	
	/** 声音报警：默认关 **/
	private String soundSwitch ;
	
	/** Arp警示阈值 **/
	private String arpLimen ;
	/** 报警升级门限（报limitToAddLevel次警后，提升报警级别） **/
	private String limitToAddLevel ;
	/** 设备、主机检查间隔（分钟）**/
	private String checkGap ;
	/** 异常后的检查间隔（分钟）**/
	private String errorGap ;
	
	public String getEmailSwitch() {
		return emailSwitch;
	}
	public void setEmailSwitch(String emailSwitch) {
		this.emailSwitch = emailSwitch;
	}
	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}
	public String getMailSender() {
		return mailSender;
	}
	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}
	public String getMailSenderPwd() {
		return mailSenderPwd;
	}
	public void setMailSenderPwd(String mailSenderPwd) {
		this.mailSenderPwd = mailSenderPwd;
	}
	public String getMobileSwitch() {
		return mobileSwitch;
	}
	public void setMobileSwitch(String mobileSwitch) {
		this.mobileSwitch = mobileSwitch;
	}
	public String getSmsSn() {
		return smsSn;
	}
	public void setSmsSn(String smsSn) {
		this.smsSn = smsSn;
	}
	public String getSoundSwitch() {
		return soundSwitch;
	}
	public void setSoundSwitch(String soundSwitch) {
		this.soundSwitch = soundSwitch;
	}
	public String getArpLimen() {
		return arpLimen;
	}
	public void setArpLimen(String arpLimen) {
		this.arpLimen = arpLimen;
	}
	public String getLimitToAddLevel() {
		return limitToAddLevel;
	}
	public void setLimitToAddLevel(String limitToAddLevel) {
		this.limitToAddLevel = limitToAddLevel;
	}
	public String getCheckGap() {
		return checkGap;
	}
	public void setCheckGap(String checkGap) {
		this.checkGap = checkGap;
	}
	public String getErrorGap() {
		return errorGap;
	}
	public void setErrorGap(String errorGap) {
		this.errorGap = errorGap;
	}
	 
	 
	
	
}
