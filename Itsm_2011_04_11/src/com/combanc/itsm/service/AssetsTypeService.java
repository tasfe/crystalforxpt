package com.combanc.itsm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsTypeDAO;
import com.combanc.itsm.pojo.AssetsType;

public class AssetsTypeService extends BaseService<AssetsType, Integer> {
	private AssetsTypeDAO assetsTypeDAO;
	private AssetsBaseDAO assetsBaseDAO;
	public AssetsBaseDAO getAssetsBaseDAO() {
		return assetsBaseDAO;
	}

	public void setAssetsBaseDAO(AssetsBaseDAO assetsBaseDAO) {
		this.assetsBaseDAO = assetsBaseDAO;
	}

	private static final Log log = LogFactory.getLog(AssetsTypeService.class);

	public AssetsTypeDAO getAssetsTypeDAO() {
		return assetsTypeDAO;
	}

	public void setAssetsTypeDAO(AssetsTypeDAO assetsTypeDAO) {
		this.assetsTypeDAO = assetsTypeDAO;
	}

	public List<AssetsType> findAll() {
		return assetsTypeDAO.findAll();
	}

	public List<AssetsType> findAllByPid(Integer pid) {
		return assetsTypeDAO.findByPid(pid);
	}

	public AssetsType findById(Integer assetsTypeId) {
		return (AssetsType) assetsTypeDAO.findById(assetsTypeId);
	}

	public void save(AssetsType assetsType) {
		if (assetsType.getPid().intValue() != 0) {
			assetsType.setFlag(assetsTypeDAO.findById(assetsType.getPid())
					.getFlag()
					+ assetsType.getPid().toString() + ":");
		} else {
			assetsType.setFlag(":0:");
		}
		assetsTypeDAO.save(assetsType);
	}
	
	public AssetsType findBydes(String des){
		AssetsType type=new AssetsType();
		List list=assetsTypeDAO.findByProperty("description", des);
		if(list.size()==0){
			type=null;
		}else{
			type=(AssetsType)list.get(0);
		}
		return type;
	}

	public void update(AssetsType assetsType) {
		assetsTypeDAO.update(assetsType);
	}

	public void saveOrUpdate(AssetsType assetsType) {
		assetsTypeDAO.attachDirty(assetsType);
	}
	
	public List isnull(String code){
		return assetsBaseDAO.findByTypeCodes(code);
		
	}
	
	public List findByName(String name){
		return assetsTypeDAO.findByName(name);
	}
	
	public void deleteById(Integer assetsTypeId) {
		AssetsType assetsType = null;
		if (assetsTypeId != null)
			assetsType = (AssetsType) assetsTypeDAO.findById(assetsTypeId);
		if (assetsType != null)
			assetsTypeDAO.delete(assetsType);
	}

	public List<AssetsType> findAllByAssetsTypePid(int pid) {
		String queryString;
		return assetsTypeDAO.findAllByAssetsTypePid(pid);
	}
}
