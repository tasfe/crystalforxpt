package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.SchedualedTaskUserDAO;
import com.combanc.itsm.pojo.SchedualedTaskUser;

public class SchedualedTaskUserService {
	
	private SchedualedTaskUserDAO schedualedTaskUserDAO;
	
	public void delete(SchedualedTaskUser stu) {
		if(stu!=null) {
			schedualedTaskUserDAO.delete(stu);
		}
	}
	
	public SchedualedTaskUserDAO getSchedualedTaskUserDAO() {
		return schedualedTaskUserDAO;
	}

	public void setSchedualedTaskUserDAO(SchedualedTaskUserDAO schedualedTaskUserDAO) {
		this.schedualedTaskUserDAO = schedualedTaskUserDAO;
	}
	
	public List findByUserId(int userId){
		return schedualedTaskUserDAO.findByUserId(userId);
	}
	
	public List findByTaskIdAndUserId(Integer taskId,Integer userId){
		return schedualedTaskUserDAO.findByTaskIdAndUserId(taskId, userId);
	}
	
	public void update(SchedualedTaskUser stu){
		schedualedTaskUserDAO.update(stu);
	}
	
	public SchedualedTaskUser findById(Integer id){
		return schedualedTaskUserDAO.findById(id);
	}
}
