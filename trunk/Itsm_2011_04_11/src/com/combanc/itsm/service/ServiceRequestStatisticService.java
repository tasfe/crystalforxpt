package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.combanc.commonsupport.dataaccess.Abstract.AbstractDataAccessFacade;
import com.combanc.commonsupport.dataaccess.factory.DataAccessFacadeCreator;
import com.combanc.itsm.dao.ServiceRequestDAO;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.util.DateHelp;

@SuppressWarnings("all")
public class ServiceRequestStatisticService {
	
	private ServiceRequestDAO serviceRequestDAO;
	public ServiceRequestDAO getServiceRequestDAO() {
		return serviceRequestDAO;
	}
	public void setServiceRequestDAO(ServiceRequestDAO serviceRequestDAO) {
		this.serviceRequestDAO = serviceRequestDAO;
	}
	public List<ServiceRequest> getRequests(String requnos){
		List<ServiceRequest> requests=new ArrayList<ServiceRequest>();
		if(null==requnos||requnos.equals("")){
			return requests;
		}
		String requ[]=requnos.split(",");
		String condition="";
		if(null!=requ&&requ.length>0){
			condition+="(";
			condition+="'"+requ[0]+"'";
			for(int i=1;i<requ.length;i++){
				condition+=",";
				condition+="'"+requ[i]+"'";
			}
			condition+=")";
			return this.serviceRequestDAO.findByHql(" from ServiceRequest where requestNo in "+condition+"");
		}
		return requests;
	}
	public List<String> getEngineerWork(int engineerId,String startTime,String endTime){
		List<String> result=new ArrayList<String>();
		if((null==startTime||startTime.equals(""))&&(endTime==null||endTime.equals(""))&&engineerId==0){//默认显示本月
			startTime=DateHelp.getNowYM()+"-01 00:00:00";
			endTime=DateHelp.getStringDate();
		}
		try{
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getWorkFinish(engineerId, startTime, endTime);
			System.out.println("getEngineerWork==="+sqlString);
			List list =daf.getList(sqlString);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					String work="";
					Map map=(Map)list.get(i);
					work+=map.get("USERNAME").toString();
					work+="~";
					work+=map.get("NUM").toString();
					result.add(work);
				}
			}
		}catch (Exception e) {
		}
		return result;
	}
	public List getEngineerWorkDetail(Integer engineerId,String startTime,String endTime){
		List result=new ArrayList();
		if((null==startTime||startTime.equals(""))&&(endTime==null||endTime.equals(""))&&(null==engineerId||engineerId==0)){//默认显示本月
			startTime=DateHelp.getNowYM()+"-01 00:00:00";
			endTime=DateHelp.getStringDate();
			engineerId=0;
		}
		try{
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getWorkFinishDetail(engineerId, startTime, endTime);
			System.out.println("getEngineerWorkDetail==="+sqlString);
			result =daf.getList(sqlString);
		}catch (Exception e) {
		}
		return result;
	}
	public List<String> getEngineerWork(){
		List<String> result=new ArrayList<String>();
		String startTime = DateHelp.getNowYM()+"-01 00:00:00";
		String nowDate = DateHelp.getStringDate();
		List list=this.getEngineerWork(startTime, nowDate);
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String string="";
				Map map=(Map)list.get(i);
				string+=map.get("username").toString();
				string+="~";
				string+=map.get("state1").toString();
				string+="~";
				string+=map.get("state2").toString();
				result.add(string);
			}
		}
		return result;
	}
	public List getEngineerWork(String startTime,String endTime){
		List result=new ArrayList();
		if(null==startTime||startTime==""){
			return result;
		}
		if(null==endTime||endTime==""){
			return result;
		}
		try{
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getStatistic(startTime, endTime, "");
			System.out.println("month engineer work==="+sqlString);
			List list =daf.getList(sqlString);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Map mapNew=new HashMap(); 
					Map map=(Map)list.get(i);
					Map mapNext=null;
					if((i+1)<list.size()){
						 mapNext=(Map)list.get(i+1);
					}
					if(mapNext==null){
						mapNew.put("username", map.get("TRUENAME")==null?"":map.get("TRUENAME").toString());
						if(map.get("STATE").toString()=="1"){
							mapNew.put("state1", map.get("NUM")==null?"":map.get("NUM").toString());
							mapNew.put("state2", "0");
						}else{
							mapNew.put("state2", map.get("NUM")==null?"":map.get("NUM").toString());
							mapNew.put("state1", "0");
						}
					}
					if(mapNext!=null&&mapNext.get("TRUENAME").equals(map.get("TRUENAME"))){
						mapNew.put("username", map.get("TRUENAME")==null?"":map.get("TRUENAME").toString());
						if(map.get("STATE").toString()=="1"){
							mapNew.put("state1", map.get("NUM")==null?"":map.get("NUM").toString());
							mapNew.put("state2", mapNext.get("NUM")==null?"":mapNext.get("NUM").toString());
						}else{
							mapNew.put("state2", map.get("NUM")==null?"":map.get("NUM").toString());
							mapNew.put("state1", mapNext.get("NUM")==null?"":mapNext.get("NUM").toString());
						}
						list.remove(i+1);
					}
					result.add(mapNew);
				}
			}
		}catch (Exception e) {
		}
		return result;
	}
	public String getMonthIncState(){
		String result="";
		String startTime = DateHelp.getNowYM()+"-01 00:00:00";
		String nowDate = DateHelp.getStringDate();
		Map incMap = this.getMonthIncState(startTime, nowDate);
		if(incMap.containsKey("finishCount")){
			result+=incMap.get("finishCount").toString();
		}else{
			result+=0;
		}
		result+="~";
		if(incMap.containsKey("notfinishCount")){
			result+=incMap.get("notfinishCount").toString();
		}else{
			result+=0;
		}
		return result;
	}
	public Map getMonthIncState(String startTime,String endTime){
		Map result = new HashMap();
		if(null==startTime||startTime==""){
			return result;
		}
		if(null==endTime||endTime==""){
			return result;
		}
		try{
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getIncidentState(startTime, endTime);
			System.out.println("month inc state==="+sqlString);
			List list = daf.getList(sqlString);
			if(null!=list&&list.size()>0){
				int notfinishCount = 0;
				int finishCount = 0;
				for(int i=0;i<list.size();i++){
					Map listMap = (Map)list.get(i);
					if(listMap.get("FLOWENDSTATE")==null||listMap.get("FLOWENDSTATE").toString().equals("0")){
						notfinishCount += Integer.parseInt(listMap.get("NUM").toString());
					}else {
						finishCount += Integer.parseInt(listMap.get("NUM").toString());
					}
				}
				
				result.put("finishCount", finishCount); // 已经完成
				result.put("notfinishCount", notfinishCount); // 未完成
			}else{
				result.put("finishCount", 0);// 已经完成
				result.put("notfinishCount", 0); // 未完成
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	public List<String> getIncDealTime(){
		List<String> result=new ArrayList<String>();
		String startShort = DateHelp.getWeek(DateHelp.getStringDateShort(), "0");
		String startTime = startShort+" 00:00:00";
		String nowDate = DateHelp.getStringDate();
		Map incReqMap = this.getIncAvgDealTime(startTime, nowDate);
		String[] timeList = new String[7];
		timeList[0] = startShort;
		for(int i=1;i<7;i++){
			timeList[i] = DateHelp.getNextDay(timeList[i-1], "1");
		}
		if(null!=timeList&&timeList.length==7){
			if(null!=incReqMap&&!incReqMap.isEmpty()){
				for(int i=0;i<7;i++){
					result.add(timeList[i]+"~"+incReqMap.get(timeList[i]).toString());
				}
			}else{
				for(int i=0;i<7;i++){
					result.add(timeList[i]+"~"+0);
				}
			}
			
		}
		return result;
	}
	public Map getIncAvgDealTime(String startTime,String endTime){
		Map result = new HashMap();
		if(null==startTime||startTime==""){
			return result;
		}
		if(null==endTime||endTime==""){
			return result;
		}
		try{
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getDailyIncDealTime(startTime, endTime);
			System.out.println("daily inc dealtime sql==="+sqlString);
			List list = daf.getList(sqlString);
			// 生成每天的处理时间
			if(null!=list&&list.size()>0){
				String nowtime = DateHelp.getStringDate();
				String daily = ((Map)list.get(0)).get("DAILY").toString();
				int dailyCount = 0;
				double totalSecond = 0;
				
				for(int i=0;i<list.size();i++){
					Map listMap = (Map)list.get(i);
					if(daily.equalsIgnoreCase(listMap.get("DAILY").toString())){
						System.out.println("========="+daily+","+listMap.get("DAILY").toString());
						// 该天
						if(null!=listMap.get("FACTFINISHTIME")){
							String finishtime = listMap.get("FACTFINISHTIME").toString();
							String submittime = listMap.get("SUBMITTIME").toString();
							String difftime = DateHelp.getTwoDate(submittime,finishtime);
							if(null!=difftime&&difftime.length()>0){
								totalSecond += Double.parseDouble(difftime);
								dailyCount++;
							}
						}else{
							// 当前时间减去提交时间
							String submittime = listMap.get("SUBMITTIME").toString();
							String difftime = DateHelp.getTwoDate(submittime,nowtime);
							if(null!=difftime&&difftime.length()>0){
								totalSecond += Double.parseDouble(difftime);
								dailyCount++;
							}
						}
					}else{
						// 非该天
						
						// -----------------------------------------------------------
						// 将以前保存
						int avgDealTime = (int)totalSecond/(dailyCount*60);
						result.put(daily, avgDealTime); // 单位小时

						// -------------------------------------------------------------
						// 新的
						daily = listMap.get("DAILY").toString();
						dailyCount = 0;
						totalSecond = 0;
						
						//
						if(null!=listMap.get("FACTFINISHTIME")){
							String finishtime = listMap.get("FACTFINISHTIME").toString();
							String submittime = listMap.get("SUBMITTIME").toString();
							String difftime = DateHelp.getTwoDate(submittime,finishtime);
							if(null!=difftime&&difftime.length()>0){
								totalSecond += Double.parseDouble(difftime);
								dailyCount++;
							}
						}else{
							// 当前时间减去提交时间
							String submittime = listMap.get("SUBMITTIME").toString();
							String difftime = DateHelp.getTwoDate(submittime,nowtime);
							if(null!=difftime&&difftime.length()>0){
								totalSecond += Double.parseDouble(difftime);
								dailyCount++;
							}
						}
					}
				}
				int avgDealTime = (int)totalSecond/(dailyCount*60);
				result.put(daily, avgDealTime); // 单位小时
			}
			
			// 将该时间段内其他日期的事件处理时间用0进行补齐
			if(null!=result&&result.size()!=7){
				// 生成一周日
				String[] dateList = new String[7];
				dateList[0] = startTime.substring(0, 10);
				for(int i=1;i<7;i++){
					dateList[i] = DateHelp.getNextDay(dateList[i-1], "1");
				}
				
				for(int i=0; i<dateList.length;i++){
					for(int j=0;j<result.size();j++){
						if(result.containsKey(dateList[i])){
							break;
						}else{
							// 补齐其他日期
							result.put(dateList[i], 0);// 单位小时
						}
					}
				}
			}
		}catch(Exception ex){
			
		}
		return result;
	}
	public List<String> getDailyInc(){
		List<String> dailyInc=new ArrayList<String>();
		String startShort = DateHelp.getWeek(DateHelp.getStringDateShort(), "0");
		String startTime = startShort+" 00:00:00";
		String nowDate = DateHelp.getStringDate();
		Map incReqMap = this.getDailyIncRequestCount(startTime, nowDate);
		String[] timeList = new String[7];
		timeList[0] = startShort;
		for(int i=1;i<7;i++){
			timeList[i] = DateHelp.getNextDay(timeList[i-1], "1");
		}
		if(null!=timeList&&timeList.length==7){
			if(null!=incReqMap&&!incReqMap.isEmpty()){
				for(int i=0;i<7;i++){
					dailyInc.add(timeList[i]+"~"+incReqMap.get(timeList[i]).toString());
				}
			}else{
				for(int i=0;i<7;i++){
					dailyInc.add(timeList[i]+"~"+0);
				}
			}
			
		}
		return dailyInc;
	}
	public Map getDailyIncRequestCount(String startTime, String endTime){
		Map result = new HashMap();
		if(null==startTime||startTime==""){
			return result;
		}
		if(null==endTime||endTime==""){
			return result;
		}
		
		try{
			// 查询数据
			AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
			String sqlString = daf.getAbstractChart().getDailyIncRequestCount(startTime, endTime);
			System.out.println("daily inc count sql==="+sqlString);
			List list = daf.getList(sqlString);
			
			// 生成数据
			if(null!=list&&list.size()>0){
				for(int i=0;i<list.size();i++){
					Map listMap = (Map)list.get(i);
					String daily = listMap.get("DAILY").toString();
					String num = listMap.get("NUM").toString();
					result.put(daily, num);
				}
			}
			
			// 将该时间段内其他日期的事件请求数量用0进行补齐
			if(null!=result&&result.size()!=7){
				// 生成一周日
				String[] dateList = new String[7];
				dateList[0] = startTime.substring(0, 10);
				for(int i=1;i<7;i++){
					dateList[i] = DateHelp.getNextDay(dateList[i-1], "1");
				}
				
				for(int i=0; i<dateList.length;i++){
					for(int j=0;j<result.size();j++){
						if(result.containsKey(dateList[i])){
							break;
						}else{
							// 补齐其他日期
							result.put(dateList[i], 0);// 单位小时
						}
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
