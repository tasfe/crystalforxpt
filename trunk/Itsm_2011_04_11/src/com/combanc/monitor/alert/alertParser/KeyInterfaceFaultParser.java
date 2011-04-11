package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.KeyInterfaceFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:检查关键接口异常的计数器，形成报警
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

public class KeyInterfaceFaultParser extends BaseParser{

	// 关键接口下线列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<KeyInterfaceFault> keyInterfaceFaultList = null;
	
	/** 遍历关键接口下线列表，按照设备指定的报警策略，给指定的人发送报警信息 **/
	public void checkAlert() {
		this.init();
		// 若关键接口下线列表，设备列表或者用户表为空，则直接返回
		if( null == keyInterfaceFaultList || keyInterfaceFaultList.isEmpty()
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		this.setUserMsg("关键接口报警");
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<keyInterfaceFaultList.size(); i++ ){
			KeyInterfaceFault kif = keyInterfaceFaultList.get(i);
			if (!kif.isMonitor()) 
				continue;
			int deviceId = kif.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				keyInterfaceFaultList.remove(i);
				i--;
				continue;
			}
			
			// 如果没有给该设备绑定报警策略
			if( null == dv.getMonitorAlertPolicy() ){
				// 如果出错重试次数达到默认次数
				if( kif.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !kif.isPaneAlerted() ){
						kif.setPaneAlerted(true);
						// 在报警栏新建一条报警记录
						newInterfaceAlert(dv, kif);	
					} else {
						// 更新对应的报警记录
						updateInterfaceAlert(dv, kif);	
					}
				}
				// 如果出错次数恢复为0，说明设备恢复正常
				else if( kif.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( kif.isPaneAlerted() ){
						// 在报警栏新建一条报警恢复记录
						newInterfaceRestore(dv, kif);	
					}
					// 从异常列表中删除
					keyInterfaceFaultList.remove(i);
					i--;
				}
				continue;
			}
			
