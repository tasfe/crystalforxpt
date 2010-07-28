package crystal.hibernate.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * DAO操作基类<br>
 * 本DAO层实现了通用的数据操作
 * 
 * @author 杨坤
 * 
 * @param <T>
 *            POJO实体对象
 * @param <ID>
 *            ID
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDAO<T, ID extends Serializable> extends
		HibernateDaoSupport {

	private static final Logger logger = Logger
			.getLogger(BaseHibernateDAO.class);

	/**
	 * 保存指定实体类
	 * 
	 * @param entityobj
	 *            实体类
	 */
	public void save(T entity) {
		try {
			getHibernateTemplate().save(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体类成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("保存实体异常," + entity.getClass().getName(), e);
			throw e;
		}
	}

	/**
	 * 更新
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * 删除指定实体
	 * 
	 * @param entityobj
	 *            实体类
	 */
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("删除实体类成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除实体异常", e);
			throw e;
		}
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @param entityClass
	 *            实体
	 * @return 集合
	 */
	public List<T> findAll(Class<T> entityClass) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查找所有实体：" + entityClass.getName());
			}
			return getHibernateTemplate().find("from " + entityClass.getName());
		} catch (RuntimeException e) {
			logger.error("查找指定实体集合异常，实体：" + entityClass.getName(), e);
			throw e;
		}
	}
	
	/**
	 * 获取所有实体集合的前n条记录
	 * 
	 * @param entityClass
	 *            实体
	 * @return 集合
	 */
	public List<T> findTopN(Class<T> entityClass, int topN) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查找topN实体：" + entityClass.getName());
			}
			String queryString = "from " + entityClass.getName() + " order by id";
			Session ssn = getSession();   
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			logger.error("查找指定实体集合topN异常，实体：" + entityClass.getName(), e);
			throw e;
		}
	}

	/**
	 * 更新或保存指定实体
	 * 
	 * @param entity
	 *            实体类
	 */
	public void saveOrUpdate(T entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("更新或者保存实体成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("更新或保存实体异常", e);
			throw e;
		}
	}

	/**
	 * 查找指定ID实体类对象
	 * 
	 * @param entityClass
	 *            实体Class
	 * @param id
	 *            实体ID
	 * @return 实体对象
	 */
	public T findById(Class<T> entityClass, ID id) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找ID为" + id + "的实体：" + entityClass.getName());
			}
			return (T) getHibernateTemplate().get(entityClass, id);
		} catch (RuntimeException e) {
			logger.error("查找指定ID实体异常，ID：" + id, e);
			throw e;
		}
	}

	/**
	 * 查询指定HQL，并返回集合
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变的参数列表
	 * @return 集合
	 */
	public List<Object> find(String hql, Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查询指定HQL语句," + hql);
			}
			return getHibernateTemplate().find(hql, values);
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
			throw e;
		}
	}

	/**
	 * 按照HQL语句查询唯一对象.
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数集合
	 * @return OBJECT对象
	 */
	public Object findUnique(final String hql, final Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查询返回唯一结果的HQL语句," + hql);
			}
			return getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					Query query = createQuery(s, hql, values);
					return query.uniqueResult();
				}
			});
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
			throw e;
		}
	}

	/**
	 * 查找指定HQL并返回INT型
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数列表
	 * @return INT
	 */
	public int findInt(final String hql, final Object... values) {
		return 0;
	}

	/**
	 * 获取指定实体Class指定条件的记录总数
	 * 
	 * @param entityClass
	 *            实体Class
	 * @param where
	 *            HQL的查询条件,支持参数列表
	 * @param values
	 *            可变参数列表
	 * @return 记录总数
	 */
	public int findTotalCount(Class<T> entityClass, final String where,
			final Object... values) {
		String hql = "select count(e) from " + entityClass.getName() + " as e "
				+ where;
		return findInt(hql, values);
	}

	/**
	 * 获取指定实体Class的记录总数
	 * 
	 * @param entityClass
	 *            实体Class
	 * @return 记录总数
	 */
	public int findTotalCount(Class<T> entityClass) {
		return findTotalCount(entityClass, "");
	}

	/**
	 * 查找指定属性的实体集合
	 * 
	 * @param entityClass
	 *            实体
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 */
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找指定属性：" + propertyName + "为" + value + "的实体"
						+ entityClass.getName());
			}
			String queryStr = "from " + entityClass.getName()
					+ " as model where model." + propertyName + "=?";
			return getHibernateTemplate().find(queryStr, value);
		} catch (RuntimeException e) {
			logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);
			throw e;
		}
	}

	/**
	 * 查找指定subnetId，指定属性的实体集合，不重复
	 * 
	 * @param entityClass
	 *            实体
	 * @param subnetId
	 *            实体
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 */
	public List findByPropertySubnetId(Class<T> entityClass, Integer subnetId,
			String propertyName) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找指定属性：" + propertyName + "为" + "的实体"
						+ entityClass.getName());
			}
			String queryStr = "";
			if (subnetId.equals(-1)) {
				queryStr = "select distinct " + propertyName + " from "
						+ entityClass.getName();
				return getHibernateTemplate().find(queryStr);
			} else {
				queryStr = "select distinct " + propertyName + " from "
						+ entityClass.getName()
						+ " as model where model.subnet.subnetId=?";
				return getHibernateTemplate().find(queryStr, subnetId);
			}

		} catch (RuntimeException e) {
			logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);
			throw e;
		}
	}

	/**
	 * 模糊查询指定条件对象集合 <br>
	 * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br>
	 * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br>
	 * 
	 * @param entity
	 *            条件实体
	 * @return 结合
	 */
	public List<T> findByExample(T entity) {
		try {
			List<T> results = getHibernateTemplate().findByExample(entity);
			return results;
		} catch (RuntimeException re) {
			logger.error("查找指定条件实体集合异常", re);
			throw re;
		}
	}

	/**
	 * 补充方法(未测) 据说可以无视session的状态持久化对象
	 * 
	 * @param entity
	 *            实体类
	 * @return 持久后的实体类
	 */
	public T merge(T entity) {
		try {
			T result = (T) getHibernateTemplate().merge(entity);
			return result;
		} catch (RuntimeException re) {
			logger.error("merge异常", re);
			throw re;
		}
	}

	/**
	 * 清除实体的锁定状态<br>
	 * 方法未测
	 * 
	 * @param entity
	 *            实体
	 */
	public void attachClean(T entity) {
		try {
			getHibernateTemplate().lock(entity, LockMode.NONE);
		} catch (RuntimeException re) {
			logger.error("实体解锁异常", re);
			throw re;
		}
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            页面对象
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数列表
	 * @return 分页数据
	 */
	public Page<T> findByPage(final Page<T> page, final String hql,
			final Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找指定HQL分页数据," + hql);
			}
			return (Page<T>) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session s)
								throws HibernateException, SQLException {
							Query query = createQuery(s, hql, values);
							if (page.isFirstSetted()) {
								query.setFirstResult(page.getFirst());
							}
							if (page.isPageSizeSetted()) {
								query.setMaxResults(page.getPageSize());
							}
							page.setResult(query.list());
							if (logger.isDebugEnabled()) {
								logger.debug("查找指定HQL分页数据成功," + hql);
							}
							return page;
						}
					});
		} catch (RuntimeException e) {
			logger.error("分页查询异常，HQL：" + hql, e);
			throw e;
		}
	}

	/**
	 * 根据查询条件与参数列表创建Query对象
	 * 
	 * @param session
	 *            Hibernate会话
	 * @param hql
	 *            HQL语句
	 * @param objects
	 *            参数列表
	 * @return Query对象
	 */
	public Query createQuery(Session session, String hql, Object... objects) {
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	/**
	 * 通过多个属性组合查询
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应于propertyNames的值 return 匹配的结果
	 */
	public List<T> findByPropertys(Class<T> entityClass,
			String[] propertyNames, Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + entityClass.getName());
		strBuffer.append(" as model where ");
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			strBuffer.append("=");
			strBuffer.append("? ");
		}
		String queryString = strBuffer.toString();
		return this.getHibernateTemplate().find(queryString, values);
	}

	/**
	 * 通过多个属性统计数量
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应的属性值数组 return
	 */
	public int countByPropertys(Class<T> entityClass, String[] propertyNames,
			Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select count(*) from " + entityClass.getName());
		strBuffer.append(" as model where ");
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			strBuffer.append("=");
			strBuffer.append("? ");
		}

		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		List list = query.list();
		Long result = (Long) list.get(0);
		return result.intValue();
	}

	/**
	 * 查找T并通过某一属性排序
	 * 
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序,false为倒序
	 */
	public List<T> findAndOrderByProperty(Class<T> entityClass,
			int firstResult, int fetchSize, String propertyName,
			boolean isSequence) {
		String queryString = "from " + entityClass.getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}

		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(firstResult);
		queryObject.setMaxResults(fetchSize);
		return queryObject.list();

	}

	/**
	 * 查找所有并通过某个属性排序
	 * 
	 * @param propertyName
	 *            排序依据的属性名称
	 * @param isSequence
	 *            是否顺序排列
	 */
	public List<T> findAllAndOrderByProperty(Class<T> entityClass,
			String propertyName, boolean isSequence) {
		String queryString = "from " + entityClass.getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}

	public List findBySql(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public void updateBySql(String hql) {
		getHibernateTemplate().bulkUpdate(hql);
	}

}
