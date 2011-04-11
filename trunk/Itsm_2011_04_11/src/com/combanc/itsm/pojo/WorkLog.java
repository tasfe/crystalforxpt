package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * WorkLog entity. @author MyEclipse Persistence Tools
 */

public class WorkLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5638581094582378853L;
	private Integer id;
	private Users users;
	private Location location;


	private Department department;
	private String content;
	private Date time;
	private String title;
	private int type;
	private String note;//摘要
	private Timestamp timesumbit;
	private String departmentName;
	private String userName;
	private String locationName;
    private int wordpress;
	// Constructors

	

	/** default constructor */
	public WorkLog() {
	}

	/** minimal constructor */
	public WorkLog(Integer id, Date time, Integer type) {
		this.id = id;
		this.time = time;
		this.type = type;
	}

	/** full constructor */
	public WorkLog(Integer id, Users users, Location location,
			Department department, String content, Date time,
			String title, Integer type) {
		this.id = id;
		this.users = users;
		this.location = location;
		this.department = department;
		this.content = content;
		this.time = time;
		this.title = title;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the wordpress
	 */
	public int getWordpress() {
		return wordpress;
	}

	/**
	 * @param wordpress the wordpress to set
	 */
	public void setWordpress(int wordpress) {
		this.wordpress = wordpress;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}



	/**
	 * @return the timesumbit
	 */
	public Timestamp getTimesumbit() {
		return timesumbit;
	}

	/**
	 * @param timesumbit the timesumbit to set
	 */
	public void setTimesumbit(Timestamp timesumbit) {
		this.timesumbit = timesumbit;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}