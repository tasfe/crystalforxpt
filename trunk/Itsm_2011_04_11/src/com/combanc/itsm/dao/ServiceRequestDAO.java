package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.service.ServiceRequestService;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceRequest entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.dao.ServiceRequest
 * @author MyEclipse Persistence Tools
 */
public class ServiceRequestDAO extends BaseHibernateDAO {

    private static final Log log = LogFactory.getLog(ServiceRequestDAO.class);
    public static final String REQUESTNO = "requestNo";

    protected void initDao() {
        // do nothing
    }

    public void save(ServiceRequest transientInstance) {
        log.debug("saving ServiceRequest instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ServiceRequest persistentInstance) {
        log.debug("deleting ServiceRequest instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(ServiceRequest serviceRequest) {
        log.debug("updating ServiceRequest instance");
        try {
            getHibernateTemplate().update(serviceRequest);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public ServiceRequest findById(java.lang.Integer id) {
        log.debug("getting ServiceRequest instance with id: " + id);
        try {
            ServiceRequest instance = (ServiceRequest) getHibernateTemplate().get("com.combanc.itsm.pojo.ServiceRequest", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ServiceRequestService> findByStateAndUserId(int state, int userId) {
        log.debug("getting ServiceRequest instance with userId and state: " + userId + " state " + state);
        try {
            String queryString = "from ServiceRequest as model where model.usersByEngineerId=" + userId + " and model.state=" + state;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByExample(ServiceRequest instance) {
        log.debug("finding ServiceRequest instance by example");
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

    public List findByProperties(final String queryString, final int offset,
            final int length) {
        log.debug("finding ServiceRequest instance with properties: ");
        try {
            Query query = getSession().createQuery(queryString);
            query.setFirstResult(offset);
            query.setMaxResults(length);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by properties name failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding ServiceRequest instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ServiceRequest as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByDepartment(String propertyName, Integer value) {
        log.debug("finding ServiceRequest instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ServiceRequest as model where model."
                    + propertyName + "=" + value;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByEngineerId(Integer usersByEngineerId) {
        log.debug("finding ServiceRequest instance with usersByEngineerId: "
                + ", usersByEngineerId: " + usersByEngineerId);
        try {
            String queryString = "from ServiceRequest as model where model.usersByEngineerId="
                    + usersByEngineerId;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByEngineerId2(Integer usersByEngineerId, Integer id) {
        log.debug("finding ServiceRequest instance with usersByEngineerId "
                + ", usersByEngineerId: " + usersByEngineerId + "and id is not:" + id);
        try {
            String queryString = "from ServiceRequest as model where model.usersByEngineerId="
                    + usersByEngineerId + " and model.id!=" + id;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all ServiceRequest instances");
        try {
            String queryString = "from ServiceRequest";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ServiceRequest merge(ServiceRequest detachedInstance) {
        log.debug("merging ServiceRequest instance");
        try {
            ServiceRequest result = (ServiceRequest) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ServiceRequest instance) {
        log.debug("attaching dirty ServiceRequest instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ServiceRequest instance) {
        log.debug("attaching clean ServiceRequest instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<ServiceRequest> query(ServiceRequest serviceRequest) {
        List<ServiceRequest> records = new java.util.ArrayList<ServiceRequest>();
        Session session = null;
        java.sql.ResultSet rs = null;
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        int size = 0;
        String sql = "select * from Service_Request where 1=1 ";
        if (serviceRequest.getRequestNo() != null
                && !serviceRequest.getRequestNo().equals("")) {
            sql = sql + "  and  request_no like '%"
                    + serviceRequest.getRequestNo() + "%'";
        }
        if (serviceRequest.getUsersByOperatorId() != null
                && !serviceRequest.getUsersByOperatorId().equals("")) {
            sql = sql + "  and  operatorID like '%"
                    + serviceRequest.getUsersByOperatorId() + "%'";
        }
        if (serviceRequest.getSummary() != null
                && !serviceRequest.getSummary().equals("")) {
            sql = sql + " and  summary='" + serviceRequest.getSummary() + "'";
        }
        if (serviceRequest.getServiceCategory() != null
                && serviceRequest.getServiceCategory().equals("")) {
            sql = sql + " and  categroyID='"
                    + serviceRequest.getServiceCategory() + "'";
        }
        if (serviceRequest.getDealEngineers() != null
                && serviceRequest.getDealEngineers().equals("")) {
            sql = sql + " and  deal_engineers'"
                    + serviceRequest.getDealEngineers() + "'";
        }
        if (serviceRequest.getSubmintTime() != null) {
            sql = sql + " and  submint_time='"
                    + serviceRequest.getSubmintTime() + "'";
        }

        System.out.println(sql);
        sql = sql + " order by code";

        try {
            // session = DaoMoudelApi.openSession();

            session = this.getSession(true);
            conn = session.connection();
            stmt = conn.createStatement(
                    java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
                    java.sql.ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ServiceRequest serviceRequest2 = new ServiceRequest();
                serviceRequest2.setRequestNo(REQUESTNO);
                // serviceRequest2.setUsersByOperatorId(usersByOperatorId);
                records.add(serviceRequest2);
                // size=rs.getInt("mycount");
            }
        } catch (Exception e) {
            log.debug(e);
        } finally {
            session.close();
        }
        return records;
    }

    public ServiceRequest findByRequstNo(java.lang.String requestNo) {

        List results = findByProperty(REQUESTNO, requestNo);
        if (results != null) {
            return (ServiceRequest) results.get(0);
        }
        return null;
    }

    public static ServiceRequestDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ServiceRequestDAO) ctx.getBean("ServiceRequestDAO");
    }

    public List findByusersByOperatorId(Integer usersByOperatorId) {
        log.debug("finding ServiceRequest instance with usersByEngineerId: "
                + ", usersByOperatorId: " + usersByOperatorId);
        try {
            String queryString = "from ServiceRequest as model where usersByOperatorId="
                    + usersByOperatorId;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByusersByusersByOriginatorId(Integer usersByOriginatorId) {
        log.debug("finding ServiceRequest instance with usersByEngineerId: "
                + ", usersByOriginatorId: " + usersByOriginatorId);
        try {
            String queryString = "from ServiceRequest as model where usersByOriginatorId="
                    + usersByOriginatorId;
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<ServiceRequest> findNotClosed() {
        String queryString = "from ServiceRequest as model where isFinished="
                + "1";
        return getHibernateTemplate().find(queryString);
    }
}