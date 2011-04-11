package com.combanc.monitor.vo.systemParam;

import java.util.ArrayList;
import java.util.List;

import com.combanc.monitor.pojo.MonitorPingTimePoint;

/**
 * <p>
 * Title:Ping扫描系统设置
 * </p>
 * <p>
 * Description:设置间隔
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
public class PingScan {

	/** ping次数 **/
	private String pingNum;
	/** ping包长度 **/
	private String pingSize;
	/** ping超时时间 **/
	private String pingTimeout;
	/** ping细节表保存时间 **/
	private String pingDetailLife;
	/** ping统计表保存时间 **/
	private String pingReportLife;
	/** ping执行模式 **/
	private String pingExcuteMode;
	/** ping的自动执行模式  **/
	private String pingAutoExcuteMode;
	/** ping时间（星期几） **/
	private String pingDayInWeek;
	/** ping轮询时间间隔 **/
	private String pingLoopGap;
	/** ping轮询起始时间**/
	private String pingStartTimeOfDay;
	/** ping轮询截止时间**/
	private String pingEndTimeOfDay;
	/** 是否在下列时间段才监测**/
	private String isDefinehours;
	/** 监测时间-星期**/
	private String[] days = {}; 
	/** 定时执行 时间列表**/
	private List<MonitorPingTimePoint>  timePointList = new ArrayList<MonitorPingTimePoint>();
	
	
	public String getPingDayInWeek() {
		return pingDayInWeek;
	}
	public void setPingDayInWeek(String pingDayInWeek) {
		this.pingDayInWeek = pingDayInWeek;
	}
	public List<MonitorPingTimePoint> getTimePointList() {
		return timePointList;
	}
	public void setTimePointList(List<MonitorPingTimePoint> timePointList) {
		this.timePointList = timePointList;
	}
	public String getIsDefinehours() {
		return isDefinehours;
	}
	public void setIsDefinehours(String isDefinehours) {
		this.isDefinehours = isDefinehours;
	}
	public String getPingStartTimeOfDay() {
		return pingStartTimeOfDay;
	}
	public void setPingStartTimeOfDay(String pingStartTimeOfDay) {
		this.pingStartTimeOfDay = pingStartTimeOfDay;
	}
	public String getPingEndTimeOfDay() {
		return pingEndTimeOfDay;
	}
	public void setPingEndTimeOfDay(String pingEndTimeOfDay) {
		this.pingEndTimeOfDay = pingEndTimeOfDay;
	}
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	
	public String getPingAutoExcuteMode() {
		return pingAutoExcuteMode;
	}
	public void setPingAutoExcuteMode(String pingAutoExcuteMode) {
		this.pingAutoExcuteMode = pingAutoExcuteMode;
	}
	public String getPingNum() {
		return pingNum;
	}
	public void setPingNum(String pingNum) {
		this.pingNum = pingNum;
	}
	public String getPingSize() {
		return pingSize;
	}
	public void setPingSize(String pingSize) {
		this.pingSize = pingSize;
	}
	public String getPingTimeout() {
		return pingTimeout;
	}
	public void setPingTimeout(String pingTimeout) {
		this.pingTimeout = pingTimeout;
	}
	public String getPingDetailLife() {
		return pingDetailLife;
	}
	public void setPingDetailLife(String pingDetailLife) {
		this.pingDetailLife = pingDetailLife;
	}
	public String getPingReportLife() {
		return pingReportLife;
	}
	public void setPingReportLife(String pingReportLife) {
		this.pingReportLife = pingReportLife;
	}
	public String getPingExcuteMode() {
		return pingExcuteMode;
	}
	public void setPingExcuteMode(String pingExcuteMode) {
		this.pingExcuteMode = pingExcuteMode;
	}
	public String getPingLoopGap() {
		return pingLoopGap;
	}
	public void setPingLoopGap(String pingLoopGap) {
		this.pingLoopGap = pingLoopGap;
	}
	
	
	 
}
