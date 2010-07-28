package crystal.hibernate.po;

import java.util.HashSet;
import java.util.Set;

/**
 * ProductCategory entity. @author MyEclipse Persistence Tools
 */

public class ProductCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String note;
	private Integer isUse;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public ProductCategory() {
	}

	/** full constructor */
	public ProductCategory(String name, String note, Integer isUse, Set products) {
		this.name = name;
		this.note = note;
		this.isUse = isUse;
		this.products = products;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}