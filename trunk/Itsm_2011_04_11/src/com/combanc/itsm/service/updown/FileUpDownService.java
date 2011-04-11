/**
 * 
 */
package com.combanc.itsm.service.updown;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.combanc.common.core.aop.Action;
import com.combanc.itsm.dao.AccessoryDAO;
import com.combanc.itsm.pojo.Accessory;

/**
 * @author Administrator
 * 
 */
@Transactional
public class FileUpDownService {

	private AccessoryDAO accessoryDAO;

	private static final Log logger = LogFactory.getLog(FileUpDownService.class);
	/**
	 * @return the accessoryDAO
	 */
	public AccessoryDAO getAccessoryDAO() {
		return accessoryDAO;
	}

	/**
	 * @param accessoryDAO
	 *            the accessoryDAO to set
	 */

	public void setAccessoryDAO(AccessoryDAO accessoryDAO) {
		this.accessoryDAO = accessoryDAO;
	}

	@Action(description = "新增上传附件信息")
	public boolean addUpFileInfo(Accessory accessory) {
		logger.debug("add upfile info!");
		if (accessory != null) {
			accessoryDAO.save(accessory);
			return true;
		} else {
			return false;
		}

	}
	@Action(description="更新附件信息")
	public boolean updateAccessory(Accessory accessory)
	{
		logger.debug("update upfile info");
		if(accessory!=null)
		{
			accessoryDAO.update(accessory);
			return true;
		}
		else{
			return false;
		}
	}
   @Action(description="删除上传文件信息")
	public boolean delUpFileInfo(String tableName, int tableId) {
	   
		logger.debug("delete upfileinfo!");
		if (tableName != null && !tableName.equals("")) {
			String hqlString = "from Accessory where tableName=" + tableName
					+ " and tableId=" + tableId;
			Accessory instanceAccessory = (Accessory) accessoryDAO
					.findByHql(hqlString);
			if (instanceAccessory != null) {
				accessoryDAO.delete(instanceAccessory);
				return true;
			} else {

				return false;
			}
		}
		return false;
	}
   

   public List<Accessory> getAccessorys(String tableName, int tableId) {
		
       logger.debug("getAccessoryByTableNameAndTableId");
       List<Accessory> accessoryList=null;
		if (tableName != null && !tableName.equals("")) {
			String hqlString = "from Accessory where tableName='" + tableName + "' and tableId=" + tableId;
			accessoryList= accessoryDAO.findByHql(hqlString);
		}
		return accessoryList;
	}
   public List<Accessory> getAccessoriesByNum(int num)
   {
	   logger.debug("getAccessoriesByNum");
	   return accessoryDAO.findByHql("form Accessory where num="+num);
   }
	public List<Accessory> getAccessoryByTableNameAndTableId(String tableName,
			int tableId) {
		
        logger.debug("getAccessoryByTableNameAndTableId");
        List<Accessory> instanceAccessory = null;
		if (tableName != null && !tableName.equals("")) {
			String hqlString = "from Accessory where tableName=\'" + tableName
					+ "\' and tableId=" + tableId;
			instanceAccessory =  accessoryDAO.findByHql(hqlString);
			return instanceAccessory;
		} else {
			return instanceAccessory;
		}

	}
	
	public List<String>  getFileNamesByTableNameAndTableId(String name,int tableId)
	{
		List<String> nameStrings=new ArrayList<String>();
		List<Accessory> instanceAccessory =	getAccessoryByTableNameAndTableId(name,tableId);
		for(Accessory o:instanceAccessory)
		{
			nameStrings.add(o.getTrueName());
		}
		 return nameStrings;
	}
	public List<String>  getFileNamesByTableNameAndTableIdAndVersion(String name,int tableId,int version)
	{
		List<String> nameStrings=new ArrayList<String>();
		List<Accessory> instanceAccessory =	getAccessoryByTableNameAndTableIdAndVersion(name,tableId,version);
		for(Accessory o:instanceAccessory)
		{
			nameStrings.add(o.getTrueName());
		}
		 return nameStrings;
	}
	/**
	 * @param name
	 * @param tableId
	 * @param version
	 * @return
	 */
	public  List<Accessory> getAccessoryByTableNameAndTableIdAndVersion(String name,
			int tableId, int version) {
		  logger.debug("getAccessoryByTableNameAndTableId");
	        List<Accessory> instanceAccessory = null;
			if (name != null && !name.equals("")) {
				String hqlString = "from Accessory where tableName=\'" + name
						+ "\' and tableId=" + tableId+ " and version=" + version;
				instanceAccessory =  accessoryDAO.findByHql(hqlString);
				return instanceAccessory;
			} else {
				return instanceAccessory;
			}
	}

	public Accessory getAccessoryByName(String name)
	{
		return (Accessory)accessoryDAO.findByName(name).get(0);
	}
	public boolean deleteByIdAndTableName(int tableId,
			String tableName,String uploadPath)
	{
		boolean isok=true;
		List<Accessory> aList = getAccessorys(tableName,tableId);
		for(Accessory a:aList)
		{
			String urlString=a.getUrl();
			File file = new File(uploadPath+urlString);
			if(file.exists())
			{
				file.delete();
			}
			accessoryDAO.delete(a);
			
		}
		return isok;
	}
	
	public boolean deleteById(int id,String url){
		boolean isok=true;
		Accessory accessory=accessoryDAO.findById(id);
		String urlString=accessory.getUrl();
		File file = new File(url+urlString);
		if(file.exists())
		{
			file.delete();
		}
		accessoryDAO.delete(accessory);
		return isok;
	}
	
	/**
	 * @param documentId
	 * @param acessoryId
	 * @return
	 */
	public boolean delAcessoryByAcessoryId(
			int acessoryId) {
		Accessory accessory=accessoryDAO.findById(acessoryId);
		if(accessory!=null)
		{
			accessoryDAO.delete(accessory);
			return true;
		}
		return false;
	}
	
	public Accessory getAccessoryById(int acessoryId){
		Accessory accessory=accessoryDAO.findById(acessoryId);
		return accessory;
	}
	
	public List<Accessory> findByTableId(int tableId){
		
		List<Accessory> list = accessoryDAO.findByTableId(tableId);
		return list;
		
	}
	

	


	/**
	 * @param dlFileName
	 * @return
	 */
	public Accessory getAccessoryByNameAndVersion(String dlFileName,int version) {
		return (Accessory)accessoryDAO.findByNameAndVersion(dlFileName,version).get(0);
	}
	public boolean deleteByIdAndTableNameAndVersion()
	{
		return false;
	}
}
