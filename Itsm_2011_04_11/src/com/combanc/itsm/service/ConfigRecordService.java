package com.combanc.itsm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ConfigRecordDAO;
import com.combanc.itsm.pojo.ConfigRecord;

public class ConfigRecordService extends BaseService<ConfigRecord, Integer>{
	private ConfigRecordDAO configrecordDAO;
	private static final Log log = LogFactory.getLog(ConfigRecordService.class);
	
	public ConfigRecordDAO getConfigrecordDAO() {
		return configrecordDAO;
	}

	public void setConfigrecordDAO(ConfigRecordDAO configrecordDAO) {
		this.configrecordDAO = configrecordDAO;
	}

	public List<ConfigRecord> findAll() {
		return configrecordDAO.findAll();
	}

	public ConfigRecord findById(Integer Id) {
		return (ConfigRecord)configrecordDAO.findById(Id);
	}
	
	public void save(ConfigRecord configrecord) {
		configrecordDAO.save(configrecord);
	}

	public void update(ConfigRecord configrecord) {
		configrecordDAO.update(configrecord);
	}

	public void saveOrUpdate(ConfigRecord configrecord) {
		configrecordDAO.attachDirty(configrecord);
	}

	public void deleteById(Integer id) {
		ConfigRecord configrecord = null;
		if (id != null)
			configrecord = (ConfigRecord) configrecordDAO.findById(id);
		if (configrecord != null)
			configrecordDAO.delete(configrecord);
	}
	
	public List findAllByAssetsTypeId(int pid)
	{
		String queryString="from ConfigRecord  where assets_Type_id='"+pid+"'";  
		List list=configrecordDAO.findbysql(queryString);
		return list;
	}
	
	
	public List findbysql(String queryString){
		List list=configrecordDAO.findbysql(queryString);
		return list;
	}
	
	public ConfigRecord findByPropertys(String propertyname,int pid)
	{
		ConfigRecord configrecord=new ConfigRecord();
		List list=configrecordDAO.findByProperty(propertyname,pid);
		if(!list.isEmpty())
		{
			configrecord=(ConfigRecord)list.get(0);
			return configrecord;
		}else
		{
			return null;
		}
		
	}
	
	public ConfigRecord findByPropertyinfos(String propertyname,int pid)
	{
		ConfigRecord configrecord=new ConfigRecord();
		List list=configrecordDAO.findByPropertyinfo(propertyname,pid);
		if(!list.isEmpty())
		{
			configrecord=(ConfigRecord)list.get(0);
			return configrecord;
		}else
		{
			return null;
		}
		
	}
	
	public void deleteByAssetsTypeId(int assetsTypeId){
		String sql="from ConfigRecord where assets_Type_id='"+assetsTypeId+"'";
		configrecordDAO.deleteByAssetsTypeId(sql);
	}
	
	
	
//	public PageBean  findAll(int pageSize, int page) {
//		String queryString = "from ConfigRecord";        //查询语句
//		int currentPage = PageBean.countCurrentPage(page);
//	    int allRow = configrecordDAO.getAllRowCount(queryString);    //总记录数
//	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
//	    int offset = PageBean.countOffset(pageSize, currentPage);    //当前页开始记录
//	    int length = pageSize;    //每页记录数
//	    
//	    List<ConfigRecord> list = configrecordDAO.findAll(queryString, offset, length);  
//	    
//	    PageBean pageBean = new PageBean();
//        pageBean.setPageSize(pageSize);    
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setAllRow(allRow);
//        pageBean.setTotalPage(totalPage);
//        pageBean.setList(list);
//        pageBean.init();
//        return pageBean;
//	}
//	public PageBean findAllByAssetsTypeId(int pageSize, int page,int assetsTypeid)
//	{
//		String queryString = "from ConfigRecord  where assets_Type_id='"+assetsTypeid+"'";   
//		System.out.println(queryString);//查询语句
//		int currentPage = PageBean.countCurrentPage(page);
//	    int allRow = configrecordDAO.getAllRowCount(queryString);    //总记录数
//	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
//	    int offset = PageBean.countOffset(pageSize, currentPage);    //当前页开始记录
//	    int length = pageSize;    //每页记录数
//	    
//	    List<ConfigRecord> list = configrecordDAO.findAll(queryString, offset, length);  
//	    
//	    PageBean pageBean = new PageBean();
//        pageBean.setPageSize(pageSize);    
//        pageBean.setCurrentPage(currentPage);
//        pageBean.setAllRow(allRow);
//        pageBean.setTotalPage(totalPage);
//        pageBean.setList(list);
//        pageBean.init();
//        return pageBean;
//	}
	
}

