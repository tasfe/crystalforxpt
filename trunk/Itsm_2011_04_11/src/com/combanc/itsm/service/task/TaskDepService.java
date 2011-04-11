/**
 * 
 */
package com.combanc.itsm.service.task;

import java.util.List;

import com.combanc.itsm.dao.TaskDepDAO;

/**
 * @author Administrator
 *
 */
public class TaskDepService {

	private TaskDepDAO taskDepDAO;

	/**
	 * @return the taskDepDAO
	 */
	public TaskDepDAO getTaskDepDAO() {
		return taskDepDAO;
	}

	/**
	 * @param taskDepDAO the taskDepDAO to set
	 */
	public void setTaskDepDAO(TaskDepDAO taskDepDAO) {
		this.taskDepDAO = taskDepDAO;
	}
	
	public List findByDep(Integer depID){
		if(depID!=null&&!depID.equals("")) {
			return taskDepDAO.findByProperty("departmentId", depID);
		}
		return null;
	}
}
