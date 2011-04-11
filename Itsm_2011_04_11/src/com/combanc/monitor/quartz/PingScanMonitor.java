package com.combanc.monitor.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorPingTimePoint;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorPingTimePointService;
import com.combanc.monitor.service.MonitorSystemParamService;
/**
 * <p>
 * Title:Ping扫描监视器
 * </p>
 * <p>
 * Description:
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
public class PingScanMonitor {

	private MonitorSystemParamService monitorSystemParamService;
	
	private MonitorPingTimePointService monitorPingTimePointService;
	
	private PingScan pingScan;
	
	List<MonitorPingTimePoint> pingTimePointList = new ArrayList<MonitorPingTimePoint>();
	
	/** Ping 扫描监视 **/
	public void scan(){
		GregorianCalendar now = new GregorianCalendar();
		int hour = now.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = now.get(GregorianCalendar.MINUTE);
		if(checkTime(now)){
			MonitorSystemParam autoExcuteModeSystemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_AUTO_EXCUTE_MODE);
			if( null != autoExcuteModeSystemParam && !"".equals(autoExcuteModeSystemParam)){
				String autoExcuteMode = autoExcuteModeSystemParam.getValue();
				if("0".equals(autoExcuteMode)){//循环执行
					int pingLoopGap = 10;
					MonitorSystemParam pingLoopGapSystemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_LOOP_GAP);
					if( null != pingLoopGapSystemParam && !"".equals(pingLoopGapSystemParam.getValue())){
						pingLoopGap = Integer.parseInt( pingLoopGapSystemParam.getValue());
						if (minute % pingLoopGap == 0 || 
								(minute == 0 && (hour * 60)%pingLoopGap == 0)) {
							// 循环ping
							pingScan.scan(pingLoopGap);
						}
					}
				}else {//定时执行
					pingTimePointList = monitorPingTimePointService.getAll();
					for (MonitorPingTimePoint ptp : pingTimePointList) {
						Calendar c1 = Calendar.getInstance();
						c1.setTime(ptp.getTimePoint());
						if (hour == c1.get(Calendar.HOUR_OF_DAY) 
								&& minute == c1.get(Calendar.MINUTE)) {
							// 定时ping	
							pingScan.scan(-1);
						}
					}
				}
			}
		}
	}
	/**
	 * 检查是否自动执行，是否符合日期、时间条件
	 * @param now
	 * @return
	 */
	public boolean checkTime(GregorianCalendar now){
		boolean fit =false;
		MonitorSystemParam systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_EXCUTE_MODE);
		if( null != systemParam && !"".equals(systemParam.getValue())){
			String excuteMode = systemParam.getValue();
			if(null != excuteMode && !"".equals(excuteMode) && "1".equals(excuteMode)){//自动执行
				
					String pingDayInWeek = "";
					int pingStartInDay = 0,pingEndInDay = 0;
					MonitorSystemParam pingDayInWeekSystemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_DAY_IN_WEEK);
					if( null != pingDayInWeekSystemParam && !"".equals(pingDayInWeekSystemParam.getValue())){
						pingDayInWeek = pingDayInWeekSystemParam.getValue();
					}
					MonitorSystemParam pingStartInDaySystemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_START_TIME_OF_DAY);
					if( null != pingStartInDaySystemParam && !"".equals(pingStartInDaySystemParam.getValue())){
						pingStartInDay = Integer.parseInt(pingStartInDaySystemParam.getValue());
					}
					MonitorSystemParam pingEndInDaySystemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_END_TIME_OF_DAY);
					if( null != pingEndInDaySystemParam && !"".equals(pingEndInDaySystemParam.getValue())){
						pingEndInDay = Integer.parseInt(pingEndInDaySystemParam.getValue());
					}
					int dayOfWeek = now.get(GregorianCalendar.DAY_OF_WEEK);
					int _hour = now.get(GregorianCalendar.HOUR_OF_DAY);
					int _minute = now.get(GregorianCalendar.MINUTE);
					int _second = now.get(GregorianCalendar.SECOND);
					int time = 1000 * ((_hour - 8) * 3600 + _minute * 60 + _second);
					dayOfWeek = (dayOfWeek + 7) % 7;
					if (pingDayInWeek.indexOf(String.valueOf(dayOfWeek)) >= 0){
						if ((pingStartInDay == 0 && pingEndInDay == 0) ||
								(_hour >= pingStartInDay && _hour <= pingEndInDay)) {
							fit = true;
							
						}
						
					}
					
				
			}
		}
		return fit;
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public MonitorPingTimePointService getMonitorPingTimePointService() {
		return monitorPingTimePointService;
	}

	public void setMonitorPingTimePointService(
			MonitorPingTimePointService monitorPingTimePointService) {
		this.monitorPingTimePointService = monitorPingTimePointService;
	}

	public List<MonitorPingTimePoint> getPingTimePointList() {
		return pingTimePointList;
	}

	public void setPingTimePointList(List<MonitorPingTimePoint> pingTimePointList) {
		this.pingTimePointList = pingTimePointList;
	}
	public PingScan getPingScan() {
		return pingScan;
	}
	public void setPingScan(PingScan pingScan) {
		this.pingScan = pingScan;
	}
	
	
}
