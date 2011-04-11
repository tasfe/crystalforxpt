package com.combanc.itsm.pojo;

import java.util.Date;

/**
 * Domain entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DomainRegister implements java.io.Serializable {

	// Fields

	private Integer id;
	private String serialNumber;
	private String domain;
	private String ipAddress;
	private String serverLocation;
	private String unitsFullName;
	private String unitsAddress;
	private String technicalContact;
	private String phone;
	private String email;
	private String foreignDomain;
	private String foreignIpAddress;
	private String foreignServerLocation;
	private String foreignUnitsFullName;
	private String foreignUnitsAddress;
	private String foreignTechnicalContact;
	private String foreignPhone;
	private String foreignEmail;
	private String remark;
	private String unitsLeader;
	private String application;
	private Date applicationDate;
	private String receiver;
	private Date receiveDate;
	private String completer;
	private Date completeDate;
	private Integer state;
	private Integer approvalResult;
	private String failReason;
	private Users sumbitUsers;

	// Constructors

	/** default constructor */
	public DomainRegister() {
	}

	/** full constructor */
	public DomainRegister(String serialNumber, String domain, String ipAddress,
			String serverLocation, String unitsFullName, String unitsAddress,
			String technicalContact, String phone, String email,
			String foreignDomain, String foreignIpAddress,
			String foreignServerLocation, String foreignUnitsFullName,
			String foreignUnitsAddress, String foreignTechnicalContact,
			String foreignPhone, String foreignEmail, String remark,
			String unitsLeader, String application, Date applicationDate,
			String receiver, Date receiveDate, String completer,
			Date completeDate, Integer state, Integer approvalResult,
			String failReason) {
		this.serialNumber = serialNumber;
		this.domain = domain;
		this.ipAddress = ipAddress;
		this.serverLocation = serverLocation;
		this.unitsFullName = unitsFullName;
		this.unitsAddress = unitsAddress;
		this.technicalContact = technicalContact;
		this.phone = phone;
		this.email = email;
		this.foreignDomain = foreignDomain;
		this.foreignIpAddress = foreignIpAddress;
		this.foreignServerLocation = foreignServerLocation;
		this.foreignUnitsFullName = foreignUnitsFullName;
		this.foreignUnitsAddress = foreignUnitsAddress;
		this.foreignTechnicalContact = foreignTechnicalContact;
		this.foreignPhone = foreignPhone;
		this.foreignEmail = foreignEmail;
		this.remark = remark;
		this.unitsLeader = unitsLeader;
		this.application = application;
		this.applicationDate = applicationDate;
		this.receiver = receiver;
		this.receiveDate = receiveDate;
		this.completer = completer;
		this.completeDate = completeDate;
		this.state = state;
		this.approvalResult = approvalResult;
		this.failReason = failReason;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Users getSumbitUsers() {
		return sumbitUsers;
	}

	public void setSumbitUsers(Users sumbitUsers) {
		this.sumbitUsers = sumbitUsers;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getServerLocation() {
		return this.serverLocation;
	}

	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}

	public String getUnitsFullName() {
		return this.unitsFullName;
	}

	public void setUnitsFullName(String unitsFullName) {
		this.unitsFullName = unitsFullName;
	}

	public String getUnitsAddress() {
		return this.unitsAddress;
	}

	public void setUnitsAddress(String unitsAddress) {
		this.unitsAddress = unitsAddress;
	}

	public String getTechnicalContact() {
		return this.technicalContact;
	}

	public void setTechnicalContact(String technicalContact) {
		this.technicalContact = technicalContact;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getForeignDomain() {
		return this.foreignDomain;
	}

	public void setForeignDomain(String foreignDomain) {
		this.foreignDomain = foreignDomain;
	}

	public String getForeignIpAddress() {
		return this.foreignIpAddress;
	}

	public void setForeignIpAddress(String foreignIpAddress) {
		this.foreignIpAddress = foreignIpAddress;
	}

	public String getForeignServerLocation() {
		return this.foreignServerLocation;
	}

	public void setForeignServerLocation(String foreignServerLocation) {
		this.foreignServerLocation = foreignServerLocation;
	}

	public String getForeignUnitsFullName() {
		return this.foreignUnitsFullName;
	}

	public void setForeignUnitsFullName(String foreignUnitsFullName) {
		this.foreignUnitsFullName = foreignUnitsFullName;
	}

	public String getForeignUnitsAddress() {
		return this.foreignUnitsAddress;
	}

	public void setForeignUnitsAddress(String foreignUnitsAddress) {
		this.foreignUnitsAddress = foreignUnitsAddress;
	}

	public String getForeignTechnicalContact() {
		return this.foreignTechnicalContact;
	}

	public void setForeignTechnicalContact(String foreignTechnicalContact) {
		this.foreignTechnicalContact = foreignTechnicalContact;
	}

	public String getForeignPhone() {
		return this.foreignPhone;
	}

	public void setForeignPhone(String foreignPhone) {
		this.foreignPhone = foreignPhone;
	}

	public String getForeignEmail() {
		return this.foreignEmail;
	}

	public void setForeignEmail(String foreignEmail) {
		this.foreignEmail = foreignEmail;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnitsLeader() {
		return this.unitsLeader;
	}

	public void setUnitsLeader(String unitsLeader) {
		this.unitsLeader = unitsLeader;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Date getApplicationDate() {
		return this.applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getCompleter() {
		return this.completer;
	}

	public void setCompleter(String completer) {
		this.completer = completer;
	}

	public Date getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getApprovalResult() {
		return this.approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

}