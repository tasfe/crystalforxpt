package com.combanc.itsm.pojo;

import com.combanc.itsm.pojo.ServiceCategory;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SchedualedTasks entity. @author MyEclipse Persistence Tools
 */

public class SchedualedTasks implements java.io.Serializable {

	// Fields

	private Integer id;
	private ServiceCategory serviceCategory;
	private Users user;
	private Integer impact;
	private Integer urgency;
	private String proNo;
	private String keyWord;
	private String proObj;
	private String submit;
	private String serverity;
	private String detail;
	private String upFile;
	private String application;
	private Timestamp submitAt;
	private Timestamp approveAt;
	private String rate;
	private String configure;
	private String tmp1;
	private String tmp2;
	private String tmp3;
	private String tmp4;
	private String tmp5;
	private Set schedualedTaskDetails = new HashSet(0);
	private int sum1;
	private int sum2;
	private int sum3;

	// Constructors

	/** default constructor */
	public SchedualedTasks() {
	}

	/** full constructor */
	public SchedualedTasks(ServiceCategory serviceCategory, Users user,
			Integer impact, Integer urgency, String proNo, String keyWord,
			String proObj, String submit, String serverity, String detail,
			String upFile, String application, Timestamp submitAt,
			Timestamp approveAt, String rate, String configure, String tmp1,
			String tmp2, String tmp3, String tmp4, String tmp5,
			Set schedualedTaskDetails) {
		this.serviceCategory = serviceCategory;
		this.user = user;
		this.impact = impact;
		this.urgency = urgency;
		this.proNo = proNo;
		this.keyWord = keyWord;
		this.proObj = proObj;
		this.submit = submit;
		this.serverity = serverity;
		this.detail = detail;
		this.upFile = upFile;
		this.application = application;
		this.submitAt = submitAt;
		this.approveAt = approveAt;
		this.rate = rate;
		this.configure = configure;
		this.tmp1 = tmp1;
		this.tmp2 = tmp2;
		this.tmp3 = tmp3;
		this.tmp4 = tmp4;
		this.tmp5 = tmp5;
		this.schedualedTaskDetails = schedualedTaskDetails;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getImpact() {
		return this.impact;
	}

	public void setImpact(Integer impact) {
		this.impact = impact;
	}

	public Integer getUrgency() {
		return this.urgency;
	}

	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
	}

	public String getProNo() {
		return this.proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getProObj() {
		return this.proObj;
	}

	public void setProObj(String proObj) {
		this.proObj = proObj;
	}

	public String getSubmit() {
		return this.submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getServerity() {
		return this.serverity;
	}

	public void setServerity(String serverity) {
		this.serverity = serverity;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUpFile() {
		return this.upFile;
	}

	public void setUpFile(String upFile) {
		this.upFile = upFile;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Timestamp getSubmitAt() {
		return this.submitAt;
	}

	public void setSubmitAt(Timestamp submitAt) {
		this.submitAt = submitAt;
	}

	public Timestamp getApproveAt() {
		return this.approveAt;
	}

	public void setApproveAt(Timestamp approveAt) {
		this.approveAt = approveAt;
	}

	public String getRate() {
		return this.rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getConfigure() {
		return this.configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String getTmp1() {
		return this.tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	public String getTmp2() {
		return this.tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	public String getTmp3() {
		return this.tmp3;
	}

	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}

	public String getTmp4() {
		return this.tmp4;
	}

	public void setTmp4(String tmp4) {
		this.tmp4 = tmp4;
	}

	public String getTmp5() {
		return this.tmp5;
	}

	public void setTmp5(String tmp5) {
		this.tmp5 = tmp5;
	}

	public Set getSchedualedTaskDetails() {
		return this.schedualedTaskDetails;
	}

	public void setSchedualedTaskDetails(Set schedualedTaskDetails) {
		this.schedualedTaskDetails = schedualedTaskDetails;
	}

	public int getSum1() {
		return sum1;
	}

	public void setSum1(int sum1) {
		this.sum1 = sum1;
	}

	public int getSum2() {
		return sum2;
	}

	public void setSum2(int sum2) {
		this.sum2 = sum2;
	}

	public int getSum3() {
		return sum3;
	}

	public void setSum3(int sum3) {
		this.sum3 = sum3;
	}
}