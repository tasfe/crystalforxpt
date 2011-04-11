package com.combanc.itsm.pojo;

import java.io.Serializable;


@SuppressWarnings("all")
public class RoleTablePrivilege implements Serializable{
	private Integer id;
	private Integer roleId;
	private Integer tableId;
	private Integer privilege;
	private String prop;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public Integer getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public RoleTablePrivilege(Integer roleId,Integer tableId){
		this.setRoleId(roleId);
		this.setTableId(tableId);
	}
	public RoleTablePrivilege(){
		
	}
}
