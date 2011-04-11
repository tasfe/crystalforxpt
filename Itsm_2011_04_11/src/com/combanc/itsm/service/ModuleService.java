package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.ModuleDAO;
import com.combanc.itsm.pojo.Module;

public class ModuleService {

	private ModuleDAO moduleDAO;

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public List<Module> findAllModule() {
		return moduleDAO.findAll(Module.class);
	}

	public Module findModuleById(Integer ModuleId) {
		return (Module) moduleDAO.findById(Module.class, ModuleId);
	}

	public void saveModule(Module Module) {
		moduleDAO.save(Module);
	}

	public void update(Module Module) {
		moduleDAO.update(Module);
	}

	public void saveOrUpdate(Module Module) {
		moduleDAO.saveOrUpdate(Module);
	}

	public void deleteById(Integer ModuleId) {
		Module Module = null;
		if (ModuleId != null)
			Module = (Module) moduleDAO.findById(Module.class, ModuleId);
		if (Module != null)
			moduleDAO.delete(Module);
	}

}
