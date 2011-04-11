package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.ProcessFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:服务器进程报警分析器
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

public class ProcessFaultParser extends BaseParser{

	// 关键进程错误列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<ProcessFault> processFaultList = null;
	
	/** 遍历设备异常列表，按照设备指定的报警策略，给指定的人发送报警信息 **/
	public void checkAlert() {
		this.init();
		// 若设备异常列表、设备列表或者用户列表为空，则直接返回
		if( null == processFaultList || processFaultList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		
		
		// 构造针对每个用户的短信报警信息和邮件报警信息
		this.setUserMsg("关键进程报警");
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<processFaultList.size(); i++ ){
			ProcessFault pf = processFaultList.get(i);
			if (!pf.isbMonitor()) 
				continue;
			int deviceId = pf.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				processFaultList.remove(i);
				i--;
				continue;
			}
			// 如果没有给该设备绑定报警策略
			if( dv.getMonitorAlertPolicy() == null ){
				// 如果出错重试次数达到默认次数
				if( pf.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !pf.isPaneAlerted() ){
						pf.setPaneAlerted(true);
						newProcessAlert(dv, pf.getProcName());	 // 在报警栏新建一条报警记录
					} else {
						updateProcessAlert(dv, pf.getProcName());// 更新对应的报警记录
					}
				}
				// 如果出错次数恢复为0，说明设备恢复正常
				else if( pf.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( pf.isPaneAlerted() ){
						newProcessRestore(dv, pf.getProcName()); // 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					processFaultList.remove(i);
					i--;
				}
				continue;
			}
			
			// 检查是否应该，发出铃声报警
			if( pf.getFaultCount() > 0 && "1".equals(this.soundSwitch)
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_PROCESS_FAULT) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于“重试次数限制”
			if( pf.getFaultCount() >= (int)dv.getMonitorAlertPolicy().getErrorRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !pf.isPaneAlerted() ){
					pf.setPaneAlerted(true);
					newProcessAlert(dv, pf.getProcName());	 // 在报警栏新建一条报警记录
				} else {
					updateProcessAlert(dv, pf.getProcName());// 更新对应的报警记录
				}
				// 如果没有报过警，则报警
				if( !pf.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "进程不存在";
					String msg = "服务器" + dv.getIp() + "(" 
									+ dv.getName() + ")的关键进程" + pf.getProcName() + "不存在"
									+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_PROCESS_FAULT, title, msg);
				}
			}// 如果出错次数恢复为0，说明设备恢复正常
			else if( pf.getFaultCount() == 0 ){
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( pf.isPaneAlerted() ){
					// 报警栏显示恢复信息
					newProcessRestore(dv, pf.getProcName()); // 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( pf.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "进程恢复正常";
					String msg = "服务器" + dv.getIp() + "(" 
									+ dv.getName() + ")的关键进程" + pf.getProcName() + "恢复正常"
									+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_PROCESS_RESTORE, title, msg);
				}
			}
			// 从异常列表中删除
			processFaultList.remove(i);
			i--;
		} // for end 
		
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
	 * @param dv 网络设备
	 * @param procName 进程名称
	 */
	private void newProcessAlert(MonitorDevice dv, String procName) {
		if(  null  == dv || null == procName || "".equals(procName) )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_PROCESS_FAULT));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		// 进程名称存储在hostName（主机名）一栏
		po.setHostName(procName);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	/**
	 *  更新报警记录
	 * @param dv 网络设备
	 * @param procName 进程名称
	 */
	private void updateProcessAlert(MonitorDevice dv, String procName) {
		if( null  == dv || null == procName || "".equals(procName) )
			return;
		try {
			String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_PROCESS_FAULT
						+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
						+" and ip = '"+dv.getIp()+"'" 
						+" and hostName = '"+procName+"' order by id desc" ;
			MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 
			if( null != alert){
				 alert.setCount(alert.getCount()+1);
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			}else{
				newProcessAlert(dv,procName);
			} 
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 添加报警恢复记录
	 * @param dv
	 * @param procName
	 */
	private void newProcessRestore(MonitorDevice dv, String procName) {
		if( null == dv || null == procName || "".equals(procName) )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_PROCESS_RESTORE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));

		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		// 进程名称存储在hostName（主机名）一栏
		po.setHostName(procName);
		 
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	public List<ProcessFault> getProcessFaultList() {
		return processFaultList;
	}

	public void setProcessFaultList(List<ProcessFault> processFaultList) {
		this.processFaultList = processFaultList;
	}
	
	
}
