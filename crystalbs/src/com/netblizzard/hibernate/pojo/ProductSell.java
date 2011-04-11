package com.netblizzard.hibernate.pojo;

import java.sql.Timestamp;

/**
 * ProductSell entity. @author MyEclipse Persistence Tools
 */

public class ProductSell implements java.io.Serializable {

	// Fields

	private Integer id;
	private Product product;
	private String productName;
	private Double productPrice;
	private Integer count;
	private Double totalPrice;
	private Timestamp time;
	private String note1;
	private String note2;

	// Constructors

	/** default constructor */
	public ProductSell() {
	}

	/** minimal constructor */
	public ProductSell(Timestamp time) {
		this.time = time;
	}

	/** full constructor */
	public ProductSell(Product product, String productName,
			Double productPrice, Integer count, Double totolPrice,
			Timestamp time, String note1, String note2) {
		this.product = product;
		this.productName = productName;
		this.productPrice = productPrice;
		this.count = count;
		this.totalPrice = totalPrice;
		this.time = time;
		this.note1 = note1;
		this.note2 = note2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

}