/**
 * 
 */
package com.combanc.itsm.htmlobj;

import java.sql.Date;


/**
 * @author Administrator
 * 
 */
public class AssetsQurey {

	private String name;
	private String code;
	private int support;
	private int pruducer;
	private int type;
	private Date inDate;
	private Date backDate;
	private int state;
	private int department;
	private Date startDate;
	private Date endDate;
	private Date startDate2;
	private Date endDate2;
	private String showtype;
	private int place;

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getShowtype() {
		return showtype;
	}

	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}

	public Date getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}

	public Date getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}

	public Date getBackDate() {
		return backDate;
	}

	public String getCode() {
		return code;
	}

	public int getDepartment() {
		return department;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Date getInDate() {
		return inDate;
	}

	public String getName() {
		return name;
	}

	public int getPruducer() {
		return pruducer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getState() {
		return state;
	}

	public int getSupport() {
		return support;
	}

	public int getType() {
		return type;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPruducer(int pruducer) {
		this.pruducer = pruducer;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setSupport(int support) {
		this.support = support;
	}

	public void setType(int type) {
		this.type = type;
	}

}
