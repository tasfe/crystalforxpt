package com.combanc.monitor.vo.systemParam;
/**
 * <p>
 * Title:流量及CPU采集
 * </p>
 * <p>
 * Description:设置采集间隔、类型等
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
public class FluxScan {
	/**启用/禁用**/
	private String fluxSwitch;
	/**采集间隔**/
	private String fluxScanGapTime;
	/**采集类型**/
	private String fluxType;
	/**分钟保存时长**/
	private String minuteDataKeep;
	/**小时保存时长**/
	private String hourDataKeep;
	
	public String getFluxSwitch() {
		return fluxSwitch;
	}
	public void setFluxSwitch(String fluxSwitch) {
		this.fluxSwitch = fluxSwitch;
	}
	public String getFluxScanGapTime() {
		return fluxScanGapTime;
	}
	public void setFluxScanGapTime(String fluxScanGapTime) {
		this.fluxScanGapTime = fluxScanGapTime;
	}
	public String getFluxType() {
		return fluxType;
	}
	public void setFluxType(String fluxType) {
		this.fluxType = fluxType;
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
