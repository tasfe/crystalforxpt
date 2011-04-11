package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.service.AbstractSrv;
import com.combanc.itsm.dao.SysConfigDao;
import com.combanc.itsm.pojo.SysConfig;

public class SysConfigSrvImpl extends AbstractSrv implements SysConfigSrv {
	
	private SysConfigDao sysConfigDao;
	public SysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

	public void setSysConfigDao(SysConfigDao sysConfigDao) {
		this.sysConfigDao = sysConfigDao;
	}

	public void delete(SysConfig sysConfig) {
		 sysConfigDao.delete(sysConfig);
	}

	public SysConfig find(String condition, Object[] parameters) {
		return sysConfigDao.find(condition, parameters);
	}

	public List<SysConfig> findAll(String condition, Object[] parameters) {
		return sysConfigDao.findAll(condition, parameters);
	}

	public void save(SysConfig sysConfig) {
		sysConfigDao.save(sysConfig);
	}

	public void update(SysConfig sysConfig) {
		sysConfigDao.update(sysConfig);
	}

}
