package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.pojo.SysConfig;

public interface SysConfigSrv {
	
	public void save(SysConfig sysConfig);
	
	public void update(SysConfig sysConfig);
	
	public void delete(SysConfig sysConfig);
	
	public SysConfig find(String condition,Object parameters[]);
	
	public List<SysConfig> findAll(String condition,Object parameters[]);
}
