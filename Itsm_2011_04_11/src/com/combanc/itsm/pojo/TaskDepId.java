package com.combanc.itsm.pojo;

/**
 * TaskDepId entity. @author MyEclipse Persistence Tools
 */

public class TaskDepId implements java.io.Serializable {

	// Fields

	private Integer departmentId;
	private Integer taskId;

	// Constructors

	/** default constructor */
	public TaskDepId() {
	}

	/** full constructor */
	public TaskDepId(Integer departmentId, Integer taskId) {
		this.departmentId = departmentId;
		this.taskId = taskId;
	}

	// Property accessors

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaskDepId))
			return false;
		TaskDepId castOther = (TaskDepId) other;

		return ((this.getDepartmentId() == castOther.getDepartmentId()) || (this
				.getDepartmentId() != null
				&& castOther.getDepartmentId() != null && this
				.getDepartmentId().equals(castOther.getDepartmentId())))
				&& ((this.getTaskId() == castOther.getTaskId()) || (this
						.getTaskId() != null
						&& castOther.getTaskId() != null && this.getTaskId()
						.equals(castOther.getTaskId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getDepartmentId() == null ? 0 : this.getDepartmentId()
						.hashCode());
		result = 37 * result
				+ (getTaskId() == null ? 0 : this.getTaskId().hashCode());
		return result;
	}

}