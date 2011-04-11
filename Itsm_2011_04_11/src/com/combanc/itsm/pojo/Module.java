package com.combanc.itsm.pojo;

/**
 * Module entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Module implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String url;
	private String img;
	private Integer orderNo;
	private Integer pid;

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** full constructor */
	public Module(String name, String url, String img, Integer orderNo,
			Integer pid) {
		this.name = name;
		this.url = url;
		this.img = img;
		this.orderNo = orderNo;
		this.pid = pid;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}