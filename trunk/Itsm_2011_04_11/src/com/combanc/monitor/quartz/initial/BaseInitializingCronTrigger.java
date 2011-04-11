package com.combanc.monitor.quartz.initial;

import java.io.Serializable;
import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerBean;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.dao.MonitorSystemParamDAO;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorSystemParamService;
/**
 * <p>
 * Title: Quartz 在Spring中动态设置cronExpression
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
public   class BaseInitializingCronTrigger extends CronTriggerBean implements Serializable{
		/** 扫描间隔参数Code **/
		protected String paramCode;
		/** 默认扫描间隔时间表达式 **/
		protected String defaultCronExpression;
	
		protected  MonitorSystemParamDAO monitorSystemParamDAO;

		
		public MonitorSystemParamDAO getMonitorSystemParamDAO() {
			return monitorSystemParamDAO;
		}

		public void setMonitorSystemParamDAO(MonitorSystemParamDAO monitorSystemParamDAO) {
			this.monitorSystemParamDAO = monitorSystemParamDAO;
			String cronExpression = getCronExpressionFromDB ();   
			 if(null != cronExpression){
				try {
					 
					setCronExpression(cronExpression);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}     
			 }
		}

		 
		 
		/** 从数据库中获得dbCronExpression的具体代码 **/
		public  String getCronExpressionFromDB(){
			MonitorSystemParam param = monitorSystemParamDAO.findById(paramCode);
			if(null != param ){
				if(!"".equals(param.getHighValue())){
					return param.getHighValue();
				}else{
					param.setHighValue(defaultCronExpression);
					monitorSystemParamDAO.update(param);
					return defaultCronExpression;
				}
			} else {
				return defaultCronExpression;
			}
		}
}
