/**
 * 
 */
package com.combanc.itsm.service.task;

import com.combanc.itsm.dao.TaskTeamDAO;

/**
 * @author Administrator
 *
 */
public class TaskTeamService {

	private TaskTeamDAO taskTeamDAO;

	/**
	 * @return the taskTeamDAO
	 */
	public TaskTeamDAO getTaskTeamDAO() {
		return taskTeamDAO;
	}

	/**
	 * @param taskTeamDAO the taskTeamDAO to set
	 */
	public void setTaskTeamDAO(TaskTeamDAO taskTeamDAO) {
		this.taskTeamDAO = taskTeamDAO;
	}
}
