package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.CpuHight;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorDevice;
/**
 * <p>
 * Title:CPU阈值报警分析器
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
public class CpuHightParser extends BaseParser{


	// CPU异常设备列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<CpuHight> cpuHightList = null;
	
	
	/**遍历设备CPU异常列表，按照设备指定的报警策略，给指定的人发送报警信息**/
	public void checkAlert() {
		this.init();
		// 若设备异常列表为空，则直接返回
		if( null == cpuHightList || cpuHightList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		 
		this.setUserMsg("设备CPU占用率报警");
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<cpuHightList.size(); i++ ){
			CpuHight ch = cpuHightList.get(i);
			int deviceId = ch.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if(dv == null){
				cpuHightList.remove(i);
				i--;
				continue;
			}
			
			// 如果没有给该设备绑定报警策略
			if( null == dv.getMonitorAlertPolicy() ){
				// 如果超阈值重试次数达到默认次数
				if( ch.getHightCount() >= MainConstants.DEFAULT_LIMEN_RETRY_COUNT ){
					// 记录到报警栏
					if( !ch.isPaneAlerted() ){
						ch.setPaneAlerted(true);
						newCpuAlert(dv, ch.getCurrCpu());	// 在报警栏新建一条报警记录
					} else {
						updateCpuAlert(dv, ch.getCurrCpu());	// 更新对应的报警记录
					}
				}
				// 如果超阈值次数恢复为0，说明设备恢复正常
				else if( ch.getHightCount() == 0 ){
					// 报警栏显示恢复信息
					if( ch.isPaneAlerted() ){
						newCpuRestore(dv, ch.getCurrCpu());	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					cpuHightList.remove(i);
					i--;
				}
				continue;
			}//end if
			
			// 接下来是设备绑定报警策略
			
			// 检查是否应该，发出铃声报警
			if( ch.getHightCount() > 0 && "1".equals(soundSwitch)
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_CPU_OVERLOAD) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于报警策略的“重试次数限制”
			if( ch.getHightCount() >= (int)dv.getMonitorAlertPolicy().getLimenRetry() ){
				// 插入到或者更新报警表
				if( !ch.isPaneAlerted() ){
					ch.setPaneAlerted(true);
					newCpuAlert(dv, ch.getCurrCpu());	// 新建一条报警记录
				} else {
					updateCpuAlert(dv, ch.getCurrCpu());	// 更新对应的报警记录
				}
				
				// 如果没有报过警，则报警
				if( !ch.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "CPU占用率超过阈值";
					String msg = "" + dv.getIp() + "(" + dv.getName() + ")"
								+ "当前CPU占用率：" + ch.getCurrCpu() + "%("
								+ "阈值：" + cpuLimen + "%)，" 
								+ "设备类型：" + (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName() )
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_CPU_OVERLOAD, title, msg);
						
					
				} 
				
			} 
			else if( ch.getHightCount() == 0 ){// 如果出错次数恢复为0，说明设备恢复正常
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( ch.isPaneAlerted() ){
					// 报警栏显示恢复信息
					newCpuRestore(dv, ch.getCurrCpu());	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( ch.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "CPU占用率恢复正常";
					String msg = "" + dv.getIp() + "(" + dv.getName() + ")"
								+ "当前CPU占用率：" + ch.getCurrCpu() + "%("
								+ "阈值：" + cpuLimen + "%)，" 
								+ "设备类型：" + (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName() )
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_CPU_RESTORE, title, msg);	
					
				}
				
				// 从异常列表中删除
				cpuHightList.remove(i);
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
	
 
	public List<CpuHight> getCpuHightList() {
		return cpuHightList;
	}


	public void setCpuHightList(List<CpuHight> cpuHightList) {
		this.cpuHightList = cpuHightList;
	}


	/**
	 * 报警栏alert 添加报警记录 
	 * @param dv 网络设备对象
	 * @param value 当前CPU占用率
	 */
	private void newCpuAlert(MonitorDevice dv, int value) {
		Integer alertSmalltypeCode = MainConstants.SALERT_CPU_OVERLOAD;
		newCpuAlert(dv, value, alertSmalltypeCode);	 
	}
	
	/**
	 * 添加报警记录
	 * @param dv
	 * @param value
	 */
	private void newCpuRestore(MonitorDevice dv, int value) {
		if(  null == dv)
			return;
		Integer alertSmalltypeCode = MainConstants.SALERT_CPU_RESTORE;
		newCpuAlert(dv, value, alertSmalltypeCode);
		 
	}
	/**
	 *  添加报警记录
	 * @param dv
	 * @param value
	 * @param alertSmalltypeCode
	 */
	private void newCpuAlert(MonitorDevice dv, int value,Integer alertSmalltypeCode){
		
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(alertSmalltypeCode));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_LIMEN));
		po.setFile(0);
		po.setOld(dv.getName());
		if (value >= 0) {
			po.setValue("" + value + "%");
		}
		 
		if (this.cpuLimen > 0){
			po.setLimen("" + this.cpuLimen + "%");
		}
		po.setIp(dv.getIp());
		po.setCount(1);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		this.monitorAlertService.save(po);
	}
	
	/** 
	 * 更新报警记录
	 * @param dv 网络设备对象
	 * @param value 当前CPU占用率
	 */
	private void updateCpuAlert(MonitorDevice dv, int value) {
		if( null == dv  )
			return;
		try {
			 String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_CPU_OVERLOAD
			 			+" and monitorAlertType.code="+MainConstants.ALERT_LIMEN
			 			+" and ip = '"+dv.getIp()+"' order by id desc";
			 MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 
			 if( null != alert){
				if( value >= 0 ){
					 alert.setValue("" + value + "%");
				 }
				if (this.cpuLimen > 0)
					 alert.setLimen("" + cpuLimen + "%");
				 alert.setCount( null == alert.getCount() ? 2:(alert.getCount()+1));
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			 }else{
				 newCpuAlert(dv,value,MainConstants.SALERT_CPU_OVERLOAD);
			 }
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	
	 

	 
	
}
