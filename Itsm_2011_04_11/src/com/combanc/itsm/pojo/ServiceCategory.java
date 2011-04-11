package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * ServiceCategory entity. @author MyEclipse Persistence Tools
 */

public class ServiceCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Formguide formguide;
	private String item;
	private Integer type;
	private String code;
	private String itemZh;
	private String explainer;
	private String icoUrl;
	private String openObject;
	private String selfService;
	private Integer description;
	private Integer isFormguide;
	private Integer defEss;
	private Integer defEme;
	private Integer isDef;
	private Integer pid;
	private String workflow;
	private String approver;
	private String charset;
	private String sla;
	private Set serviceTrans = new HashSet(0);
	private Set serviceRequests = new HashSet(0);
	private Set knowledgeBases = new HashSet(0);
	private Set taskAllocations = new HashSet(0);

	// Constructors



	/** default constructor */
	public ServiceCategory() {
	}

	/** full constructor */
	public ServiceCategory(Formguide formguide, String item, Integer type,
			String code, String itemZh, String explainer, String icoUrl,
			String openObject, String selfService, Integer description,
			Integer isFormguide, Integer defEss, Integer defEme, Integer isDef,
			Integer pid, String workflow, String approver, String charset,
			String sla, Set serviceTrans, Set serviceRequests) {
		this.formguide = formguide;
		this.item = item;
		this.type = type;
		this.code = code;
		this.itemZh = itemZh;
		this.explainer = explainer;
		this.icoUrl = icoUrl;
		this.openObject = openObject;
		this.selfService = selfService;
		this.description = description;
		this.isFormguide = isFormguide;
		this.defEss = defEss;
		this.defEme = defEme;
		this.isDef = isDef;
		this.pid = pid;
		this.workflow = workflow;
		this.approver = approver;
		this.charset = charset;
		this.sla = sla;
		this.serviceTrans = serviceTrans;
		this.serviceRequests = serviceRequests;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the taskAllocations
	 */
	public Set getTaskAllocations() {
		return taskAllocations;
	}

	/**
	 * @param taskAllocations the taskAllocations to set
	 */
	public void setTaskAllocations(Set taskAllocations) {
		this.taskAllocations = taskAllocations;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Formguide getFormguide() {
		return this.formguide;
	}

	public void setFormguide(Formguide formguide) {
		this.formguide = formguide;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getItemZh() {
		return this.itemZh;
	}

	public void setItemZh(String itemZh) {
		this.itemZh = itemZh;
	}

	public String getExplainer() {
		return this.explainer;
	}

	public void setExplainer(String explainer) {
		this.explainer = explainer;
	}

	public String getIcoUrl() {
		return this.icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	public String getOpenObject() {
		return this.openObject;
	}

	public void setOpenObject(String openObject) {
		this.openObject = openObject;
	}

	public String getSelfService() {
		return this.selfService;
	}

	public void setSelfService(String selfService) {
		this.selfService = selfService;
	}

	public Integer getDescription() {
		return this.description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public Integer getIsFormguide() {
		return this.isFormguide;
	}

	public void setIsFormguide(Integer isFormguide) {
		this.isFormguide = isFormguide;
	}

	public Integer getDefEss() {
		return this.defEss;
	}

	public void setDefEss(Integer defEss) {
		this.defEss = defEss;
	}

	public Integer getDefEme() {
		return this.defEme;
	}

	public void setDefEme(Integer defEme) {
		this.defEme = defEme;
	}

	public Integer getIsDef() {
		return this.isDef;
	}

	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getWorkflow() {
		return this.workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getCharset() {
		return this.charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSla() {
		return this.sla;
	}

	public void setSla(String sla) {
		this.sla = sla;
	}

	public Set getServiceTrans() {
		return this.serviceTrans;
	}

	public void setServiceTrans(Set serviceTrans) {
		this.serviceTrans = serviceTrans;
	}

	public Set getServiceRequests() {
		return this.serviceRequests;
	}

	public void setServiceRequests(Set serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public Set getKnowledgeBases() {
		return knowledgeBases;
	}

	public void setKnowledgeBases(Set knowledgeBases) {
		this.knowledgeBases = knowledgeBases;
	}

}