package com.combanc.itsm.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.AddressBookManage;

/**
 * A data access object (DAO) providing persistence and search support for
 * AddressBookManageAction entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.AddressBookManage
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class AddressBookManageDAO extends
		BaseHibernateDAO<AddressBookManage, Integer> {

	private static final Log log = LogFactory
			.getLog(AddressBookManageDAO.class);

	// 增加
	public void saveAddressBook(AddressBookManage addressBook) {

		this.getHibernateTemplate().save(addressBook);
	}

	// 2.根据id来查询通讯录;
	public AddressBookManage getAddressBookById(long id) {
		return (AddressBookManage) this.getHibernateTemplate().get(
				AddressBookManage.class, id);
	}

	// 3.更新对象;
	public void updateAddressBook(AddressBookManage addressBook) {
		this.getHibernateTemplate().update(addressBook);
	}

	// 删除
	public void deleteAddressBookById(long id) {

		AddressBookManage addressBook = this.getAddressBookById(id);
		this.getHibernateTemplate().delete(addressBook);
	}

	// 文件导出查询
	public List<AddressBookManage> queryByHqls(AddressBookManage addressBook) {

		List<AddressBookManage> list = new ArrayList<AddressBookManage>();

		/*
		 * StringBuffer sb = new StringBuffer(
		 * "from AddressBookManage as bean where 1=1");
		 * 
		 * if (addressBook.getUsername() != null &&
		 * addressBook.getUsername().equals("")) {
		 * sb.append("and bean.userName like '%" + addressBook.getUsername()); }
		 */

		/*
		 * if (addressBook.getDepartmentId() != null &&
		 * !addressBook.getDepartmentId().equals("")) {
		 * 
		 * sb.append("and bean.department = " + addressBook.getDepartmentId());
		 * }
		 * 
		 * sb.append("and bean.department != null");
		 * sb.append("order by bean.id");
		 * 
		 * String sql = sb.toString();
		 */

		String sb = "from AddressBookManage as bean where 1=1 and bean.department != null";

		String sql = sb.toString();

		try {
			list = getHibernateTemplate().find(sql);
		} catch (Exception e) {
			log.debug(e);
		}
		return list;
	}

	// 按姓名和部门查询

	public List<AddressBookManage> listAddressBookByHql(String hql) {

		List<AddressBookManage> list = this.getHibernateTemplate().find(hql);

		return list;
	}

	// 根据userId来查询通讯录集合
	public List<AddressBookManage> findByUserId(Object userId) {
		log.debug("finding  WorkLog By groupId");

		String sql = "from AddressBookManage as model where 1=1 and model.users.id="
				+ userId;

		return getHibernateTemplate().find(sql);
	}

	// 根据部门Id来查询通讯录集合
	public List<AddressBookManage> findByGroupId(Object groupId) {
		log.debug("finding  WorkLog By groupId");

		String sql = "from AddressBookManage as model where 1=1 and model.department.id="
				+ groupId;

		return getHibernateTemplate().find(sql);
	}

	// 按hql语句来查询通讯集合;
	public List<AddressBookManage> listAddressBookManageByHql(String hql) {
		List<AddressBookManage> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/*
	 * public List<AddressBookManage> queryByHqls(AddressBookManage
	 * addressBook){
	 * 
	 * List<AddressBookManage> list = new ArrayList<AddressBookManage>();
	 * StringBuffer sb = new
	 * StringBuffer("from AddressBookManage as bean where 1=1");
	 * 
	 * 
	 * return list; }
	 */

	// 批插入;
	public boolean batchInsert(List<AddressBookManage> addressBooks) {
		log.debug("batchInsert Weektable");
		boolean issuccesssave = true;
		try {
			for (int i = 0; i < addressBooks.size(); i++) {
				this.save((AddressBookManage) addressBooks.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			issuccesssave = false;
		}
		return issuccesssave;
	}

	public void save(AddressBookManage transientInstance) {
		log.debug("saving AddressBookManageAction instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AddressBookManage persistentInstance) {
		log.debug("deleting AddressBookManageAction instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AddressBookManage findById(java.lang.Integer id) {
		log.debug("getting AddressBookManage instance with id: " + id);
		try {
			AddressBookManage instance = (AddressBookManage) getSession().get(
					"com.combanc.itsm.pojo.AddressBookManage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AddressBookManage instance) {
		log.debug("finding AddressBookManageAction instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.AddressBookManage").add(
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
		log.debug("finding AddressBookManage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AddressBookManage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AddressBookManageAction instances");
		try {
			String queryString = "from AddressBookManage";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AddressBookManage merge(AddressBookManage detachedInstance) {
		log.debug("merging AddressBookManageAction instance");
		try {
			AddressBookManage result = (AddressBookManage) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AddressBookManage instance) {
		log.debug("attaching dirty AddressBookManageAction instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AddressBookManage instance) {
		log.debug("attaching clean AddressBookManage instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}