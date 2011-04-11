package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.AuthorityDAO;
import com.combanc.itsm.pojo.Authority;
import com.combanc.itsm.pojo.Department;

public class AuthorityService {
	private AuthorityDAO authorityDAO;

	public AuthorityDAO getAuthorityDAO() {
		return authorityDAO;
	}

	public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

	public List<Authority> findAll() {
		return authorityDAO.findAll();
	}

	public List<Authority> findAllByPid(Integer pid) {
		return authorityDAO.findByPid(pid);
	}

	public Authority findById(Integer authorityId) {
		return authorityDAO.findById(authorityId);
	}

	public void save(Authority authority) {
		authorityDAO.save(authority);
	}

	public void update(Authority authority) {
		authorityDAO.update(authority);
	}

	public void saveOrUpdate(Authority authority) {
		authorityDAO.saveOrUpdate(authority);
	}

	public void deleteById(Integer authorityId) {
		Authority authority = null;
		if (authorityId != null)
			authority = authorityDAO.findById(authorityId);
		if (authority != null)
			authorityDAO.delete(authority);
	}

	public AuthorityDAO getauthorityDAO() {
		return authorityDAO;
	}

	public void setauthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}

}
