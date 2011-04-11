package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.PrivilegeDAO;
import com.combanc.itsm.pojo.Privilege;

public class PrivilegeService extends BaseService<Privilege, Integer> {
	
	private PrivilegeDAO privilegeDAO;

	public List findByOwenr(Integer owenr){
		List list=null;
		if(owenr!=null) list=privilegeDAO.findByOwenr(owenr);
		return  list;
	}
	
	public void save(Privilege privilege) {
		if(privilege!=null) privilegeDAO.save(privilege);
	}
	
	public void delete(Privilege privilege){
		if(privilege!=null) privilegeDAO.delete(privilege);
	}
	
	public PrivilegeDAO getPrivilegeDAO() {
		return privilegeDAO;
	}

	public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
		super.setDao(privilegeDAO);
		this.privilegeDAO = privilegeDAO;
	}
	
}
