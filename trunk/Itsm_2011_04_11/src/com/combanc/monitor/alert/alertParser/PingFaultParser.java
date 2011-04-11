package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.PingFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.service.MonitorPingDestService;

/**
 * <p>
 * Title:Ping扫描分析器
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
public class PingFaultParser extends BaseParser{

	// Ping监测表
	List<MonitorPingDest> pingDestList = null;
	// 异常Ping列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<PingFault> pingFaultList = null;
	
	private MonitorPingDestService monitorPingDestService;
	
	/** 遍历Ping异常列表，按照Ping分类指定的报警策略，给指定的人发送报警信息 **/
	public void checkAlert() {
		this.init();
		// 若Ping不通列表为空，则直接返回
		if( null == pingFaultList || pingFaultList.isEmpty() 
				|| null == userList || userList.isEmpty() )
			return;
		// 查出PingDest表全部，若为空，则返回
		try{
			pingDestList = monitorPingDestService.getAll();
		}catch(Exception e){
			e.printStackTrace(); 
		}
		if( null == pingDestList || pingDestList.isEmpty())
			return;
		
		this.setUserMsg("Ping监测报警");
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for (int i=0; i<pingFaultList.size(); i++){
			PingFault pf = pingFaultList.get(i);
			MonitorPingDest pd = pf.getPingDest();
			// 如果查不到，说明Ping监测项已被删除，把异常列表中的该记录也清除
			MonitorPingDest thePd = ParserTool.getPingDestFromList(pd, pingDestList);
			if (thePd == null) {
				pingFaultList.remove(i);
				i--;
				continue;
			}
			// 如果没有给该Ping分类绑定报警策略
			if ( null == thePd.getMonitorPingRegion() || null == thePd.getMonitorPingRegion().getMonitorAlertPolicy()  ){
				// 如果出错重试次数达到默认次数
				if( pf.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !pf.isbPaneAlerted() ){
						pf.setbPaneAlerted(true);
						newPingAlert(thePd);		// 在报警栏新建一条报警记录
					} else {
						updatePingAlert(thePd);	// 更新对应的报警记录
					}
				}
				// 如果出错次数恢复为0，说明Ping恢复正常
				else if( pf.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( pf.isbPaneAlerted() ){
						newPingRestore(thePd);	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					pingFaultList.remove(i);
					i--;
				}
				continue;
			} // if end
			
			// 接下来是设备绑定报警策略
			
			// 检查是否应该，发出铃声报警
			if( pf.getFaultCount() > 0 && "1".equals(soundSwitch) 
					&& thePd.getMonitorPingRegion().getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if (checkAlertPolicy(thePd.getMonitorPingRegion().getMonitorAlertPolicy(), MainConstants.SALERT_PING_FAULT)) {
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			// 如果计数器的值大于等于“重试次数限制”
			if( pf.getFaultCount() >= (int)thePd.getMonitorPingRegion().getMonitorAlertPolicy().getErrorRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !pf.isbPaneAlerted() ){
					pf.setbPaneAlerted(true);
					newPingAlert(thePd);		// 在报警栏新建一条报警记录
				} else {
					updatePingAlert(thePd);	// 更新对应的报警记录
				}
				// 如果没有报过警，则报警
				if( !pf.isbAlerted() ){
					//短信和邮件的标题、内容
					String title = "Ping监控无响应";
					String smsg = "";
					if ( null != thePd.getMonitorPingRegion() ) {
						smsg = thePd.getMonitorPingRegion().getName() + "：";
					}
					String msg =  smsg + thePd.getDestIpUrl()+ "Ping不通。";
					this.setAlertMsg(thePd.getMonitorPingRegion().getMonitorAlertPolicy(),MainConstants.SALERT_PING_FAULT, title, msg);
				}
			}// 如果出错次数恢复为0，说明Ping监测恢复正常
			else if( pf.getFaultCount() == 0 ){
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( pf.isbPaneAlerted() ){
					// 报警栏显示恢复信息
					newPingRestore(thePd);	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if (pf.isbAlerted()) {
					//短信和邮件的标题、内容
					String title = "Ping响应恢复正常";
					String smsg = "";
					if ( null != thePd.getMonitorPingRegion() ) {
						smsg = "("+thePd.getMonitorPingRegion().getName() + ")";
					}
					String msg =  smsg + thePd.getDestIpUrl()+ "恢复Ping响应。";
					this.setAlertMsg(thePd.getMonitorPingRegion().getMonitorAlertPolicy(),MainConstants.SALERT_PING_FAULT, title, msg);
				}
				
				// 从异常列表中删除
				pingFaultList.remove(i);
				i--;
			} // else if end 
			
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
	 * @param pd
	 */
	private void newPingAlert(MonitorPingDest pd) {
		if( null == pd  )
			return;
		MonitorAlert po = new MonitorAlert();
		
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_PING_FAULT));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		if ( null != pd.getMonitorPingRegion()) {
			po.setOld(pd.getMonitorPingRegion().getName());
		}
		po.setIp(pd.getDestIpUrl());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		po.setObjectId(pd.getId());
		monitorAlertService.save(po);
	}
	
	/**
	 *  更新报警记录
	 * @param pd
	 */
	private void updatePingAlert(MonitorPingDest pd) {
		if( null == pd  )
			return;
		String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_PING_FAULT
			+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
			+" and objectId = '"+pd.getId()+"' order by id desc";
		MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
		if( null != alert){
			if (null != pd.getMonitorPingRegion()) {
				alert.setOld(pd.getMonitorPingRegion().getName());
			}
			 alert.setCount(alert.getCount()+1);
			 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
			 monitorAlertService.update(alert);
		}else{
			newPingAlert(pd);
		}
		 
			
		 
		 
	}
	/**
	 *  添加报警恢复记录
	 * @param pd
	 */
	private void newPingRestore(MonitorPingDest pd) {
		if( null == pd  )
			return;
		MonitorAlert po = new MonitorAlert();
		
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_PING_RESTORE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		if ( null != pd.getMonitorPingRegion()) {
			po.setOld(pd.getMonitorPingRegion().getName());
		}
		
		po.setIp(pd.getDestIpUrl());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		po.setObjectId(pd.getId());
		monitorAlertService.save(po);
		
	}
	public List<MonitorPingDest> getPingDestList() {
		return pingDestList;
	}

	public void setPingDestList(List<MonitorPingDest> pingDestList) {
		this.pingDestList = pingDestList;
	}

	public List<PingFault> getPingFaultList() {
		return pingFaultList;
	}

	public void setPingFaultList(List<PingFault> pingFaultList) {
		this.pingFaultList = pingFaultList;
	}


	public MonitorPingDestService getMonitorPingDestService() {
		return monitorPingDestService;
	}


	public void setMonitorPingDestService(
			MonitorPingDestService monitorPingDestService) {
		this.monitorPingDestService = monitorPingDestService;
	}
	
	
}
