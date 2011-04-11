package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsConfig;


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

public class AssetsConfigDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssetsConfigDAO.class);

	protected void initDao() {
		// do nothing
	}
	
	public List findbysql(String sql)
	{
		return getHibernateTemplate().find(sql);
	}

	public void save(AssetsConfig transientInstance) {
		log.debug("saving AssetsConfig instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsConfig persistentInstance) {
		log.debug("deleting AssetsConfig instance");
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

	public void update(AssetsConfig persistentInstance) {
		log.debug("updating AssetsConfig instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public AssetsConfig findById(Integer id) {
		log.debug("getting AssetsConfig instance with id: " + id);
		try {
			AssetsConfig instance = (AssetsConfig) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.AssetsConfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsConfig instance) {
		log.debug("finding AssetsConfig instance by example");
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
		log.debug("finding AssetsConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsConfig as model where model."
					+ propertyName + "= ?"+"order by id asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findbychoose(String propertyName, Object value) {
		log.debug("finding AssetsConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsConfig as model where model.flag=0 and model.ischoose=1 and model."
					+ propertyName + "= ?"+"order by id asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findbyinfochoose(String propertyName, Object value) {
		log.debug("finding AssetsConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsConfig as model where model.flag=1 and model.ischoose=1 and model."
					+ propertyName + "= ?"+"order by id asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List findByPropertyinfos(String propertyName, Object value) {
		log.debug("finding AssetsConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsConfig as model where model.flag=1 and model."
					+ propertyName + "= ?"+"order by id asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyinfo(String propertyName, Object value) {
		log.debug("finding AssetsConfig instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsConfig as model where model.flag=1 and model."
					+ propertyName + "= ?"+"order by id asc";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all AssetsConfig instances");
		try {
			String queryString = "from AssetsConfig";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsConfig merge(AssetsConfig detachedInstance) {
		log.debug("merging ConfigRecord instance");
		try {
			AssetsConfig result = (AssetsConfig) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsConfig instance) {
		log.debug("attaching dirty AssetsConfig instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByProperties(final String queryString, final int offset, final int length) {
		log.debug("finding AssetsConfig instance with properties: ");
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
	
	public List<AssetsConfig> findAll(final String queryString, final int offset, final int length) {
		log.debug("finding all AssetsConfig instances");
		try {
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
	 
	public void attachClean(AssetsConfig instance) {
		log.debug("attaching clean AssetsConfig instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
		
	public static AssetsConfigDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsConfigDAO) ctx.getBean("AssetsConfigDAO");
	}
	
}