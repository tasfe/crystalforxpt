package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.dao.MonitorTopologyNodeDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorTopologyNode;
import com.combanc.monitor.util.ApplicationContextUtil;

public class GetDeviceInfo  extends Thread {
	
	private List<MonitorDevice> deviceList ;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	private MonitorTopologyNodeDAO monitorTopologyNodeDAO;
	
	public GetDeviceInfo(List<MonitorDevice> deviceList ){
		this.deviceList = deviceList;
		deviceSnmpQuery = (DeviceSnmpQuery) ApplicationContextUtil.getContext().getBean("DeviceSnmpQuery");
		monitorTopologyNodeDAO = (MonitorTopologyNodeDAO) ApplicationContextUtil.getContext().getBean("MonitorTopologyNodeDAO");
	}
	public void run(){
		for(MonitorDevice device:deviceList){
			if(null != device.getMonitorDeviceType() && MainConstants.DEVICE_VIRTUAL == device.getMonitorDeviceType().getCode())
				continue;
			MonitorTopologyNode nodeInfo = new MonitorTopologyNode();
			List<MonitorTopologyNode> nodeInfoList=monitorTopologyNodeDAO.findByProperty("device.id", device.getId());
			if(nodeInfoList.size()>0){
				nodeInfo = nodeInfoList.get(0);
			}else{
				nodeInfo.setDevice(device);
			}
			String cpuLoadOid = nodeInfo.getCpuOid();
			String ret;
			if(null == cpuLoadOid|| "".equals(cpuLoadOid)){
				cpuLoadOid=deviceSnmpQuery.getVendorCpuOid(device);
				nodeInfo.setCpuOid(cpuLoadOid);
			}
			if ("超时".equals(cpuLoadOid)) {
				nodeInfo.setStatus("超时");
				//向心流量
			} else {
				this.getNodeInfo(device, cpuLoadOid, nodeInfo);
				if(!("过载".equals(nodeInfo.getStatus()) || "正常".equals(nodeInfo.getStatus()))) {
					if(cpuLoadOid.indexOf(".1.3.6.1.4.1.25506") >=0) {
						cpuLoadOid = cpuLoadOid.replaceFirst("25506", "2011.10");
						this.getNodeInfo(device, cpuLoadOid, nodeInfo);
					}
				}
			}
			String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
			nodeInfo.setTime(time);//轮询时间
			
			monitorTopologyNodeDAO.saveOrUpdate(nodeInfo);
		}
	}
	
	
	private void getNodeInfo (MonitorDevice device, String cpuLoadOid,MonitorTopologyNode nodeInfo){
		
		// 首先读取系统运行时间并获得响应时间
		

		long timeUsed = System.currentTimeMillis();
		String result = deviceSnmpQuery.getSysUpTime(device);
		timeUsed = System.currentTimeMillis() - timeUsed;
		
		if (result == null) {
			nodeInfo.setReplyTime("无数据");			//响应时间
			nodeInfo.setRunTime("无数据");			//运行时间
			nodeInfo.setCpu("无数据");				//CPU
			nodeInfo.setCpuAverage("无数据");		//CPU均值
			nodeInfo.setCpuPeak("无数据");			//CPU峰值
			nodeInfo.setPeakTime("无数据");			//峰值时间
			nodeInfo.setStatus("超时");				//状态
			return;
		}  
		
		nodeInfo.setReplyTime(timeUsed + " ms");//响应时间
		nodeInfo.setRunTime(Tools.timeToChinese(result));			//运行时间
		
		result=deviceSnmpQuery.getCpuRunTime(device,cpuLoadOid);
		
		if(result!="-1"){
			// 记录CPU当前值
			Long now= Long.parseLong(result);
			nodeInfo.setCpu(result);
			// 刷新平均值
			nodeInfo.setGrandTotal(nodeInfo.getGrandTotal()+now);
			nodeInfo.setCount(nodeInfo.getCount()+1);
			nodeInfo.setCpuAverage(String.valueOf(nodeInfo.getGrandTotal()/nodeInfo.getCount()));
			// 刷新峰值及时间
			if(nodeInfo.getCpuPeak().equals("")||nodeInfo.getCpuPeak().equals("无数据")||now >= Long.parseLong(nodeInfo.getCpuPeak())){
				nodeInfo.setCpuPeak(nodeInfo.getCpu());
				nodeInfo.setPeakTime(nodeInfo.getTime());
			} 
			// 如果是AKCP温度传感器则使用温度，否则使用CPU的阈值。
			long limen;
			if (nodeInfo.getCpuOid().equals(".1.3.6.1.4.1.3854.1.2.2.1.16.1.3.0"))
				limen = MainConstants.TEMP_LIMEN;
			else
				limen = MainConstants.CPU_LIMEN;
			if (now >= limen)
				nodeInfo.setStatus("过载");
			else
				nodeInfo.setStatus("正常");
		} else {
			nodeInfo.setStatus("超时");
		}
		
	}

}
