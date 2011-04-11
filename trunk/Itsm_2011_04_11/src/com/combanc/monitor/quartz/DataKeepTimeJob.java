package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.util.Date;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorCpuDataHourService;
import com.combanc.monitor.service.MonitorCpuDataService;
import com.combanc.monitor.service.MonitorRegularDataHourService;
import com.combanc.monitor.service.MonitorRegularDataService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.ReportPubData;

/**
 * <p>
 * Title:数据保存时长处理
 * </p>
 * <p>
 * Description:处理报警超过最大记录条数或者流量及CPU数据超过保存时长
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author Combanc
 * @version 1.0
 */
public class DataKeepTimeJob {
	
	final static long ONE_DAY_LONG		= 1000*60*60*24;
	/**报警数据保存记录数 **/
	private int alertDataKeep = SystemParamConstants.SYSTEM_ALERT_DATA_KEEP_DEFUALT_VALUE;
	/** 流量及CPU采集数据分钟保存时长 **/
	private int minuteDataKeep= SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP_DEFUALT_VALUE;
	/** 流量及CPU采集数据小时保存时长 **/
	private int hourDataKeep= SystemParamConstants.SYSTEM_HOUR_DATA_KEEP_DEFUALT_VALUE;
	
	private Timestamp endTimeDelMinute	= null;	// 删除分钟数据的末尾时间戳
	private Timestamp endTimeDelHour	= null;	// 删除小时数据的末尾时间戳
	
	private MonitorSystemParamService monitorSystemParamService;
	
	
	/** 初始化 **/
	private void init(){
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ALERT_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertDataKeep = Integer.parseInt(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			hourDataKeep = Integer.parseInt(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			minuteDataKeep = Integer.parseInt(param.getValue());
		
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			ReportPubData.MINUTE_TABLE_LIFE_TIME = Integer.parseInt(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			ReportPubData.HOUR_TABLE_LIFE_TIME = Integer.parseInt(param.getValue());
		// 创建删除分钟数据的时间区间
		Date date0 = new Date();
		date0.setTime( (new Date()).getTime() - ONE_DAY_LONG * ReportPubData.MINUTE_TABLE_LIFE_TIME );
		endTimeDelMinute = new Timestamp( date0.getTime() );
		toDayStart(endTimeDelMinute);		// 转到23:59:59.999999999
		// 创建删除小时数据的时间区间
		Date date1 = new Date();
		date1.setTime( (new Date()).getTime() - ONE_DAY_LONG * ReportPubData.HOUR_TABLE_LIFE_TIME );
		endTimeDelHour = new Timestamp( date1.getTime() );
		toDayStart(endTimeDelHour);		// 转到23:59:59.999999999
	}
	// 转换到当天的0点
	void toDayStart(Timestamp ts) {
		ts.setHours(0);
		ts.setMinutes(0);
		ts.setSeconds(0);
		ts.setNanos(0);
	}
	/**
	 * 转换到当天的23:59:59.999
	 * @param ts
	 */
	@SuppressWarnings("deprecation")
	private void toDayEnd(Timestamp   ts) {
		ts.setHours(23);
		ts.setMinutes(59);
		ts.setSeconds(59);
		ts.setNanos(999999999);
	}
	
	/** 处理数据 **/
	public void process(){
		init();
		deleteAlertData();
		deleteMinuteCpuData();
		deleteHourCpuData();
		deleteMinuteRegularData();
		deleteHourRegularData();
	}
	/**
	 *  删除超过指定条数的旧的报警记录
	 */
	private void deleteAlertData(){
		System.out.println("正在删除过期的CPU占用率历史数据...");
		try{
			monitorSystemParamService.deleteBefore("monitor_alert", "id", alertDataKeep);
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
	}
	
	/**
	 *  删除设定天数前的Cpudata分钟数据
	 */
	private void deleteMinuteCpuData(){
		System.out.println("正在删除过期的CPU占用率历史数据...");
		try{
			monitorSystemParamService.deleteBefore("monitor_cpu_data", "GATHER_TIME", endTimeDelMinute);
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
	}
	
	/**
	 *  删除设定天数前的Cpudata小时数据
	 */
	private void deleteHourCpuData(){
		System.out.println("正在删除过期的CPU占用率历史数据...");
		try{
			monitorSystemParamService.deleteBefore("monitor_cpu_data_hour", "GATHER_TIME", endTimeDelHour);
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
	}
	/**
	 *  删除设定天数前的RegularData分钟数据
	 */
	private void deleteMinuteRegularData(){
		String sMsg = "正在删除过期的端口流量历史数据...";
		try{
			monitorSystemParamService.deleteBefore("monitor_regular_data", "GATHER_TIME", endTimeDelMinute);
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
	}
	/**
	 *  删除设定天数前的RegularData小时数据
	 */
	private void deleteHourRegularData(){
		String sMsg = "正在删除过期的端口流量历史数据...";
		try{
			monitorSystemParamService.deleteBefore("monitor_regular_data_hour", "GATHER_TIME", endTimeDelHour);
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
	}
	
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}
	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	
	
	
	

}
