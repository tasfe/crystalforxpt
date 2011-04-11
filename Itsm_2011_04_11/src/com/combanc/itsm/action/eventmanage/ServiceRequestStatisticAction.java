package com.combanc.itsm.action.eventmanage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.snmp4j.security.PrivAES;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.data.SRStatisticsData;
import com.combanc.itsm.service.ServiceRequestStatisticService;
/*
 * 服务请求统计,jyxiao
 */
@SuppressWarnings("all")
public class ServiceRequestStatisticAction extends BaseActionSupport implements
ServletRequestAware{
	private ServiceRequestStatisticService serviceRequestStatisticService;
	private String startTime;
	private String endTime;
	private Integer engineerId;
	private String engineerName;
	private List<SRStatisticsData> datas;
	private List<ServiceRequest> requests;
	private String requNos;//多个请求工单号
	private String requNo;//单个请求工单号，查看该请求详情
	public String getRequNos() {
		return requNos;
	}

	public void setRequNos(String requNos) {
		this.requNos = requNos;
	}

	public String getRequNo() {
		return requNo;
	}

	public void setRequNo(String requNo) {
		this.requNo = requNo;
	}

	public List<ServiceRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<ServiceRequest> requests) {
		this.requests = requests;
	}

	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public List<SRStatisticsData> getDatas() {
		return datas;
	}

	public void setDatas(List<SRStatisticsData> datas) {
		this.datas = datas;
	}

	//private 
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	public ServiceRequestStatisticService getServiceRequestStatisticService() {
		return serviceRequestStatisticService;
	}

	public void setServiceRequestStatisticService(
			ServiceRequestStatisticService serviceRequestStatisticService) {
		this.serviceRequestStatisticService = serviceRequestStatisticService;
	}
	/*
	 * 工程师服务请求统计，包含数目及平均处理时长
	 */
	public String getEngineerWorkStatics() {
		datas=new ArrayList<SRStatisticsData>();
		List list=this.serviceRequestStatisticService.getEngineerWorkDetail(engineerId, startTime, endTime);
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				SRStatisticsData data=new SRStatisticsData();
				Map map=(Map)list.get(i);
				data.setEngineerName(map.get("USERNAME").toString());
				if(datas.contains(data)){
					for(int j=0;j<datas.size();j++){
						if(datas.get(j).getEngineerName().equals(map.get("USERNAME").toString())){
							datas.get(j).setNum(datas.get(j).getNum()+1);//次数加1
							datas.get(j).setDealTime(datas.get(j).getDealTime()+getDealTime(map.get("DIFF").toString()));
							datas.get(j).setCondition(datas.get(j).getCondition()+map.get("REQUNO").toString()+",");
						}
					}
				}else{
					data.setCondition(map.get("REQUNO").toString()+",");
					data.setNum(1);
					data.setDealTime(getDealTime(map.get("DIFF").toString()));
					datas.add(data);
				}
			}
		}
		return "success";
	}
	public String getListByRequnos(){
		requests=this.serviceRequestStatisticService.getRequests(this.getRequNos());
		return "success";
	}
	public String getDetailByRequno(){
		return "success";
	}
	/*
	 * 处理时长，数据库函数timediff返回格式：xx:xx:xx
	 */
	public int getDealTime(String diff){
		int hour=0;
		if(null==diff||diff.equals("")){
			return 0;
		}
		return Integer.valueOf(diff);
		/*
		String splitHour[]=diff.split(":");
		if(null==splitHour||splitHour.length!=3){
			return 0;
		}
		hour=Integer.valueOf(splitHour[0]);
		if(Integer.valueOf(splitHour[1])>=30){//分钟大于30，算一小时
			hour++;
		}
		return hour;
		*/
	}
}
