package com.combanc.itsm.pojo;

/**
 * TaskCat entity. @author MyEclipse Persistence Tools
 */

public class TaskCat implements java.io.Serializable {

	// Fields

	private TaskCatId id;
	private int taskId;
	private int catId;
	private TaskAllocation taskallocation;
	private ServiceCategory serviceCategory;

	// Constructors

	/** default constructor */
	public TaskCat() {
	}

	/** full constructor */
	public TaskCat(TaskCatId id, TaskAllocation taskallocation,
			ServiceCategory serviceCategory) {
		this.id = id;
		this.taskallocation = taskallocation;
		this.serviceCategory = serviceCategory;
	}

	// Property accessors

	public TaskCatId getId() {
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
	 * @return the catId
	 */
	public int getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(int catId) {
		this.catId = catId;
	}

	public void setId(TaskCatId id) {
		this.id = id;
	}

	public TaskAllocation getTaskallocation() {
		return this.taskallocation;
	}

	public void setTaskallocation(TaskAllocation taskallocation) {
		this.taskallocation = taskallocation;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

}