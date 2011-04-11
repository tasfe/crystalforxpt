package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsState;

public class AssetsStateDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssetsStateDAO.class);

	protected void initDao() {
	}

	public void save(AssetsState transientInstance) {
		log.debug("saving AssetsState instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsState assetsState) {
		log.debug("deleting AssetsState instance");
		try {
			getHibernateTemplate().delete(assetsState);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(AssetsState persistentInstance) {
		log.debug("updating AssetsState instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public AssetsState findById(Integer id) {
		log.debug("getting AssetsState instance with id: " + id);
		try {
			AssetsState instance = (AssetsState) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.AssetsState", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsState instance) {
		log.debug("finding AssetsState instance by example");
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
		log.debug("finding AssetsState instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsState as model where model."
					+ propertyName + "= ?"; 
			List list=getHibernateTemplate().find(queryString, value);
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findbyhql(String queryString){
		List list=getHibernateTemplate().find(queryString);
		return list;
	}

	public List findByName(String name) {
		List list=findByProperty("name", name);
		return list;
	}
	
	public List findbySequence(int Sequence){
		String sql="from AssetsState as model where model.sequence>"+Sequence;
		List list=getHibernateTemplate().find(sql);
		return list;
	}
//	
//	public List findByNames(String name) {
//		return findByProperty(NAME, name);
//	}
//
//	public List findByFlag(Object flag) {
//		return findByProperty(FLAG, flag);
//	}


//	public List<AssetsState> findAllByAssetsTypePid(int pid) {
//		String queryString;
//		try {
//			queryString = "from AssetsType where flag like '%:" + pid + ":%' ";
//			return getHibernateTemplate().find(queryString);
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
	public List findAll() {
		log.debug("finding all AssetsState instances");
		try {
			String queryString = "from AssetsState model where model.id!=0 order by model.sequence asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsState merge(AssetsState detachedInstance) {
		log.debug("merging AssetsType instance");
		try {
			AssetsState result = (AssetsState) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsState instance) {
		log.debug("attaching dirty AssetsType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsState instance) {
		log.debug("attaching clean AssetsState instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsStateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsStateDAO) ctx.getBean("AssetsStateDAO");
	}
}