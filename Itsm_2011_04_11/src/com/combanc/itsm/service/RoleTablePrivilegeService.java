package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.RoleTablePrivilegeDao;
import com.combanc.itsm.pojo.RoleTablePrivilege;

@SuppressWarnings("all")
public class RoleTablePrivilegeService {
	private RoleTablePrivilegeDao roleTablePrivilegeDao;

	public RoleTablePrivilegeDao getRoleTablePrivilegeDao() {
		return roleTablePrivilegeDao;
	}

	public void setRoleTablePrivilegeDao(RoleTablePrivilegeDao roleTablePrivilegeDao) {
		this.roleTablePrivilegeDao = roleTablePrivilegeDao;
	}
	
	public void save(RoleTablePrivilege roleTablePrivilege){
		 this.roleTablePrivilegeDao.save(roleTablePrivilege);
	}
	public void delete(RoleTablePrivilege roleTablePrivilege){
		this.roleTablePrivilegeDao.delete(roleTablePrivilege);
	}
	public List<RoleTablePrivilege> getByRoleId(Integer roleId){
		return this.roleTablePrivilegeDao.findAll("roleId=?", new Object[]{roleId});
	}
	public void delete(List<RoleTablePrivilege> rList){
		if(null==rList||rList.isEmpty()){
			return;
		}
		for(RoleTablePrivilege roleTablePrivilege:rList){
			this.delete(roleTablePrivilege);
		}
	}
	public RoleTablePrivilege getByBussinessKey(Integer roleId,Integer tableId){
		return this.roleTablePrivilegeDao.find("roleId=? and tableId=?", new Object[]{roleId,tableId});
	}
}
