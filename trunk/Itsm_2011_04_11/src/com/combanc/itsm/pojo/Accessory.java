/**
 * 
 */
package com.combanc.itsm.pojo;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Administrator
 *
 */
public class Accessory implements java.io.Serializable{
	
	private int id;
	private String name;//文件名
	private String trueName;//上传时文件的真是名字
	private Timestamp uploadDate;
	private String tableName; //关联表名
	private String uploadUserName;//上传人的名字
	private int tableId;//关联表中的Id
	private String type;//文件类型
	private int size;
	private String url;
	private int version;
	
	
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the trueName
	 */
	public String getTrueName() {
		return trueName;
	}
	/**
	 * @param trueName the trueName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	
	
	public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	/**
	 * @return the uploadUserName
	 */
	public String getUploadUserName() {
		return uploadUserName;
	}
	/**
	 * @param uploadUserName the uploadUserName to set
	 */
	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
	/**
	 * @return the tableId
	 */
	public int getTableId() {
		return tableId;
	}
	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	
	

}
