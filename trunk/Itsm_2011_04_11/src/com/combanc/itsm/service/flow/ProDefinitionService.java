package com.combanc.itsm.service.flow;

import javax.annotation.Resource;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ProDefinitionDAO;
import com.combanc.itsm.pojo.ProDefinition;

public class ProDefinitionService extends BaseService<ProDefinition, Integer> {
	@Resource
	private ProDefinitionDAO proDefinitionDAO;

	public ProDefinitionDAO getProDefinitionDAO() {
		return proDefinitionDAO;
	}

	public void setProDefinitionDAO(ProDefinitionDAO proDefinitionDAO) {
		super.setDao(proDefinitionDAO);
		this.proDefinitionDAO = proDefinitionDAO;
	}

	public ProDefinition getByDeployId(String deployId) {
		return this.proDefinitionDAO.findById(Integer.valueOf(deployId));
	}

	public ProDefinition getByName(String name) {
		return this.proDefinitionDAO.findByName(name);
	}
}
