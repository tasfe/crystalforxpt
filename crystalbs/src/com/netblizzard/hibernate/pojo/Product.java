package com.netblizzard.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer id;
	private Material materialByMid15;
	private Material materialByMid4;
	private Material materialByMid9;
	private Material materialByMid3;
	private Material materialByMid19;
	private Material materialByMid16;
	private Material materialByMid7;
	private Material materialByMid10;
	private Material materialByMid2;
	private Material materialByMid12;
	private Material materialByMid17;
	private Material materialByMid6;
	private Material materialByMid11;
	private Material materialByMid20;
	private Material materialByMid13;
	private Material materialByMid1;
	private ProductCategory productCategory;
	private Material materialByMid14;
	private Material materialByMid5;
	private Material materialByMid18;
	private Material materialByMid8;
	private String name;
	private Double price;
	private Double materialPrice;
	private Integer countMake;
	private Integer count;
	private String color;
	private Integer height;
	private Integer width;
	private Integer mcount1;
	private Integer mcount2;
	private Integer mcount3;
	private Integer mcount4;
	private Integer mcount5;
	private Integer mcount6;
	private Integer mcount7;
	private Integer mcount8;
	private Integer mcount9;
	private Integer mcount10;
	private Integer mcount11;
	private Integer mcount12;
	private Integer mcount13;
	private Integer mcount14;
	private Integer mcount15;
	private Integer mcount16;
	private Integer mcount17;
	private Integer mcount18;
	private Integer mcount19;
	private Integer mcount20;
	private String note1;
	private String note2;
	private Set productSells = new HashSet(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(Material materialByMid15, Material materialByMid4,
			Material materialByMid9, Material materialByMid3,
			Material materialByMid19, Material materialByMid16,
			Material materialByMid7, Material materialByMid10,
			Material materialByMid2, Material materialByMid12,
			Material materialByMid17, Material materialByMid6,
			Material materialByMid11, Material materialByMid20,
			Material materialByMid13, Material materialByMid1,
			ProductCategory productCategory, Material materialByMid14,
			Material materialByMid5, Material materialByMid18,
			Material materialByMid8, String name, Double price,
			Double materialPrice, Integer countMake, Integer count,
			String color, Integer height, Integer width, Integer mcount1,
			Integer mcount2, Integer mcount3, Integer mcount4, Integer mcount5,
			Integer mcount6, Integer mcount7, Integer mcount8, Integer mcount9,
			Integer mcount10, Integer mcount11, Integer mcount12,
			Integer mcount13, Integer mcount14, Integer mcount15,
			Integer mcount16, Integer mcount17, Integer mcount18,
			Integer mcount19, Integer mcount20, String note1, String note2,
			Set productSells) {
		this.materialByMid15 = materialByMid15;
		this.materialByMid4 = materialByMid4;
		this.materialByMid9 = materialByMid9;
		this.materialByMid3 = materialByMid3;
		this.materialByMid19 = materialByMid19;
		this.materialByMid16 = materialByMid16;
		this.materialByMid7 = materialByMid7;
		this.materialByMid10 = materialByMid10;
		this.materialByMid2 = materialByMid2;
		this.materialByMid12 = materialByMid12;
		this.materialByMid17 = materialByMid17;
		this.materialByMid6 = materialByMid6;
		this.materialByMid11 = materialByMid11;
		this.materialByMid20 = materialByMid20;
		this.materialByMid13 = materialByMid13;
		this.materialByMid1 = materialByMid1;
		this.productCategory = productCategory;
		this.materialByMid14 = materialByMid14;
		this.materialByMid5 = materialByMid5;
		this.materialByMid18 = materialByMid18;
		this.materialByMid8 = materialByMid8;
		this.name = name;
		this.price = price;
		this.materialPrice = materialPrice;
		this.countMake = countMake;
		this.count = count;
		this.color = color;
		this.height = height;
		this.width = width;
		this.mcount1 = mcount1;
		this.mcount2 = mcount2;
		this.mcount3 = mcount3;
		this.mcount4 = mcount4;
		this.mcount5 = mcount5;
		this.mcount6 = mcount6;
		this.mcount7 = mcount7;
		this.mcount8 = mcount8;
		this.mcount9 = mcount9;
		this.mcount10 = mcount10;
		this.mcount11 = mcount11;
		this.mcount12 = mcount12;
		this.mcount13 = mcount13;
		this.mcount14 = mcount14;
		this.mcount15 = mcount15;
		this.mcount16 = mcount16;
		this.mcount17 = mcount17;
		this.mcount18 = mcount18;
		this.mcount19 = mcount19;
		this.mcount20 = mcount20;
		this.note1 = note1;
		this.note2 = note2;
		this.productSells = productSells;
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

	public Material getMaterialByMid15() {
		return this.materialByMid15;
	}

	public void setMaterialByMid15(Material materialByMid15) {
		this.materialByMid15 = materialByMid15;
	}

	public Material getMaterialByMid4() {
		return this.materialByMid4;
	}

	public void setMaterialByMid4(Material materialByMid4) {
		this.materialByMid4 = materialByMid4;
	}

	public Material getMaterialByMid9() {
		return this.materialByMid9;
	}

	public void setMaterialByMid9(Material materialByMid9) {
		this.materialByMid9 = materialByMid9;
	}

	public Material getMaterialByMid3() {
		return this.materialByMid3;
	}

	public void setMaterialByMid3(Material materialByMid3) {
		this.materialByMid3 = materialByMid3;
	}

	public Material getMaterialByMid19() {
		return this.materialByMid19;
	}

	public void setMaterialByMid19(Material materialByMid19) {
		this.materialByMid19 = materialByMid19;
	}

	public Material getMaterialByMid16() {
		return this.materialByMid16;
	}

	public void setMaterialByMid16(Material materialByMid16) {
		this.materialByMid16 = materialByMid16;
	}

	public Material getMaterialByMid7() {
		return this.materialByMid7;
	}

	public void setMaterialByMid7(Material materialByMid7) {
		this.materialByMid7 = materialByMid7;
	}

	public Material getMaterialByMid10() {
		return this.materialByMid10;
	}

	public void setMaterialByMid10(Material materialByMid10) {
		this.materialByMid10 = materialByMid10;
	}

	public Material getMaterialByMid2() {
		return this.materialByMid2;
	}

	public void setMaterialByMid2(Material materialByMid2) {
		this.materialByMid2 = materialByMid2;
	}

	public Material getMaterialByMid12() {
		return this.materialByMid12;
	}

	public void setMaterialByMid12(Material materialByMid12) {
		this.materialByMid12 = materialByMid12;
	}

	public Material getMaterialByMid17() {
		return this.materialByMid17;
	}

	public void setMaterialByMid17(Material materialByMid17) {
		this.materialByMid17 = materialByMid17;
	}

	public Material getMaterialByMid6() {
		return this.materialByMid6;
	}

	public void setMaterialByMid6(Material materialByMid6) {
		this.materialByMid6 = materialByMid6;
	}

	public Material getMaterialByMid11() {
		return this.materialByMid11;
	}

	public void setMaterialByMid11(Material materialByMid11) {
		this.materialByMid11 = materialByMid11;
	}

	public Material getMaterialByMid20() {
		return this.materialByMid20;
	}

	public void setMaterialByMid20(Material materialByMid20) {
		this.materialByMid20 = materialByMid20;
	}

	public Material getMaterialByMid13() {
		return this.materialByMid13;
	}

	public void setMaterialByMid13(Material materialByMid13) {
		this.materialByMid13 = materialByMid13;
	}

	public Material getMaterialByMid1() {
		return this.materialByMid1;
	}

	public void setMaterialByMid1(Material materialByMid1) {
		this.materialByMid1 = materialByMid1;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Material getMaterialByMid14() {
		return this.materialByMid14;
	}

	public void setMaterialByMid14(Material materialByMid14) {
		this.materialByMid14 = materialByMid14;
	}

	public Material getMaterialByMid5() {
		return this.materialByMid5;
	}

	public void setMaterialByMid5(Material materialByMid5) {
		this.materialByMid5 = materialByMid5;
	}

	public Material getMaterialByMid18() {
		return this.materialByMid18;
	}

	public void setMaterialByMid18(Material materialByMid18) {
		this.materialByMid18 = materialByMid18;
	}

	public Material getMaterialByMid8() {
		return this.materialByMid8;
	}

	public void setMaterialByMid8(Material materialByMid8) {
		this.materialByMid8 = materialByMid8;
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

	public Double getMaterialPrice() {
		return this.materialPrice;
	}

	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}

	public Integer getCountMake() {
		return this.countMake;
	}

	public void setCountMake(Integer countMake) {
		this.countMake = countMake;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public Integer getMcount1() {
		return this.mcount1;
	}

	public void setMcount1(Integer mcount1) {
		this.mcount1 = mcount1;
	}

	public Integer getMcount2() {
		return this.mcount2;
	}

	public void setMcount2(Integer mcount2) {
		this.mcount2 = mcount2;
	}

	public Integer getMcount3() {
		return this.mcount3;
	}

	public void setMcount3(Integer mcount3) {
		this.mcount3 = mcount3;
	}

	public Integer getMcount4() {
		return this.mcount4;
	}

	public void setMcount4(Integer mcount4) {
		this.mcount4 = mcount4;
	}

	public Integer getMcount5() {
		return this.mcount5;
	}

	public void setMcount5(Integer mcount5) {
		this.mcount5 = mcount5;
	}

	public Integer getMcount6() {
		return this.mcount6;
	}

	public void setMcount6(Integer mcount6) {
		this.mcount6 = mcount6;
	}

	public Integer getMcount7() {
		return this.mcount7;
	}

	public void setMcount7(Integer mcount7) {
		this.mcount7 = mcount7;
	}

	public Integer getMcount8() {
		return this.mcount8;
	}

	public void setMcount8(Integer mcount8) {
		this.mcount8 = mcount8;
	}

	public Integer getMcount9() {
		return this.mcount9;
	}

	public void setMcount9(Integer mcount9) {
		this.mcount9 = mcount9;
	}

	public Integer getMcount10() {
		return this.mcount10;
	}

	public void setMcount10(Integer mcount10) {
		this.mcount10 = mcount10;
	}

	public Integer getMcount11() {
		return this.mcount11;
	}

	public void setMcount11(Integer mcount11) {
		this.mcount11 = mcount11;
	}

	public Integer getMcount12() {
		return this.mcount12;
	}

	public void setMcount12(Integer mcount12) {
		this.mcount12 = mcount12;
	}

	public Integer getMcount13() {
		return this.mcount13;
	}

	public void setMcount13(Integer mcount13) {
		this.mcount13 = mcount13;
	}

	public Integer getMcount14() {
		return this.mcount14;
	}

	public void setMcount14(Integer mcount14) {
		this.mcount14 = mcount14;
	}

	public Integer getMcount15() {
		return this.mcount15;
	}

	public void setMcount15(Integer mcount15) {
		this.mcount15 = mcount15;
	}

	public Integer getMcount16() {
		return this.mcount16;
	}

	public void setMcount16(Integer mcount16) {
		this.mcount16 = mcount16;
	}

	public Integer getMcount17() {
		return this.mcount17;
	}

	public void setMcount17(Integer mcount17) {
		this.mcount17 = mcount17;
	}

	public Integer getMcount18() {
		return this.mcount18;
	}

	public void setMcount18(Integer mcount18) {
		this.mcount18 = mcount18;
	}

	public Integer getMcount19() {
		return this.mcount19;
	}

	public void setMcount19(Integer mcount19) {
		this.mcount19 = mcount19;
	}

	public Integer getMcount20() {
		return this.mcount20;
	}

	public void setMcount20(Integer mcount20) {
		this.mcount20 = mcount20;
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

	public Set getProductSells() {
		return this.productSells;
	}

	public void setProductSells(Set productSells) {
		this.productSells = productSells;
	}

}