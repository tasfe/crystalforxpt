package com.combanc.monitor.service;

import java.text.ParseException;

import org.quartz.SchedulerException;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.dao.MonitorSystemParamDAO;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.quartz.ScanLoopTimer;
import com.combanc.monitor.vo.systemParam.AlertSystem;
import com.combanc.monitor.vo.systemParam.ComputerScan;
import com.combanc.monitor.vo.systemParam.DataKeepTime;
import com.combanc.monitor.vo.systemParam.FaultScan;
import com.combanc.monitor.vo.systemParam.FluxScan;
import com.combanc.monitor.vo.systemParam.OtherSetting;
import com.combanc.monitor.vo.systemParam.PingScan;
import com.combanc.monitor.vo.systemParam.TopologyScan;

public class MonitorSystemParamService extends BaseService<MonitorSystemParam, Integer>{

	private ScanLoopTimer scanLoopTimer;
	private MonitorSystemParamDAO monitorSystemParamDAO;
	/**保存计算机扫描设置**/
	public void saveComputerScan(ComputerScan computerScan){
		try {
		MonitorSystemParam scanGapTime = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SCAN_GAP_TIME);
		MonitorSystemParam scanHour0 = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SCAN_HOUR0);
		MonitorSystemParam scanHour1 = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SCAN_HOUR1);
		MonitorSystemParam scanHour2 = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SCAN_HOUR2);
		MonitorSystemParam arpNbt = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_ARP_NBT);
		MonitorSystemParam pingScan = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_SCAN);
		MonitorSystemParam ignoreError = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_IGNORE_ERROR);
		MonitorSystemParam l23Scan = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_L23_SCAN);
		MonitorSystemParam changeRefresh = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_CHANGE_REFRESH);
		MonitorSystemParam changeAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_CHANGE_ALERT);
		MonitorSystemParam newHost = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_NEW_HOST);
		MonitorSystemParam ipChangedAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_IP_CHANGED_ALERT);
		MonitorSystemParam userChangedAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_USER_CHANGED_ALERT);
		MonitorSystemParam computerNameChangedAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_COMPUTER_NAME_CHANGED_ALERT);
		MonitorSystemParam domainChangedAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_DOMAIN_CHANGED_ALERT);
		MonitorSystemParam linkChangedAlert = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_LINK_CHANGED_ALERT);

		
		if(null!=computerScan.getScanGapTime()&&!"".equals(computerScan.getScanGapTime())) {
				scanGapTime.setValue(computerScan.getScanGapTime());
				//设置定时任务时间表达式
				String cronExpression = "";
				if(!"0".equals(computerScan.getScanGapTime())){//每隔X分钟
					cronExpression = "0 0/"+computerScan.getScanGapTime() +" * * * ?";
//					if(!scanGapTime.getHighValue().equals(cronExpression)){
//						scanLoopTimer.reScheduleJobTimer(cronExpression);
//					}
					scanGapTime.setHighValue(cronExpression);
				} else {
					//定时
					String hours="";
					if(null!=computerScan.getScanHour0()&&!"".equals(computerScan.getScanHour0())){
						scanHour0.setValue(computerScan.getScanHour0());
						if(!"-1".equals(computerScan.getScanHour0()))
							hours=hours+computerScan.getScanHour0()+",";
					}
					if(null!=computerScan.getScanHour1()&&!"".equals(computerScan.getScanHour1())){
						scanHour1.setValue(computerScan.getScanHour1());
						if(!"-1".equals(computerScan.getScanHour1()))
							hours=hours+computerScan.getScanHour1()+",";
					}
					if(null!=computerScan.getScanHour2()&&!"".equals(computerScan.getScanHour2())){
						scanHour2.setValue(computerScan.getScanHour2());
						if(!"-1".equals(computerScan.getScanHour2()))
							hours=hours+computerScan.getScanHour2()+",";
					} 
					 
					if(hours.length()>0){
						hours=hours.substring(0, hours.length()-1);
						cronExpression = "0 0 "+hours +" * * ?";
					} else{
						cronExpression="";
					}
//					if(!scanGapTime.getHighValue().equals(cronExpression)){
//						scanLoopTimer.reScheduleJobTimer(cronExpression);
//					}
						
					scanGapTime.setHighValue(cronExpression);
					
				}
			 
			
			
		}

		
		arpNbt.setValue(computerScan.getArpNbt()==null?"0":computerScan.getArpNbt());
		pingScan.setValue(computerScan.getPingScan()==null?"0":computerScan.getPingScan());
		ignoreError.setValue(computerScan.getIgnoreError()==null?"0":computerScan.getIgnoreError());
		l23Scan.setValue(computerScan.getL23Scan()==null?"":computerScan.getL23Scan());
		changeRefresh.setValue(computerScan.getChangeRefresh()==null?"":computerScan.getChangeRefresh());
		changeAlert.setValue(computerScan.getChangeAlert()==null?"":computerScan.getChangeAlert());
		newHost.setValue(computerScan.getNewHost()==null?"0":computerScan.getNewHost());
		ipChangedAlert.setValue(computerScan.getIpChangedAlert()==null?"0":computerScan.getIpChangedAlert());
		userChangedAlert.setValue(computerScan.getUserChangedAlert()==null?"0":computerScan.getUserChangedAlert());
		computerNameChangedAlert.setValue(computerScan.getComputerNameChangedAlert()==null?"0":computerScan.getComputerNameChangedAlert());
		domainChangedAlert.setValue(computerScan.getDomainChangedAlert()==null?"0":computerScan.getDomainChangedAlert());
		linkChangedAlert.setValue(computerScan.getLinkChangedAlert()==null?"0":computerScan.getLinkChangedAlert());
		
		monitorSystemParamDAO.update(scanGapTime);
		monitorSystemParamDAO.update(scanHour0);
		monitorSystemParamDAO.update(scanHour1);
		monitorSystemParamDAO.update(scanHour2);
		monitorSystemParamDAO.update(arpNbt);
		monitorSystemParamDAO.update(pingScan);
		monitorSystemParamDAO.update(ignoreError);
		monitorSystemParamDAO.update(l23Scan);
		monitorSystemParamDAO.update(changeRefresh);
		monitorSystemParamDAO.update(changeAlert);
		monitorSystemParamDAO.update(newHost);
		monitorSystemParamDAO.update(ipChangedAlert);
		monitorSystemParamDAO.update(userChangedAlert);
		monitorSystemParamDAO.update(computerNameChangedAlert);
		monitorSystemParamDAO.update(domainChangedAlert);
		monitorSystemParamDAO.update(linkChangedAlert);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
	}
	/**保存流量及CPU采集设置**/
	public void saveFluxScan(FluxScan fluxScan){
		try {
			MonitorSystemParam fluxSwitch = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_FLUX_SWITCH);
			MonitorSystemParam fluxScanGapTime = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_FLUX_SCAN_GAP_TIME);
			MonitorSystemParam fluxType = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_FLUX_TYPE);
			MonitorSystemParam minuteDataKeep = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP);
			MonitorSystemParam hourDataKeep = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP);
			fluxSwitch.setValue(fluxScan.getFluxSwitch()==null?"0":fluxScan.getFluxSwitch());
			if(null!=fluxScan.getFluxScanGapTime()&&!"".equals(fluxScan.getFluxScanGapTime())) {
				fluxScanGapTime.setValue(fluxScan.getFluxScanGapTime());
				//设置定时任务时间表达式
				String cronExpression = "0 0/"+fluxScan.getFluxScanGapTime() +" * * * ?";
				fluxScanGapTime.setHighValue(cronExpression);
			}
			fluxType.setValue(fluxScan.getFluxType()==null?"0" :fluxScan.getFluxType());
			minuteDataKeep.setValue(fluxScan.getMinuteDataKeep()==null ? String.valueOf(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP_DEFUALT_VALUE ) :fluxScan.getMinuteDataKeep());
			hourDataKeep.setValue(fluxScan.getHourDataKeep()==null?String.valueOf(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP_DEFUALT_VALUE ) :fluxScan.getHourDataKeep());

			monitorSystemParamDAO.update(fluxSwitch);
			monitorSystemParamDAO.update(fluxScanGapTime);
			monitorSystemParamDAO.update(fluxType);
			monitorSystemParamDAO.update(minuteDataKeep);
			monitorSystemParamDAO.update(hourDataKeep);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
		 
	}
	
	/**保存拓扑设置**/
	public void saveTopology(TopologyScan topologyScan){
		try {
			MonitorSystemParam pollGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_POLL_GAP);
			MonitorSystemParam maxBroadcast = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MAX_BROADCAST);
			MonitorSystemParam maxFlow = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MAX_FLOW);
			MonitorSystemParam backColor = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_BACK_COLOR);
			MonitorSystemParam backPicPath = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_BACK_PIC_PATH);
			if(null!=topologyScan.getPollGap()&&!"".equals(topologyScan.getPollGap())) {
				pollGap.setValue(topologyScan.getPollGap());
				//设置定时任务时间表达式
				String cronExpression = "0 0/" + topologyScan.getPollGap() + " * * * ?";
				pollGap.setHighValue(cronExpression);
			}
			maxBroadcast.setValue(topologyScan.getMaxBroadcast());
			maxFlow.setValue(topologyScan.getMaxFlow());
			backColor.setValue(topologyScan.getBackColor());
			if(null!=topologyScan.getBackPicPath()){
				backPicPath.setValue(topologyScan.getBackPicPath());
			}
			
			monitorSystemParamDAO.update(pollGap);
			monitorSystemParamDAO.update(maxBroadcast);
			monitorSystemParamDAO.update(maxFlow);
			monitorSystemParamDAO.update(backColor);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
		
		
	}
	
	/**保存拓扑设置**/
	public void saveFaultScan(FaultScan faultScan){
		
		try {
			MonitorSystemParam checkGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_CHECK_GAP);
			MonitorSystemParam errorGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_ERROR_GAP);
			if(null!=faultScan.getCheckGap()&&!"".equals(faultScan.getCheckGap())) {
				checkGap.setValue(faultScan.getCheckGap());
				//设置定时任务时间表达式
				String cronExpression = "0 0/" + faultScan.getCheckGap() + " * * * ?";
				checkGap.setHighValue(cronExpression);
			}
			if(null!=faultScan.getErrorGap()&&!"".equals(faultScan.getErrorGap())) {
				errorGap.setValue(faultScan.getErrorGap());
				//设置定时任务时间表达式
				String cronExpression = "0 0/" + faultScan.getErrorGap() + " * * * ?";
				errorGap.setHighValue(cronExpression);
			}
			monitorSystemParamDAO.update(checkGap);
			monitorSystemParamDAO.update(errorGap);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
		
		
	}
	/**保存系统其它设置**/
	public void saveOtherSetting(OtherSetting other){
		try {
			MonitorSystemParam topN = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_TOP_N);
			MonitorSystemParam snmpRetry = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SNMP_RETRY);
			MonitorSystemParam snmpTimeOut = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SNMP_TIME_OUT);
			topN.setValue(other.getTopN());
			snmpRetry.setValue(other.getSnmpRetry());
			snmpTimeOut.setValue(other.getSnmpTimeOut());
			monitorSystemParamDAO.update(topN);
			monitorSystemParamDAO.update(snmpRetry);
			monitorSystemParamDAO.update(snmpTimeOut);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
	}
	
	/**保存系统其它设置**/
	public void savePingScanSetting(PingScan pingScan){
		try {
			MonitorSystemParam pingNum = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_NUM);
			MonitorSystemParam pingSize = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_SIZE);
			MonitorSystemParam pingTimeout = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_TIMEOUT);
			MonitorSystemParam pingLoopGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_LOOP_GAP);
			MonitorSystemParam pingDetailLife = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_DETAIL_LIFE);
			MonitorSystemParam pingReportLife = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_REPORT_LIFE);
			MonitorSystemParam pingExcuteMode = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_EXCUTE_MODE);
			MonitorSystemParam pingIsDefineHours = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_IS_DEFINE_HOURS);
			MonitorSystemParam pingAutoExcuteMode = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_AUTO_EXCUTE_MODE);
			MonitorSystemParam pingStartTimeOfDay = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_START_TIME_OF_DAY);
			MonitorSystemParam pingEndTimeOfDay = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_END_TIME_OF_DAY);
			MonitorSystemParam pingDayInWeek = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_PING_DAY_IN_WEEK);
			if(null != pingScan.getPingNum() && !"".equals( pingScan.getPingNum())) {
				pingNum.setValue(pingScan.getPingNum());
				monitorSystemParamDAO.update(pingNum);
			}
			if(null != pingScan.getPingSize() && !"".equals( pingScan.getPingSize())) {
				pingSize.setValue(pingScan.getPingSize());
				monitorSystemParamDAO.update(pingSize);
			}
			if(null != pingScan.getPingTimeout() && !"".equals( pingScan.getPingTimeout())) {
				pingTimeout.setValue(pingScan.getPingTimeout());
				monitorSystemParamDAO.update(pingTimeout);
			}
			if(null != pingScan.getPingDetailLife() && !"".equals( pingScan.getPingDetailLife())) {
				pingDetailLife.setValue(pingScan.getPingDetailLife());
				monitorSystemParamDAO.update(pingDetailLife);
			}
			if(null != pingScan.getPingReportLife() && !"".equals( pingScan.getPingReportLife())) {
				pingReportLife.setValue(pingScan.getPingReportLife());
				monitorSystemParamDAO.update(pingReportLife);
			}
			if(null != pingScan.getPingExcuteMode() && !"".equals( pingScan.getPingExcuteMode())) {
				String  excuteMode = pingScan.getPingExcuteMode();
				pingExcuteMode.setValue(excuteMode);
				monitorSystemParamDAO.update(pingExcuteMode);
				if("0".equals(excuteMode)){//手动执行
					
				}else{//自动执行
					//星期
					String days = "";
					for(int i=0; i<pingScan.getDays().length;i++){
						days+=pingScan.getDays()[i]+",";
				    }
					days = days.length()==0 ?"*":days.substring(0, days.length()-1);
					pingDayInWeek.setValue(days);
					monitorSystemParamDAO.update(pingDayInWeek);
					//小时
					String hour = "*";
					if(null == pingScan.getIsDefinehours() ) {
						pingIsDefineHours.setValue("0");
					}else{
						pingIsDefineHours.setValue("1");
						if(null != pingScan.getPingStartTimeOfDay() && !"".equals( pingScan.getPingStartTimeOfDay())
								&&	null != pingScan.getPingEndTimeOfDay() && !"".equals( pingScan.getPingEndTimeOfDay())){
							pingStartTimeOfDay.setValue(pingScan.getPingStartTimeOfDay());
							pingEndTimeOfDay.setValue(pingScan.getPingEndTimeOfDay());
							monitorSystemParamDAO.update(pingStartTimeOfDay);
							monitorSystemParamDAO.update(pingEndTimeOfDay);
							hour = pingScan.getPingStartTimeOfDay()+"-"+pingScan.getPingEndTimeOfDay();
						}
					}
					String autoExcuteMode = pingScan.getPingAutoExcuteMode();
					pingAutoExcuteMode.setValue(autoExcuteMode);
					monitorSystemParamDAO.update(pingAutoExcuteMode);
					if("0".equals(autoExcuteMode)){//循环执行
						if(null != pingScan.getPingLoopGap() && !"".equals( pingScan.getPingLoopGap())) {
							String gap = pingScan.getPingLoopGap();
							pingLoopGap.setValue(gap);
							String cronExpression = "0 0/" + gap + " "+hour+" ? * "+days;
							pingLoopGap.setHighValue(cronExpression);
							monitorSystemParamDAO.update(pingLoopGap);
						}
						
					}else{//自动执行
						
					}
				}
				 
			}
			

			
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
	}
	
	/**保存报警设置**/
	public void saveAlertSetting(AlertSystem alertSystem){
		try {
			MonitorSystemParam emailSwitch = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_EMAIL_SWTICH);
			MonitorSystemParam smtpServer = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SMTP_SERVER);
			MonitorSystemParam mailSender = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MAIL_SENDER);
			MonitorSystemParam mailSenderPwd = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MAIL_SENDER_PWD);
			MonitorSystemParam mobileSwitch = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MOBILE_SWTICH);
			MonitorSystemParam smsSn = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SMS_SN);
			MonitorSystemParam soundSwitch = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_SOUND_SWTICH);
			MonitorSystemParam arpLimen = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_ARP_LIMEN);
			MonitorSystemParam limitToAddLevel = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_LIMIT_TO_ADD_LEVEL);
			MonitorSystemParam checkGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_CHECK_GAP);
			MonitorSystemParam errorGap = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_ERROR_GAP);
			
			emailSwitch.setValue(alertSystem.getEmailSwitch());
			smtpServer.setValue(alertSystem.getSmtpServer());
			mailSender.setValue(alertSystem.getMailSender());
			mailSenderPwd.setValue(alertSystem.getMailSenderPwd());
			mobileSwitch.setValue(alertSystem.getMobileSwitch());
			smsSn.setValue(alertSystem.getSmsSn());
			soundSwitch.setValue(alertSystem.getSoundSwitch());
			arpLimen.setValue(alertSystem.getArpLimen());
			limitToAddLevel.setValue(alertSystem.getLimitToAddLevel());
			checkGap.setValue(alertSystem.getCheckGap());
			errorGap.setValue(alertSystem.getErrorGap());
			monitorSystemParamDAO.update(emailSwitch);
			monitorSystemParamDAO.update(smtpServer);
			monitorSystemParamDAO.update(mailSender);
			monitorSystemParamDAO.update(mailSenderPwd);
			monitorSystemParamDAO.update(mobileSwitch);
			monitorSystemParamDAO.update(smsSn);
			monitorSystemParamDAO.update(soundSwitch);
			monitorSystemParamDAO.update(arpLimen);
			monitorSystemParamDAO.update(limitToAddLevel);
			monitorSystemParamDAO.update(checkGap);
			monitorSystemParamDAO.update(errorGap);
			 
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
	}
	
	/**数据保存时间设置**/
	public void saveDataKeepTime(DataKeepTime dataKeepTime){
		try {
			MonitorSystemParam alertDataKeep = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_ALERT_DATA_KEEP);
			MonitorSystemParam minuteDataKeep = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP);
			MonitorSystemParam hourDataKeep = monitorSystemParamDAO.findById(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP);
			
			alertDataKeep.setValue(dataKeepTime.getAlertDataKeep() == null ? String.valueOf(SystemParamConstants.SYSTEM_ALERT_DATA_KEEP ):dataKeepTime.getAlertDataKeep());
			minuteDataKeep.setValue(dataKeepTime.getMinuteDataKeep()==null ? String.valueOf(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP_DEFUALT_VALUE ) :dataKeepTime.getMinuteDataKeep());
			hourDataKeep.setValue(dataKeepTime.getHourDataKeep()==null?String.valueOf(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP_DEFUALT_VALUE ) :dataKeepTime.getHourDataKeep());

			monitorSystemParamDAO.update(alertDataKeep);
			monitorSystemParamDAO.update(minuteDataKeep);
			monitorSystemParamDAO.update(hourDataKeep);
		}catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
			 
		}
		 
	}
	
	public MonitorSystemParamDAO getMonitorSystemParamDAO() {
		return monitorSystemParamDAO;
	}

	public void setMonitorSystemParamDAO(MonitorSystemParamDAO monitorSystemParamDAO) {
		super.setDao(monitorSystemParamDAO);
		this.monitorSystemParamDAO = monitorSystemParamDAO;
	}
	
	public MonitorSystemParam findByParamCode(String paramCode){
		return monitorSystemParamDAO.findById(paramCode);
	}
	public ScanLoopTimer getScanLoopTimer() {
		return scanLoopTimer;
	}
	public void setScanLoopTimer(ScanLoopTimer scanLoopTimer) {
		this.scanLoopTimer = scanLoopTimer;
	}
	
	public boolean getBooleanParam(String code){
		MonitorSystemParam param = this.findByParamCode(code);
		if(null != param && null != param.getValue() && !"".equals(param.getValue())){
			if("1".equals(param.getValue()))
				return true;
			else
				return false;
		}else {
			return false;
		}
		
	}
	
}
