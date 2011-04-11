package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ProjectManage;


/**
 * A data access object (DAO) providing persistence and search support for
 * ProjectManage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.ProjectManage
 * @author MyEclipse Persistence Tools
 */

public class ProjectManageDAO extends BaseHibernateDAO<ProjectManage, Integer> {
	
	 private static final Log log = LogFactory.getLog(ProjectManageDAO.class);
		//property constants
	public static final String PROJECT_NAME = "projectName";
	public static final String PROJECT_MANAGER = "projectManager";
	public static final String PROJECT_DEGRADE = "projectDegrade";
	public static final String PROJECT_TYPE = "projectType";
	public static final String PROJECT_CONTENT = "projectContent";
	public static final String FILE = "file";
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";



 
 public void save(ProjectManage transientInstance) {
     log.debug("saving ProjectManage instance");
     try {
         getSession().save(transientInstance);
         log.debug("save successful");
     } catch (RuntimeException re) {
         log.error("save failed", re);
         throw re;
     }
 }
 
	public void delete(ProjectManage persistentInstance) {
     log.debug("deleting ProjectManage instance");
     try {
         getSession().delete(persistentInstance);
         log.debug("delete successful");
     } catch (RuntimeException re) {
         log.error("delete failed", re);
         throw re;
     }
 }
 
 public ProjectManage findById( java.lang.Long id) {
     log.debug("getting ProjectManage instance with id: " + id);
     try {
         ProjectManage instance = (ProjectManage) getSession()
                 .get("com.combanc.itsm.pojo.ProjectManage", id);
         return instance;
     } catch (RuntimeException re) {
         log.error("get failed", re);
         throw re;
     }
 }
 
 
 public List findByExample(ProjectManage instance) {
     log.debug("finding ProjectManage instance by example");
     try {
         List results = getSession()
                 .createCriteria("com.combanc.itsm.pojo.ProjectManage")
                 .add(Example.create(instance))
         .list();
         log.debug("find by example successful, result size: " + results.size());
         return results;
     } catch (RuntimeException re) {
         log.error("find by example failed", re);
         throw re;
     }
 }    
 
 public List findByProperty(String propertyName, Object value) {
   log.debug("finding ProjectManage instance with property: " + propertyName
         + ", value: " + value);
   try {
      String queryString = "from ProjectManage as model where model." 
      						+ propertyName + "= ?";
      Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
   } catch (RuntimeException re) {
      log.error("find by property name failed", re);
      throw re;
   }
	}

	public List findByProjectName(Object projectName
	) {
		return findByProperty(PROJECT_NAME, projectName
		);
	}
	
	public List findByProjectManager(Object projectManager
	) {
		return findByProperty(PROJECT_MANAGER, projectManager
		);
	}
	
	public List findByProjectDegrade(Object projectDegrade
	) {
		return findByProperty(PROJECT_DEGRADE, projectDegrade
		);
	}
	
	public List findByProjectType(Object projectType
	) {
		return findByProperty(PROJECT_TYPE, projectType
		);
	}
	
	public List findByProjectContent(Object projectContent
	) {
		return findByProperty(PROJECT_CONTENT, projectContent
		);
	}
	
	public List findByFile(Object file
	) {
		return findByProperty(FILE, file
		);
	}
	
	public List findByUserId(Object userId
	) {
		return findByProperty(USER_ID, userId
		);
	}
	
	public List findByUserName(Object userName
	) {
		return findByProperty(USER_NAME, userName
		);
	}
	

	public List findAll() {
		log.debug("finding all ProjectManage instances");
		try {
			String queryString = "from ProjectManage";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
 public ProjectManage merge(ProjectManage detachedInstance) {
     log.debug("merging ProjectManage instance");
     try {
         ProjectManage result = (ProjectManage) getSession()
                 .merge(detachedInstance);
         log.debug("merge successful");
         return result;
     } catch (RuntimeException re) {
         log.error("merge failed", re);
         throw re;
     }
 }

 public void attachDirty(ProjectManage instance) {
     log.debug("attaching dirty ProjectManage instance");
     try {
         getSession().saveOrUpdate(instance);
         log.debug("attach successful");
     } catch (RuntimeException re) {
         log.error("attach failed", re);
         throw re;
     }
 }
 
 public void attachClean(ProjectManage instance) {
     log.debug("attaching clean ProjectManage instance");
     try {
         getSession().lock(instance, LockMode.NONE);
         log.debug("attach successful");
     } catch (RuntimeException re) {
         log.error("attach failed", re);
         throw re;
     }
 }

	// 增;
	public void saveProject(ProjectManage projectManage) {
		
		this.getHibernateTemplate().save(projectManage);
	}

	// 查
	public ProjectManage getProjectById(long id) {

		return (ProjectManage) this.getHibernateTemplate().get(
				ProjectManage.class, id);

	}

	// 删
	public void deleteProject(long id) {
		ProjectManage project = this.getProjectById(id);
		this.getHibernateTemplate().delete(project);
	}

	// 更新之前,先查出来;
	public ProjectManage projectById(long id) {
		String hql = "from ProjectManage as bean where bean.id=" + id
				+ "order by bean.id asc";
		ProjectManage project = (ProjectManage) this.getHibernateTemplate()
				.find(hql);
		return project;
	}

	// 改;
	public void updateProject(ProjectManage project) {

		this.getHibernateTemplate().update(project);
	}

	// 查出一个集合的方法;
	@SuppressWarnings("unchecked")
	public List<ProjectManage> listProjectById(long id) {
		String hql = "from ProjectManage as bean where bean.id=" + id
				+ "order by bean.id desc";
		List<ProjectManage> list = this.getHibernateTemplate().find(hql);
		return list;

	}

}