package com.combanc.itsm.dao;

import java.util.List;

import com.combanc.common.core.dao.AbstractDao;
import com.combanc.itsm.pojo.RoleGroup;
@SuppressWarnings("all")
public class RoleGroupDao extends AbstractDao {
	
	public void save(RoleGroup roleGroup) {
		super.getSession().merge(roleGroup);
	}
	
	public void delete(RoleGroup roleGroup) {
		super.delete(roleGroup);
	}
	
	public RoleGroup findByID(Integer id) {
		return (RoleGroup)super.findByID(RoleGroup.class.getName(), id);
	}
	
	public RoleGroup find(String condition) {
		return (RoleGroup)super.find(RoleGroup.class.getName(), condition, null);
	}
	
	public RoleGroup find(String condition, Object[] parameters) {
		return (RoleGroup)super.find(RoleGroup.class.getName(), condition, parameters);
	}
	
	
	public List<RoleGroup> findAll(String condition) {
		return super.findAll(RoleGroup.class.getName(), condition, null, null);
	}
	
	public List<RoleGroup> findAll(String condition, Object[] parameters) {
		return super.findAll(RoleGroup.class.getName(), condition, parameters, null);
	}
	
	public boolean exists(String condition, Object[] parameters) {
		return super.exists(RoleGroup.class.getName(), condition, parameters);
	}
	
	public List<Object> findValues(String selection, String condition, Object[] parameters) {
		return super.findValues(RoleGroup.class.getName(), selection, condition, parameters);
	}
	
	public int findTotalCount(String condition, Object[] parameters) {
		return super.findTotalCount(RoleGroup.class.getName(), condition, parameters);
	}
	
	public List<RoleGroup> findAllWithPaging(String condition, Object[] parameters, int start, int limit) {
		return super.findAllWithPaging(RoleGroup.class.getName(), condition, parameters, null, start, limit);
	}

}
