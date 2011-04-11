package com.combanc.itsm.pojo;

public class Holiday implements java.io.Serializable {

	private Integer id;
	private String name;
	private String holiday;

	public Holiday() {
	}

	public Holiday(String name, String holiday) {
		this.name = name;
		this.holiday = holiday;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHoliday() {
		return this.holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

}