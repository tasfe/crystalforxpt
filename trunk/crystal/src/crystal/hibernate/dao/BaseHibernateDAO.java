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
 * DAO��������<br>
 * ��DAO��ʵ����ͨ�õ����ݲ���
 * 
 * @author ����
 * 
 * @param <T>
 *            POJOʵ�����
 * @param <ID>
 *            ID
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDAO<T, ID extends Serializable> extends
		HibernateDaoSupport {

	private static final Logger logger = Logger
			.getLogger(BaseHibernateDAO.class);

	/**
	 * ����ָ��ʵ����
	 * 
	 * @param entityobj
	 *            ʵ����
	 */
	public void save(T entity) {
		try {
			getHibernateTemplate().save(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("����ʵ����ɹ�," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("����ʵ���쳣," + entity.getClass().getName(), e);
			throw e;
		}
	}

	/**
	 * ����
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * ɾ��ָ��ʵ��
	 * 
	 * @param entityobj
	 *            ʵ����
	 */
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("ɾ��ʵ����ɹ�," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("ɾ��ʵ���쳣", e);
			throw e;
		}
	}

	/**
	 * ��ȡ����ʵ�弯��
	 * 
	 * @param entityClass
	 *            ʵ��
	 * @return ����
	 */
	public List<T> findAll(Class<T> entityClass) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��������ʵ�壺" + entityClass.getName());
			}
			return getHibernateTemplate().find("from " + entityClass.getName());
		} catch (RuntimeException e) {
			logger.error("����ָ��ʵ�弯���쳣��ʵ�壺" + entityClass.getName(), e);
			throw e;
		}
	}
	
	/**
	 * ��ȡ����ʵ�弯�ϵ�ǰn����¼
	 * 
	 * @param entityClass
	 *            ʵ��
	 * @return ����
	 */
	public List<T> findTopN(Class<T> entityClass, int topN) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("����topNʵ�壺" + entityClass.getName());
			}
			String queryString = "from " + entityClass.getName() + " order by id";
			Session ssn = getSession();   
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			logger.error("����ָ��ʵ�弯��topN�쳣��ʵ�壺" + entityClass.getName(), e);
			throw e;
		}
	}

	/**
	 * ���»򱣴�ָ��ʵ��
	 * 
	 * @param entity
	 *            ʵ����
	 */
	public void saveOrUpdate(T entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("���»��߱���ʵ��ɹ�," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("���»򱣴�ʵ���쳣", e);
			throw e;
		}
	}

	/**
	 * ����ָ��IDʵ�������
	 * 
	 * @param entityClass
	 *            ʵ��Class
	 * @param id
	 *            ʵ��ID
	 * @return ʵ�����
	 */
	public T findById(Class<T> entityClass, ID id) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ����IDΪ" + id + "��ʵ�壺" + entityClass.getName());
			}
			return (T) getHibernateTemplate().get(entityClass, id);
		} catch (RuntimeException e) {
			logger.error("����ָ��IDʵ���쳣��ID��" + id, e);
			throw e;
		}
	}

	/**
	 * ��ѯָ��HQL�������ؼ���
	 * 
	 * @param hql
	 *            HQL���
	 * @param values
	 *            �ɱ�Ĳ����б�
	 * @return ����
	 */
	public List<Object> find(String hql, Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ��ѯָ��HQL���," + hql);
			}
			return getHibernateTemplate().find(hql, values);
		} catch (RuntimeException e) {
			logger.error("��ѯָ��HQL�쳣��HQL��" + hql, e);
			throw e;
		}
	}

	/**
	 * ����HQL����ѯΨһ����.
	 * 
	 * @param hql
	 *            HQL���
	 * @param values
	 *            �ɱ��������
	 * @return OBJECT����
	 */
	public Object findUnique(final String hql, final Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ��ѯ����Ψһ�����HQL���," + hql);
			}
			return getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					Query query = createQuery(s, hql, values);
					return query.uniqueResult();
				}
			});
		} catch (RuntimeException e) {
			logger.error("��ѯָ��HQL�쳣��HQL��" + hql, e);
			throw e;
		}
	}

	/**
	 * ����ָ��HQL������INT��
	 * 
	 * @param hql
	 *            HQL���
	 * @param values
	 *            �ɱ�����б�
	 * @return INT
	 */
	public int findInt(final String hql, final Object... values) {
		return 0;
	}

	/**
	 * ��ȡָ��ʵ��Classָ�������ļ�¼����
	 * 
	 * @param entityClass
	 *            ʵ��Class
	 * @param where
	 *            HQL�Ĳ�ѯ����,֧�ֲ����б�
	 * @param values
	 *            �ɱ�����б�
	 * @return ��¼����
	 */
	public int findTotalCount(Class<T> entityClass, final String where,
			final Object... values) {
		String hql = "select count(e) from " + entityClass.getName() + " as e "
				+ where;
		return findInt(hql, values);
	}

	/**
	 * ��ȡָ��ʵ��Class�ļ�¼����
	 * 
	 * @param entityClass
	 *            ʵ��Class
	 * @return ��¼����
	 */
	public int findTotalCount(Class<T> entityClass) {
		return findTotalCount(entityClass, "");
	}

	/**
	 * ����ָ�����Ե�ʵ�弯��
	 * 
	 * @param entityClass
	 *            ʵ��
	 * @param propertyName
	 *            ������
	 * @param value
	 *            ����
	 * @return ʵ�弯��
	 */
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ����ָ�����ԣ�" + propertyName + "Ϊ" + value + "��ʵ��"
						+ entityClass.getName());
			}
			String queryStr = "from " + entityClass.getName()
					+ " as model where model." + propertyName + "=?";
			return getHibernateTemplate().find(queryStr, value);
		} catch (RuntimeException e) {
			logger.error("����ָ������ʵ�弯���쳣��������" + propertyName, e);
			throw e;
		}
	}

	/**
	 * ����ָ��subnetId��ָ�����Ե�ʵ�弯�ϣ����ظ�
	 * 
	 * @param entityClass
	 *            ʵ��
	 * @param subnetId
	 *            ʵ��
	 * @param propertyName
	 *            ������
	 * @param value
	 *            ����
	 * @return ʵ�弯��
	 */
	public List findByPropertySubnetId(Class<T> entityClass, Integer subnetId,
			String propertyName) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ����ָ�����ԣ�" + propertyName + "Ϊ" + "��ʵ��"
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
			logger.error("����ָ������ʵ�弯���쳣��������" + propertyName, e);
			throw e;
		}
	}

	/**
	 * ģ����ѯָ���������󼯺� <br>
	 * �÷�������ʵ����һ���յ�T������Ҫ��ѯĳ���ֶΣ���set���ֶε�����Ȼ����ñ�����<br>
	 * ȱ�㣺Ŀǰ����ò��ֻ��֧��String��ģ����ѯ����Ȼ�а취��д����û��Ҫ��������HQL<br>
	 * 
	 * @param entity
	 *            ����ʵ��
	 * @return ���
	 */
	public List<T> findByExample(T entity) {
		try {
			List<T> results = getHibernateTemplate().findByExample(entity);
			return results;
		} catch (RuntimeException re) {
			logger.error("����ָ������ʵ�弯���쳣", re);
			throw re;
		}
	}

	/**
	 * ���䷽��(δ��) ��˵��������session��״̬�־û�����
	 * 
	 * @param entity
	 *            ʵ����
	 * @return �־ú��ʵ����
	 */
	public T merge(T entity) {
		try {
			T result = (T) getHibernateTemplate().merge(entity);
			return result;
		} catch (RuntimeException re) {
			logger.error("merge�쳣", re);
			throw re;
		}
	}

	/**
	 * ���ʵ�������״̬<br>
	 * ����δ��
	 * 
	 * @param entity
	 *            ʵ��
	 */
	public void attachClean(T entity) {
		try {
			getHibernateTemplate().lock(entity, LockMode.NONE);
		} catch (RuntimeException re) {
			logger.error("ʵ������쳣", re);
			throw re;
		}
	}

	/**
	 * ��HQL��ҳ��ѯ.
	 * 
	 * @param page
	 *            ҳ�����
	 * @param hql
	 *            HQL���
	 * @param values
	 *            �ɱ�����б�
	 * @return ��ҳ����
	 */
	public Page<T> findByPage(final Page<T> page, final String hql,
			final Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("��ʼ����ָ��HQL��ҳ����," + hql);
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
								logger.debug("����ָ��HQL��ҳ���ݳɹ�," + hql);
							}
							return page;
						}
					});
		} catch (RuntimeException e) {
			logger.error("��ҳ��ѯ�쳣��HQL��" + hql, e);
			throw e;
		}
	}

	/**
	 * ���ݲ�ѯ����������б���Query����
	 * 
	 * @param session
	 *            Hibernate�Ự
	 * @param hql
	 *            HQL���
	 * @param objects
	 *            �����б�
	 * @return Query����
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
	 * ͨ�����������ϲ�ѯ
	 * 
	 * @param propertyNames
	 *            ������������
	 * @param values
	 *            ��Ӧ��propertyNames��ֵ return ƥ��Ľ��
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
	 * ͨ���������ͳ������
	 * 
	 * @param propertyNames
	 *            ������������
	 * @param values
	 *            ��Ӧ������ֵ���� return
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
	 * ����T��ͨ��ĳһ��������
	 * 
	 * @param property
	 *            �������ݵ�˳��
	 * @param isSequence
	 *            �Ƿ�˳������,falseΪ����
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
	 * �������в�ͨ��ĳ����������
	 * 
	 * @param propertyName
	 *            �������ݵ���������
	 * @param isSequence
	 *            �Ƿ�˳������
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
