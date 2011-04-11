package com.combanc.monitor.vo.systemParam;

import java.util.ArrayList;
import java.util.List;

import com.combanc.itsm.pojo.Users;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.pojo.MonitorAlertSmalltype;

public class AlertPolicy {
	
	private MonitorAlertPolicy monitorAlertPolicy; 
	
	private String isDefinehours="0";//是否在下列时间段才监测
	
	/** 监测时间-星期**/
	private String[] days = {}; 
	/** 重要报警 **/
	private List<MonitorAlertSmalltype> alertSmallTypeList1 = new ArrayList<MonitorAlertSmalltype>();
	/** 普通报警 **/
	private List<MonitorAlertSmalltype> alertSmallTypeList2 = new ArrayList<MonitorAlertSmalltype>();
	/** 次要报警 **/
	private List<MonitorAlertSmalltype> alertSmallTypeList3 = new ArrayList<MonitorAlertSmalltype>();
	
	/** 选择的报警类型**/
	private Integer[] alertSmallTypes1 = {}; 
	private Integer[] alertSmallTypes2 = {}; 
	private Integer[] alertSmallTypes3 = {}; 
	
	/** 全局报警状态 **/
	private Boolean mobileSwitch;
	private Boolean emailSwitch;
	private Boolean soundSwitch;
	
	private List<Users> userList;
	/** 故障/阈值检查时间间隔 **/
	private Integer checkGap;
	/** 异常后的检查时间间隔**/
	private Integer errorGap;
 
	 
	public MonitorAlertPolicy getMonitorAlertPolicy() {
		return monitorAlertPolicy;
	}
	public void setMonitorAlertPolicy(MonitorAlertPolicy monitorAlertPolicy) {
		this.monitorAlertPolicy = monitorAlertPolicy;
	}
	public String getIsDefinehours() {
		return isDefinehours;
	}
	public void setIsDefinehours(String isDefinehours) {
		this.isDefinehours = isDefinehours;
	}
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	
	public List<MonitorAlertSmalltype> getAlertSmallTypeList3() {
		return alertSmallTypeList3;
	}
	public void setAlertSmallTypeList3(
			List<MonitorAlertSmalltype> alertSmallTypeList3) {
		this.alertSmallTypeList3 = alertSmallTypeList3;
	}
	public List<MonitorAlertSmalltype> getAlertSmallTypeList1() {
		return alertSmallTypeList1;
	}
	public void setAlertSmallTypeList1(
			List<MonitorAlertSmalltype> alertSmallTypeList1) {
		this.alertSmallTypeList1 = alertSmallTypeList1;
	}
	public List<MonitorAlertSmalltype> getAlertSmallTypeList2() {
		return alertSmallTypeList2;
	}
	public void setAlertSmallTypeList2(
			List<MonitorAlertSmalltype> alertSmallTypeList2) {
		this.alertSmallTypeList2 = alertSmallTypeList2;
	}
	public Integer[] getAlertSmallTypes1() {
		return alertSmallTypes1;
	}
	public void setAlertSmallTypes1(Integer[] alertSmallTypes1) {
		this.alertSmallTypes1 = alertSmallTypes1;
	}
	public Integer[] getAlertSmallTypes2() {
		return alertSmallTypes2;
	}
	public void setAlertSmallTypes2(Integer[] alertSmallTypes2) {
		this.alertSmallTypes2 = alertSmallTypes2;
	}
	public Integer[] getAlertSmallTypes3() {
		return alertSmallTypes3;
	}
	public void setAlertSmallTypes3(Integer[] alertSmallTypes3) {
		this.alertSmallTypes3 = alertSmallTypes3;
	}
	public Boolean getMobileSwitch() {
		return mobileSwitch;
	}
	public void setMobileSwitch(Boolean mobileSwitch) {
		this.mobileSwitch = mobileSwitch;
	}
	public Boolean getEmailSwitch() {
		return emailSwitch;
	}
	public void setEmailSwitch(Boolean emailSwitch) {
		this.emailSwitch = emailSwitch;
	}
	public Boolean getSoundSwitch() {
		return soundSwitch;
	}
	public void setSoundSwitch(Boolean soundSwitch) {
		this.soundSwitch = soundSwitch;
	}
	public List<Users> getUserList() {
		return userList;
	}
	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}
	public Integer getCheckGap() {
		return checkGap;
	}
	public void setCheckGap(Integer checkGap) {
		this.checkGap = checkGap;
	}
	public Integer getErrorGap() {
		return errorGap;
	}
	public void setErrorGap(Integer errorGap) {
		this.errorGap = errorGap;
	}
	 
	 
	 
	
	
}
