package com.combanc.itsm.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.WorkLog;

/**
 * @see com.combanc.itsm.dao.Department
 * @author MyEclipse Persistence Tools
 */

public class DepartmentDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DepartmentDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	public static final String PID = "pid";

	protected void initDao() {

	}
	/*
	 * 获取部门树的最大深度
	 */
	public int getLevel(){
		int level=0;
		String query=" select distinct length(code) as level from Department";
		List list=getHibernateTemplate().find(query);
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				try{
					if(Integer.valueOf(list.get(i).toString())>level){
						level=Integer.valueOf(list.get(i).toString());
					}
				}catch (Exception e) {
					return 0;
				}
			}
		}
		return level;
	}
	/*
	 * 根据CODE获取其下所有子节点
	 */
	public List getSubDepartmentsByCode(String code){
		try {
			if(code==""){
				return getHibernateTemplate().find(" from Department as model where model.pid=0 or model.pid is null");
			}
			String queryString = "from Department as model where model.code like '"+code+"%'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/*
	 * 获取所有节点，按照深度排序
	 */
	public List getAllDeptsOrderLevel(){
		String queryString = "from Department order by length(code) asc";
		return getHibernateTemplate().find(queryString);
	}
	public void save(Department transientInstance) {
		log.debug("saving Department instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Department persistentInstance) {
		log.debug("deleting Department instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Department findById(java.lang.Integer id) {
		log.debug("getting Department instance with id: " + id);
		try {
			Department instance = (Department) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Department", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Department instance) {
		log.debug("finding Department instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Department instance with property: " + propertyName
				+ ", value: " + value);
		try {
			if(value==null && propertyName.toLowerCase().equals("pid")){
				return getHibernateTemplate().find(" from Department where pid is null or pid=0");
			}
			if( propertyName.toLowerCase().equals("pid")&&(Integer)(value)==-1){
				return getHibernateTemplate().find("from Department order by pid asc");
			}
			String queryString = "from Department as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findAll() {
		log.debug("finding all Department instances");
		try {
			String queryString = "from Department";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Department merge(Department detachedInstance) {
		log.debug("merging Department instance");
		try {
			Department result = (Department) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Department instance) {
		log.debug("attaching dirty Department instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Department instance) {
		log.debug("attaching clean Department instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DepartmentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DepartmentDAO) ctx.getBean("DepartmentDAO");
	}
	
	
	
}