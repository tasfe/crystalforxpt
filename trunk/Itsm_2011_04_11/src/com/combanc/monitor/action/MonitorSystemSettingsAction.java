package com.combanc.monitor.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.db.StringUtils;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.UserService;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.pojo.MonitorAlertSmalltype;
import com.combanc.monitor.pojo.MonitorArpAlertWhiteMac;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorPingTimePoint;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetDevice;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorAlertPolicyService;
import com.combanc.monitor.service.MonitorAlertSmalltypeService;
import com.combanc.monitor.service.MonitorArpAlertWhiteMacService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorPingTimePointService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.vo.systemParam.AlertPolicy;
import com.combanc.monitor.vo.systemParam.AlertSystem;
import com.combanc.monitor.vo.systemParam.ComputerScan;
import com.combanc.monitor.vo.systemParam.DataKeepTime;
import com.combanc.monitor.vo.systemParam.FaultScan;
import com.combanc.monitor.vo.systemParam.FluxScan;
import com.combanc.monitor.vo.systemParam.OtherSetting;
import com.combanc.monitor.vo.systemParam.PingScan;
import com.combanc.monitor.vo.systemParam.Threshold;
import com.combanc.monitor.vo.systemParam.TopologyScan;

/**
 * <p>
 * Title:系统设置
 * </p>
 * <p>
 * Description:设置各类公共变量
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
public class MonitorSystemSettingsAction  extends BaseActionSupport implements ServletRequestAware{

	HttpServletRequest request = null;
	
	private MonitorSystemParamService monitorSystemParamService;
	
	private MonitorPingTimePointService monitorPingTimePointService;
	
	private MonitorAlertPolicyService monitorAlertPolicyService;
	
	private MonitorSubnetService monitorSubnetService;
	
	private MonitorDeviceService monitorDeviceService;
	
	private MonitorAlertSmalltypeService monitorAlertSmalltypeService;
	
	private UserService userService;
	
	private MonitorArpAlertWhiteMacService monitorArpAlertWhiteMacService;
	
	
	/**阈值**/
	private Threshold threshold;
	/**计算机扫描**/
	private ComputerScan computerScan;
	/**流量及CPU扫描**/
	private FluxScan fluxScan;
	/**拓扑**/
	private TopologyScan topologyScan;
	/**故障扫描**/
	private FaultScan faultScan;
	/**系统其它设置**/
	private OtherSetting other;
	/**ping扫描**/
	private PingScan pingScan;
	/**报警设置**/
	private AlertSystem alertSystem;
	/**数据保存时间设置**/
	private DataKeepTime dataKeepTime;
	
	/**报警策略**/
	private List<MonitorAlertPolicy> alertPolicyList;
	
	private List<MonitorSubnet> subnetList;
	
	private List<MonitorSubnetDevice> deviceList;
	
	private AlertPolicy alertPolicy ;
	
	private List<MonitorArpAlertWhiteMac> arpAlertWhiteMacList;
	
	private String mac;
	
	private int page;
	
    private PageBean pageBean;
    
    
	
	/**阈值**/
	public String thresholdSetting(){
		threshold = new Threshold();
		threshold.setArpLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ARP_LIMEN));
		threshold.setBandLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BAND_LIMEN));
		threshold.setBroadcastLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BROADCAST_LIMEN));
		threshold.setCpuLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CPU_LIMEN));
		threshold.setDiskRate(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_DISK_RATE));
		threshold.setMemRate(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MEM_RATE));
		threshold.setProcNum(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PROC_NUM));
		threshold.setTcpConn(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TCP_CONN));
		threshold.setTempLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TEMP_LIMEN));
		threshold.setUnicastLimen(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_UNICAST_LIMEN));
		threshold.setVmemRate(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_VMEM_RATE));
		return SUCCESS;
	}
	public String  saveThresholdSetting(){
		monitorSystemParamService.update(threshold.getArpLimen());
		monitorSystemParamService.update(threshold.getBandLimen());
		monitorSystemParamService.update(threshold.getBroadcastLimen());
		monitorSystemParamService.update(threshold.getCpuLimen());
		monitorSystemParamService.update(threshold.getDiskRate());
		monitorSystemParamService.update(threshold.getMemRate());
		monitorSystemParamService.update(threshold.getProcNum());
		monitorSystemParamService.update(threshold.getTcpConn());
		monitorSystemParamService.update(threshold.getTempLimen());
		monitorSystemParamService.update(threshold.getUnicastLimen());
		monitorSystemParamService.update(threshold.getVmemRate());
		return SUCCESS;
	}
	/**计算机扫描**/
	public String computerScanSetting(){
		computerScan = new ComputerScan();
		computerScan.setArpNbt(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ARP_NBT).getValue());
		computerScan.setChangeAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CHANGE_ALERT).getValue());
		computerScan.setChangeRefresh(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CHANGE_REFRESH).getValue());
		computerScan.setComputerNameChangedAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_COMPUTER_NAME_CHANGED_ALERT).getValue());
		computerScan.setDomainChangedAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_DOMAIN_CHANGED_ALERT).getValue());
		computerScan.setIgnoreError(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_IGNORE_ERROR).getValue());
		computerScan.setIpChangedAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_IP_CHANGED_ALERT).getValue());
		computerScan.setL23Scan(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_L23_SCAN).getValue());
		 
		computerScan.setLinkChangedAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_LINK_CHANGED_ALERT).getValue());
		computerScan.setNewHost(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_NEW_HOST).getValue());
		computerScan.setPingScan(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_SCAN).getValue());
		computerScan.setScanGapTime(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SCAN_GAP_TIME).getValue());
		computerScan.setScanHour0(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SCAN_HOUR0).getValue());
		computerScan.setScanHour1(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SCAN_HOUR1).getValue());
		computerScan.setScanHour2(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SCAN_HOUR2).getValue());
		computerScan.setUserChangedAlert(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_USER_CHANGED_ALERT).getValue());
		subnetList = monitorSubnetService.findAll();
		return SUCCESS;
	}
	
	public String saveComputerScanSetting(){
		
		monitorSystemParamService.saveComputerScan(computerScan);
		//扫描分区设置保存
		if(null != subnetList){
			for(MonitorSubnet subnet:subnetList){
				if(null != subnet){
					if(null==subnet.getScan())
						subnet.setScan(0);
					MonitorSubnet po = monitorSubnetService.findById(subnet.getId());
					if(null != po ){
						if(null == po.getScan()){
							po.setScan(0);
							monitorSubnetService.update(po);
						}else if(!po.getScan().equals(subnet.getScan())){
							po.setScan(subnet.getScan());
							monitorSubnetService.update(po);
						}
						
						
					}
				}
				
				
			}
		}
		return SUCCESS;
	}
	/**流量及CPU扫描 **/
	public String fluxScanSetting(){
		fluxScan = new FluxScan();
		fluxScan.setFluxSwitch(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_FLUX_SWITCH).getValue());
		fluxScan.setFluxScanGapTime(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_FLUX_SCAN_GAP_TIME).getValue());
		fluxScan.setFluxType(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_FLUX_TYPE).getValue());
		fluxScan.setHourDataKeep(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP).getValue());
		fluxScan.setMinuteDataKeep(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP).getValue());
		return SUCCESS;
	} 
	public String saveFluxScanSetting(){
		monitorSystemParamService.saveFluxScan(fluxScan);
		return SUCCESS;
	}
	/**流量及CPU扫描 end**/
	
	/**拓扑图**/
	public String topologyScanSetting(){
		topologyScan = new TopologyScan();
		topologyScan.setPollGap(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_POLL_GAP).getValue() );
		topologyScan.setMaxBroadcast(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAX_BROADCAST).getValue() );
		topologyScan.setMaxFlow(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAX_FLOW).getValue() );
		topologyScan.setBackColor( monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BACK_COLOR).getValue());
		topologyScan.setBackPicPath(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BACK_PIC_PATH).getValue() );
		return SUCCESS;
	}
	public String saveTopologyScanSetting() throws IOException{
		try {
        	String fileName= topologyScan.getUploadFileName();
        	//更换默认背景图片
        	if(null!=fileName){
        		int position = fileName.lastIndexOf(".");
        		String extension = fileName.substring(position);
            	String targetDirectory=ServletActionContext.getServletContext().getRealPath("images/monitor");
    			File target = new File(targetDirectory, "defaultBackPic"+extension);
    			FileUtils.copyFile(topologyScan.getUpload(), target);
    			topologyScan.setBackPicPath("images/monitor/defaultBackPic"+extension);
        	}
        	monitorSystemParamService.saveTopology(topologyScan);
			
		 }catch (Exception e) {
			   e.printStackTrace();
		 }
		return SUCCESS;
	}
	/**拓扑图 end**/
	
	/**故障扫描**/
	public String faultScanSetting(){
		faultScan = new FaultScan();
		faultScan.setCheckGap(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CHECK_GAP).getValue());
		faultScan.setErrorGap(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ERROR_GAP).getValue());
		return SUCCESS;
	}
	public String saveFaultScanSetting(){
		monitorSystemParamService.saveFaultScan(faultScan);
		return SUCCESS;
	}
	/**故障扫描 end**/
	
	/**系统其他设置**/
	public String otherSetting(){
		other = new OtherSetting();
		other.setTopN(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_TOP_N).getValue());
		other.setSnmpRetry(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SNMP_RETRY).getValue());
		other.setSnmpTimeOut(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SNMP_TIME_OUT).getValue());
		return SUCCESS;
	}
	
	public String saveOtherSetting(){
		monitorSystemParamService.saveOtherSetting(other);
		return SUCCESS;
	}
	/**系统其他设置 end**/
	
	/**Ping扫描设置**/
	public String pingScanSetting(){
		pingScan = new PingScan();
		pingScan.setPingDetailLife(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_DETAIL_LIFE).getValue());
		pingScan.setPingExcuteMode(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_EXCUTE_MODE).getValue());
		pingScan.setPingAutoExcuteMode(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_AUTO_EXCUTE_MODE).getValue());
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_LOOP_GAP);
		pingScan.setPingLoopGap(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_DAY_IN_WEEK);
		if(null != param && null != param.getValue() && !"".equals(param.getValue())){
			String[] days = param.getValue().split(",");
			pingScan.setDays(days);
		}
		pingScan.setPingStartTimeOfDay(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_START_TIME_OF_DAY).getValue());
		pingScan.setPingEndTimeOfDay(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_END_TIME_OF_DAY).getValue());
		pingScan.setTimePointList(monitorPingTimePointService.getAll());
		pingScan.setPingNum(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_NUM).getValue());
		pingScan.setPingReportLife(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_REPORT_LIFE).getValue());
		pingScan.setPingSize(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_SIZE).getValue());
		pingScan.setPingTimeout(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_TIMEOUT).getValue());
		return SUCCESS;
	}
	public String savePingScanSetting(){
		GregorianCalendar now = new GregorianCalendar();
		int dayOfWeek = now.get(GregorianCalendar.DAY_OF_WEEK);
		dayOfWeek = (dayOfWeek + 5) % 7;
		monitorSystemParamService.savePingScanSetting(pingScan);
		return SUCCESS;
	}
	
	public String deleteTimePoint(){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			monitorPingTimePointService.remove(Integer.parseInt(id));
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String saveTimePoint(){
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");
		if(null != hour && null !=minute && !"".equals(hour) && !"".equals(minute)){
			MonitorPingTimePoint po =new MonitorPingTimePoint();
			Timestamp t = new Timestamp(System.currentTimeMillis());
			t.setHours(Integer.parseInt(hour));
			t.setMinutes(Integer.parseInt(minute));
			po.setTimePoint(t);
			monitorPingTimePointService.save(po);
		}
		return SUCCESS;
	}
	/**Ping扫描设置 end**/
	
	/**报警设置**/
	public String alertSetting(){
		alertSystem = new AlertSystem();
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_EMAIL_SWTICH);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setEmailSwitch(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SMTP_SERVER);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setSmtpServer(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAIL_SENDER);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setMailSender(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MAIL_SENDER_PWD);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setMailSenderPwd(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MOBILE_SWTICH);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setMobileSwitch(param.getValue());

		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SMS_SN);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setSmsSn(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SOUND_SWTICH);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setSoundSwitch(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ARP_LIMEN);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setArpLimen(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_LIMIT_TO_ADD_LEVEL);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setLimitToAddLevel(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CHECK_GAP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setCheckGap(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ERROR_GAP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			alertSystem.setErrorGap(param.getValue());
		
		return SUCCESS;
	}
	
	public String saveAlertSetting(){
		
		monitorSystemParamService.saveAlertSetting(alertSystem);
		return SUCCESS;
	}
	/**报警设置 end**/
	
	/**报警策略设置**/
	public String alertPolicySetting(){
		alertPolicyList = monitorAlertPolicyService.getAll();
		subnetList = monitorSubnetService.findAll();
		deviceList = monitorSubnetService.getAllInSubnetDevice();
		return SUCCESS;
	}
	
	public String saveAlertPolicySetting(){
	 
		return SUCCESS;
	}
	/**解除策略**/
	public String relievePolicy(){
		 
		String deviceId = request.getParameter("deviceId");
		if( null != deviceId){
			String [] deviceIds = deviceId.split(",");
			for(int i =0 ;i < deviceIds.length; i++){
				monitorDeviceService.relievePolicy(Integer.parseInt(deviceIds[i]));
			}
		}
		return SUCCESS;
	}
	/**应用策略**/
	public String applyPolicy(){
		String policyId = request.getParameter("policyId");
		String deviceId = request.getParameter("deviceId");
		if( null!= policyId && null != deviceId){
			String [] deviceIds = deviceId.split(",");
			for(int i =0 ;i < deviceIds.length; i++){
				monitorDeviceService.applyPolicy(Integer.parseInt(policyId),Integer.parseInt(deviceIds[i]));
			}
		}
		return SUCCESS;
	}
	/** 修改应用策略 **/
	public String alertPolicy(){
		String policyId = request.getParameter("policyId");
		if(null == policyId)
			policyId = "1";
		if(null!=policyId){
			MonitorAlertPolicy monitorAlertPolicy = monitorAlertPolicyService.get(Integer.parseInt(policyId));
			if(null != monitorAlertPolicy){
				alertPolicy =new AlertPolicy();
				alertPolicy.setMonitorAlertPolicy(monitorAlertPolicy);
				//监测时间间隔
				MonitorSystemParam checkGap = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CHECK_GAP);
				if(null != checkGap && !"".equals(checkGap)){
					alertPolicy.setCheckGap(Integer.parseInt(checkGap.getValue()));
				}
				MonitorSystemParam errorGap = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ERROR_GAP);
				if(null != errorGap && !"".equals(errorGap)){
					alertPolicy.setErrorGap(Integer.parseInt(errorGap.getValue()));
				}
				//监测时间过滤 
				if(0 == alertPolicy.getMonitorAlertPolicy().getDailyStart()&& 0 == alertPolicy.getMonitorAlertPolicy().getDailyEnd()){
					alertPolicy.setIsDefinehours("0");
				}else{
					alertPolicy.setIsDefinehours("1");
				}
				if(null != alertPolicy.getMonitorAlertPolicy().getDayOfWeek() && !"".equals(alertPolicy.getMonitorAlertPolicy().getDayOfWeek())){
					String[] days = alertPolicy.getMonitorAlertPolicy().getDayOfWeek().split(",");
					alertPolicy.setDays(days);
				}
				//报警方式
				String[] alertSmallTypes = alertPolicy.getMonitorAlertPolicy().getAlertTypes().split(",");
				Integer[] types = new Integer[alertSmallTypes.length];
				for(int i=0;i<alertSmallTypes.length;i++){
					types[i] =  Integer.parseInt(alertSmallTypes[i]);
				}
				alertPolicy.setAlertSmallTypes1(types);
				alertPolicy.setAlertSmallTypes2(types);
				alertPolicy.setAlertSmallTypes3(types);
				List<MonitorAlertSmalltype> list = monitorAlertSmalltypeService.getAll();
				
				if(null != list && list.size()>0){
					List<MonitorAlertSmalltype> list1  =new ArrayList<MonitorAlertSmalltype>();
					List<MonitorAlertSmalltype> list2  =new ArrayList<MonitorAlertSmalltype>();
					List<MonitorAlertSmalltype> list3  =new ArrayList<MonitorAlertSmalltype>();
					for(MonitorAlertSmalltype alertSmalltype : list){
						Integer level = alertSmalltype.getLevel();
						switch(level){
							case 1:
								list1.add(alertSmalltype);
								break;
							case 2:
								list2.add(alertSmalltype);
								break;
							case 3:
								list3.add(alertSmalltype);
								break;
							case 4:
								list1.add(alertSmalltype);
								break;
							case 5:
								list2.add(alertSmalltype);
								break;
						}
					}
					
					alertPolicy.setAlertSmallTypeList1(list1);
					alertPolicy.setAlertSmallTypeList2(list2);
					alertPolicy.setAlertSmallTypeList3(list3);
				}
				
				//报警状态
				MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MOBILE_SWTICH);
				if(null != param){
					if("1" .equals(param.getValue()))
						alertPolicy.setMobileSwitch(true);
					else
						alertPolicy.setMobileSwitch(false);
					 
				}
				param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_EMAIL_SWTICH);
				if(null != param){
					if("1" .equals(param.getValue()))
						alertPolicy.setEmailSwitch(true);
					else
						alertPolicy.setEmailSwitch(false);
				}
				param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_SOUND_SWTICH);
				if(null != param){
					if("1" .equals(param.getValue()))
						alertPolicy.setSoundSwitch(true);
					else
						alertPolicy.setSoundSwitch(false);
				}
				//初始化用户列表
				List<Users> userList = userService.findByHql(" from Users where usertype <> 'user'");
				alertPolicy.setUserList(userList);
				 
				
			}
			
		}
		return SUCCESS;
	}
	/** 保存修改应用策略 **/
	public String saveAlertPolicy(){
		monitorAlertPolicyService.saveAlertPolicy(alertPolicy);
		return SUCCESS;
	}
	/** 删除应用策略 **/
	public String deleteAlertPolicy(){
		String policyId = request.getParameter("policyId");
		if(null != policyId && !"".equals(policyId))
			monitorAlertPolicyService.deleteAlertPolicy(Integer.parseInt(policyId));
		return SUCCESS;
	}
	/** 添加应用策略 **/
	@SuppressWarnings("deprecation")
	public String addAlertPolicy(){
		
			String name;
			try {
				name = new String(request.getParameter("name").getBytes("ISO8859_1"),"UTF8");
				if(null != name && !"".equals(name)){
					monitorAlertPolicyService.addAlertPolicy(name);
				}
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		
		
			
		return SUCCESS;
	}
	/** 创建策略副本 **/
	public String cloneAlertPolicy(){
		
		String policyId = request.getParameter("policyId");
		if(null != policyId && !"".equals(policyId))
			monitorAlertPolicyService.cloneAlertPolicy(Integer.parseInt(policyId));
		return SUCCESS;
	}
	/**报警策略设置 end**/
	
	/** 查看ARP报警白名单列表 **/
	public String arpAlertWhiteMacSetting(){
		this.arpAlertWhiteMacList = monitorArpAlertWhiteMacService.getAll();

		return SUCCESS;
	}
	
	public String deleteArpAlertWhileMac(){
		String id = request.getParameter("id");
		if(null != id && id.length()>0){
			String[] ids = id.split(",");
			for(int i=0; i<ids.length; i++){
				Integer macId = Integer.parseInt(ids[i]);
				monitorArpAlertWhiteMacService.remove(macId);
			}
		}
		return SUCCESS;
	}
	/** 增加ARP报警白名单 **/
	public String addArpAlertWhileMac(){
		
		if(null != mac && mac.length()>0){
			MonitorArpAlertWhiteMac po = new MonitorArpAlertWhiteMac(mac);
			monitorArpAlertWhiteMacService.save(po);
		}
		return SUCCESS;
	}
	/** 数据保存时间设置 **/
	public String dataKeepTime(){
		dataKeepTime = new DataKeepTime();
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ALERT_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			dataKeepTime.setAlertDataKeep(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_HOUR_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			dataKeepTime.setHourDataKeep(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_MINUTE_DATA_KEEP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			dataKeepTime.setMinuteDataKeep(param.getValue());
		return SUCCESS;
	}
	public String saveDataKeepTime(){
		monitorSystemParamService.saveDataKeepTime(dataKeepTime);
		return SUCCESS;
	}
	/**数据保存时间设置 end**/
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}
	public ComputerScan getComputerScan() {
		return computerScan;
	}
	public void setComputerScan(ComputerScan computerScan) {
		this.computerScan = computerScan;
	}
	public FluxScan getFluxScan() {
		return fluxScan;
	}
	public void setFluxScan(FluxScan fluxScan) {
		this.fluxScan = fluxScan;
	}
	public TopologyScan getTopologyScan() {
		return topologyScan;
	}
	public void setTopologyScan(TopologyScan topologyScan) {
		this.topologyScan = topologyScan;
	}
	public FaultScan getFaultScan() {
		return faultScan;
	}
	public void setFaultScan(FaultScan faultScan) {
		this.faultScan = faultScan;
	}
	public OtherSetting getOther() {
		return other;
	}
	public void setOther(OtherSetting other) {
		this.other = other;
	}
	public PingScan getPingScan() {
		return pingScan;
	}
	public void setPingScan(PingScan pingScan) {
		this.pingScan = pingScan;
	}
	public MonitorPingTimePointService getMonitorPingTimePointService() {
		return monitorPingTimePointService;
	}
	public void setMonitorPingTimePointService(
			MonitorPingTimePointService monitorPingTimePointService) {
		this.monitorPingTimePointService = monitorPingTimePointService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	public AlertSystem getAlertSystem() {
		return alertSystem;
	}
	public void setAlertSystem(AlertSystem alertSystem) {
		this.alertSystem = alertSystem;
	}
	public MonitorAlertPolicyService getMonitorAlertPolicyService() {
		return monitorAlertPolicyService;
	}
	public void setMonitorAlertPolicyService(
			MonitorAlertPolicyService monitorAlertPolicyService) {
		this.monitorAlertPolicyService = monitorAlertPolicyService;
	}
	public List<MonitorAlertPolicy> getAlertPolicyList() {
		return alertPolicyList;
	}
	public void setAlertPolicyList(List<MonitorAlertPolicy> alertPolicyList) {
		this.alertPolicyList = alertPolicyList;
	}
	public List<MonitorSubnet> getSubnetList() {
		return subnetList;
	}
	public void setSubnetList(List<MonitorSubnet> subnetList) {
		this.subnetList = subnetList;
	}
	public List<MonitorSubnetDevice> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<MonitorSubnetDevice> deviceList) {
		this.deviceList = deviceList;
	}
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}
	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	public AlertPolicy getAlertPolicy() {
		return alertPolicy;
	}
	public void setAlertPolicy(AlertPolicy alertPolicy) {
		this.alertPolicy = alertPolicy;
	}
	public MonitorAlertSmalltypeService getMonitorAlertSmalltypeService() {
		return monitorAlertSmalltypeService;
	}
	public void setMonitorAlertSmalltypeService(
			MonitorAlertSmalltypeService monitorAlertSmalltypeService) {
		this.monitorAlertSmalltypeService = monitorAlertSmalltypeService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public DataKeepTime getDataKeepTime() {
		return dataKeepTime;
	}
	public void setDataKeepTime(DataKeepTime dataKeepTime) {
		this.dataKeepTime = dataKeepTime;
	}
	public List<MonitorArpAlertWhiteMac> getArpAlertWhiteMacList() {
		return arpAlertWhiteMacList;
	}
	public void setArpAlertWhiteMacList(
			List<MonitorArpAlertWhiteMac> arpAlertWhiteMacList) {
		this.arpAlertWhiteMacList = arpAlertWhiteMacList;
	}
	public MonitorArpAlertWhiteMacService getMonitorArpAlertWhiteMacService() {
		return monitorArpAlertWhiteMacService;
	}
	public void setMonitorArpAlertWhiteMacService(
			MonitorArpAlertWhiteMacService monitorArpAlertWhiteMacService) {
		this.monitorArpAlertWhiteMacService = monitorArpAlertWhiteMacService;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	 
	
	
}
