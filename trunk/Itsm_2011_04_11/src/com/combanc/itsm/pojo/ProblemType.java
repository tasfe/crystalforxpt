package com.combanc.itsm.pojo;

public class ProblemType implements java.io.Serializable {

	private Integer id;
	private String problemCate;
	private String problemCateSC;
	private String code;
	private Integer pid;
	private Integer addTable1;
	private Integer addTable2;

	public ProblemType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblemCate() {
		return this.problemCate;
	}

	public void setProblemCate(String problemCate) {
		this.problemCate = problemCate;
	}

	public String getProblemCateSC() {
		return problemCateSC;
	}

	public void setProblemCateSC(String problemCateSC) {
		this.problemCateSC = problemCateSC;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAddTable1() {
		return this.addTable1;
	}

	public void setAddTable1(Integer addTable1) {
		this.addTable1 = addTable1;
	}

	public Integer getAddTable2() {
		return this.addTable2;
	}

	public void setAddTable2(Integer addTable2) {
		this.addTable2 = addTable2;
	}

}