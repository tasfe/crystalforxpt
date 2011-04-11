package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.TempHight;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:温度超阈值报警分析器
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

public class TempHightParser extends BaseParser{

	// 温度异常设备列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<TempHight> tempHightList = null;
	
	/** 遍历设备温度异常列表，按照设备指定的报警策略，给指定的人发送报警信息 **/
	public void checkAlert() {
		
		this.init();
		deviceList = monitorDeviceService.findByDeviceType(MainConstants.DEVICE_SERVER);
		
		// 温度异常设备列表、设备列表或者用户列表为空，则直接返回
		if( null == tempHightList || tempHightList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		
		// 构造针对每个用户的短信报警信息和邮件报警信息
		this.setUserMsg("温度超阈值报警");
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<tempHightList.size(); i++ ){
			TempHight th = tempHightList.get(i);
			int deviceId = th.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				tempHightList.remove(i);
				i--;
				continue;
			}
			// 如果没有给该设备绑定报警策略
			if( dv.getMonitorAlertPolicy() == null ){
				// 如果超阈值重试次数达到默认次数
				if( th.getHightCount() >= MainConstants.DEFAULT_TEMP_RETRY_COUNT ){
					// 记录到报警栏
					if( !th.isPaneAlerted() ){
						th.setPaneAlerted(true);
						newTempAlert(dv, th.getCurrTemp());	// 在报警栏新建一条报警记录
					} else {
						updateTempAlert(dv, th.getCurrTemp());// 更新对应的报警记录
					}
				}
				// 如果超阈值次数恢复为0，说明设备恢复正常
				else if( th.getHightCount() == 0 ){
					// 报警栏显示恢复信息
					if( th.isPaneAlerted() ){
						newTempRestore(dv, th.getCurrTemp());	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					tempHightList.remove(i);
					i--;
				}
				continue;
			}
			
			// 检查是否应该，发出铃声报警
			if( th.getHightCount() > 0 && "1".equals(this.soundSwitch)
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_TEMP_OVERLOAD) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于“重试次数限制”
			if( th.getHightCount() >= (int)dv.getMonitorAlertPolicy().getTempRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !th.isPaneAlerted() ){
					th.setPaneAlerted(true);
					newTempAlert(dv, th.getCurrTemp());	// 在报警栏新建一条报警记录
				} else {
					updateTempAlert(dv, th.getCurrTemp());// 更新对应的报警记录
				}
				// 如果没有报过警，则报警
				if( !th.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "温度超过阈值";
					String msg ="当前温度：" + th.getCurrTemp() + "("
								+ "阈值：" + tempLimen + ")，" 
								+ dv.getIp() + "(" + dv.getName() + ")，" 
								+ "设备类型：温度传感器" 
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_TEMP_OVERLOAD, title, msg);
				}
			}else if( th.getHightCount() == 0 ){
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( th.isPaneAlerted() ){
					// 报警栏显示恢复信息
					newTempRestore(dv, th.getCurrTemp());	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( th.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "温度恢复正常";
					String msg ="当前温度：" + th.getCurrTemp() + "("
								+ "阈值：" + tempLimen + ")，" 
								+ dv.getIp() + "(" + dv.getName() + ")，" 
								+ "设备类型：温度传感器" 
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_TEMP_RESTORE, title, msg);
				}
			}
			// 从异常列表中删除
			tempHightList.remove(i);
			i--;
		}
		
		// 短信报警
		if( "1".equals(this.mobileSwitch) ){
			sendMobileMsg();
		}
		// 邮件报警
		if( "1".equals(this.emailSwitch) ) {
			sendEmail();
		}
	}
		
	/**
	 *  添加报警记录
	 * @param dv
	 * @param value
	 */
	private void newTempAlert(MonitorDevice dv, int value) {
		if( null == dv )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_TEMP_OVERLOAD));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_LIMEN));
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		if (value > -30) {
			po.setValue("" + value);
		}
		if (tempLimen > 0) {
			po.setLimen("" +tempLimen);
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}	
		
	/**
	 *  更新报警记录
	 * @param dv
	 * @param value
	 */
	private void updateTempAlert(MonitorDevice dv, int value) {
		if( null == dv )
			return;
		try {
			String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_TEMP_OVERLOAD
						+" and monitorAlertType.code="+MainConstants.ALERT_LIMEN
						+" and ip = '"+dv.getIp()+"' order by id desc" ;
			MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 
			if( null != alert){
				if (value > -30) {
					 alert.setValue("" +value);
				 }
				 if (tempLimen > 0) {
					 alert.setLimen("" +tempLimen);
				 }
				 alert.setCount(alert.getCount()+1);
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			}else{
				newTempAlert(dv,value);
			}
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加报警恢复记录
	private void newTempRestore(MonitorDevice dv, int value) {
		if( null == dv )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_TEMP_RESTORE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_LIMEN));
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		 
		if (value > -30) {
			po.setValue("" + value);
		}
		if (tempLimen > 0) {
			po.setLimen("" +tempLimen);
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	public List<TempHight> getTempHightList() {
		return tempHightList;
	}

	public void setTempHightList(List<TempHight> tempHightList) {
		this.tempHightList = tempHightList;
	}
	
	
	
}
