package com.combanc.monitor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.monitor.algorithm.DataPollTools;
import com.combanc.monitor.algorithm.tool.CpuPollTool;
import com.combanc.monitor.constants.OidConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.service.MonitorDeviceService;

public class MonitorRealTimeDataAction  extends BaseActionSupport implements ServletRequestAware {
	
	HttpServletRequest request = null;
	
	private MonitorDeviceService monitorDeviceService;
	
	DataPollTools dataPollTools = new DataPollTools();
	
	private long preSendData;
	
	public long getPreSendData() {
		return preSendData;
	}
	public void setPreSendData(long preSendData) {
		this.preSendData = preSendData;
	}
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}
	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getRealTimeCpuData(){
		String ip = request.getParameter("ip");
		String value = "||";
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			if(null != device){
				CpuPollTool cpuPollTool = new CpuPollTool();
				String	ret = String.valueOf(cpuPollTool.getCpuLoad(ip, device.getReadCommunity(), null));
				value = ret;
			}
			
		}
		
	    
	    Calendar cal = Calendar.getInstance(); 
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
	    String timeLabel = sdf.format(cal.getTime()); 
	  //数据格式 
	    String dataParameters = "&label=" +timeLabel+ "&value=" +value; 
	    HttpServletResponse response = ServletActionContext.getResponse();
	    try {
			PrintWriter out=response.getWriter();
			out.print(dataParameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return null;
	}

	public String getRealTimeAkcpData(){
		String value = "||";
		String ip = request.getParameter("ip");
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			if(null != device){
				CpuPollTool cpuPollTool = new CpuPollTool();
				SnmpTarget target = new SnmpTarget();
				float result[] = cpuPollTool.getAkcpData(target, device.getIp(), device.getReadCommunity());
				if( result[0] > -1.0)
					value = result[0]+"|"+result[1];
			}
			
		}
		
		 	Calendar cal = Calendar.getInstance(); 
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
		    String timeLabel = sdf.format(cal.getTime()); 
		  //数据格式 
		    String dataParameters = "&label=" +timeLabel+ "&value=" +value; 
		    HttpServletResponse response = ServletActionContext.getResponse();
		    try {
				PrintWriter out=response.getWriter();
				out.print(dataParameters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    return null;
	}
	
	public String getRealTimeInterfaceData(){
		String value = "0|0|0";
		String ip = request.getParameter("ip");
		String ifIndex = request.getParameter("ifIndex");
		String fluxType = request.getParameter("fluxType");
		HttpSession session = request.getSession();
		Long pre0 = (Long) session.getAttribute("pre0");
		Long pre1 = (Long) session.getAttribute("pre1");
		long[] preData = new long[2];// 前次发送、接收数据
		
		
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			SnmpTarget target = new SnmpTarget();
			String counter64 = "";
			String c64 = device.getNote1();
			if (c64 != null && c64.length() > 0)
				counter64 = c64.toUpperCase();
			String[] result = dataPollTools.getFluxData(target, ip, ifIndex, device.getReadCommunity(), counter64);
			
			long[] nowData = new long[2];// 当前发送、接收
			long[] gap = new long[3];// 前后两次差值
			long[] disp = new long[3];// 显示双向、发送、接收数据
			
			if(null != result){
				if (fluxType.equals("流量")) { //流量
					nowData[0] = Long.parseLong(result[0]);
					nowData[1] = Long.parseLong(result[1]);
				 
				}else if(fluxType.equals("单播包")){ //单播包
					nowData[0] = Long.parseLong(result[2]);
					nowData[1] = Long.parseLong(result[3]);
					
				}else if(fluxType.equals("广播包")){ //广播包
					nowData[0] = Long.parseLong(result[4]);
					nowData[1] = Long.parseLong(result[5]);
					
				}else if(fluxType.equals("错包")){  //错包
					nowData[0] = Long.parseLong(result[6]);
					nowData[1] = Long.parseLong(result[7]);
					
				}else if(fluxType.equals("丢包")){ //丢包
					nowData[0] = Long.parseLong(result[8]);
					nowData[1] = Long.parseLong(result[9]);
				}
				request.getSession().setAttribute("pre0", nowData[0]);
				request.getSession().setAttribute("pre1", nowData[1]);
				
				if (null == pre0 ) {// 初始化第一组数据
					preData[0] = nowData[0];
					preData[1] = nowData[1];
				}else{
					preData[0] = pre0;
					preData[1] = pre1;
				}
				
				for (int i = 0; i < 2; i++)
					if (nowData[i] >= preData[i])
						gap[i] = nowData[i] - preData[i];
					else
						gap[i] = nowData[i] + Long.parseLong("4294967296") - preData[i];
				
				if (fluxType.equals("单播包") || fluxType.equals("广播包")) {
					disp[1] = gap[0] * 1000 / 3000;// 显示发送值
					disp[2] = gap[1] * 1000 / 3000;// 显示接收值
					disp[0] = disp[1] + disp[2];// 显示双向值
				}

				if (fluxType.equals("流量")) {
					disp[1] = gap[0] * 8000 / 3000;// 显示发送值
					disp[2] = gap[1] * 8000 / 3000;// 显示接收值
					disp[0] = disp[1] + disp[2];// 显示双向值
				}
				if (fluxType.equals("错包") || fluxType.equals("丢包")) {
					disp[1] = gap[0];// 显示发送值，为间隔期间值，不是秒速率
					disp[2] = gap[1];// 显示接收值
					disp[0] = disp[1] + disp[2];// 显示双向值
				}
				
				value = disp[0]+"|"+disp[1]+"|"+disp[2];
			}
			
			
			
			
		}
		
		 	Calendar cal = Calendar.getInstance(); 
		    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
		    String timeLabel = sdf.format(cal.getTime()); 
		  //数据格式 
		    String dataParameters = "&label=" +timeLabel+ "&value=" +value; 
		    HttpServletResponse response = ServletActionContext.getResponse();
		    try {
				PrintWriter out=response.getWriter();
				out.print(dataParameters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    return null;
		
		 
	}
	
	public String getRealTimeAvailabilityData(){
		
		String ip = request.getParameter("ip");
		String type = request.getParameter("type");
		
		//数据格式 
		int b=0;//产生0-1000的整数随机数

	    String dataParameters = "&value=" +b; 
	    if(null != type ){
	    	if(type.equals("1"))
	    		dataParameters = "&value=" +getRealTimeCpuAvailability(ip);
	    	else if(type.equals("2")){
	    		float[] result = getRealTimeAkcpAvailability(ip);
				if(result.length>0){
					if(result[0]>-1)
						dataParameters = "&value=" +result[0];
				}
	    	}else if(type.equals("3")){
	    		float[] result = getRealTimeAkcpAvailability(ip);
				if(result.length>0){
					if(result[1]>-1)
						dataParameters = "&value=" +result[1];
				}
	    	}
	    		
	    }
	    
	    System.out.println(dataParameters);
	    HttpServletResponse response = ServletActionContext.getResponse();
	    try {
			PrintWriter out=response.getWriter();
			out.print(dataParameters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    return null;
	}
	
	public String getRealTimeCpuAvailability(String ip){
		
		String	ret ="0";
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			if(null != device){
				CpuPollTool cpuPollTool = new CpuPollTool();
				long cpu = cpuPollTool.getCpuLoad(device.getIp(), device.getReadCommunity(), null);
				if(cpu == -1)
					cpu = 0;
				ret = String.valueOf(cpu);
			}
		}
		
		return ret;
	}
	
	public float[]  getRealTimeAkcpAvailability(String ip){
		
		
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			if(null != device){
				SnmpTarget target = new SnmpTarget();
				CpuPollTool cpuPollTool = new CpuPollTool();
				float[] result = cpuPollTool.getAkcpData(target, device.getIp(), device.getReadCommunity());
				return result;
			}
		}
		
		return null;
	}

}
