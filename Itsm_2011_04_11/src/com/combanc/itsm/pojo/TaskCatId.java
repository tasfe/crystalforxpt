package com.combanc.itsm.pojo;

/**
 * TaskCatId entity. @author MyEclipse Persistence Tools
 */

public class TaskCatId implements java.io.Serializable {

	// Fields

	private Integer taskId;
	private Integer catId;

	// Constructors

	/** default constructor */
	public TaskCatId() {
	}

	/** full constructor */
	public TaskCatId(Integer taskId, Integer catId) {
		this.taskId = taskId;
		this.catId = catId;
	}

	// Property accessors

	public Integer getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TaskCatId))
			return false;
		TaskCatId castOther = (TaskCatId) other;

		return ((this.getTaskId() == castOther.getTaskId()) || (this
				.getTaskId() != null
				&& castOther.getTaskId() != null && this.getTaskId().equals(
				castOther.getTaskId())))
				&& ((this.getCatId() == castOther.getCatId()) || (this
						.getCatId() != null
						&& castOther.getCatId() != null && this.getCatId()
						.equals(castOther.getCatId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTaskId() == null ? 0 : this.getTaskId().hashCode());
		result = 37 * result
				+ (getCatId() == null ? 0 : this.getCatId().hashCode());
		return result;
	}

}