package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.SchedualedTaskDetail;

public class SchedualedTaskDetailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SchedualedTaskDetailDAO.class);
	// property constants
	public static final String ENGINEER = "engineer";
	public static final String TASK_CODE = "taskCode";
	public static final String STATE = "state";
	public static final String SOLUTION = "solution";
	public static final String ADVICE = "advice";
	public static final String SERVICE_LVL = "serviceLvl";
	public static final String SAR_LVL = "sarLvl";

	public void save(SchedualedTaskDetail transientInstance) {
		log.debug("saving SchedualedTaskDetail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SchedualedTaskDetail persistentInstance) {
		log.debug("deleting SchedualedTaskDetail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SchedualedTaskDetail findById(java.lang.Integer id) {
		log.debug("getting SchedualedTaskDetail instance with id: " + id);
		try {
			SchedualedTaskDetail instance = (SchedualedTaskDetail) getSession()
					.get("com.combanc.itsm.pojo.SchedualedTaskDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SchedualedTaskDetail instance) {
		log.debug("finding SchedualedTaskDetail instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.SchedualedTaskDetail").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchedualedTaskDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SchedualedTaskDetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEngineer(Object engineer) {
		return findByProperty(ENGINEER, engineer);
	}

	public List findByTaskCode(Object taskCode) {
		return findByProperty(TASK_CODE, taskCode);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findBySolution(Object solution) {
		return findByProperty(SOLUTION, solution);
	}

	public List findByAdvice(Object advice) {
		return findByProperty(ADVICE, advice);
	}

	public List findByServiceLvl(Object serviceLvl) {
		return findByProperty(SERVICE_LVL, serviceLvl);
	}

	public List findBySarLvl(Object sarLvl) {
		return findByProperty(SAR_LVL, sarLvl);
	}

	public List findAll() {
		log.debug("finding all SchedualedTaskDetail instances");
		try {
			String queryString = "from SchedualedTaskDetail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SchedualedTaskDetail merge(SchedualedTaskDetail detachedInstance) {
		log.debug("merging SchedualedTaskDetail instance");
		try {
			SchedualedTaskDetail result = (SchedualedTaskDetail) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SchedualedTaskDetail instance) {
		log.debug("attaching dirty SchedualedTaskDetail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SchedualedTaskDetail instance) {
		log.debug("attaching clean SchedualedTaskDetail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}