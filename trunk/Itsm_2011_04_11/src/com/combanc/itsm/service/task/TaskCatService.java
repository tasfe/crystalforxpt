/**
 * 
 */
package com.combanc.itsm.service.task;

import com.combanc.itsm.dao.TaskCatDAO;

/**
 * @author Administrator
 *
 */
public class TaskCatService {
	
	
	private TaskCatDAO taskCatDAO;

	/**
	 * @return the taskCatDAO
	 */
	public TaskCatDAO getTaskCatDAO() {
		return taskCatDAO;
	}

	/**
	 * @param taskCatDAO the taskCatDAO to set
	 */
	public void setTaskCatDAO(TaskCatDAO taskCatDAO) {
		this.taskCatDAO = taskCatDAO;
	}
	
	public boolean ishasTaskByCatId(int catId)
	{
		
		return 	this.taskCatDAO.ishasTaskByCatId(catId);
	
			
	}
	
	

}
