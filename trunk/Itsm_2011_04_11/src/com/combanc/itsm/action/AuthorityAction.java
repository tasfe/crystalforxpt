package com.combanc.itsm.action;

import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Authority;
import com.combanc.itsm.service.AuthorityService;

public class AuthorityAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer authorityId;
	private Authority authority;
	private List<Authority> authorityList;
	private String actionURI;
	private AuthorityService authorityService;
	private Integer pid = 0;

	public String list() throws Exception {
		if (pid != 0) {
			authorityList = authorityService.findAllByPid(pid);
		} else {
			authorityList = authorityService.findAll();
		}
		return "success";

	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		System.out.println(authority.getId());
		authorityService.save(authority);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		authority = authorityService.findById(authorityId);
		return "success";
	}

	public String update() throws Exception {
		authorityService.update(authority);
		return "list";
	}

	public String delete() throws Exception {
		authorityService.deleteById(authorityId);
		return "list";
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
