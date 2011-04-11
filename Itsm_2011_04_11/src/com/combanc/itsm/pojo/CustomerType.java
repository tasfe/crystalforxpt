package com.combanc.itsm.pojo;

/**
 * CustomerType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CustomerType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer value;

	// Constructors

	/** default constructor */
	public CustomerType() {
	}

	/** full constructor */
	public CustomerType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	// Property accessors

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

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}