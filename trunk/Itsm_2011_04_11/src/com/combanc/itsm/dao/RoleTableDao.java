package com.combanc.itsm.dao;

import java.util.List;

import com.combanc.common.core.dao.AbstractDao;
import com.combanc.itsm.pojo.RoleTable;
@SuppressWarnings("all")
public class RoleTableDao extends AbstractDao {
	public void save(RoleTable roleTable) {
		super.getSession().merge(roleTable);
	}
	
	public void delete(RoleTable roleTable) {
		super.delete(roleTable);
	}
	
	public RoleTable findByID(Integer id) {
		return (RoleTable)super.findByID(RoleTable.class.getName(), id);
	}
	
	public RoleTable find(String condition) {
		return (RoleTable)super.find(RoleTable.class.getName(), condition, null);
	}
	
	public RoleTable find(String condition, Object[] parameters) {
		return (RoleTable)super.find(RoleTable.class.getName(), condition, parameters);
	}
	
	
	
	public List<RoleTable> findAll(String condition) {
		return super.findAll(RoleTable.class.getName(), condition, null, null);
	}
	
	public List<RoleTable> findAll(String condition, Object[] parameters) {
		return super.findAll(RoleTable.class.getName(), condition, parameters, null);
	}
	
	public boolean exists(String condition, Object[] parameters) {
		return super.exists(RoleTable.class.getName(), condition, parameters);
	}
	
	public List<Object> findValues(String selection, String condition, Object[] parameters) {
		return super.findValues(RoleTable.class.getName(), selection, condition, parameters);
	}
	
	public int findTotalCount(String condition, Object[] parameters) {
		return super.findTotalCount(RoleTable.class.getName(), condition, parameters);
	}
	
	public List<RoleTable> findAllWithPaging(String condition, Object[] parameters, int start, int limit) {
		return super.findAllWithPaging(RoleTable.class.getName(), condition, parameters, null, start, limit);
	}
}
