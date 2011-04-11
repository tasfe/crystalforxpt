package com.combanc.itsm.pojo;

public class DocumentAuthority implements java.io.Serializable {

	private Integer id;
	private Integer tid;
	private Integer did;
	private Integer authoritySel = 0;
	private Integer authorityAdd = 0;
	private Integer authorityDel = 0;
	private Integer authorityUpd = 0;

	public DocumentAuthority() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public Integer getAuthoritySel() {
		return authoritySel;
	}

	public void setAuthoritySel(Integer authoritySel) {
		this.authoritySel = authoritySel;
	}

	public Integer getAuthorityAdd() {
		return authorityAdd;
	}

	public void setAuthorityAdd(Integer authorityAdd) {
		this.authorityAdd = authorityAdd;
	}

	public Integer getAuthorityDel() {
		return authorityDel;
	}

	public void setAuthorityDel(Integer authorityDel) {
		this.authorityDel = authorityDel;
	}

	public Integer getAuthorityUpd() {
		return authorityUpd;
	}

	public void setAuthorityUpd(Integer authorityUpd) {
		this.authorityUpd = authorityUpd;
	}

}