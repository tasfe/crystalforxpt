package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Menu entity. @author MyEclipse Persistence Tools
 */

public class Menu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parentId;
	private String name;
	private String type;
	private String url;
	private String visiable;
	private String icon;
	private Integer orderFlag;
	private String privField;
	private String prop1;
	private String prop2;
	private String memo;
	private Timestamp addTime;
	private String addUser;
	private Timestamp modifyTime;
	private String modifyUser;
	private Map buttonMap;
	//private int roleType;
	private String roleType;
	// Constructors

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Integer parentId, String name, String type, Integer orderFlag,
			Timestamp addTime, String addUser) {
		this.parentId = parentId;
		this.name = name;
		this.type = type;
		this.orderFlag = orderFlag;
		this.addTime = addTime;
		this.addUser = addUser;
	}

	/** full constructor */
	public Menu(Integer parentId, String name, String type, String url,
			String visiable, String icon, Integer orderFlag, String privField,
			String prop1, String prop2, String memo, Timestamp addTime,
			String addUser, Timestamp modifyTime, String modifyUser) {
		this.parentId = parentId;
		this.name = name;
		this.type = type;
		this.url = url;
		this.visiable = visiable;
		this.icon = icon;
		this.orderFlag = orderFlag;
		this.privField = privField;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.memo = memo;
		this.addTime = addTime;
		this.addUser = addUser;
		this.modifyTime = modifyTime;
		this.modifyUser = modifyUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRoleType() {
		return roleType;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVisiable() {
		return this.visiable;
	}

	public void setVisiable(String visiable) {
		this.visiable = visiable;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderFlag() {
		return this.orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getPrivField() {
		return this.privField;
	}

	public void setPrivField(String privField) {
		this.privField = privField;
	}

	public String getProp1() {
		return this.prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return this.prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Map getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map buttonMap) {
		this.buttonMap = buttonMap;
	}

}