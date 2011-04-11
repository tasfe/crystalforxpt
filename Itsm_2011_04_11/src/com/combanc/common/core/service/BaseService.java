package com.combanc.common.core.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.QueryFilter;
import com.combanc.common.core.dao.BaseHibernateDAO;

public class BaseService<T, ID extends Serializable> {
	protected Log logger = LogFactory.getLog(BaseService.class);

	protected BaseHibernateDAO<T, Serializable> dao = null;

	public void setDao(BaseHibernateDAO dao) {
		this.dao = dao;
	}

	public T get(Class<T> entityClass, ID id) {
		return this.dao.get(entityClass, id);
	}

	public T get(ID id) {
		return this.dao.get(id);
	}

	public T merge(T entity) {
		return this.dao.merge(entity);
	}

	public void save(T entity) {
		this.dao.save(entity);
	}
	
	public void saveWithFlushMode(T entity) {
		this.dao.saveWithFlushMode(entity);
	}

	public void update(T entity) {
		this.dao.update(entity);
	}

	public void evict(T entity) {
		this.dao.evict(entity);
	}

	public void remove(ID id) {
		this.dao.remove(id);
	}

	public void remove(T entity) {
		this.dao.remove(entity);
	}

	public void flush() {
		this.dao.flush();
	}

	public List<T> getAll() {
		return this.dao.getAll();
	}

	public List<T> getAll(QueryFilter filter) {
		return this.dao.getAll(filter);
	}
	
	/**清空表**/
	public void truncateTable(){
		this.dao.truncateTable();
	}
	/** 删除指定时间前的记录**/
	public void deleteBefore(String tableName, String column,Timestamp time){
		String logStr = "delete " + tableName + " before " + time.toString();
		try {
			String sql = "DELETE FROM " + tableName + " WHERE " + column + " < '" + time + "'";
			this.dao.getJdbcTemplate().execute(sql);	 
		} catch (RuntimeException re) {
			 
			throw re;
		}
	}
	/** 删除超过XX条以前的记录**/
	public void deleteBefore(String tableName, String column,int keepNum){
		String logStr = "delete " + tableName + " more than " + keepNum;
		try {
			String sql = "delete from  " + tableName + " where " + column + "<(select min(tid."+column+") from (select "+column+" from "+tableName+"  order by "+column+" desc limit "+keepNum+") as tid)";
			this.dao.getJdbcTemplate().execute(sql);	 
		} catch (RuntimeException re) {
			 
			throw re;
		}
	}
}
