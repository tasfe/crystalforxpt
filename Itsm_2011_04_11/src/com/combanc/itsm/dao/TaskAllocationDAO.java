package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.janino.samples.ShippingCost;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.TaskAllocation;

public class TaskAllocationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TaskAllocationDAO.class);
	// property constants
	public static final String DISPLAYNAME = "displayname";
	public static final String CATEGORY = "category";
	public static final String DEPARTMENT = "department";
	public static final String CODE = "code";
	public static final String CONTENT = "content";
	public static final String ENGINEER = "engineer";

	protected void initDao() {
		// do nothing
	}

	public void save(TaskAllocation transientInstance) {
		log.debug("saving TaskAllocation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskAllocation persistentInstance) {
		log.debug("deleting TaskAllocation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(TaskAllocation entity) {
		log.debug("updating TaskAllocation instance");
		try {
			getHibernateTemplate().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public TaskAllocation findById(java.lang.Integer id) {
		log.debug("getting TaskAllocation instance with id: " + id);
		try {
			TaskAllocation instance = (TaskAllocation) getHibernateTemplate()
					.get("com.combanc.itsm.pojo.TaskAllocation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskAllocation instance) {
		log.debug("finding TaskAllocation instance by example");
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
		log.debug("finding TaskAllocation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TaskAllocation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDisplayname(Object displayname) {
		return findByProperty(DISPLAYNAME, displayname);
	}

	public List findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByEngineer(Object engineer) {
		return findByProperty(ENGINEER, engineer);
	}

	public List findAll() {
		log.debug("finding all TaskAllocation instances");
		try {
			String queryString = "from TaskAllocation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByDeptAndCate(Integer dep, Integer cat) {
		log
				.debug("finding all TaskAllocation instances by department and category: ");
		try {
			if (dep != null) {
				if (cat != null) {
					String queryString = "from TaskAllocation as model where model.department="
							+ dep + " and model.category=" + cat;
					return getHibernateTemplate().find(queryString);
				} else {
					String queryString = "from TaskAllocation as model where model.department="
							+ dep;
					return getHibernateTemplate().find(queryString);
				}
			} else if (cat != null) {
				String queryString = "from TaskAllocation as model where model.category="
						+ cat;
				return getHibernateTemplate().find(queryString);
			} else {
				return null;
			}

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskAllocation merge(TaskAllocation detachedInstance) {
		log.debug("merging TaskAllocation instance");
		try {
			TaskAllocation result = (TaskAllocation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskAllocation instance) {
		log.debug("attaching dirty TaskAllocation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskAllocation instance) {
		log.debug("attaching clean TaskAllocation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TaskAllocationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TaskAllocationDAO) ctx.getBean("TaskAllocationDAO");
	}

	public List<TaskAllocation> getTaskAllocationsByDepIdAndCatId(int depId,
			int catId) {

//		List<TaskAllocation> list = getSession(true).createCriteria(
//				TaskAllocation.class)
////				.createCriteria("cats")
////				.add(Restrictions.eq("id", catId)).list();
//				.createCriteria("department").createCriteria(Department.class).
//				.add(Restrictions.eq("id", 1)).list();
		// String sqlString="";
		// if(depId>0)
		// {
		// sqlString="from TaskAllocation as model where model.department.id="+depId;
		// }
		// if(catId>0)
		// {
		// sqlString=sqlString+" and model.cats.id="+catId;
		//			
		// }
		// return getHibernateTemplate().find(sqlString);
          
		return null;
	}
}