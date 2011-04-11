package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ProblemType;

public class ProblemTypeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ProblemTypeDAO.class);
	public static final String PROBLEM_CATE = "problemCate";
	public static final String PROBLEM_CATE_SC = "problemCateSC";
	public static final String CODE = "code";
	public static final String PID = "pid";
	public static final String ADD_TABLE1 = "addTable1";
	public static final String ADD_TABLE2 = "addTable2";

	protected void initDao() {
		// do nothing
	}

	public void save(ProblemType transientInstance) {
		log.debug("saving ProblemType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProblemType persistentInstance) {
		log.debug("deleting ProblemType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProblemType findById(java.lang.Integer id) {
		log.debug("getting ProblemType instance with id: " + id);
		try {
			ProblemType instance = (ProblemType) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.ProblemType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProblemType instance) {
		log.debug("finding ProblemType instance by example");
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
		log.debug("finding ProblemType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProblemType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProblemCate(Object problemCate) {
		return findByProperty(PROBLEM_CATE, problemCate);
	}

	public List findByProblemCateSc(Object problemCateSc) {
		return findByProperty(PROBLEM_CATE_SC, problemCateSc);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByAddTable1(Object addTable1) {
		return findByProperty(ADD_TABLE1, addTable1);
	}

	public List findByAddTable2(Object addTable2) {
		return findByProperty(ADD_TABLE2, addTable2);
	}

	public List findAll() {
		log.debug("finding all ProblemType instances");
		try {
			String queryString = "from ProblemType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProblemType merge(ProblemType detachedInstance) {
		log.debug("merging ProblemType instance");
		try {
			ProblemType result = (ProblemType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProblemType instance) {
		log.debug("attaching dirty ProblemType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProblemType instance) {
		log.debug("attaching clean ProblemType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProblemTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProblemTypeDAO) ctx.getBean("ProblemTypeDAO");
	}
}