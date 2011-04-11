package com.combanc.monitor.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.monitor.algorithm.LinkPortDiscovery;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorRealtimeCpu;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;
import com.combanc.monitor.pojo.MonitorRealtimeUse;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetDevice;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorComputerService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorRealtimeCpuService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorRealtimeUseService;
import com.combanc.monitor.service.MonitorSubnetService;

public class MonitorSubnetAction extends BaseActionSupport implements ServletRequestAware{
	
	HttpServletRequest request = null;
	private MonitorSubnet monitorSubnet;
	private Integer subnetId;
	private List<MonitorSubnet> monitorSubnetList;
	private MonitorSubnetService monitorSubnetService;
	private MonitorDeviceService monitorDeviceService;
	private MonitorLinkportService monitorLinkportService;
	private MonitorComputerService monitorComputerService;
	private MonitorRealtimeUseService monitorRealtimeUseService;
	private MonitorRealtimeCpuService monitorRealtimeCpuService;
	private MonitorRealtimePortflowService monitorRealtimePortflowService;
	private MonitorAlertService monitorAlertService;
	private List<MonitorDevice> selectedDeviceList;
	private List<MonitorDevice> availableDeviceList;
	private List<MonitorLinkport> linkportList;
	private String selectedDeviceIds;
	private String actionURI;
	private int page;
	private PageBean pageBean;
	private LinkPortDiscovery linkportDiscovery;
	private List<MonitorSubnet> parentSubnetList=new ArrayList<MonitorSubnet>();
	

	
    
