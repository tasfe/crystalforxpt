package com.combanc.itsm.pojo;

/**
 * AddressBookManage entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class AddressBookManage implements java.io.Serializable {
	// Fields
	private Long id;
	private Long departmentId;
	private Long customertypeId;
	private String username;
	private String phone;
	private String telephone;
	private String phs;
	private String homephone;
	private String fax;
	private String email;
	private String homepage;
	private String qq;
	private String msn;
	private String address;
	private String zipCode;
	private String remarks;
	private String officePhone;
	private String departName;
	private Long userContactId;
	private Department department;
	private String companyName;
	private String duties;
	private String website;
	private String personalWebsite;
	private Users users;
	private CustomerType customertype;
	private String pinying;
	private  String initials;
	// Constructors

	/** default constructor */
	public AddressBookManage() {
	}

	/** full constructor */
	public AddressBookManage(Long departmentId, String username, String phone,
			String telephone, String phs, String homephone, String fax,
			String email, String homepage, String qq, String msn,
			String address, String zipCode, String remarks, String officePhone,
			String companyName, String duties, String website,
			String personalWebsite) {
		this.departmentId = departmentId;
		this.username = username;
		this.phone = phone;
		this.telephone = telephone;
		this.phs = phs;
		this.homephone = homephone;
		this.fax = fax;
		this.email = email;
		this.homepage = homepage;
		this.qq = qq;
		this.msn = msn;
		this.address = address;
		this.zipCode = zipCode;
		this.remarks = remarks;
		this.officePhone = officePhone;
		// this.companyName = companyName;
		// this.duties = duties;
		// this.website = website;
		// this.personalWebsite = personalWebsite;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public Long getCustomertypeId() {
		return customertypeId;
	}

	public void setCustomertypeId(Long customertypeId) {
		this.customertypeId = customertypeId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhs() {
		return this.phs;
	}

	public void setPhs(String phs) {
		this.phs = phs;
	}

	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return this.homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Long getUserContactId() {
		return userContactId;
	}

	public void setUserContactId(Long userContactId) {
		this.userContactId = userContactId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}

	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}

	public CustomerType getCustomertype() {
		return customertype;
	}

	public void setCustomertype(CustomerType customertype) {
		this.customertype = customertype;
	}

	public String getPinying() {
		return pinying;
	}

	public void setPinying(String pinying) {
		this.pinying = pinying;
	}
	
}