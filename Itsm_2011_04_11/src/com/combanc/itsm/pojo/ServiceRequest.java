package com.combanc.itsm.pojo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ServiceRequest entity. @author MyEclipse Persistence Tools
 */

public class ServiceRequest implements java.io.Serializable {

	// Fields

	private Integer id;
	private Department departmentByRequestDept;
	private Department departmentBySourceDept;
	private ServiceCategory serviceCategory;
	private Users usersByOperatorId;
	private Users usersByOriginatorId;
	private Integer severityTypBySeverity;
	private Integer severityTypByEmergency;
	private Users usersByRequestUser;
	private Integer severityTypByEssential;
	private Users usersByEngineerId;
	private String requestNo;
	private Integer score;
	private Integer times;
	private String source;
	private String message;
	private String relationEvents;
	private Timestamp submintTime;
	private Timestamp expectTime;
	private Timestamp assignTime;
	private String description;
	private String ip;
	private String cause;
	private Integer isFinished;
	private Integer priorityLvl;
	private String solution;
	private String errorCause;
	private String returnCause;
	private String pauseCause;
	private String transmitCause;
	private String plan;
	private String summary;
	private String ci;
	private Timestamp beginTime;
	private Timestamp finishTime;
	private String feedback;
	private Timestamp promiseTime;
	private Integer state;
	private Integer addToKnowledge;
	private Timestamp responseTime;
	private Timestamp transmitTime;
	private String acceptEngineers;
	private Integer processStep;
	private Integer serviceLvl;
	private String dealEngineers;
	private String tableInput;
	private Integer temp1;
	private String temp2;
	private String temp3;
	private String temp4;
	private Timestamp startDate;
	private Timestamp endDate;
	private Set accessoryList = new HashSet(0);
	private Set assets =new HashSet(0);

	// Constructors



	public Set getAssets() {
		return assets;
	}

	public void setAssets(Set assets) {
		this.assets = assets;
	}

	/**
	 * @return the accessoryList
	 */
	public Set getAccessoryList() {
		return accessoryList;
	}

	/**
	 * @param accessoryList the accessoryList to set
	 */
	public void setAccessoryList(Set accessoryList) {
		this.accessoryList = accessoryList;
	}

	/** default constructor */
	public ServiceRequest() {
	}