	public MonitorRealtimePortflowService getMonitorRealtimePortflowService() {
		return monitorRealtimePortflowService;
	}
	public void setMonitorRealtimePortflowService(
			MonitorRealtimePortflowService monitorRealtimePortflowService) {
		this.monitorRealtimePortflowService = monitorRealtimePortflowService;
	}
	public MonitorRealtimeCpuService getMonitorRealtimeCpuService() {
		return monitorRealtimeCpuService;
	}
	public void setMonitorRealtimeCpuService(
			MonitorRealtimeCpuService monitorRealtimeCpuService) {
		this.monitorRealtimeCpuService = monitorRealtimeCpuService;
	}
	public MonitorRealtimeUseService getMonitorRealtimeUseService() {
		return monitorRealtimeUseService;
	}
	public void setMonitorRealtimeUseService(
			MonitorRealtimeUseService monitorRealtimeUseService) {
		this.monitorRealtimeUseService = monitorRealtimeUseService;
	}
	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}
	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}
	public MonitorComputerService getMonitorComputerService() {
		return monitorComputerService;
	}
	public void setMonitorComputerService(
			MonitorComputerService monitorComputerService) {
		this.monitorComputerService = monitorComputerService;
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

	public Integer getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(Integer subnetId) {
		this.subnetId = subnetId;
	}
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}
	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
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
	public MonitorSubnet getMonitorSubnet() {
		return monitorSubnet;
	}
	public void setMonitorSubnet(MonitorSubnet monitorSubnet) {
		this.monitorSubnet = monitorSubnet;
	}
	public List<MonitorSubnet> getMonitorSubnetList() {
		return monitorSubnetList;
	}
	public void setMonitorSubnetList(List<MonitorSubnet> monitorSubnetList) {
		this.monitorSubnetList = monitorSubnetList;
	}
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}
	public LinkPortDiscovery getLinkportDiscovery() {
		return linkportDiscovery;
	}
	public void setLinkportDiscovery(LinkPortDiscovery linkportDiscovery) {
		this.linkportDiscovery = linkportDiscovery;
	}
	public String getActionURI() {
		return actionURI;
	}
	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
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
	
	@Action(description="查看分区列表")
	public String list() throws Exception {
	
       monitorSubnetList=monitorSubnetService.findAll();
       /* StringBuilder gridTreeList=new StringBuilder();
        for(MonitorSubnet subnet:monitorSubnetList) {
        	String opration1="<a href=\'updateInput.action?subnetId="+subnet.getId()+"\'>修改</a>";
        	String opration2="<a href=\'subnetDelete.action?subnetId="+subnet.getId()+"\'>删除</a>";
        	String opration3="<a href=\'#\' onclick=\'openTopology("+subnet.getId()+")\'>打开拓扑</a>";
        	String opration4="<a href=\'#\' onclick=\'linkportList("+subnet.getId()+")\'>查看互连端口</a>";
        	String opration5="<a href=\'#\' onclick=\'layout("+subnet.getId()+")\'>布局</a>";
        	Integer countDevice = monitorSubnetService.countDeviceBySubnet(subnet.getId());//设备总数
        	Integer countLinkport = monitorSubnetService.countLinkportBySubnet(subnet.getId());//设备总数
        	
        	gridTreeList.append("  dataList=new Array(\"")
			.append(subnet.getName())
			.append("\",\"")
			.append(null==subnet.getMonitorSubnetType()?"":subnet.getMonitorSubnetType().getName())
			.append("\",\"")
			.append(countDevice)
			.append("\",\"")
			.append(countLinkport)
			.append("\",\"")
			.append(null==subnet.getCenterIp()?"":subnet.getCenterIp())
			.append("\",\"")
			.append(null==subnet.getScan()?"":(1==subnet.getScan()?"是":"否" ))
			.append("\",\"")
			.append(null==subnet.getStart()?"":(1==subnet.getStart()?"是":"否" ))
			.append("\",\"")
			.append(null==subnet.getNote1()?"":subnet.getNote1())
			.append("\",\"")
			.append(opration1+"&nbsp&nbsp&nbsp"+opration2+"&nbsp&nbsp&nbsp"+opration3+"&nbsp&nbsp&nbsp"+opration4+"&nbsp&nbsp&nbsp"+opration5)
			.append("\");")
			.append("	gridTree.addGirdNode(dataList,")
			.append(subnet.getId())
			.append(",")
			.append(null==subnet.getParentSubnet()?-1:subnet.getParentSubnet().getId())
			.append(",null,")
			.append(1)
			.append(");" );
		}
        request.setAttribute("gridTreeList", gridTreeList.toString());*/
        
		return "success";
	}
	
	
	public String addInput() {
		monitorSubnet=new MonitorSubnet();
		this.parentSubnetList=monitorSubnetService.findAll();
		return "success";
	}
	
	@Action(description="新增分区")
	public String save() {
		if(null==monitorSubnet.getScan())
			monitorSubnet.setScan(0);
		if(null==monitorSubnet.getStart())
			monitorSubnet.setStart(0);
		
		if(null!=monitorSubnet.getMonitorSubnetType()&&null!=monitorSubnet.getMonitorSubnetType().getCode())
			monitorSubnet.setMonitorSubnetType(monitorSubnetService.getSubnetType(monitorSubnet.getMonitorSubnetType().getCode()));
		else
			monitorSubnet.setMonitorSubnetType(null);
		if(monitorSubnet.getParentSubnet().getId()==0)
			monitorSubnet.setParentSubnet(null);
		else
			monitorSubnet.setParentSubnet(monitorSubnetService.findById(monitorSubnet.getParentSubnet().getId()));
		monitorSubnetService.save(monitorSubnet);
		this.subnetId = monitorSubnet.getId();
		return "success";
	}
	
	 
	
	
	public String updateInput() throws Exception {
		monitorSubnet = monitorSubnetService.get(subnetId);
		selectedDeviceList = monitorSubnetService.getSelectedDeviceBySubnet(Integer.valueOf(subnetId));
		
		 
		this.parentSubnetList=monitorSubnetService.getAvalibaleParentList(monitorSubnet.getId());
		 
		return "success";
	}
	
	@Action(description="编辑分区")
	public String update() throws Exception {
		if(null==monitorSubnet.getScan())
			monitorSubnet.setScan(0);
		if(null==monitorSubnet.getStart())
			monitorSubnet.setStart(0);
		if(null!=monitorSubnet.getMonitorSubnetType()&&null!=monitorSubnet.getMonitorSubnetType().getCode())
			monitorSubnet.setMonitorSubnetType(monitorSubnetService.getSubnetType(monitorSubnet.getMonitorSubnetType().getCode()));
		else
			monitorSubnet.setMonitorSubnetType(null);
		if(monitorSubnet.getParentSubnet().getId()==0)
			monitorSubnet.setParentSubnet(null);
		else
			monitorSubnet.setParentSubnet(monitorSubnetService.findById(monitorSubnet.getParentSubnet().getId()));
		monitorSubnetService.update(monitorSubnet); 
		return "success";
	}
	public void validateUpdate(){
		
	}
	public String deleteDevice(){
		
		String deviceIds=request.getParameter("deviceIds");
		String method=request.getParameter("method");
		if(null != this.subnetId)
			this.monitorSubnet = this.monitorSubnetService.findById(subnetId);
		if(null!=method&&"add".equals(method)){
			
			if(null!=deviceIds&&!deviceIds.trim().equals("")){
				String[] id=deviceIds.split(",");
				if(id.length>0){
					List<MonitorDevice> list=new ArrayList<MonitorDevice>();
					for(int i=0;i<id.length;i++){
						MonitorDevice model=monitorDeviceService.findById(Integer.valueOf(id[i]));
						list.add(model);
							
					}
					monitorSubnetService.batchInsert(this.monitorSubnet, list);
				}
				
				
			}
		} else {
			if(null!=deviceIds&&!deviceIds.trim().equals("")){
				String[] id=deviceIds.split(",");
				if(id.length>0){
					List<MonitorDevice> list=new ArrayList<MonitorDevice>();
					for(int i=0;i<id.length;i++){
						MonitorDevice model=monitorDeviceService.findById(Integer.valueOf(id[i]));
						list.add(model);
							
					}
					String message = monitorSubnetService.batchDetele(this.monitorSubnet, list);
					request.setAttribute("message", message);
				}

			}
		}
		//添加或者删除设备之后需要重新布局一下拓扑图
		monitorSubnetService.subnetLayout(this.monitorSubnet.getId());
		selectedDeviceList=monitorSubnetService.getSelectedDeviceBySubnet(this.monitorSubnet.getId());
		return "success";
	}
	

	 
	@Action(description="删除分区")
	public String delete() {
		if(null != this.subnetId)
			monitorSubnetService.remove(this.subnetId);
		return "success";
	}
	
	public String detail() throws Exception {
		monitorSubnet=monitorSubnetService.get(Integer.valueOf(subnetId));
		this.parentSubnetList=monitorSubnetService.getAvalibaleParentList(monitorSubnet.getId());
		Integer topN = 10;
		List<MonitorComputer> computerList = new ArrayList<MonitorComputer>();
		
		computerList = this.monitorComputerService.findLatestBySubnet(monitorSubnet.getId(),topN);
		List<MonitorAlert> alertList = this.monitorAlertService.findLatestBySubnet(monitorSubnet.getId(),topN);
		List<MonitorRealtimeUse> realtimeUseList = monitorRealtimeUseService.findTopN(monitorSubnet.getId(),topN);
		List<MonitorRealtimeCpu> realtimeCpuList = monitorRealtimeCpuService.findTopN(monitorSubnet.getId(),topN);
		List<MonitorRealtimePortflow> realtimeLinkPortFlowList = this.monitorRealtimePortflowService.findTopN(monitorSubnet.getId(),topN*2, 1);
		List<MonitorLinkport> linkportList=monitorLinkportService.findEffectiveLinkports(monitorSubnet.getId());
		Iterator it=realtimeLinkPortFlowList.iterator();
		int n = 0;
		while( it.hasNext() ){
			MonitorRealtimePortflow model= (MonitorRealtimePortflow) it.next();
			boolean isEffective=false;
			for(MonitorLinkport linkport: linkportList){
				if(model.getDeviceIp().equals(linkport.getIp())
						&&model.getInterface_().equals(linkport.getInterface_())){
					isEffective=true;
					break;
				}
			}
			if(!isEffective||n>=topN)
				it.remove();
			else
				n++;
			
		}
		List<MonitorRealtimePortflow> realtimeUsePortFlowList =  monitorRealtimePortflowService.findTopN(subnetId, topN, 0);
		request.setAttribute("computerList", computerList);
		request.setAttribute("alertList", alertList);
		request.setAttribute("realtimeUseList", realtimeUseList);
		request.setAttribute("realtimeCpuList", realtimeCpuList);
		request.setAttribute("realtimeLinkPortFlowList", realtimeLinkPortFlowList);
		request.setAttribute("realtimeUsePortFlowList", realtimeUsePortFlowList);
		return "success";
	}
	
	public String devices() throws Exception {
		selectedDeviceList=monitorSubnetService.getSelectedDeviceBySubnet(Integer.valueOf(subnetId));
		return "success";
	}
	public String dashboard() throws Exception {
		selectedDeviceList=monitorSubnetService.getSelectedDeviceBySubnet(Integer.valueOf(subnetId));
		return "success";
	}
	//互联端口发现
	public String linkportDiscovery() throws Exception {
		String algorithm = request.getParameter("algorithm");//传入算法 1:通用算法，2:思科CDP，3:华为NDP
		String centerIP = request.getParameter("centerIP");// 当有多个三层设备时，所选核心IP地址
		monitorSubnet=monitorSubnetService.get(Integer.valueOf(subnetId));
		linkportDiscovery.init(monitorSubnet, centerIP);
		linkportDiscovery.run();
		return SUCCESS;
	}
	
	public String selectDevices(){
		String type = request.getParameter("type"); 
		String fromIpAddress = request.getParameter("fromIpAddress"); 
		String endIpAddress = request.getParameter("endIpAddress"); 
		String exceptSubnetId = null == subnetId ? "":String.valueOf(subnetId);//不包含在这个分区
		String selectSubnetId = null == monitorSubnet ? "":String.valueOf(monitorSubnet.getId());//包含在选择的分区
		availableDeviceList=monitorSubnetService.getAvailableDevice(exceptSubnetId,selectSubnetId,type, fromIpAddress, endIpAddress);
		return SUCCESS;
		
	}
	/** 查看互连端口 **/
	public String linkportList() throws Exception{
		String condition = "";
		if (null != subnetId ) {
			condition = " and monitorSubnet.id="+subnetId;
		}
	    this.linkportList = monitorLinkportService.findByCondition(condition);
		return SUCCESS;
	}
	/** 删除互连端口 **/
	public String deleteLinkport() throws Exception{
		String id = request.getParameter("id");
		if (null != id && !"".equals(id)) {
			Integer subnetId = 0;
			String[] ids = id.split(",");
			if(ids.length>0){
				for(int i=0; i<ids.length; i++){
					
					MonitorLinkport linkport = monitorLinkportService.get(Integer.valueOf(ids[i]));
					if(subnetId == 0)
						subnetId = linkport.getMonitorSubnet().getId();
					monitorLinkportService.delete(linkport);
				}
					
			}
			//添加或者删除设备之后需要重新布局一下拓扑图
			if(subnetId > 0)
				monitorSubnetService.subnetLayout(subnetId);
		}
		return SUCCESS;
	}
	/** 导入其他子网的互连端口 **/
	public String selectLinkport(){	
		 
		 
		if(null != this.monitorSubnet ){
			this.linkportList = monitorLinkportService.findBySubnetId(this.monitorSubnet.getId()); 
		}else{
			String	condition = " and monitorSubnet.id  <>"+this.subnetId;	
			this.linkportList = monitorLinkportService.findByCondition(condition); 
		}	
		return SUCCESS;
		
	}
 
	public String addLinkports(){
		
		String linkportIds=request.getParameter("linkportIds");
		 
		String message = "";
			if(null!=subnetId){
				String[] id=linkportIds.split(",");
				if(id.length>0){
					List<String> ipList = new ArrayList<String>();
					List<MonitorLinkport> oldLinkportList = monitorLinkportService.findBySubnetId(subnetId); 
					 
					MonitorSubnet subnet = monitorSubnetService.get(subnetId);
					
					Integer successId = 0;			//成功导入互连接口数
					Integer successSubnetDevice =0;	//成功导入设备数
					Integer failerSubnetDevice =0;	//由于重复无法导入的设备数
					Integer failerId =0;			//由于重复无法导入的互连接口记录数
					for(int i=0;i<id.length;i++){
						Integer linkportId = Integer.valueOf(id[i]);
						MonitorLinkport model = monitorLinkportService.get(linkportId);
						boolean exsist = false;
						for(MonitorLinkport old : oldLinkportList){
							if(model.getIp().equals(old.getIp())
									&& model.getInterface_().equals(old.getInterface_())
									&& model.getInterface_().equals(old.getInterface_())
									&& model.getInterface_().equals(old.getInterface_())){
								exsist = true;
								break;
							} 
								
						}
						if(exsist){
							failerId++;
						}else{
							model.setMonitorSubnet(subnet);
							monitorLinkportService.save(model);
							successId++;
						}
						//将互连接口的IP加入ipList，重复的除外
						if(!"".equals(model.getIp()) && !ipList.contains(model.getIp()))
							ipList.add(model.getIp());
						if(!"".equals(model.getDownlinkIp()) && !ipList.contains(model.getDownlinkIp()))
							ipList.add(model.getDownlinkIp());

					}
					//遍历ipList，判断分区是否包含此设备，不包含的话加进分区
					for(String ip : ipList){
						if(checkExsist(subnet,ip))
							failerSubnetDevice++;
						else
							successSubnetDevice++;
					}
					
					//添加或者删除设备之后需要重新布局一下拓扑图
					monitorSubnetService.subnetLayout(subnetId);
					
					//弹出提示信息
					
					message = "成功导入"+successId+"条互连端口记录";
					if(successSubnetDevice>0)
						message += "，及"+successSubnetDevice+"个相关的设备！";
					if(failerId > 0||failerSubnetDevice>0){
						if(failerId>0 && failerSubnetDevice>0)
							message += " 有"+failerId+"条互连端口记录条记录 "+failerSubnetDevice+"个相关设备由于重复而无法导入";
						else if(failerId>0 )
							message += " 有"+failerId+"条互连端口记录条记录由于重复而忽略！";
						else if(failerSubnetDevice>0 )
							message += " 有"+failerSubnetDevice+"个相关设备由于重复而忽略！";
					}
									
					request.setAttribute("message", message);
					
					
				}
				
				
			}
		
		return SUCCESS;
	}
	/**检查分区内是否有此ip的设备，如果没有添加上**/
	public boolean checkExsist(MonitorSubnet subnet,String ip){
		
		List<MonitorDevice> deviceList = monitorDeviceService.findByIp(ip);
		if(deviceList.size()>0){
			MonitorDevice device = deviceList.get(0);
			List result = monitorSubnetService.findBySubnetAndIp(subnet.getId(),ip);
			if(result.size()==0){
				MonitorSubnetDevice po =new MonitorSubnetDevice();
				po.setMonitorDevice(device);
				po.setMonitorSubnet(subnet);
				if(null != device.getMonitorDeviceType()){
					po.setIcon(device.getMonitorDeviceType().getIcon());
				}
				po.setMonitored(1);
				po.setSelected(1);
				po.setIsLink(0);
				monitorSubnetService.saveSubnetDevice(po);
				return false;
			}
			else
				return true;
		}
		else 
			return true;
		 
		
		
		
	}
	public String initTopology() throws Exception {
		return SUCCESS;
	}
	 
	public List<MonitorDevice> getAvailableDeviceList() {
		return availableDeviceList;
	}
	public void setAvailableDeviceList(List<MonitorDevice> availableDeviceList) {
		this.availableDeviceList = availableDeviceList;
	}
	public String getSelectedDeviceIds() {
		return selectedDeviceIds;
	}
	public void setSelectedDeviceIds(String selectedDeviceIds) {
		this.selectedDeviceIds = selectedDeviceIds;
	}
	public List<MonitorDevice> getSelectedDeviceList() {
		return selectedDeviceList;
	}
	public void setSelectedDeviceList(List<MonitorDevice> selectedDeviceList) {
		this.selectedDeviceList = selectedDeviceList;
	}
	public List<MonitorSubnet> getParentSubnetList() {
		return parentSubnetList;
	}
	public void setParentSubnetList(List<MonitorSubnet> parentSubnetList) {
		this.parentSubnetList = parentSubnetList;
	}
	
	 
	
	
}
