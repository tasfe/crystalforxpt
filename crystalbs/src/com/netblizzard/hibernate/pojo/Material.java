package com.netblizzard.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Material entity. @author MyEclipse Persistence Tools
 */

public class Material implements java.io.Serializable {

	// Fields

	private Integer id;
	private MaterialCategory materialCategory;
	private String name;
	private Double price;
	private Integer type;
	private String typeNumber;
	private Integer height;
	private Integer width;
	private String color;
	private Integer countBuy;
	private Integer count;
	private Long batchNum;
	private String note1;
	private String note2;
	private Set productsForMid19 = new HashSet(0);
	private Set productsForMid11 = new HashSet(0);
	private Set productsForMid14 = new HashSet(0);
	private Set productsForMid16 = new HashSet(0);
	private Set productsForMid9 = new HashSet(0);
	private Set productsForMid5 = new HashSet(0);
	private Set materialBuies = new HashSet(0);
	private Set productsForMid3 = new HashSet(0);
	private Set productsForMid15 = new HashSet(0);
	private Set productsForMid2 = new HashSet(0);
	private Set productsForMid8 = new HashSet(0);
	private Set productsForMid1 = new HashSet(0);
	private Set productsForMid18 = new HashSet(0);
	private Set productsForMid12 = new HashSet(0);
	private Set productsForMid7 = new HashSet(0);
	private Set productsForMid10 = new HashSet(0);
	private Set productsForMid20 = new HashSet(0);
	private Set productsForMid13 = new HashSet(0);
	private Set productsForMid4 = new HashSet(0);
	private Set productsForMid17 = new HashSet(0);
	private Set productsForMid6 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Material() {
	}

	/** full constructor */
	public Material(MaterialCategory materialCategory, String name,
			Double price, Integer type, String typeNumber, Integer height,
			Integer width, String color, Integer countBuy, Integer count,
			Long batchNum, String note1, String note2, Set productsForMid19,
			Set productsForMid11, Set productsForMid14, Set productsForMid16,
			Set productsForMid9, Set productsForMid5, Set materialBuies,
			Set productsForMid3, Set productsForMid15, Set productsForMid2,
			Set productsForMid8, Set productsForMid1, Set productsForMid18,
			Set productsForMid12, Set productsForMid7, Set productsForMid10,
			Set productsForMid20, Set productsForMid13, Set productsForMid4,
			Set productsForMid17, Set productsForMid6) {
		this.materialCategory = materialCategory;
		this.name = name;
		this.price = price;
		this.type = type;
		this.typeNumber = typeNumber;
		this.height = height;
		this.width = width;
		this.color = color;
		this.countBuy = countBuy;
		this.count = count;
		this.batchNum = batchNum;
		this.note1 = note1;
		this.note2 = note2;
		this.productsForMid19 = productsForMid19;
		this.productsForMid11 = productsForMid11;
		this.productsForMid14 = productsForMid14;
		this.productsForMid16 = productsForMid16;
		this.productsForMid9 = productsForMid9;
		this.productsForMid5 = productsForMid5;
		this.materialBuies = materialBuies;
		this.productsForMid3 = productsForMid3;
		this.productsForMid15 = productsForMid15;
		this.productsForMid2 = productsForMid2;
		this.productsForMid8 = productsForMid8;
		this.productsForMid1 = productsForMid1;
		this.productsForMid18 = productsForMid18;
		this.productsForMid12 = productsForMid12;
		this.productsForMid7 = productsForMid7;
		this.productsForMid10 = productsForMid10;
		this.productsForMid20 = productsForMid20;
		this.productsForMid13 = productsForMid13;
		this.productsForMid4 = productsForMid4;
		this.productsForMid17 = productsForMid17;
		this.productsForMid6 = productsForMid6;
	}
	
