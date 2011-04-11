package com.combanc.itsm.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ServiceTranDAO;
import com.combanc.itsm.pojo.ServiceTran;

public class ServiceTranService extends BaseService<ServiceTran, Integer> {

	private ServiceTranDAO serviceTranDAO;

	public ServiceTranDAO getServiceTranDAO() {
		return serviceTranDAO;
	}

	public void setServiceTranDAO(ServiceTranDAO serviceTranDAO) {
		this.serviceTranDAO = serviceTranDAO;
	}

	public List<ServiceTran> findAll() {
		return serviceTranDAO.findAll();
	}

	public List<ServiceTran> findAllById(Integer id) {
		return (List<ServiceTran>) serviceTranDAO.findById(id);
	}

	public ServiceTran findServiceTranById(Integer serviceTranId) {
		return (ServiceTran) serviceTranDAO.findById(serviceTranId);
	}

	public void save(ServiceTran serviceTran) {
		serviceTranDAO.save(serviceTran);
	}

	public void update(ServiceTran serviceTran) {
		serviceTranDAO.update(serviceTran);
	}

	public void saveOrUpdate(ServiceTran serviceTran) {
		serviceTranDAO.attachDirty(serviceTran);
	}

	public void deleteById(Integer serviceTranId) {
		ServiceTran serviceTran = null;
		if (serviceTranId != null)
			serviceTran = (ServiceTran) serviceTranDAO.findById(serviceTranId);
		if (serviceTran != null)
			serviceTranDAO.delete(serviceTran);
	}

	public List<ServiceTran> getServiceTransByRequestNo(String requestNo) {
		return serviceTranDAO.findByRequNo(requestNo);
	}
	public List findByExample(ServiceTran instance) {		
		List results = serviceTranDAO.findByExample(instance);
		return results;
	}
	
	public ServiceTran findById(java.lang.Integer id) {
			ServiceTran instance = serviceTranDAO.findById(id);
			return instance;		
	}
	
	public List<ServiceTran> findAllByrequNo(String requNo) {
		String queryString;
		Session session = null;
		try {
			session = serviceTranDAO.getHibernateTemplate().getSessionFactory().getCurrentSession();
			// queryString = "from ServiceTran model where model.id="+ "1"
			// +"order by model.operatorTime";
			queryString = "from ServiceTran model where model.requNo='" + requNo
					+ "' order by model.operatorTime";

			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {

			throw re;
		} 
	}
}
