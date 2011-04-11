package com.combanc.monitor.alert.alertParser;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.monitor.alert.alertParser.abnormalCounter.UrlFault;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorUrlResponse;
import com.combanc.monitor.service.MonitorUrlResponseService;

/**
 * <p>
 * Title:URL监测报警分析器
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

public class UrlFaultParser  extends BaseParser{

	// URL监测表
	List<MonitorUrlResponse> urlList = null;
	// 异常URL列表（被用来反复轮询，检查是否达次数，需要报警，是否恢复，需要报警）
	List<UrlFault> urlFaultList = null;
	
	private MonitorUrlResponseService monitorUrlResponseService;
	
	// 遍历URL异常列表，按照URL指定的报警策略，给指定的人发送报警信息
	public void checkAlert() {
		this.init();
		
		if( null == urlFaultList || urlFaultList.isEmpty() 
				|| null == userList || userList.isEmpty())
			return ;
		
		urlList = monitorUrlResponseService.getAll();
		if( null == urlList || urlList.isEmpty())
			return ;
		// 构造针对每个用户的短信报警信息和邮件报警信息
		this.setUserMsg("URL监测报警");
		
		// 检查计数器列表，更新报警栏，形成报警信息
		for( int i=0; i<urlFaultList.size(); i++ ){
			UrlFault uf = urlFaultList.get(i);
			int urlId = uf.getUrlId();
			// 如果查不到，说明URL监测项已被删除，把异常列表中的该记录也清除
			MonitorUrlResponse hur = ParserTool.getUrlFromList(urlId, urlList);
			if(hur == null){
				urlFaultList.remove(i);
				i--;
				continue;
			}
			// 如果没有给该URL绑定报警策略
			if( hur.getAlertPolicy() == null ){
				// 如果出错重试次数达到默认次数
				if( uf.getFaultCount() >= MainConstants.DEFAULT_ERROR_RETRY_COUNT ){
					// 记录到报警栏
					if( !uf.isbPaneAlerted() ){
						uf.setbPaneAlerted(true);
						newUrlAlert(hur);		// 在报警栏新建一条报警记录
					} else {
						updateUrlAlert(hur);	// 更新对应的报警记录
					}
				}
				// 如果出错次数恢复为0，说明URL恢复正常
				else if( uf.getFaultCount() == 0 ){
					// 报警栏显示恢复信息
					if( uf.isbPaneAlerted() ){
						newUrlRestore(hur);	// 在报警栏新建一条报警恢复记录
					}
					// 从异常列表中删除
					urlFaultList.remove(i);
					i--;
				}
				continue;
			}
			
			// 检查是否应该，发出铃声报警
			if( uf.getFaultCount() > 0 && "1".equals(this.soundSwitch)
					&& hur.getAlertPolicy().getSoundSwitch() ){
				// 检查是否符合报警策略所设置的报警条件（时间范围和报警类型）
				if (checkAlertPolicy(hur.getAlertPolicy(), MainConstants.SALERT_URL_FAULT)) {
					// 声音报警
					soundAlert(MainConstants.ALERT_ALARM_FILE);
				}
			}
			
			// 拼写网址
			String ip = hur.getServerIp();
			int port = Integer.valueOf(hur.getPort());
			String absUrl = hur.getAddress();
			String domain = hur.getDomain();
			boolean chkDomain = (hur.getDomain() != null);
			if (chkDomain) {
				ip = domain;
			}
			String httpUrl = "";
			if (hur.getType() == 1) {
				httpUrl = "http://" + ip + ":" + port + absUrl;
			} else {
				httpUrl = "https://" + ip + ":" + port + absUrl;
			}
			
			// 如果计数器的值大于等于“重试次数限制”
			if( uf.getFaultCount() >= (int)hur.getAlertPolicy().getErrorRetry() ){
				// 如果未加入到过报警栏，记录到报警栏
				if( !uf.isbPaneAlerted() ){
					uf.setbPaneAlerted(true);
					newUrlAlert(hur);		// 在报警栏新建一条报警记录
				} else {
					updateUrlAlert(hur);	// 更新对应的报警记录
				}
				// 如果没有报过警，则报警
				if( !uf.isbAlerted() ){
					//短信和邮件的标题、内容
					String title = "URL监控异常";
					String msg = hur.getServerIp() + "下的" + 
								hur.getTitle() + "(" + httpUrl + ")：\n" 
								+ uf.getsMsg()+"。\n";
					this.setAlertMsg(hur.getAlertPolicy(),MainConstants.SALERT_URL_FAULT, title, msg);
				}
			}else if( uf.getFaultCount() == 0 ){
				// 如果已经加入到过报警栏，报警栏记录恢复信息
				if( uf.isbPaneAlerted() ){
					// 报警栏显示恢复信息
					newUrlRestore(hur);	// 在报警栏新建一条报警恢复记录
				}
				// 如果已经报过警，则发出恢复报警
				if( uf.isbAlerted() ){
					//短信和邮件的标题、内容
					String title = "URL监控恢复正常";
					String msg = hur.getServerIp() + "下的" + 
								hur.getTitle() + "(" + httpUrl + ")：\n" 
								+ uf.getsMsg()+"。\n";
					this.setAlertMsg(hur.getAlertPolicy(),MainConstants.SALERT_URL_RESTORE, title, msg);
				}
			}
			// 从异常列表中删除
			urlFaultList.remove(i);
			i--;
		}// for end 
		
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
	 * @param hur
	 */
	private void newUrlAlert(MonitorUrlResponse hur) {
		if(  null == hur )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_URL_FAULT));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		po.setOld(hur.getTitle());
		po.setIp(hur.getServerIp());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		po.setObjectId(hur.getId());
		monitorAlertService.save(po);
		
	}
	
	/**
	 *  更新报警记录
	 * @param hur
	 */
	private void updateUrlAlert(MonitorUrlResponse hur) {
		if( null == hur )
			return;
		try {
			String hql = " from MonitorAlert where 1=1 and monitorAlertSmalltype.code= "+MainConstants.SALERT_URL_FAULT
						+" and monitorAlertType.code="+MainConstants.ALERT_FAULT
						+" and ip = '"+hur.getServerIp()+"'" 
						+" and objectId = '"+hur.getId()+"' order by id desc" ;
			MonitorAlert alert = monitorAlertService.findAlertBySql(hql);
			 
			if( null != alert){
				 alert.setOld(hur.getTitle());
				 alert.setCount(alert.getCount()+1);
				 alert.setLastTime(new Timestamp(System.currentTimeMillis()));
				 monitorAlertService.update(alert);
			}else{
				newUrlAlert(hur);
			}
			  
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  添加报警恢复记录
	 * @param hur
	 */
	private void newUrlRestore(MonitorUrlResponse hur) {
		if( null == hur )
			return;
		MonitorAlert po = new MonitorAlert();
		po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_URL_RESTORE));
		po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_FAULT));
		po.setFile(0);
		po.setOld(hur.getTitle());
		po.setIp(hur.getServerIp());
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		po.setFirstTime(ts);
		po.setLastTime(ts);
		po.setObjectId(hur.getId());
		monitorAlertService.save(po);
	}
	
	public List<MonitorUrlResponse> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<MonitorUrlResponse> urlList) {
		this.urlList = urlList;
	}

	public List<UrlFault> getUrlFaultList() {
		return urlFaultList;
	}

	public void setUrlFaultList(List<UrlFault> urlFaultList) {
		this.urlFaultList = urlFaultList;
	}

	public MonitorUrlResponseService getMonitorUrlResponseService() {
		return monitorUrlResponseService;
	}

	public void setMonitorUrlResponseService(
			MonitorUrlResponseService monitorUrlResponseService) {
		this.monitorUrlResponseService = monitorUrlResponseService;
	}
	
	
}
