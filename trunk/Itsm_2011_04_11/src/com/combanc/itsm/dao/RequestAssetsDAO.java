package com.combanc.itsm.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 * A data access object (DAO) providing persistence and search support for
 * TaskCat entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see db.TaskCat
 * @author MyEclipse Persistence Tools
 */

public class RequestAssetsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(RequestAssetsDAO.class);

	// property constants

    @Override
	protected void initDao() {
		// do nothing
	}
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RequestAssets instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from RequestAssets as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

      public List findRequestIdByAssetsId(int assetsId)
      {
          return findByProperty("asset_id", assetsId); 
      }

}