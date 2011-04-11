package com.combanc.itsm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsStateDAO;
import com.combanc.itsm.dao.AssetsTypeDAO;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;

public class AssetsStateService extends BaseService<AssetsType, Integer> {
	private AssetsStateDAO assetsStateDAO;

	public AssetsStateDAO getAssetsStateDAO() {
		return assetsStateDAO;
	}

	public void setAssetsStateDAO(AssetsStateDAO assetsStateDAO) {
		this.assetsStateDAO = assetsStateDAO;
	}

	private static final Log log = LogFactory.getLog(AssetsStateService.class);
	
	public List findAll(){
		return assetsStateDAO.findAll();
	}
	
	public void save(AssetsState assetsState){
		assetsStateDAO.save(assetsState);
	}
	
	public void delete(AssetsState assetsState){
		assetsStateDAO.delete(assetsState);
	}
	public AssetsState findbyId(int id){
		AssetsState assetsState=assetsStateDAO.findById(id);
		return assetsState;
	}
	
	public void update(AssetsState assetsState){
		assetsStateDAO.update(assetsState);
	}
	
	public boolean findbyName(String name){
		boolean a=false;
		List list=assetsStateDAO.findByName(name);
		if(list.size()!=0){
			a=true;
		}
		return a;
	}
	
	public int findSequenceMax(){
		String queryString="from AssetsState as model order by model.sequence desc";
		AssetsState state=(AssetsState)assetsStateDAO.findbyhql(queryString).get(0);
		return state.getSequence();
	}
	
	public AssetsState findbyNames(String name){
		List list=assetsStateDAO.findByName(name);
		AssetsState state=null;
		if(list.size()!=0){
			state=(AssetsState)list.get(0);
		}
		return state;
	}
	
	public List findByPid(int pid){
		return assetsStateDAO.findByProperty("Pid", pid);
	}
	
	public List findbynamesize(String name){
		return  assetsStateDAO.findByName(name);
	}
	
	public List findbySequence(int Sequence){
		return assetsStateDAO.findbySequence(Sequence);
	}
}
