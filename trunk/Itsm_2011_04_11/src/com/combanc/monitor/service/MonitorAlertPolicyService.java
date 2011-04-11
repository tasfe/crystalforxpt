package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorAlertPolicyDAO;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.vo.systemParam.AlertPolicy;

public class MonitorAlertPolicyService extends BaseService<MonitorAlertPolicy, Integer>{
	
	private MonitorAlertPolicyDAO monitorAlertPolicyDAO;

	public MonitorAlertPolicyDAO getMonitorAlertPolicyDAO() {
		return monitorAlertPolicyDAO;
	}

	public void setMonitorAlertPolicyDAO(MonitorAlertPolicyDAO monitorAlertPolicyDAO) {
		super.setDao(monitorAlertPolicyDAO);
		this.monitorAlertPolicyDAO = monitorAlertPolicyDAO;
	}
	/**保存报警策略**/
	public boolean saveAlertPolicy(AlertPolicy alertPolicy ){
		MonitorAlertPolicy monitorAlertPolicy = alertPolicy.getMonitorAlertPolicy();
		String [] days = alertPolicy.getDays();
		String selectedDays = "";
		if(days.length>0){	
			for(int i=0; i<days.length ;i++){
				selectedDays += days[i] + ",";
			}
			selectedDays = selectedDays.substring(0, selectedDays.length()-1);
		}
		monitorAlertPolicy.setDayOfWeek(selectedDays);
		if(!alertPolicy.getIsDefinehours().equals("1")){
			monitorAlertPolicy.setDailyStart(0);
			monitorAlertPolicy.setDailyEnd(0);
		} 
		String alertTypes = "";
		if(alertPolicy.getAlertSmallTypes1().length>0){
			Integer[] type = alertPolicy.getAlertSmallTypes1();
			for(int i=0;i<type.length ; i++){
				alertTypes += String.valueOf(type[i])+",";
			}
		}
		if(alertPolicy.getAlertSmallTypes2().length>0){
			Integer[] type = alertPolicy.getAlertSmallTypes2();
			for(int i=0;i<type.length ; i++){
				alertTypes += String.valueOf(type[i])+",";
			}
		}
		if(alertPolicy.getAlertSmallTypes3().length>0){
			Integer[] type = alertPolicy.getAlertSmallTypes3();
			for(int i=0;i<type.length ; i++){
				alertTypes += String.valueOf(type[i])+",";
			}
		}
		if(alertTypes.length()>0)
			alertTypes = alertTypes.substring(0, alertTypes.length()-1);
		monitorAlertPolicy.setAlertTypes(alertTypes);
		monitorAlertPolicy.setModifyTime(new Timestamp(System.currentTimeMillis()));
		monitorAlertPolicyDAO.update(monitorAlertPolicy);
		return true;
	}
	/**删除报警策略**/
	public boolean deleteAlertPolicy(Integer policyId){
		MonitorAlertPolicy monitorAlertPolicy = monitorAlertPolicyDAO.findById(policyId);
		if(null != monitorAlertPolicy){
			monitorAlertPolicyDAO.delete(monitorAlertPolicy);
			return true;
		}else
			return false;
		
	}
	/**添加报警策略**/
	public void addAlertPolicy(String name){
		MonitorAlertPolicy monitorAlertPolicy = new MonitorAlertPolicy();
		monitorAlertPolicy.setName(name);
		monitorAlertPolicy.setDayOfWeek("0,1,2,3,4,5,6");
		monitorAlertPolicy.setDailyStart(0);
		monitorAlertPolicy.setDailyEnd(0);
		monitorAlertPolicy.setAlertTypes("1,2,3");
		monitorAlertPolicy.setModifyTime(new Timestamp(System.currentTimeMillis()));
		monitorAlertPolicyDAO.save(monitorAlertPolicy);
	}
	
	/**创建策略副本**/
	public void cloneAlertPolicy(Integer policyId){
		MonitorAlertPolicy monitorAlertPolicy = monitorAlertPolicyDAO.findById(policyId);
		if(null != monitorAlertPolicy){
			int count = 1;
			String name = monitorAlertPolicy.getName() + "副本" + count;
			while(monitorAlertPolicyDAO.findByName(name).size() > 0) {
				count++;
				name = monitorAlertPolicy.getName() + "副本" + count;
			}
			try {
				MonitorAlertPolicy po = (MonitorAlertPolicy) monitorAlertPolicy.clone();
				po.setName(name);
				po.setModifyTime(new Timestamp(System.currentTimeMillis()));
				monitorAlertPolicyDAO.save(po);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**检查策略名称是否重名**/
	public boolean checkName(String name,String exceptId){
		boolean result = false;
		if( null != name){
			String hql = " from MonitorAlertPolicy where name = '"+name+"'";
			if( null != exceptId && !"".equals(exceptId)){
				if(Integer.parseInt(exceptId)>0)
					hql += " and id <>" + exceptId;
						
			}
			List list = monitorAlertPolicyDAO.findByHql(hql);
			if(null != list && list.size()>0)
				result = true;
		}
		
		return result;
	}

}
