package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Accessory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Accessory entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see itsm.db.test.Accessory
 * @author MyEclipse Persistence Tools
 */

public class AccessoryDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AccessoryDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TRUENAME = "truename";
	public static final String TABLE_ID = "tableId";
	public static final String TYPE = "type";
	public static final String TABLE_NAME = "tableName";
	public static final String UPLOAD_USERNAME = "uploadUsername";
	public static final String SIZE = "size";
	public static final String URL = "url";
	public static final String VERSION="version";

	public void save(Accessory transientInstance) {
		log.debug("saving Accessory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Accessory persistentInstance) {
		log.debug("deleting Accessory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accessory findById(java.lang.Integer id) {
		log.debug("getting Accessory instance with id: " + id);
		try {
			Accessory instance = (Accessory) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Accessory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	public List findByExample(Accessory instance) {
		log.debug("finding Accessory instance by example");
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
		log.debug("finding Accessory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Accessory as model where model."
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

	public List findByTruename(Object truename) {
		return findByProperty(TRUENAME, truename);
	}

	public List findByTableId(Object tableId) {
		return findByProperty(TABLE_ID, tableId);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByTableName(Object tableName) {
		return findByProperty(TABLE_NAME, tableName);
	}

	public List findByUploadUsername(Object uploadUsername) {
		return findByProperty(UPLOAD_USERNAME, uploadUsername);
	}

	public List findBySize(Object size) {
		return findByProperty(SIZE, size);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findAll() {
		log.debug("finding all Accessory instances");
		try {
			String queryString = "from Accessory";

			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Accessory merge(Accessory detachedInstance) {
		log.debug("merging Accessory instance");
		try {
			Accessory result = (Accessory) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Accessory instance) {
		log.debug("attaching dirty Accessory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accessory instance) {
		log.debug("attaching clean Accessory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * @param dlFileName
	 * @param version2
	 * @return
	 */
	public List<Accessory> findByNameAndVersion(String dlFileName, int version2) {
		
		String hqlString="from Accessory as model where model.name="+dlFileName+" and model.version="+version2;
		return getHibernateTemplate().find(hqlString);
	}
	public List<Accessory> findByNameAndTableIdAndVersion(String dlFileName,int tableId, int version2) {
		
		String hqlString="from Accessory as model where model.name="+dlFileName+" and model.version="+version2+" and model.tableId="+tableId;
		return getHibernateTemplate().find(hqlString);
	}

}