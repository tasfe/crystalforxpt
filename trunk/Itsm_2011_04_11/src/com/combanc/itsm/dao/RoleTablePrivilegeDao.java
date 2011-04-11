package com.combanc.itsm.dao;

import java.util.List;

import com.combanc.common.core.dao.AbstractDao;
import com.combanc.itsm.pojo.RoleTablePrivilege;
@SuppressWarnings("all")
public class RoleTablePrivilegeDao extends AbstractDao{
	public void save(RoleTablePrivilege roleTablePrivilege) {
		super.getSession().merge(roleTablePrivilege);
	}
	
	public void delete(RoleTablePrivilege roleTablePrivilege) {
		super.delete(roleTablePrivilege);
	}
	
	public RoleTablePrivilege findByID(Integer id) {
		return (RoleTablePrivilege)super.findByID(RoleTablePrivilege.class.getName(), id);
	}
	
	public RoleTablePrivilege find(String condition) {
		return (RoleTablePrivilege)super.find(RoleTablePrivilege.class.getName(), condition, null);
	}
	
	public RoleTablePrivilege find(String condition, Object[] parameters) {
		return (RoleTablePrivilege)super.find(RoleTablePrivilege.class.getName(), condition, parameters);
	}
	
	
	
	public List<RoleTablePrivilege> findAll(String condition) {
		return super.findAll(RoleTablePrivilege.class.getName(), condition, null, null);
	}
	
	public List<RoleTablePrivilege> findAll(String condition, Object[] parameters) {
		return super.findAll(RoleTablePrivilege.class.getName(), condition, parameters, null);
	}
	
	public boolean exists(String condition, Object[] parameters) {
		return super.exists(RoleTablePrivilege.class.getName(), condition, parameters);
	}
	
	public List<Object> findValues(String selection, String condition, Object[] parameters) {
		return super.findValues(RoleTablePrivilege.class.getName(), selection, condition, parameters);
	}
	
	public int findTotalCount(String condition, Object[] parameters) {
		return super.findTotalCount(RoleTablePrivilege.class.getName(), condition, parameters);
	}
	
	public List<RoleTablePrivilege> findAllWithPaging(String condition, Object[] parameters, int start, int limit) {
		return super.findAllWithPaging(RoleTablePrivilege.class.getName(), condition, parameters, null, start, limit);
	}
}
