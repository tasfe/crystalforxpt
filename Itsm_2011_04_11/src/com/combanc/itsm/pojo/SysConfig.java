package com.combanc.itsm.pojo;

import java.sql.Date;

@SuppressWarnings("all")
public class SysConfig implements java.io.Serializable{
	private long id;
	private String enField;
	private String chField;
	private String value;
	private String property1;
	private String value1;
	private String property2;
	private String value2;
	private String property3;
	private String value3;
	private String property4;
	private String value4;
	private Users createUser;
	private Date createTime;
	private Users lastModifyUser;
	private Date lastModifyTime;
	
	protected SysConfig(){}
    
    protected SysConfig(long  id){
    	this.id=id;
    }
    
    public static SysConfig create(){
    	return new SysConfig(KeyTable.getNextKey());
    }
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnField() {
		return enField;
	}
	public void setEnField(String enField) {
		this.enField = enField;
	}
	public String getChField() {
		return chField;
	}
	public void setChField(String chField) {
		this.chField = chField;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getProperty1() {
		return property1;
	}
	public void setProperty1(String property1) {
		this.property1 = property1;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getProperty2() {
		return property2;
	}
	public void setProperty2(String property2) {
		this.property2 = property2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getProperty3() {
		return property3;
	}
	public void setProperty3(String property3) {
		this.property3 = property3;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getProperty4() {
		return property4;
	}
	public void setProperty4(String property4) {
		this.property4 = property4;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Users getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Users createUser) {
		this.createUser = createUser;
	}

	public Users getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Users lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
}
