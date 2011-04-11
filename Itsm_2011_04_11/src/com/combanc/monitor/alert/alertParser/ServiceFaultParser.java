package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.ServiceFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:服务无响应报警分析器
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

public class ServiceFaultParser  extends BaseParser{

	// 服务异常列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<ServiceFault> serviceFaultList = null;
	
	/**遍历服务异常列表，按照设备指定的报警策略，给指定的人发送报警信息**/
	public void checkAlert() {
		this.init();
		// 若设备异常列表、设备列表或者用户列表为空，则直接返回
		if( null == serviceFaultList || serviceFaultList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		// 构造针对每个用户的短信报警信息和邮件报警信息
		this.setUserMsg("服务类报警");
		
		
		for( int i=0; i<serviceFaultList.size(); i++ ){
			ServiceFault sf = serviceFaultList.get(i);
			int deviceId = sf.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				serviceFaultList.remove(i);
				i--;
				continue;
			} else {
				String sNote1 = dv.getNote1();
				String sNote2 = dv.getNote2();
				String sNote3 = dv.getNote3();
				String sNote4 = dv.getNote4();
				// 若服务名在备注中找不到，说明服务删除，把异常列表中的该记录也清除
				if( !sf.getServiceName().equals(sNote1) &&
						!sf.getServiceName().equals(sNote2) &&
						!sf.getServiceName().equals(sNote3) &&
						!sf.getServiceName().equals(sNote4) ){
					serviceFaultList.remove(i);
					i--;
					continue;
				}
			}
			
			// 如果没有给该设备绑定报警策略
			if( dv.getMonitorAlertPolicy() == null ){
				// 如果出错重试次数达到默认次数
				if( sf.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !sf.isPaneAlerted() ){
						sf.setPaneAlerted(true);
						// 在报警栏新建一条报警记录
						newServiceAlert(dv, sf.getServicePort(), sf.getServiceName());
					} else {
						// 更新对应的报警记录
						updateServiceAlert(dv, sf.getServicePort(), sf.getServiceName());
					}
				}
				// 如果出错次数恢复为0，说明设备恢复正常
				else if( sf.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( sf.isPaneAlerted() ){
						// 在报警栏新建一条报警恢复记录
						newServiceRestore(dv, sf.getServicePort(), sf.getServiceName());
					}
					// 从异常列表中删除
					serviceFaultList.remove(i);
					i--;
				}
				continue;
			}
			
			// 检查是否应该，发出铃声报警
			if( sf.getFaultCount() > 0 && "1".equals(this.soundSwitch)
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_SERVICE) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于“重试次数限制”,且没有报过警，则报警
			if( sf.getFaultCount() >= (int)dv.getMonitorAlertPolicy().getErrorRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !sf.isPaneAlerted() ){
					sf.setPaneAlerted(true);
					// 在报警栏新建一条报警记录
					newServiceAlert(dv, sf.getServicePort(), sf.getServiceName());
				} else {
					// 更新对应的报警记录
					updateServiceAlert(dv, sf.getServicePort(), sf.getServiceName());
				}
				// 如果没有报过警，则报警
				if( !sf.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "服务无反应";
					String msg =  sf.getServiceName() + "(端口：" 
									+ sf.getServicePort()+ ")，" + dv.getIp() + "(" 
									+ dv.getName() + ")，" + "设备类型：" 
									+ ( null == dv.getMonitorDeviceType() ? "未确定" : dv.getMonitorDeviceType().getName()) + "，"
									+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_SERVICE, title, msg);
				}
			}// 如果出错次数恢复为0，说明设备恢复正常
			else if( sf.getFaultCount() == 0 ){
					// 如果已经加入到过报警栏，报警栏记录恢复信息
					if( sf.isPaneAlerted() ){
						// 在报警栏新建一条报警恢复记录
						newServiceRestore(dv, sf.getServicePort(), sf.getServiceName());
					}
					// 如果已经报过警，则发出恢复报警
					if( sf.isAlerted() ){
						//短信和邮件的标题、内容
						String title = "服务恢复正常";
						String msg =  sf.getServiceName() + "(端口：" 
										+ sf.getServicePort()+ ")，" + dv.getIp() + "(" 
										+ dv.getName() + ")，" + "设备类型：" 
										+ ( null == dv.getMonitorDeviceType() ? "未确定" : dv.getMonitorDeviceType().getName()) + "，"
										+ "。";
						this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_SERVICE_RESTORE, title, msg);
					}
					// 从异常列表中删除
					serviceFaultList.remove(i);
					i--;
			} // else if end
		} //  for end
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
	 * @param dv 设备对象（正确情况下应为服务器）
	 * @param servicePort 服务的端口号
	 * @param serviceName 服务的名称
	 */
	private void newServiceAlert(MonitorDevice dv, int servicePort, String serviceName) {
		if( null == dv  || servicePort <= 0	|| null == serviceName || "".equals(serviceName) )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_SERVICE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		po.setOld(dv.getName() + "@" + serviceName);
		po.setIp(dv.getIp()+ "@" + servicePort);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	/**
	 *  更新报警记录
	 * @param dv 设备对象（正确情况下应为服务器）
	 * @param servicePort 服务的端口号
	 * @param serviceName 服务的名称
	 */
	private void updateServiceAlert(MonitorDevice dv, int servicePort, String serviceName) {
		if( null == dv  || servicePort <= 0	|| null == serviceName || "".equals(serviceName) )
			return;
		
		try {
			String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_SERVICE
						+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
						+" and ip = '"+dv.getIp() + "@" + servicePort+"'" 
						+" and old = '"+dv.getName() + "@" + serviceName+"' order by id desc" ;
			MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			if( null != alert){
				alert.setCount(alert.getCount()+1);
				alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				monitorAlertService.update(alert);
			}else{
				newServiceAlert(dv,servicePort,serviceName);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  添加报警恢复记录
	 * @param dv 设备对象（正确情况下应为服务器）
	 * @param servicePort 服务的端口号
	 * @param serviceName 服务的名称
	 */
	private void newServiceRestore(MonitorDevice dv, int servicePort, String serviceName) {
		if( null == dv  || servicePort <= 0	|| null == serviceName || "".equals(serviceName) )
			return;
		
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_SERVICE_RESTORE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		po.setOld(dv.getName() + "@" + serviceName);
		po.setIp(dv.getIp()+ "@" + servicePort);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	public List<ServiceFault> getServiceFaultList() {
		return serviceFaultList;
	}

	public void setServiceFaultList(List<ServiceFault> serviceFaultList) {
		this.serviceFaultList = serviceFaultList;
	}
	
	
}
