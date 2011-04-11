package com.combanc.monitor.alert.alertParser;

import java.util.List;

import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * <p>
 * Title:ARP报警分析器
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
public class ArpAlertParser  extends BaseParser{

	private List<MonitorAlert> arpAlertList = null;

	public List<MonitorAlert> getArpAlertList() {
		return arpAlertList;
	}

	public void setArpAlertList(List<MonitorAlert> arpAlertList) {
		this.arpAlertList = arpAlertList;
	}
	
	public void checkAlert() {
		this.init();
		if( null == arpAlertList || arpAlertList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		
		this.setUserMsg("接入计算机类报警");
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<arpAlertList.size(); i++ ){
			MonitorAlert a = arpAlertList.get(i);
			String deviceIp = a.getUplink();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromListByIp(deviceIp, deviceList);
			if( null == dv ){
				//arpAlertList.remove(i);
				//i--;
				continue;
			}
			
			// 如果没有给该设备绑定报警策略，直接继续
			if( null == dv.getMonitorAlertPolicy() ){
				continue;
			}else if (checkAlertPolicy(dv.getMonitorAlertPolicy(), a.getMonitorAlertSmalltype().getCode())) {
				//短信和邮件的标题、内容
				String title = "报警类型：" + a.getMonitorAlertSmalltype().getName();
				String msg = " 计算机IP：" + a.getIp() 
							+ "，计算机MAC：" + a.getMac()
							+ "，计算机上连设备：" + a.getUplink()
							+ "，计算机上连设备接口：" + a.getInterface_()
							+ ".";
			
				this.setAlertMsg(dv.getMonitorAlertPolicy(), a.getMonitorAlertSmalltype().getCode(), title, msg);
			}
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
	
}
