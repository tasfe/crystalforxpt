package com.combanc.itsm.dao;

import java.util.List;

import com.combanc.common.core.dao.AbstractDao;
import com.combanc.itsm.pojo.SysConfig;
@SuppressWarnings("all")
public class SysConfigDaoImpl extends AbstractDao implements SysConfigDao{

	public void delete(SysConfig sysConfig) {
		super.delete(sysConfig);
	}

	public boolean exists(String condition, Object[] parameters) {
		return super.exists(SysConfig.class.getName(), condition, parameters);
	}
	
	public SysConfig find(String condition, Object[] parameters) {
		return (SysConfig)super.find(SysConfig.class.getName(), condition, parameters);
	}

	public List<SysConfig> findAll(String condition) {
		return super.findAll(SysConfig.class.getName(), condition, null, "id");
	}

	public List<SysConfig> findAll(String condition, Object[] parameters) {
		return super.findAll(SysConfig.class.getName(), condition, parameters, "id");
	}

	public List<SysConfig> findAllWithPaging(String condition,
			Object[] parameters, int start, int limit) {
		return super.findAllWithPaging(SysConfig.class.getName(), condition, parameters, "id", start, limit);
	}
	
	public int findTotalCount(String condition, Object[] parameters) {
		return super.findTotalCount(SysConfig.class.getName(), condition, parameters);
	}
	
	public SysConfig findByBusinessKey(String enField) {
		return (SysConfig)super.find(SysConfig.class.getName(), "enField=?", new Object[]{enField});
	}

	public SysConfig findByID(long id) {
		return (SysConfig)super.findByID(SysConfig.class.getName(),Integer.valueOf(String.valueOf(id)));
	}

	public List<Object> findValues(String selection, String condition,
			Object[] parameters) {
		return super.findValues(SysConfig.class.getName(), selection, condition, parameters);
	}

	public void save(SysConfig sysConfig) {
		super.save(SysConfig.class.getName(), sysConfig);
	}

	public void update(SysConfig sysConfig) {
		super.update(SysConfig.class.getName(), sysConfig);
	}

	public SysConfig find(String condition) {
		return (SysConfig)super.find(SysConfig.class.getName(), condition, null);
	}
	
	
}
