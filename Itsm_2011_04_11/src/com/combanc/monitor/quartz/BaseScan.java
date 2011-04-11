package com.combanc.monitor.quartz;

import java.text.ParseException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.combanc.monitor.algorithm.ArpModeScan;
import com.combanc.monitor.dao.MonitorSystemParamDAO;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorSystemParamService;

public  class BaseScan implements ApplicationContextAware{
	
	/** 扫描间隔参数Code **/
	protected String paramCode;
	/** 默认扫描间隔时间表达式 **/
	protected String defaultCronExpression;
	/** 触发器名称 **/
	protected String cronTriggerName;
	
	protected ApplicationContext ctx;
	
	protected Scheduler scheduler;
	
	protected MonitorSystemParamService monitorSystemParamService;
	
	public BaseScan (){

	}
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
		
	}
	/** 定时器动态设置定时时间 **/
	public void reScheduleJob(){
        String dbCronExpression = getCronExpressionFromDB();
        this.reScheduleJobTimer(dbCronExpression);
	}
	
	/**去读数据库，拿到相应的dbCronExpression**/
	public String getCronExpressionFromDB() {
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(paramCode);
		if(null != param ){
			if(!"".equals(param.getHighValue())){
				return param.getHighValue();
			}else{
				param.setHighValue(defaultCronExpression);
				monitorSystemParamService.update(param);
				return defaultCronExpression;
			}
		} else {
			return defaultCronExpression;
		}
	}
	/**
	 * 定时器动态设置定时时间
	 * @param dbCronExpression 时间表达式
	 */
	public void reScheduleJobTimer(String dbCronExpression){
		CronTriggerBean trigger;
		try {
			trigger = (CronTriggerBean) ((Scheduler) scheduler).getTrigger(cronTriggerName, Scheduler.DEFAULT_GROUP);
			String originConExpression = trigger.getCronExpression();
		    // 判断从DB中取得的任务时间(dbCronExpression)和现在的quartz线程中的任务时间(originConExpression)是否相等
		    // 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob
	        if(!originConExpression.equalsIgnoreCase(dbCronExpression)){
	            try {
					trigger.setCronExpression(dbCronExpression);
					 ((Scheduler) scheduler).rescheduleJob(cronTriggerName, Scheduler.DEFAULT_GROUP, trigger);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        }
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	public void scan(){
		scheduler = (Scheduler) ctx.getBean("schedulerFactory");
		reScheduleJob();
		startScan();
	}
	public  void startScan(){
		
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}
	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}
	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getDefaultCronExpression() {
		return defaultCronExpression;
	}

	public void setDefaultCronExpression(String defaultCronExpression) {
		this.defaultCronExpression = defaultCronExpression;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	public String getCronTriggerName() {
		return cronTriggerName;
	}
	public void setCronTriggerName(String cronTriggerName) {
		this.cronTriggerName = cronTriggerName;
	}

	
	

}
