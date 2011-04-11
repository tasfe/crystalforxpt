/*
* 封装通用数据库操作
 */
package com.combanc.common.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * 抽象访问器
 *
 */
public abstract class AbstractDao extends HibernateDaoSupport {

	protected static final String SELECT = " select ";
	protected static final String FROM = " from ";
	protected static final String WHERE = " where ";
	
	protected static final String AND = " and ";
	protected static final String OR = " or ";
	
	protected static final String ORDERBY = " order by ";
	
	protected static final String COUNT(String item) {
		if(item == null || item.length() == 0)
			item = "*";
		return " count(" + item + ")";
	}
	/**
	 * 更新
	 */
	protected void  update(String entityName,Object entity) {
		if (entity == null)
			return ;
		Session session = super.getSession();
		session.update(entity);
	}
	/**
	 * 1、保存
	 */
	protected void save(String entityName,Object entity) {
		if (entity == null)
			return ;
		Session session = super.getSession();
		session.save(entity);
	}
	
	/**
	 * 2、删除
	 */
	protected void delete(Object entity) {
		if (entity == null)
			return;
		Session session = super.getSession();
		session.delete(entity);
		session.flush();
	}
	
	/**
	 * 3、根据ID查找
	 */
	protected Object findByID(String entityName, Integer id) {
		if (entityName == null || entityName.length() == 0)
			return null;
		Session session = super.getSession();
		return session.get(entityName, id);
	}
	
	/**
	 * 5、单实体查找
	 */
	protected Object find(String entityName, String condition, Object[] parameters) {
		if (entityName == null || entityName.length() == 0)
			return null;
		if (condition == null)
			condition = "";
		String hql = FROM + entityName;
		if (condition.length() != 0)
			hql = hql + WHERE + condition;
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		return query.uniqueResult();  //当明确知道只会返回一个结果时使用uniqueResult()
	}
	
	/**
	 * 7、多实体查找
	 */
	@SuppressWarnings("unchecked")
	protected List findAll(String entityName, String condition, Object[] parameters, String defaultSort) {
		if (entityName == null || entityName.length() == 0)
			return new ArrayList<Object>();
		if (condition == null)
			condition = "";
		String hql = FROM + entityName;
		if (defaultSort != null && defaultSort.trim().length() != 0) {
			String tmpCondition = condition.toLowerCase();
			if (tmpCondition.indexOf(ORDERBY) < 0)
				condition = (condition + ORDERBY + defaultSort);
		}
		if (condition.startsWith(ORDERBY))
			hql = hql + condition;
		else
			if (condition.length() != 0)
				hql = hql + WHERE + condition;
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		return query.list();
	}
	
	/**
	 * 9、是否存在
	 */
	protected boolean exists(String entityName, String condition, Object[] parameters) {
		if (entityName == null || entityName.length() == 0)
			return false;
		if (condition == null)
			condition = "";
		String hql = SELECT + COUNT("*") + FROM + entityName;
		if (condition.length() != 0)
			hql = hql + WHERE + condition;
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		Long count = (Long)query.uniqueResult();
		return count > 0;
	}
	
	/**
	 * 10、标量查找
	 */
	@SuppressWarnings("unchecked")
	protected List<Object> findValues(String entityName, String selection, String condition, Object[] parameters) {
		if (entityName == null || entityName.length() == 0)
			return new ArrayList<Object>();
		if (selection == null || selection.length() == 0)
			selection = "*";
		String hql = SELECT + selection + FROM + entityName;
		if (condition != null && condition.length() != 0)
			hql = hql + WHERE + condition;
		return super.getHibernateTemplate().find(hql, parameters);
	}
	
	/**
	 * 11、查找满足条件的记录数
	 */
	protected int findTotalCount(String entityName, String condition, Object[] parameters) {
		if (entityName == null || entityName.length() == 0)
			return 0;
		if (condition == null)
			condition = "";
		String hql = SELECT + COUNT("*") + FROM + entityName;
		if (condition.length() != 0)
			hql = hql + WHERE + condition;
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		Long count = (Long)query.uniqueResult();
		if(count == null)
			return 0;
		return count.intValue();
	}
	
	/**
	 * 12、分页查找
	 */
	@SuppressWarnings("unchecked")
	protected List findAllWithPaging(String entityName, String condition, Object[] parameters, 
			String defaultSort, int start, int limit) {
		if (entityName == null || entityName.length() == 0)
			return new ArrayList<Object>();
		if (condition == null)
			condition = "";
		String hql = FROM + entityName;
		if (defaultSort != null && defaultSort.trim().length() != 0) {
			String tmpCondition = condition.toLowerCase();
			if (tmpCondition.indexOf(ORDERBY) < 0)
				condition = (condition + ORDERBY + defaultSort);
		}
		if(condition.startsWith(ORDERBY))
			hql = hql + condition;
		else
			if (condition.length() != 0)
				hql = hql + WHERE + condition;
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		query.setFirstResult(start);
		query.setMaxResults(limit);
		
		return query.list();
	}
	/**
	 * 13 执行非查询HQL语句
	 * */
	public void executeHQLNotQuery(String hql ,Object[] parameters){
		Session session = super.getSession();
		
		Query query = session.createQuery( hql );
		if (parameters != null && parameters.length != 0) {
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] != null)
					query.setParameter(i, parameters[i]);
			}
		}
		query.executeUpdate();
		
		
	}
}
