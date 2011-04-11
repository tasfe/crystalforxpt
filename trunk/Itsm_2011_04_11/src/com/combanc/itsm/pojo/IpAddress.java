package com.combanc.itsm.pojo;

import java.util.Date;

/**
 * IpAdress entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IpAddress implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String serialNumber;
	private Integer applyIpv4Count;
	private Integer applyIpv6Count;
	private String applyIpPurpose;
	private String ipUseRoom;
	private String unitsFullName;
	private String unitsAddress;
	private Integer unitsExtentIpCount;
	private String unitsExtentIpPurpose;
	private String technicalContact;
	private String phone;
	private String email;
	private String applyIpCause;
	private String unitsLeader;
	private String application;
	private Date applicationDate;
	private String receiver;
	private Date receiveDate;
	private String completer;
	private Date completeDate;
	private String applyIpv4Detail;
	private String applyIpv6Detail;
	private String unitsIpv4Detail;
	private String unitsIpv6Detail;
	private Integer state; //审批状态
	private Integer approvalResult;//审批结果
	private String failReason; //未通过审批原因
	private Users sumbitUsers;
	

	// Constructors




	public IpAddress(Integer id, String serialNumber, Integer applyIpv4Count,
			Integer applyIpv6Count, String applyIpPurpose, String ipUseRoom,
			String unitsFullName, String unitsAddress,
			Integer unitsExtentIpCount, String unitsExtentIpPurpose,
			String technicalContact, String phone, String email,
			String applyIpCause, String unitsLeader, String application,
			Date applicationDate, String receiver, Date receiveDate,
			String completer, Date completeDate, String applyIpv4Detail,
			String applyIpv6Detail, String unitsIpv4Detail,
			String unitsIpv6Detail, Integer state, Integer approvalResult,
			String failReason) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.applyIpv4Count = applyIpv4Count;
		this.applyIpv6Count = applyIpv6Count;
		this.applyIpPurpose = applyIpPurpose;
		this.ipUseRoom = ipUseRoom;
		this.unitsFullName = unitsFullName;
		this.unitsAddress = unitsAddress;
		this.unitsExtentIpCount = unitsExtentIpCount;
		this.unitsExtentIpPurpose = unitsExtentIpPurpose;
		this.technicalContact = technicalContact;
		this.phone = phone;
		this.email = email;
		this.applyIpCause = applyIpCause;
		this.unitsLeader = unitsLeader;
		this.application = application;
		this.applicationDate = applicationDate;
		this.receiver = receiver;
		this.receiveDate = receiveDate;
		this.completer = completer;
		this.completeDate = completeDate;
		this.applyIpv4Detail = applyIpv4Detail;
		this.applyIpv6Detail = applyIpv6Detail;
		this.unitsIpv4Detail = unitsIpv4Detail;
		this.unitsIpv6Detail = unitsIpv6Detail;
		this.state = state;
		this.approvalResult = approvalResult;
		this.failReason = failReason;
	}

	public Users getSumbitUsers() {
		return sumbitUsers;
	}

	public void setSumbitUsers(Users sumbitUsers) {
		this.sumbitUsers = sumbitUsers;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	/** default constructor */
	public IpAddress() {
	}

	/** full constructor */


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplyIpv4Count() {
		return this.applyIpv4Count;
	}

	public void setApplyIpv4Count(Integer applyIpv4Count) {
		this.applyIpv4Count = applyIpv4Count;
	}

	public Integer getApplyIpv6Count() {
		return this.applyIpv6Count;
	}

	public void setApplyIpv6Count(Integer applyIpv6Count) {
		this.applyIpv6Count = applyIpv6Count;
	}

	public String getApplyIpPurpose() {
		return this.applyIpPurpose;
	}

	public void setApplyIpPurpose(String applyIpPurpose) {
		this.applyIpPurpose = applyIpPurpose;
	}

	public String getIpUseRoom() {
		return this.ipUseRoom;
	}

	public void setIpUseRoom(String ipUseRoom) {
		this.ipUseRoom = ipUseRoom;
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

	public Integer getUnitsExtentIpCount() {
		return this.unitsExtentIpCount;
	}

	public void setUnitsExtentIpCount(Integer unitsExtentIpCount) {
		this.unitsExtentIpCount = unitsExtentIpCount;
	}

	public String getUnitsExtentIpPurpose() {
		return this.unitsExtentIpPurpose;
	}

	public void setUnitsExtentIpPurpose(String unitsExtentIpPurpose) {
		this.unitsExtentIpPurpose = unitsExtentIpPurpose;
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

	public String getApplyIpCause() {
		return this.applyIpCause;
	}

	public void setApplyIpCause(String applyIpCause) {
		this.applyIpCause = applyIpCause;
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
		return completeDate;
	}

	public String getApplyIpv4Detail() {
		return this.applyIpv4Detail;
	}

	public void setApplyIpv4Detail(String applyIpv4Detail) {
		this.applyIpv4Detail = applyIpv4Detail;
	}

	public String getApplyIpv6Detail() {
		return this.applyIpv6Detail;
	}

	public void setApplyIpv6Detail(String applyIpv6Detail) {
		this.applyIpv6Detail = applyIpv6Detail;
	}

	public String getUnitsIpv4Detail() {
		return this.unitsIpv4Detail;
	}

	public void setUnitsIpv4Detail(String unitsIpv4Detail) {
		this.unitsIpv4Detail = unitsIpv4Detail;
	}

	public String getUnitsIpv6Detail() {
		return this.unitsIpv6Detail;
	}

	public void setUnitsIpv6Detail(String unitsIpv6Detail) {
		this.unitsIpv6Detail = unitsIpv6Detail;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}

}