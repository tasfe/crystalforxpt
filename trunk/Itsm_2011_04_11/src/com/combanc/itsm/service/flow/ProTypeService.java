package com.combanc.itsm.service.flow;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ProTypeDAO;
import com.combanc.itsm.pojo.ProType;

public class ProTypeService extends BaseService<ProType, Integer> {
	private ProTypeDAO proTypeDAO;

	public ProTypeDAO getProTypeDAO() {
		return proTypeDAO;
	}

	public void setProTypeDAO(ProTypeDAO proTypeDAO) {
		this.proTypeDAO = proTypeDAO;
	}

}
