package com.combanc.itsm.pojo;

/**
 * IpNetcenter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IpNetcenter implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer type;
	private String phone;
	private String email;

	// Constructors

	/** default constructor */
	public IpNetcenter() {
	}

	/** full constructor */
	public IpNetcenter(String name, Integer type, String phone, String email) {
		this.name = name;
		this.type = type;
		this.phone = phone;
		this.email = email;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}