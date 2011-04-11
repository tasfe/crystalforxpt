package com.combanc.itsm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.DocumentAuthority;

public class DocumentAuthorityDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(DocumentAuthorityDAO.class);
	public static final String DID = "did";
	public static final String TID = "tid";
	public static final String AUTHORITY_SEL = "authoritySel";
	public static final String AUTHORITY_ADD = "authorityAdd";
	public static final String AUTHORITY_DEL = "authorityDel";
	public static final String AUTHORITY_UPD = "authorityUpd";

	protected void initDao() {
	}

	public void save(DocumentAuthority transientInstance) {
		log.debug("saving DocumentAuthority instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentAuthority persistentInstance) {
		log.debug("deleting DocumentAuthority instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentAuthority findById(java.lang.Integer id) {
		log.debug("getting DocumentAuthority instance with id: " + id);
		try {
			DocumentAuthority instance = (DocumentAuthority) getHibernateTemplate()
					.get("com.combanc.itsm.pojo.DocumentAuthority", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocumentAuthority instance) {
		log.debug("finding DocumentAuthority instance by example");
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
		log.debug("finding DocumentAuthority instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocumentAuthority as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDid(Object did) {
		return findByProperty(DID, did);
	}

	public List findByTid(Object tid) {
		return findByProperty(TID, tid);
	}

	public List findByAuthoritySel(Object authoritySel) {
		return findByProperty(AUTHORITY_SEL, authoritySel);
	}

	public List findByAuthorityAdd(Object authorityAdd) {
		return findByProperty(AUTHORITY_ADD, authorityAdd);
	}

	public List findByAuthorityDel(Object authorityDel) {
		return findByProperty(AUTHORITY_DEL, authorityDel);
	}

	public List findByAuthorityUpd(Object authorityUpd) {
		return findByProperty(AUTHORITY_UPD, authorityUpd);
	}

	public List findAll() {
		log.debug("finding all DocumentAuthority instances");
		try {
			String queryString = "from DocumentAuthority";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteAllByTid(final Integer tid) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from DocumentAuthority d where d.tid = "
						+ tid;
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	public void deleteAllByDid(final Integer did) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "delete from DocumentAuthority d where d.did = "
						+ did;
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	public DocumentAuthority merge(DocumentAuthority detachedInstance) {
		log.debug("merging DocumentAuthority instance");
		try {
			DocumentAuthority result = (DocumentAuthority) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentAuthority instance) {
		log.debug("attaching dirty DocumentAuthority instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentAuthority instance) {
		log.debug("attaching clean DocumentAuthority instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentAuthorityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentAuthorityDAO) ctx.getBean("DocumentAuthorityDAO");
	}
}