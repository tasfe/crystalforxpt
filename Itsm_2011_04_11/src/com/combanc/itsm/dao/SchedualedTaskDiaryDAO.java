package com.combanc.itsm.dao;


import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.SchedualedTaskDiary;


public class SchedualedTaskDiaryDAO extends BaseHibernateDAO  {
	private static final Log log = LogFactory
			.getLog(SchedualedTaskDiaryDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String PROGRESS = "progress";
	public static final String USER_ID = "userId";
	public static final String TASK_DETAIL_ID = "taskDetailId";

	public void save(SchedualedTaskDiary transientInstance) {
		log.debug("saving SchedualedTaskDiary instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SchedualedTaskDiary persistentInstance) {
		log.debug("deleting SchedualedTaskDiary instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SchedualedTaskDiary findById(java.lang.Integer id) {
		log.debug("getting SchedualedTaskDiary instance with id: " + id);
		try {
			SchedualedTaskDiary instance = (SchedualedTaskDiary) getSession()
					.get("com.combanc.itsm.pojo.SchedualedTaskDiary", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SchedualedTaskDiary instance) {
		log.debug("finding SchedualedTaskDiary instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.SchedualedTaskDiary")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchedualedTaskDiary instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SchedualedTaskDiary as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByProgress(Object progress) {
		return findByProperty(PROGRESS, progress);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByTaskDetailId(Object taskDetailId) {
		return findByProperty(TASK_DETAIL_ID, taskDetailId);
	}

	public List findAll() {
		log.debug("finding all SchedualedTaskDiary instances");
		try {
			String queryString = "from SchedualedTaskDiary";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SchedualedTaskDiary merge(SchedualedTaskDiary detachedInstance) {
		log.debug("merging SchedualedTaskDiary instance");
		try {
			SchedualedTaskDiary result = (SchedualedTaskDiary) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SchedualedTaskDiary instance) {
		log.debug("attaching dirty SchedualedTaskDiary instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SchedualedTaskDiary instance) {
		log.debug("attaching clean SchedualedTaskDiary instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}