	public String toString() {
		return this.name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MaterialCategory getMaterialCategory() {
		return this.materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeNumber() {
		return this.typeNumber;
	}

	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getCountBuy() {
		return this.countBuy;
	}

	public void setCountBuy(Integer countBuy) {
		this.countBuy = countBuy;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getBatchNum() {
		return this.batchNum;
	}

	public void setBatchNum(Long batchNum) {
		this.batchNum = batchNum;
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

	public Set getProductsForMid19() {
		return this.productsForMid19;
	}

	public void setProductsForMid19(Set productsForMid19) {
		this.productsForMid19 = productsForMid19;
	}

	public Set getProductsForMid11() {
		return this.productsForMid11;
	}

	public void setProductsForMid11(Set productsForMid11) {
		this.productsForMid11 = productsForMid11;
	}

	public Set getProductsForMid14() {
		return this.productsForMid14;
	}

	public void setProductsForMid14(Set productsForMid14) {
		this.productsForMid14 = productsForMid14;
	}

	public Set getProductsForMid16() {
		return this.productsForMid16;
	}

	public void setProductsForMid16(Set productsForMid16) {
		this.productsForMid16 = productsForMid16;
	}

	public Set getProductsForMid9() {
		return this.productsForMid9;
	}

	public void setProductsForMid9(Set productsForMid9) {
		this.productsForMid9 = productsForMid9;
	}

	public Set getProductsForMid5() {
		return this.productsForMid5;
	}

	public void setProductsForMid5(Set productsForMid5) {
		this.productsForMid5 = productsForMid5;
	}

	public Set getMaterialBuies() {
		return this.materialBuies;
	}

	public void setMaterialBuies(Set materialBuies) {
		this.materialBuies = materialBuies;
	}

	public Set getProductsForMid3() {
		return this.productsForMid3;
	}

	public void setProductsForMid3(Set productsForMid3) {
		this.productsForMid3 = productsForMid3;
	}

	public Set getProductsForMid15() {
		return this.productsForMid15;
	}

	public void setProductsForMid15(Set productsForMid15) {
		this.productsForMid15 = productsForMid15;
	}

	public Set getProductsForMid2() {
		return this.productsForMid2;
	}

	public void setProductsForMid2(Set productsForMid2) {
		this.productsForMid2 = productsForMid2;
	}

	public Set getProductsForMid8() {
		return this.productsForMid8;
	}

	public void setProductsForMid8(Set productsForMid8) {
		this.productsForMid8 = productsForMid8;
	}

	public Set getProductsForMid1() {
		return this.productsForMid1;
	}

	public void setProductsForMid1(Set productsForMid1) {
		this.productsForMid1 = productsForMid1;
	}

	public Set getProductsForMid18() {
		return this.productsForMid18;
	}

	public void setProductsForMid18(Set productsForMid18) {
		this.productsForMid18 = productsForMid18;
	}

	public Set getProductsForMid12() {
		return this.productsForMid12;
	}

	public void setProductsForMid12(Set productsForMid12) {
		this.productsForMid12 = productsForMid12;
	}

	public Set getProductsForMid7() {
		return this.productsForMid7;
	}

	public void setProductsForMid7(Set productsForMid7) {
		this.productsForMid7 = productsForMid7;
	}

	public Set getProductsForMid10() {
		return this.productsForMid10;
	}

	public void setProductsForMid10(Set productsForMid10) {
		this.productsForMid10 = productsForMid10;
	}

	public Set getProductsForMid20() {
		return this.productsForMid20;
	}

	public void setProductsForMid20(Set productsForMid20) {
		this.productsForMid20 = productsForMid20;
	}

	public Set getProductsForMid13() {
		return this.productsForMid13;
	}

	public void setProductsForMid13(Set productsForMid13) {
		this.productsForMid13 = productsForMid13;
	}

	public Set getProductsForMid4() {
		return this.productsForMid4;
	}

	public void setProductsForMid4(Set productsForMid4) {
		this.productsForMid4 = productsForMid4;
	}

	public Set getProductsForMid17() {
		return this.productsForMid17;
	}

	public void setProductsForMid17(Set productsForMid17) {
		this.productsForMid17 = productsForMid17;
	}

	public Set getProductsForMid6() {
		return this.productsForMid6;
	}

	public void setProductsForMid6(Set productsForMid6) {
		this.productsForMid6 = productsForMid6;
	}

}