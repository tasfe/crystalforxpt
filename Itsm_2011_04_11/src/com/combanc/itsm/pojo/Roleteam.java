package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Roleteam entity. @author MyEclipse Persistence Tools
 */

public class Roleteam implements java.io.Serializable {

	// Fields

	private Integer id;
	private Location location;
	private String name;
	private String type;
	private String description;
	private Integer orderNo;
	private Integer useFor;
	private Set taskAllocationsForTaskAllocationId = new HashSet(0);
	private Set taskAllocationsForRoleteam = new HashSet(0);
	private Set userses = new HashSet(0);
	private Set authorities = new HashSet(0);
	private Integer teamLeader;
	private int roleType;
	private String roleTypeName;
	private int roleGroup;//角色分组
	// Constructors

	

	/** default constructor */
	public Roleteam() {
	}

	public String getRoleTypeName() {
		return roleTypeName;
	}

	public void setRoleTypeName(String roleTypeName) {
		this.roleTypeName = roleTypeName;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public Integer getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Integer teamLeader) {
		this.teamLeader = teamLeader;
	}

	/** full constructor */
	public Roleteam(Location location, String name, String type,
			String description, Integer orderNo, Integer useFor,
			Set taskAllocationsForTaskAllocationId,
			Set taskAllocationsForRoleteam, Set userses, Set authorities) {
		this.location = location;
		this.name = name;
		this.type = type;
		this.description = description;
		this.orderNo = orderNo;
		this.useFor = useFor;
		this.taskAllocationsForTaskAllocationId = taskAllocationsForTaskAllocationId;
		this.taskAllocationsForRoleteam = taskAllocationsForRoleteam;
		this.userses = userses;
		this.authorities = authorities;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getUseFor() {
		return this.useFor;
	}

	public void setUseFor(Integer useFor) {
		this.useFor = useFor;
	}

	public Set getTaskAllocationsForTaskAllocationId() {
		return this.taskAllocationsForTaskAllocationId;
	}

	public void setTaskAllocationsForTaskAllocationId(
			Set taskAllocationsForTaskAllocationId) {
		this.taskAllocationsForTaskAllocationId = taskAllocationsForTaskAllocationId;
	}

	public Set getTaskAllocationsForRoleteam() {
		return this.taskAllocationsForRoleteam;
	}

	public void setTaskAllocationsForRoleteam(Set taskAllocationsForRoleteam) {
		this.taskAllocationsForRoleteam = taskAllocationsForRoleteam;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(Set authorities) {
		this.authorities = authorities;
	}

	public int getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(int roleGroup) {
		this.roleGroup = roleGroup;
	}

}