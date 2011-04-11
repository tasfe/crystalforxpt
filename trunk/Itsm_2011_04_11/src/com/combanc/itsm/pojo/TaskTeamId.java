package com.combanc.itsm.pojo;

/**
 * TaskTeamId entity. @author MyEclipse Persistence Tools
 */

public class TaskTeamId implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private Integer teamId;

	// Constructors

	/** default constructor */
	public TaskTeamId() {
	}

	/** full constructor */
	public TaskTeamId(Integer taskId, Integer teamId) {
		this.taskId = taskId;
		this.teamId = teamId;
	}

	// Property accessors

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaskTeamId))
			return false;
		TaskTeamId castOther = (TaskTeamId) other;

		return ((this.getTaskId() == castOther.getTaskId()) || (this
				.getTaskId() != null
				&& castOther.getTaskId() != null && this.getTaskId().equals(
				castOther.getTaskId())))
				&& ((this.getTeamId() == castOther.getTeamId()) || (this
						.getTeamId() != null
						&& castOther.getTeamId() != null && this.getTeamId()
						.equals(castOther.getTeamId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTaskId() == null ? 0 : this.getTaskId().hashCode());
		result = 37 * result
				+ (getTeamId() == null ? 0 : this.getTeamId().hashCode());
		return result;
	}

}