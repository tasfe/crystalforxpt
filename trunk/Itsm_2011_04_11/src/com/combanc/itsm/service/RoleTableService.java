package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.RoleTableDao;
import com.combanc.itsm.pojo.RoleTable;

public class RoleTableService {
	private RoleTableDao roleTableDao;
	public RoleTableDao getRoleTableDao() {
		return roleTableDao;
	}
	public void setRoleTableDao(RoleTableDao roleTableDao) {
		this.roleTableDao = roleTableDao;
	}
	public List<RoleTable> getAll(){
		return this.roleTableDao.findAll("");
	}
}
