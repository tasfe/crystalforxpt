package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.ProblemTypeDAO;
import com.combanc.itsm.pojo.ProblemType;

public class ProblemTypeService {
	private ProblemTypeDAO problemTypeDAO;

	public List<ProblemType> findAll() {
		return problemTypeDAO.findAll();
	}

	public ProblemType findById(Integer problemTypeId) {
		return problemTypeDAO.findById(problemTypeId);
	}

	public List<ProblemType> findAllByPid(Integer pid) {
		return problemTypeDAO.findByPid(pid);
	}

	public void save(ProblemType problemType) {
		problemTypeDAO.save(problemType);
		Integer parentId = problemType.getPid();
		if (parentId != null && parentId != 0) {
			ProblemType parent = problemTypeDAO.findById(parentId);
			problemType.setCode(parent.getCode() + "|" + problemType.getId()
					+ "$");
		} else {
			problemType.setCode("|" + problemType.getId() + "$");
		}
		update(problemType);
	}

	public void update(ProblemType problemType) {
		problemTypeDAO.update(problemType);
	}

	public void deleteById(Integer problemTypeId) {
		ProblemType problemType = null;
		if (problemTypeId != null) {
			problemType = problemTypeDAO.findById(problemTypeId);
		}
		if (problemType != null) {
			String code = problemType.getCode();
			List<ProblemType> list = problemTypeDAO
					.findBySql("from ProblemType p where p.code like '%" + code
							+ "%'");
			for (ProblemType p : list) {
				problemTypeDAO.delete(p);
			}
		}
	}

	public ProblemTypeDAO getProblemTypeDAO() {
		return problemTypeDAO;
	}

	public void setProblemTypeDAO(ProblemTypeDAO problemTypeDAO) {
		this.problemTypeDAO = problemTypeDAO;
	}

}
