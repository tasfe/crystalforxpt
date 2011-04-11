package com.combanc.itsm.pojo;

/**
 * TaskDep entity. @author MyEclipse Persistence Tools
 */

public class TaskDep implements java.io.Serializable {

	// Fields

	private TaskDepId id;
	private TaskAllocation taskallocation;
	private Department department;
	private int taskId;
	private int departmentId;

	// Constructors

	/** default constructor */
	public TaskDep() {
	}

	/** full constructor */
	public TaskDep(TaskDepId id, TaskAllocation taskallocation,
			Department department) {
		this.id = id;
		this.taskallocation = taskallocation;
		this.department = department;
	}

	// Property accessors

	public TaskDepId getId() {
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
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setId(TaskDepId id) {
		this.id = id;
	}

	public TaskAllocation getTaskallocation() {
		return this.taskallocation;
	}

	public void setTaskallocation(TaskAllocation taskallocation) {
		this.taskallocation = taskallocation;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}