package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.ServiceCategoryDAO;
import com.combanc.itsm.pojo.ServiceCategory;

public class ServiceCategoryService {
	public static final Integer EVENT_TYPE = 1;
	public static final Integer PLANTASK_TYPE = 2;
	public static final Integer CHANGEREQUEST_TYPE = 3;
	public static final Integer PROJECT_TYPE = 4;
	public static final Integer PROJECTTASK_TYPE = 5;
	private ServiceCategoryDAO serviceCategoryDAO;

	public List<ServiceCategory> findAll() {
		return serviceCategoryDAO.findAll();
	}

	public ServiceCategory findById(Integer serviceCategoryId) {
		return serviceCategoryDAO.findById(serviceCategoryId);
	}

	public List<ServiceCategory> findAllByType(Integer type) {
		return serviceCategoryDAO.findByType(type);
	}

	public List<ServiceCategory> findAllByPid(Integer pid) {
		return serviceCategoryDAO.findByPid(pid);
	}

	public List<ServiceCategory> findAllByTypeAndPid(Integer type, Integer pid) {
		StringBuffer hql = new StringBuffer();
		hql.append("from ServiceCategory s where s.type = ");
		hql.append(type);
		hql.append(" and s.pid = ");
		hql.append(pid);
		hql.append(" order by s.id");
		return serviceCategoryDAO.findBySql(hql.toString());
	}

	public void save(ServiceCategory serviceCategory) {
		serviceCategoryDAO.save(serviceCategory);
		Integer parentId = serviceCategory.getPid();
		if (parentId != null && parentId != 0) {
			ServiceCategory parent = serviceCategoryDAO.findById(parentId);
			serviceCategory.setCode(parent.getCode() + "|"
					+ serviceCategory.getId() + "$");
		} else {
			serviceCategory.setCode("|" + serviceCategory.getId() + "$");
		}
		update(serviceCategory);
	}

	public void update(ServiceCategory serviceCategory) {
		serviceCategoryDAO.update(serviceCategory);
	}

	public void deleteById(Integer serviceCategoryId) {
		ServiceCategory serviceCategory = null;
		if (serviceCategoryId != null) {
			serviceCategory = serviceCategoryDAO.findById(serviceCategoryId);
		}
		if (serviceCategory != null) {
			String code = serviceCategory.getCode();
			List<ServiceCategory> list = serviceCategoryDAO
					.findBySql("from ServiceCategory s where s.code like '%"
							+ code + "%'");
			for (ServiceCategory s : list) {
				serviceCategoryDAO.delete(s);
			}
		}
	}

	public ServiceCategoryDAO getServiceCategoryDAO() {
		return serviceCategoryDAO;
	}

	public void setServiceCategoryDAO(ServiceCategoryDAO serviceCategoryDAO) {
		this.serviceCategoryDAO = serviceCategoryDAO;
	}

}
