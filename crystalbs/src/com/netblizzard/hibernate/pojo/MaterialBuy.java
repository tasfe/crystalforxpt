package com.netblizzard.hibernate.pojo;

import java.sql.Timestamp;

/**
 * MaterialBuy entity. @author MyEclipse Persistence Tools
 */

public class MaterialBuy implements java.io.Serializable {

	// Fields

	private Integer id;
	private Material material;
	private String materialName;
	private Double materialPrice;
	private Integer count;
	private Double totalPrice;
	private Timestamp time;
	private String note1;
	private String note2;

	// Constructors

	/** default constructor */
	public MaterialBuy() {
	}

	/** minimal constructor */
	public MaterialBuy(Timestamp time) {
		this.time = time;
	}

	/** full constructor */
	public MaterialBuy(Material material, String materialName,
			Double materialPrice, Integer count, Double totalPrice,
			Timestamp time, String note1, String note2) {
		this.material = material;
		this.materialName = materialName;
		this.materialPrice = materialPrice;
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

	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Double getMaterialPrice() {
		return this.materialPrice;
	}

	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
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