			// 检查是否应该，发出铃声报警
			if( kif.getFaultCount() > 0 && "1".equals(soundSwitch) 
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_INTERFACE_FAULT) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于“重试次数限制”
			if( kif.getFaultCount() >= (int)dv.getMonitorAlertPolicy().getErrorRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !kif.isPaneAlerted() ){
					kif.setPaneAlerted(true);
					// 在报警栏新建一条报警记录
					newInterfaceAlert(dv, kif);	
				} else {
					// 更新对应的报警记录
					updateInterfaceAlert(dv, kif);	
				}
				// 如果没有报过警，则报警
				if( !kif.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "关键端口下线";
					String interfaceText = kif.getInterfaceDesc();
					if ( null != kif.getInterfaceNote() && !kif.getInterfaceNote().isEmpty()) {
						interfaceText += "（" + kif.getInterfaceNote() + "）";
					}
					String interfaceStatus = "";
					int faultType = kif.getFaultType();
					if (faultType == MainConstants.INTERFACE_STATUS_OFFLINE) {
						interfaceStatus = "下线";
					} else if (faultType == MainConstants.INTERFACE_STATUS_SHUTDOWN) {
						interfaceStatus = "被关闭";
					} else if (faultType == MainConstants.INTERFACE_STATUS_NODATA) {
						interfaceStatus = "不能被获取数据";
					}
					String msg = "当前带宽占用：" + "设备" + dv.getIp() + "(" 
								+ dv.getName() + ")的关键端口" + interfaceText
								+ interfaceStatus 
								+ "。";
					 
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_INTERFACE_FAULT, title, msg);
				}
			}else if( kif.getFaultCount() == 0 ){
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( kif.isPaneAlerted() ){
					// 报警栏显示恢复信息
					// 在报警栏新建一条报警恢复记录
					newInterfaceRestore(dv, kif);	
				}
				
				// 如果已经报过警，则发出恢复报警
				if( kif.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "接口恢复上线";
					String interfaceText = kif.getInterfaceDesc();
					if ( null != kif.getInterfaceNote() && !kif.getInterfaceNote().isEmpty()) {
						interfaceText += "（" + kif.getInterfaceNote() + "）";
					}
					
					String msg = "当前带宽占用：" + "设备" + dv.getIp() + "(" 
								+ dv.getName() + ")的关键端口" + interfaceText
								+ "。";
					 
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_INTERFACE_RESTORE, title, msg);
				}
				
				// 从异常列表中删除
				keyInterfaceFaultList.remove(i);
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
	 * 添加报警记录
	 * @param dv
	 * @param kif
	 */
	private void newInterfaceAlert(MonitorDevice dv, KeyInterfaceFault kif) {
		if ( null == dv || null == kif.getInterfaceNum() || "".equals(kif.getInterfaceNum())) 
			return;
		
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_INTERFACE_FAULT ));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		// "接口号（接口描述）"存储在hostName（主机名）一栏
		String interfaceText = kif.getInterfaceNum() ;
		if ( null != kif.getInterfaceDesc()  && ! kif.getInterfaceDesc().isEmpty())
			interfaceText += "（" + kif.getInterfaceDesc() + "）";
		int faultType = kif.getFaultType();
		if ( faultType == MainConstants.INTERFACE_STATUS_OFFLINE ) {
			interfaceText += "下线";
		} else if (faultType == MainConstants.INTERFACE_STATUS_SHUTDOWN) {
			interfaceText += "被关闭";
		} else if (faultType == MainConstants.INTERFACE_STATUS_NODATA) {
			interfaceText += "读不到数据";
		}
		po.setHostName(interfaceText);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
		 
	}
	
	/**
	 * 更新报警记录
	 * @param dv
	 * @param kif
	 */
	private void updateInterfaceAlert(MonitorDevice dv, KeyInterfaceFault kif ) {
		if ( null == dv || null == kif.getInterfaceNum() || "".equals(kif.getInterfaceNum())) 
			return;
		try {
			String interfaceNum =  kif.getInterfaceNum();
			String interfaceDesc = kif.getInterfaceDesc();
			int faultType = kif.getFaultType();
			
			String interfaceText = interfaceNum;
			if (interfaceDesc != null && !interfaceDesc.isEmpty())
				interfaceText += "（" + interfaceDesc + "）";
			if (faultType == MainConstants.INTERFACE_STATUS_OFFLINE) {
				interfaceText += "下线";
			} else if (faultType == MainConstants.INTERFACE_STATUS_SHUTDOWN) {
				interfaceText += "被关闭";
			} else if (faultType == MainConstants.INTERFACE_STATUS_NODATA) {
				interfaceText += "读不到数据";
			}
			
			 String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_INTERFACE_FAULT
				+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
				+" and ip = '"+dv.getIp()+"'" 
				+" and hostName = '"+interfaceText+"' order by id desc" ;
			 
			 MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 if( null != alert){
				 alert.setCount(alert.getCount()+1);
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			 }else{
				 newInterfaceAlert(dv,kif);
			 }
			  
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加报警恢复记录
	private void newInterfaceRestore(MonitorDevice dv, KeyInterfaceFault kif) {
		if ( null == dv || null == kif.getInterfaceNum() || "".equals(kif.getInterfaceNum())) 
			return;
		MonitorAlert po = new MonitorAlert();
		
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_INTERFACE_FAULT ));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		
		po.setFile(0);
		po.setOld(dv.getName());
		po.setIp(dv.getIp());
		
		// "接口号（接口描述）"存储在hostName（主机名）一栏
		String interfaceText = kif.getInterfaceNum() ;
		if ( null != kif.getInterfaceDesc()  && ! kif.getInterfaceDesc().isEmpty())
			interfaceText +=  kif.getInterfaceDesc() ;
		
		po.setHostName(interfaceText);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		
		monitorAlertService.save(po);
		
	}
	
	public List<KeyInterfaceFault> getKeyInterfaceFaultList() {
		return keyInterfaceFaultList;
	}

	public void setKeyInterfaceFaultList(
			List<KeyInterfaceFault> keyInterfaceFaultList) {
		this.keyInterfaceFaultList = keyInterfaceFaultList;
	}
	
}
