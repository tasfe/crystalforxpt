package com.combanc.itsm.pojo;

public class ErrorType implements java.io.Serializable {

	private Integer id;
	private String name;
	private String reason;

	public ErrorType() {
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

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}