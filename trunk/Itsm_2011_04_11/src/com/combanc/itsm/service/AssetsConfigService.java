package com.combanc.itsm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetsConfigDAO;
import com.combanc.itsm.pojo.AssetsConfig;

public class AssetsConfigService extends BaseService<AssetsConfig,Integer>{
	private AssetsConfigDAO assetsConfigDAO;
	private static final Log log = LogFactory.getLog(AssetsConfigService.class);
	
	public List<AssetsConfigDAO> findAll() {
		return assetsConfigDAO.findAll();
	}
	
	public AssetsConfig findById(Integer Id) {
		return (AssetsConfig)assetsConfigDAO.findById(Id);
	}
	
	public void save(AssetsConfig assetsConfig) {
		assetsConfigDAO.save(assetsConfig);
	}

	public void update(AssetsConfig assetsConfig) {
		assetsConfigDAO.update(assetsConfig);
	}

	public void saveOrUpdate(AssetsConfig assetsConfig) {
		assetsConfigDAO.attachDirty(assetsConfig);
	}

	public void deleteById(Integer id) {
		AssetsConfig assetsConfig = null;
		if (id != null)
			assetsConfig = (AssetsConfig) assetsConfigDAO.findById(id);
		if (assetsConfig != null)
			assetsConfigDAO.delete(assetsConfig);
	}
	
	public List findAllByAssetsTypeId(int pid)
	{
		String queryString="from AssetsConfig  where assets_Type_id='"+pid+"'";  
		List list=assetsConfigDAO.findbysql(queryString);
		return list;
	}
	
	public List findByProperty(String propertyname,int pid){
		List list=assetsConfigDAO.findByProperty(propertyname,pid);
		return list;
	}
	
	
	
	public AssetsConfig findByPropertys(String propertyname,int pid)
	{
		AssetsConfig assetsConfig=new AssetsConfig();
		List list=assetsConfigDAO.findByProperty(propertyname,pid);
		if(!list.isEmpty())
		{
			assetsConfig=(AssetsConfig)list.get(0);
			return assetsConfig;
		}else
		{
			return null;
		}
		
	}
	
	public List findbychoose(String propertyname,int pid)
	{
		return assetsConfigDAO.findbychoose(propertyname,pid);
	}
	
	public List findByPropertyinfo(String propertyname,int pid)
	{
		List list=assetsConfigDAO.findByPropertyinfos(propertyname,pid);
		return list;
	}
	
	public List findbyinfochoose(String propertyname,int pid)
	{
		List list=assetsConfigDAO.findbyinfochoose(propertyname,pid);
		return list;
	}
	
	public void deleteByAssetsTypeId(int assetsTypeId){
		String queryString="from AssetsConfig  where assets_Type_id='"+assetsTypeId+"'";
		assetsConfigDAO.deleteByAssetsTypeId(queryString);
	}
	
	public List findbysql(String sql){
		return assetsConfigDAO.findbysql(sql);
	}

	public AssetsConfigDAO getAssetsConfigDAO() {
		return assetsConfigDAO;
	}

	public void setAssetsConfigDAO(AssetsConfigDAO assetsConfigDAO) {
		this.assetsConfigDAO = assetsConfigDAO;
	}
}

