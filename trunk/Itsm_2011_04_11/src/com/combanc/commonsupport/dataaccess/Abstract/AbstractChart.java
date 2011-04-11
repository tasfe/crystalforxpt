package com.combanc.commonsupport.dataaccess.Abstract;

public class AbstractChart {
	/*
	 * 事件数目
	 */
	public String getDailyIncRequestCount(String startTime, String endTime){
		return null;
	}
	/*
	 * 事件平均处理时长
	 */
	public String getDailyIncDealTime(String startTime, String endTime){
		return null;
	}
	/*
	 * 事件请求状态，关闭与未关闭
	 */
	public String getIncidentState(String startTime,String endTime){
		return null;
	}
	/*
	 * 工程师处理请求统计
	 */
	public String getStatistic(String startTime,String endTime,String otherCondition){
		return null;
	}
	/*
	 * 工程师已完成请求统计,统计逻辑：最后完成该请求
	 */
	public String getWorkFinish(int engineerId,String startTime,String endTime){
		return null;
	}
	/*
	 * 
	 */
	public String getWorkFinishDetail(int engineerId,String startTime,String endTime){
		return null;
	}
}
