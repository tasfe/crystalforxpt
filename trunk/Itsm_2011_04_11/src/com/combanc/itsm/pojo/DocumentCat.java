package com.combanc.itsm.pojo;

import java.util.HashSet;
import java.util.Set;

public class DocumentCat implements java.io.Serializable {

	private Integer id;
	private Integer pid;
	private String document;
	private String documentSC;
	private String code;
	private Set<DocumentAuthority> documentAuthoritys;

	public DocumentCat() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getDocument() {
		return this.document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocumentSC() {
		return documentSC;
	}

	public void setDocumentSC(String documentSC) {
		this.documentSC = documentSC;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<DocumentAuthority> getDocumentAuthoritys() {
		return documentAuthoritys;
	}

	public void setDocumentAuthoritys(Set<DocumentAuthority> documentAuthoritys) {
		this.documentAuthoritys = documentAuthoritys;
	}

}