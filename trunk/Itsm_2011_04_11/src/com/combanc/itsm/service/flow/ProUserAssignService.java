package com.combanc.itsm.service.flow;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ProUserAssignDAO;
import com.combanc.itsm.pojo.ProUserAssign;

public class ProUserAssignService extends BaseService<ProUserAssign, Integer> {
	private ProUserAssignDAO proUserAssignDAO;

	public ProUserAssignDAO getProUserAssignDAO() {
		return proUserAssignDAO;
	}

	public void setProUserAssignDAO(ProUserAssignDAO proUserAssignDAO) {
		this.proUserAssignDAO = proUserAssignDAO;
	}

	public List<ProUserAssign> getByDeployId(String deployId) {
		return this.proUserAssignDAO.findByDeployId(deployId);
	}

	public ProUserAssign getByDeployIdActivityName(String deployId,
			String activityName) {
		return this.proUserAssignDAO.getByDeployIdActivityName(deployId,
				activityName);
	}
}
