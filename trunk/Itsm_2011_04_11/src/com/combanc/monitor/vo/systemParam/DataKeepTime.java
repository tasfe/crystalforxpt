package com.combanc.monitor.vo.systemParam;

import com.combanc.monitor.constants.SystemParamConstants;

public class DataKeepTime {
	
	/**报警数据保存记录数 **/
	private String alertDataKeep = String.valueOf(SystemParamConstants.SYSTEM_ALERT_DATA_KEEP_DEFUALT_VALUE);
	/** 流量及CPU采集数据分钟保存时长 **/
	private String minuteDataKeep= String.valueOf(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP_DEFUALT_VALUE);
	/** 流量及CPU采集数据小时保存时长 **/
	private String hourDataKeep= String.valueOf(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP_DEFUALT_VALUE);
	
	public String getAlertDataKeep() {
		return alertDataKeep;
	}
	public void setAlertDataKeep(String alertDataKeep) {
		this.alertDataKeep = alertDataKeep;
	}
	public String getMinuteDataKeep() {
		return minuteDataKeep;
	}
	public void setMinuteDataKeep(String minuteDataKeep) {
		this.minuteDataKeep = minuteDataKeep;
	}
	public String getHourDataKeep() {
		return hourDataKeep;
	}
	public void setHourDataKeep(String hourDataKeep) {
		this.hourDataKeep = hourDataKeep;
	}
	
	
}
