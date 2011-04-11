package com.combanc.itsm.pojo;

/**
 * TaskTeam entity. @author MyEclipse Persistence Tools
 */

public class TaskTeam implements java.io.Serializable {

	// Fields

	private TaskTeamId id;
	private TaskAllocation taskallocation;
	private Roleteam roleteam;
	private int taskId;
	private int teamId;

	// Constructors

	/** default constructor */
	public TaskTeam() {
	}

	/** full constructor */
	public TaskTeam(TaskTeamId id, TaskAllocation taskallocation,
			Roleteam roleteam) {
		this.id = id;
		this.taskallocation = taskallocation;
		this.roleteam = roleteam;
	}

	// Property accessors

	public TaskTeamId getId() {
		return this.id;
	}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public void setId(TaskTeamId id) {
		this.id = id;
	}

	public TaskAllocation getTaskallocation() {
		return this.taskallocation;
	}

	public void setTaskallocation(TaskAllocation taskallocation) {
		this.taskallocation = taskallocation;
	}

	public Roleteam getRoleteam() {
		return this.roleteam;
	}

	public void setRoleteam(Roleteam roleteam) {
		this.roleteam = roleteam;
	}

}