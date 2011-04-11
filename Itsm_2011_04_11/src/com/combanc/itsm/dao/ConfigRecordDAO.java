package com.combanc.itsm.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.ConfigRecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfigRecord entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.ConfigRecord
 * @author MyEclipse Persistence Tools
 */

public class ConfigRecordDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ConfigRecordDAO.class);

	protected void initDao() {
		// do nothing
	}
	
	public List findbysql(String sql)
	{
		return getHibernateTemplate().find(sql);
	}

	public void save(ConfigRecord transientInstance) {
		log.debug("saving ConfigRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfigRecord persistentInstance) {
		log.debug("deleting ConfigRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void deleteByAssetsTypeId(String sql){
		List list=this.findbysql(sql);
		getHibernateTemplate().deleteAll(list);
	}

	public void update(ConfigRecord persistentInstance) {
		log.debug("updating ConfigRecord instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public ConfigRecord findById(Integer id) {
		log.debug("getting ConfigRecord instance with id: " + id);
		try {
			ConfigRecord instance = (ConfigRecord) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.ConfigRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ConfigRecord instance) {
		log.debug("finding ConfigRecord instance by example");
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
		log.debug("finding ConfigRecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfigRecord as model where model.isAssetsinfo=0 and model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyinfo(String propertyName, Object value) {
		log.debug("finding ConfigRecord instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfigRecord as model where model.isAssetsinfo=1 and model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all ConfigRecord instances");
		try {
			String queryString = "from ConfigRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfigRecord merge(ConfigRecord detachedInstance) {
		log.debug("merging ConfigRecord instance");
		try {
			ConfigRecord result = (ConfigRecord) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfigRecord instance) {
		log.debug("attaching dirty ConfigRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByProperties(final String queryString, final int offset, final int length) {
		log.debug("finding ConfigRecord instance with properties: ");
		try {
			Query query =getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
		    return query.list();
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}
	
	public List<ConfigRecord> findAll(final String queryString, final int offset, final int length) {
		log.debug("finding all ConfigRecord instances");
		try {
//			String queryString = "from AssetsProducer";
			Query query =getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
		     return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	 public int getAllRowCount(String hql){
	        return getHibernateTemplate().find(hql).size();
	    }
	 
	public void attachClean(ConfigRecord instance) {
		log.debug("attaching clean ConfigRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
		
	public static ConfigRecordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ConfigRecordDAO) ctx.getBean("ConfigRecordDAO");
	}
	
}