package com.combanc.itsm.pojo.data;


/*
 * 服务统计辅助类，此类统计出来的不应该放在POJO里
 */
public class SRStatisticsData {
	
	private String engineerName;
	private int num;
	private int dealTime;
	private double avgDealTime;
	private String condition;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getDealTime() {
		return dealTime;
	}
	public void setDealTime(int dealTime) {
		this.dealTime = dealTime;
	}
	public double getAvgDealTime() {
		return avgDealTime;
	}
	public void setAvgDealTime(double avgDealTime) {
		this.avgDealTime = avgDealTime;
	}
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (null == obj){
			return false;
		}
		if (obj instanceof SRStatisticsData){
			SRStatisticsData user = (SRStatisticsData)obj;
			if (this.engineerName .equals(user.engineerName)  && this.getEngineerName().equals(user.getEngineerName()) ){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
}
