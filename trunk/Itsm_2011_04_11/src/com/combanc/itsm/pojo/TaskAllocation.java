package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TaskAllocation entity. @author MyEclipse Persistence Tools
 */

public class TaskAllocation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -143779312744027684L;
	private Integer id;
	private Roleteam roleteamByRoleteam;
	private Roleteam roleteamByTaskAllocationId;
	private String displayname;
	private Integer category;
	private String code;
	private String content;
	private Set<Roleteam> team = new HashSet<Roleteam>(0);
	private Set<Department> department = new HashSet<Department>(0);
	private Set<ServiceCategory> cats=new HashSet<ServiceCategory>(0);
	// Constructors

	/**
	 * @return the cats
	 */
	public Set<ServiceCategory> getCats() {
		return cats;
	}


	/**
	 * @param cats the cats to set
	 */
	public void setCats(Set<ServiceCategory> cats) {
		this.cats = cats;
	}


	/** default constructor */
	public TaskAllocation() {
	}


	// Property accessors

	/**
	 * @return the team
	 */
	public Set<Roleteam> getTeam() {
		return team;
	}


	/**
	 * @param team the team to set
	 */
	public void setTeam(Set<Roleteam> team) {
		this.team = team;
	}


	/**
	 * @return the department
	 */
	public Set<Department> getDepartment() {
		return department;
	}


	/**
	 * @param department the department to set
	 */
	public void setDepartment(Set<Department> department) {
		this.department = department;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Roleteam getRoleteamByRoleteam() {
		return this.roleteamByRoleteam;
	}

	public void setRoleteamByRoleteam(Roleteam roleteamByRoleteam) {
		this.roleteamByRoleteam = roleteamByRoleteam;
	}

	public Roleteam getRoleteamByTaskAllocationId() {
		return this.roleteamByTaskAllocationId;
	}

	public void setRoleteamByTaskAllocationId(
			Roleteam roleteamByTaskAllocationId) {
		this.roleteamByTaskAllocationId = roleteamByTaskAllocationId;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}