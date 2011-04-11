package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.DeviceFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:设备故障报警分析器
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
public class DeviceFaultParser extends BaseParser {
	
	/** 异常设备列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）**/
	private List<DeviceFault> deviceFaultList = null;
	

	 
	/**遍历设备异常列表，按照设备指定的报警策略，给指定的人发送报警信息**/
	public void checkAlert() {
		this.init();
		// 若设备异常列表、设备列表或者用户列表为空，则直接返回
		if( null == deviceFaultList || deviceFaultList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		
		
		// 构造针对每个用户的短信报警信息和邮件报警信息
		this.setUserMsg("设备类报警");
		
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<deviceFaultList.size(); i++ ){
			DeviceFault df = deviceFaultList.get(i);
			int deviceId = df.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				deviceFaultList.remove(i);
				i--;
				continue;
			}
			
			// 如果没有给该设备绑定报警策略
			if( null == dv.getMonitorAlertPolicy()  ){
				// 如果出错重试次数达到默认次数
				if( df.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !df.isPaneAlerted() ){
						df.setPaneAlerted(true);
						newDeviceAlert(dv);		// 在报警栏新建一条报警记录
					} else {
						updateDeviceAlert(dv);	// 更新对应的报警记录
					}
				}
				// 如果出错次数恢复为0，说明设备恢复正常
				else if( df.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( df.isPaneAlerted() ){
						newDeviceRestore(dv);	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					deviceFaultList.remove(i);
					i--;
				}
				continue;
			}// 如果没有给该设备绑定报警策略 if end
			
			// 接下来是设备绑定报警策略
			// 检查是否应该，发出铃声报警
			if( df.getFaultCount() > 0 &&  "1".equals(this.soundSwitch) 
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), 
						MainConstants.isDevice(dv.getMonitorDeviceType().getCode()) ? 
								MainConstants.SALERT_DEVICE : MainConstants.SALERT_SERVER) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			} // 检查是否应该，发出铃声报警 if end 
			
			// 如果计数器的值大于等于“重试次数限制”
			if( df.getFaultCount() >= (int)dv.getMonitorAlertPolicy().getErrorRetry() ){
				
				// 插入到或者更新报警表
				if( !df.isPaneAlerted() ){
					df.setPaneAlerted(true);
					newDeviceAlert(dv);		//  新建一条报警记录
				} else {
					updateDeviceAlert(dv);	// 更新对应的报警记录
				}
				
				// 如果没有报过警，则报警
				if( !df.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "";
					int alert = 0;
					if( MainConstants.isDevice(dv.getMonitorDeviceType().getCode()) ){
						title = "网络设备无反应";
						alert = MainConstants.SALERT_DEVICE;
					} else if(MainConstants.isServer(dv.getMonitorDeviceType().getCode())){
						title = "服务器无反应";
						alert = MainConstants.SALERT_SERVER;
					}
					String msg = "" + dv.getIp() + "(" 
								+ dv.getName() + ")，" + "设备类型：" 
								+ (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName())
								+ "。\n";
					
					this.setAlertMsg(dv.getMonitorAlertPolicy(),alert, title, msg);	
					
				}
			} else if( df.getFaultCount() == 0 ){// 如果出错次数恢复为0，说明设备恢复正常
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( df.isPaneAlerted() ){
					// 报警栏显示恢复信息
					newDeviceRestore(dv);	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( df.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "";
					int alert = 0;
					if( MainConstants.isDevice(dv.getMonitorDeviceType().getCode()) ){
						title = "网络设备恢复正常";
						alert = MainConstants.SALERT_DEVICE_RESTORE;
					} else if(MainConstants.isServer(dv.getMonitorDeviceType().getCode())){
						title = "服务器恢复正常";
						alert = MainConstants.SALERT_SERVER_RESTORE;
					}
					String msg = "" + dv.getIp() + "(" 
								+ dv.getName() + ")，" + "设备类型：" 
								+ (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName())
								+ "。\n";
					
					this.setAlertMsg(dv.getMonitorAlertPolicy(),alert, title, msg);	
				}
				
				// 从异常列表中删除
				deviceFaultList.remove(i);
				i--;
				} // else if end 
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
	  * 添加报警记录
	  * @param dv 
	 */
	private void newDeviceAlert(MonitorDevice dv) {
		if( null == dv  )
			return;
		MonitorAlert po = new MonitorAlert();
		int type =( null == dv.getMonitorDeviceType()?-1:dv.getMonitorDeviceType().getCode());
		if (MainConstants.isDevice(type)) {
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_DEVICE));
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		} else if(MainConstants.DEVICE_SERVER==type){
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_SERVER));
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		}
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		po.setCount(1);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	/**
	 * 更新报警记录
	 * @param dv
	 */
	private void updateDeviceAlert(MonitorDevice dv) {
		if( null == dv  )
			return;
		try {
			String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code in( "+MainConstants.SALERT_DEVICE+","+MainConstants.SALERT_SERVER+")"
						+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
						+" and ip = '"+dv.getIp()+"'  order by id desc";
			MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
		 
			if( null != alert){
				alert.setCount(alert.getCount()+1);
				alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				monitorAlertService.update(alert);
			}else{
				newDeviceAlert(dv);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 添加报警恢复记录
	 * @param dv
	 */
	private void newDeviceRestore(MonitorDevice dv) {
		if( dv == null )
			return;
		MonitorAlert po = new MonitorAlert();
		int type =( null == dv.getMonitorDeviceType()?-1:dv.getMonitorDeviceType().getCode());
		 
		if (MainConstants.isDevice(type)) {
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_DEVICE_RESTORE));
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		} else {
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_SERVER_RESTORE));
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		}
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		po.setCount(1);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	public List<DeviceFault> getDeviceFaultList() {
		return deviceFaultList;
	}

	public void setDeviceFaultList(List<DeviceFault> deviceFaultList) {
		this.deviceFaultList = deviceFaultList;
	}

	 
}
