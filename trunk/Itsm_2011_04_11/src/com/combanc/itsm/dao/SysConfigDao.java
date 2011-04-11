package com.combanc.itsm.dao;

import java.util.List;

import com.combanc.itsm.pojo.SysConfig;


public interface SysConfigDao {
	
	public void save(SysConfig sysConfig);
	
	public void delete(SysConfig sysConfig);
	
	public void update(SysConfig sysConfig);
	
	public SysConfig findByID(long id);
	
	public SysConfig findByBusinessKey(String enField);
	
	public SysConfig find(String condition);
	
	public SysConfig find(String condition, Object[] parameters);
	
	public List<SysConfig> findAll(String condition);
	
	public List<SysConfig> findAll(String condition, Object[] parameters);
	
	public boolean exists(String condition, Object[] parameters);
	
	public List<Object> findValues(String selection, String condition, Object[] parameters);
	
	public int findTotalCount(String condition, Object[] parameters);
	
	public List<SysConfig> findAllWithPaging(String condition, Object[] parameters, int start, int limit);
	
	public void executeHQLNotQuery(String hql ,Object[] parameters);
}
