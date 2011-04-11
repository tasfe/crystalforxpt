package com.combanc.itsm.pojo;

/**
 * Ldapadapter entity. @author MyEclipse Persistence Tools
 */

public class Ldapadapter implements java.io.Serializable {

	// Fields

	private Long ldapId;
	private String domaincontroller;
	private String initialContextFactory;
	private String isSecured;
	private String username;
	private String password;
	private String baseDn;
	private String searchFilter;
	private String loginAttribute;
	private String mailAttribute;
	private String dnAttribute;
	private String ldapserverType;
	private Boolean isimported;

	// Constructors

	/** default constructor */
	public Ldapadapter() {
	}

	/** minimal constructor */
	public Ldapadapter(String domaincontroller, String initialContextFactory,
			String isSecured, String password) {
		this.domaincontroller = domaincontroller;
		this.initialContextFactory = initialContextFactory;
		this.isSecured = isSecured;
		this.password = password;
	}

	/** full constructor */
	public Ldapadapter(String domaincontroller, String initialContextFactory,
			String isSecured, String username, String password, String baseDn,
			String searchFilter, String loginAttribute, String mailAttribute,
			String dnAttribute, String ldapserverType, Boolean isimported) {
		this.domaincontroller = domaincontroller;
		this.initialContextFactory = initialContextFactory;
		this.isSecured = isSecured;
		this.username = username;
		this.password = password;
		this.baseDn = baseDn;
		this.searchFilter = searchFilter;
		this.loginAttribute = loginAttribute;
		this.mailAttribute = mailAttribute;
		this.dnAttribute = dnAttribute;
		this.ldapserverType = ldapserverType;
		this.isimported = isimported;
	}

	// Property accessors

	public Long getLdapId() {
		return this.ldapId;
	}

	public void setLdapId(Long ldapId) {
		this.ldapId = ldapId;
	}

	public String getDomaincontroller() {
		return this.domaincontroller;
	}

	public void setDomaincontroller(String domaincontroller) {
		this.domaincontroller = domaincontroller;
	}

	public String getInitialContextFactory() {
		return this.initialContextFactory;
	}

	public void setInitialContextFactory(String initialContextFactory) {
		this.initialContextFactory = initialContextFactory;
	}

	public String getIsSecured() {
		return this.isSecured;
	}

	public void setIsSecured(String isSecured) {
		this.isSecured = isSecured;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBaseDn() {
		return this.baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public String getSearchFilter() {
		return this.searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getLoginAttribute() {
		return this.loginAttribute;
	}

	public void setLoginAttribute(String loginAttribute) {
		this.loginAttribute = loginAttribute;
	}

	public String getMailAttribute() {
		return this.mailAttribute;
	}

	public void setMailAttribute(String mailAttribute) {
		this.mailAttribute = mailAttribute;
	}

	public String getDnAttribute() {
		return this.dnAttribute;
	}

	public void setDnAttribute(String dnAttribute) {
		this.dnAttribute = dnAttribute;
	}

	public String getLdapserverType() {
		return this.ldapserverType;
	}

	public void setLdapserverType(String ldapserverType) {
		this.ldapserverType = ldapserverType;
	}

	public Boolean getIsimported() {
		return this.isimported;
	}

	public void setIsimported(Boolean isimported) {
		this.isimported = isimported;
	}

}