	/** full constructor */
	public ServiceRequest(Department departmentByRequestDept,
			Department departmentBySourceDept, ServiceCategory serviceCategory,
			Users usersByOperatorId, Integer severityTypBySeverity, Integer severityTypByEmergency,
			Users usersByRequestUser, Integer severityTypByEssential,
			Users usersByEngineerId,Users  usersByOriginatorId, String requestNo, Integer score,
			Integer times, String message, String relationEvents,
			Timestamp submintTime, Timestamp expectTime, Timestamp assignTime,
			String description, String ip, String cause, Integer isFinished,
			Integer priorityLvl, String solution, String errorCause,
			String summary, String ci, Timestamp beginTime,
			Timestamp finishTime, String feedback, Timestamp promiseTime,
			Integer originator, Integer state, Integer addToKnowledge,
			Timestamp responseTime, Timestamp transmitTime,
			String acceptEngineers, Integer processStep, Integer serviceLvl,
			String dealEngineers, String tableInput, Integer temp1,
			String temp2, String temp3, String temp4) {
		this.departmentByRequestDept = departmentByRequestDept;
		this.departmentBySourceDept = departmentBySourceDept;
		this.serviceCategory = serviceCategory;
		this.usersByOperatorId = usersByOperatorId;
		this.severityTypByEmergency = severityTypByEmergency;
		this.usersByRequestUser = usersByRequestUser;
		this.severityTypByEssential = severityTypByEssential;
		this.usersByEngineerId = usersByEngineerId;
		this.usersByOriginatorId=usersByOriginatorId;
		this.requestNo = requestNo;
		this.score = score;
		this.times = times;
		this.message = message;
		this.relationEvents = relationEvents;
		this.submintTime = submintTime;
		this.expectTime = expectTime;
		this.assignTime = assignTime;
		this.description = description;
		this.ip = ip;
		this.cause = cause;
		this.isFinished = isFinished;
		this.priorityLvl = priorityLvl;
		this.solution = solution;
		this.errorCause = errorCause;
		this.summary = summary;
		this.ci = ci;
		this.beginTime = beginTime;
		this.finishTime = finishTime;
		this.feedback = feedback;
		this.promiseTime = promiseTime;
		this.state = state;
		this.addToKnowledge = addToKnowledge;
		this.responseTime = responseTime;
		this.transmitTime = transmitTime;
		this.acceptEngineers = acceptEngineers;
		this.processStep = processStep;
		this.serviceLvl = serviceLvl;
		this.dealEngineers = dealEngineers;
		this.tableInput = tableInput;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.temp3 = temp3;
		this.temp4 = temp4;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartmentByRequestDept() {
		return this.departmentByRequestDept;
	}

	public void setDepartmentByRequestDept(Department departmentByRequestDept) {
		this.departmentByRequestDept = departmentByRequestDept;
	}

	public Department getDepartmentBySourceDept() {
		return this.departmentBySourceDept;
	}

	public void setDepartmentBySourceDept(Department departmentBySourceDept) {
		this.departmentBySourceDept = departmentBySourceDept;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Users getUsersByOperatorId() {
		return this.usersByOperatorId;
	}

	public void setUsersByOperatorId(Users usersByOperatorId) {
		this.usersByOperatorId = usersByOperatorId;
	}

	public Integer getSeverityTypByEmergency() {
		return this.severityTypByEmergency;
	}

	public void setSeverityTypByEmergency(Integer severityTypByEmergency) {
		this.severityTypByEmergency = severityTypByEmergency;
	}

	public Users getUsersByRequestUser() {
		return this.usersByRequestUser;
	}

	public void setUsersByRequestUser(Users usersByRequestUser) {
		this.usersByRequestUser = usersByRequestUser;
	}

	public Integer getSeverityTypByEssential() {
		return this.severityTypByEssential;
	}

	public void setSeverityTypByEssential(Integer severityTypByEssential) {
		this.severityTypByEssential = severityTypByEssential;
	}

	public Users getUsersByEngineerId() {
		return this.usersByEngineerId;
	}

	public void setUsersByEngineerId(Users usersByEngineerId) {
		this.usersByEngineerId = usersByEngineerId;
	}

	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRelationEvents() {
		return this.relationEvents;
	}

	public void setRelationEvents(String relationEvents) {
		this.relationEvents = relationEvents;
	}

	public Timestamp getSubmintTime() {
		return this.submintTime;
	}

	public void setSubmintTime(Timestamp submintTime) {
		this.submintTime = submintTime;
	}

	public Timestamp getExpectTime() {
		return this.expectTime;
	}

	public void setExpectTime(Timestamp expectTime) {
		this.expectTime = expectTime;
	}

	public Timestamp getAssignTime() {
		return this.assignTime;
	}

	public void setAssignTime(Timestamp assignTime) {
		this.assignTime = assignTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Integer getIsFinished() {
		return this.isFinished;
	}

	public void setIsFinished(Integer isFinished) {
		this.isFinished = isFinished;
	}

	public Integer getPriorityLvl() {
		return this.priorityLvl;
	}

	public void setPriorityLvl(Integer priorityLvl) {
		this.priorityLvl = priorityLvl;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getErrorCause() {
		return this.errorCause;
	}

	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Timestamp getPromiseTime() {
		return this.promiseTime;
	}

	public void setPromiseTime(Timestamp promiseTime) {
		this.promiseTime = promiseTime;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAddToKnowledge() {
		return this.addToKnowledge;
	}

	public void setAddToKnowledge(Integer addToKnowledge) {
		this.addToKnowledge = addToKnowledge;
	}

	public Timestamp getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public Timestamp getTransmitTime() {
		return this.transmitTime;
	}

	public void setTransmitTime(Timestamp transmitTime) {
		this.transmitTime = transmitTime;
	}

	public String getAcceptEngineers() {
		return this.acceptEngineers;
	}

	public void setAcceptEngineers(String acceptEngineers) {
		this.acceptEngineers = acceptEngineers;
	}

	public Integer getProcessStep() {
		return this.processStep;
	}

	public void setProcessStep(Integer processStep) {
		this.processStep = processStep;
	}

	public Integer getServiceLvl() {
		return this.serviceLvl;
	}

	public void setServiceLvl(Integer serviceLvl) {
		this.serviceLvl = serviceLvl;
	}

	public String getDealEngineers() {
		return this.dealEngineers;
	}

	public void setDealEngineers(String dealEngineers) {
		this.dealEngineers = dealEngineers;
	}

	public String getTableInput() {
		return this.tableInput;
	}

	public void setTableInput(String tableInput) {
		this.tableInput = tableInput;
	}

	public Integer getTemp1() {
		return this.temp1;
	}

	public void setTemp1(Integer temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return this.temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getTemp3() {
		return this.temp3;
	}

	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}

	public String getTemp4() {
		return this.temp4;
	}

	public void setTemp4(String temp4) {
		this.temp4 = temp4;
	}

	public Integer getSeverityTypBySeverity() {
		return severityTypBySeverity;
	}

	public void setSeverityTypBySeverity(Integer severityTypBySeverity) {
		this.severityTypBySeverity = severityTypBySeverity;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReturnCause() {
		return returnCause;
	}

	public void setReturnCause(String returnCause) {
		this.returnCause = returnCause;
	}

	public String getTransmitCause() {
		return transmitCause;
	}

	public void setTransmitCause(String transmitCause) {
		this.transmitCause = transmitCause;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getPauseCause() {
		return pauseCause;
	}

	public void setPauseCause(String pauseCause) {
		this.pauseCause = pauseCause;
	}

	public Users getUsersByOriginatorId() {
		return usersByOriginatorId;
	}

	public void setUsersByOriginatorId(Users usersByOriginatorId) {
		this.usersByOriginatorId = usersByOriginatorId;
	}

	
}