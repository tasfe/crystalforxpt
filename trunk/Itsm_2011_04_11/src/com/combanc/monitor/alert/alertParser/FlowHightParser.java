package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.FlowHight;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.service.MonitorLinkportService;

/**
 * <p>
 * Title:检查带宽占用超阈值计数器，形成报警
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

public class FlowHightParser extends BaseParser{

	/**流量异常端口列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）**/
	private List<FlowHight> flowHightList = null;
	/**互联端口列表**/
	private List<MonitorLinkport> linkportList = null;
	
	private MonitorLinkportService monitorLinkportService ;
	
	/**遍历端口流量异常列表，按照设备指定的报警策略，给指定的人发送报警信息**/
	public void checkAlert() {
		this.init();
		linkportList = monitorLinkportService.findUniqueLegitimateList();
		// 若流量异常端口列表,设备列表，互联接口表或者用户表为空，则直接返回
		if( null == flowHightList || flowHightList.isEmpty() 
				|| null == deviceList || deviceList.isEmpty() 
				|| null == linkportList || linkportList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return;
		 
		this.setUserMsg("接口流量报警");
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<flowHightList.size(); i++ ){
			FlowHight fh = flowHightList.get(i);
			
			// 获取设备对象
			int deviceId = fh.getDeviceId();
			// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
			MonitorDevice dv = ParserTool.getDeviceFromList(deviceId, deviceList);
			if( null == dv ){
				flowHightList.remove(i);
				i--;
				continue;
			}
			
			// 获取设备接口
			int interface_ = fh.getInterface_();
			// 如果查不到，说明互连接口已被删除，把异常列表中的该记录也清除
			MonitorLinkport lp = ParserTool.getLinkportFromList(dv.getIp(),interface_, linkportList);
			if( null == lp) {
				flowHightList.remove(i);
				i--;
				continue;
			}
			
			// 如果没有给该设备绑定报警策略
			if( null == dv.getMonitorAlertPolicy()){
				// 如果超阈值重试次数达到默认次数
				if( fh.getHightCount() >= MainConstants.DEFAULT_LIMEN_RETRY_COUNT ){
					// 记录到报警栏
					if( !fh.isPaneAlerted() ){
						fh.setPaneAlerted(true);
						newFlowAlert(dv, lp, fh.getCurrFlowRate());	// 在报警栏新建一条报警记录
					}else {
						updateFlowAlert(dv, lp, fh.getCurrFlowRate());// 更新对应的报警记录
					}
				} 
				// 如果超阈值次数恢复为0，说明设备恢复正常
				else if( fh.getHightCount() == 0 ){
					// 报警栏显示恢复信息
					if( fh.isPaneAlerted() ){
						newFlowRestore(dv, lp, fh.getCurrFlowRate());	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					flowHightList.remove(i);
					i--;
				}
				continue;
			}
			
			// 接下来是设备绑定报警策略
			// 检查是否应该，发出铃声报警
			if( fh.getHightCount() > 0 && !"1".equals(soundSwitch) 
					&& dv.getMonitorAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if( checkAlertPolicy(dv.getMonitorAlertPolicy(), MainConstants.SALERT_FLOW_OVERLOAD) ){
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 如果计数器的值大于等于“重试次数限制”
			if( fh.getHightCount() >= (int)dv.getMonitorAlertPolicy().getLimenRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !fh.isPaneAlerted() ){
					fh.setPaneAlerted(true);
					newFlowAlert(dv, lp, fh.getCurrFlowRate());	// 在报警栏新建一条报警记录
				} else {
					updateFlowAlert(dv, lp, fh.getCurrFlowRate());// 更新对应的报警记录
				}
				// 如果没有报过警，则报警
				if( !fh.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "接口带宽过载";
					String msg = "当前带宽占用：" + fh.getCurrFlowRate() + "%("
								+ "阈值：" +bandLimen + "%)，"  
								+ lp.getDescription() + "("
								+ lp.getInterface_()+ ")，" + dv.getIp() 
								+ "(" + dv.getName() + ")，设备类型：" 
								+ (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName()) 
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_FLOW_OVERLOAD, title, msg);
				} 
			} else if( fh.getHightCount() == 0 ){ // 如果出错次数恢复为0，说明设备恢复正常
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( fh.isPaneAlerted() ){
					// 报警栏显示恢复信息
					newFlowRestore(dv, lp, fh.getCurrFlowRate());	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( fh.isAlerted() ){
					//短信和邮件的标题、内容
					String title = "接口带宽占用恢复正常";
					String msg = "当前带宽占用：" + fh.getCurrFlowRate() + "%("
								+ "阈值：" +bandLimen + "%)，"  
								+ lp.getDescription() + "("
								+ lp.getInterface_()+ ")，" + dv.getIp() 
								+ "(" + dv.getName() + ")，设备类型：" 
								+ (null==dv.getMonitorDeviceType()?"未确定":dv.getMonitorDeviceType().getName()) 
								+ "。";
					this.setAlertMsg(dv.getMonitorAlertPolicy(),MainConstants.SALERT_FLOW_RESTORE, title, msg);
				}
				// 从异常列表中删除
				flowHightList.remove(i);
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
	 * @param lp
	 * @param value
	 */
	private void newFlowAlert(MonitorDevice dv, MonitorLinkport lp, int value) {
		if ( null == dv || null == lp) 
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_FLOW_OVERLOAD ));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_LIMEN));
		po.setFile(0);
		po.setOld(dv.getName());
		if (value >= 0) {
			po.setValue("" + value + "%");
		}
		if (this.bandLimen > 0) {
			po.setLimen("" + this.bandLimen + "%");
		}
		po.setIp(dv.getIp());
		
		po.setInterface_(lp.getInterface_());
		po.setDescription(lp.getDescription());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		this.monitorAlertService.save(po);
	}
	
	/**
	 *  更新报警记录
	 * @param dv
	 * @param lp
	 * @param value
	 */
	private void updateFlowAlert(MonitorDevice dv, MonitorLinkport lp, int value) {
		if ( null == dv || null == lp) 
			return;
		try {
			 String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_FLOW_OVERLOAD
				+" and monitorAlertType.code="+MainConstants.ALERT_LIMEN
				+" and ip = '"+dv.getIp()+"'" 
				+" and interface_ = '"+lp.getInterface_()+"' order by id desc" ;
			 MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 
			 if( null != alert){
				 if( value >= 0 ){
					 alert.setValue("" + value + "%");
				 }
				 if ( this.bandLimen > 0 ) {
					 alert.setLimen("" + bandLimen + "%");
				 }
				 alert.setCount(alert.getCount()+1);
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			 }else{
				 newFlowAlert(dv,lp,value);
			 }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 添加报警恢复记录
	private void newFlowRestore(MonitorDevice dv, MonitorLinkport lp, int value) {
		if ( null == dv || null == lp) 
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_FLOW_RESTORE ));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_LIMEN));

		po.setFile(0);
		po.setOld(dv.getName());
		if( value >= 0 ){
			po.setValue("" + value + "%");
		}
		if ( this.bandLimen > 0) {
			po.setLimen("" + this.bandLimen + "%");
		}
		po.setIp(dv.getIp());
		
		po.setInterface_(lp.getInterface_());
		po.setDescription(lp.getDescription());
	
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		monitorAlertService.save(po);
	}
	
	public List<FlowHight> getFlowHightList() {
		return flowHightList;
	}

	public void setFlowHightList(List<FlowHight> flowHightList) {
		this.flowHightList = flowHightList;
	}

	public List<MonitorLinkport> getLinkportList() {
		return linkportList;
	}

	public void setLinkportList(List<MonitorLinkport> linkportList) {
		this.linkportList = linkportList;
	}

	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}
	
	
}
