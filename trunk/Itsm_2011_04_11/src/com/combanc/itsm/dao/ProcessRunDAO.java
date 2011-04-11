package com.combanc.itsm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ProcessRun;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProcessRun entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.dao.ProcessRun
 * @author MyEclipse Persistence Tools
 */

public class ProcessRunDAO extends BaseHibernateDAO<ProcessRun, Integer> {
	private static final Log log = LogFactory.getLog(ProcessRunDAO.class);
	// property constants
	public static final String SUBJECT = "subject";
	public static final String CREATOR = "creator";
	public static final String USER_ID = "userId";
	public static final String DEF_ID = "defId";
	public static final String PI_ID = "piId";
	public static final String RUN_STATUS = "runStatus";

	protected void initDao() {
		// do nothing
	}

	public void save(ProcessRun transientInstance) {
		log.debug("saving ProcessRun instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProcessRun persistentInstance) {
		log.debug("deleting ProcessRun instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProcessRun findById(java.lang.Integer id) {
		log.debug("getting ProcessRun instance with id: " + id);
		try {
			ProcessRun instance = (ProcessRun) getHibernateTemplate().get(
					"com.combanc.itsm.dao.ProcessRun", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public ProcessRun getByPiId(String piId) {
		String hql = "from ProcessRun pr where pr.piId=?";
		return (ProcessRun) findUnique(hql, new Object[] { piId });
	}

	public List<ProcessRun> getByDefId(Integer defId, PageBean pb) {
		String hql = " from ProcessRun pr where pr.proDefinition.defId=? ";
		return findByHql(hql, new Object[] { defId }, pb);
	}

	public List<ProcessRun> getByUserIdSubject(Long userId, String subject,
			PageBean pb) {
		ArrayList params = new ArrayList();
		String hql = "select pr from ProcessRun as pr join pr.processForms as pf where pf.creatorId=? group by pr.runId order by pr.createtime desc";
		params.add(userId);
		if (StringUtils.isNotEmpty(subject)) {
			hql = hql + " and pr.subject like ?";
			params.add("%" + subject + "%");
		}

		return findByHql(hql, params.toArray(), pb);
	}

	public List findByExample(ProcessRun instance) {
		log.debug("finding ProcessRun instance by example");
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
		log.debug("finding ProcessRun instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProcessRun as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySubject(Object subject) {
		return findByProperty(SUBJECT, subject);
	}

	public List findByCreator(Object creator) {
		return findByProperty(CREATOR, creator);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByDefId(Object defId) {
		return findByProperty(DEF_ID, defId);
	}

	public List findByPiId(Object piId) {
		return findByProperty(PI_ID, piId);
	}

	public List findByRunStatus(Object runStatus) {
		return findByProperty(RUN_STATUS, runStatus);
	}

	public List findAll() {
		log.debug("finding all ProcessRun instances");
		try {
			String queryString = "from ProcessRun";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProcessRun merge(ProcessRun detachedInstance) {
		log.debug("merging ProcessRun instance");
		try {
			ProcessRun result = (ProcessRun) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProcessRun instance) {
		log.debug("attaching dirty ProcessRun instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProcessRun instance) {
		log.debug("attaching clean ProcessRun instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProcessRunDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProcessRunDAO) ctx.getBean("ProcessRunDAO");
	}
}