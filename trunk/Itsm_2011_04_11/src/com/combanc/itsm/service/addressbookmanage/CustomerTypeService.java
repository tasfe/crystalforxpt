package com.combanc.itsm.service.addressbookmanage;

import com.combanc.itsm.dao.CustomerTypeDAO;
import com.combanc.itsm.pojo.CustomerType;

public class CustomerTypeService {
	
	private CustomerTypeDAO customerTypeDAO;

	public CustomerTypeDAO getCustomerTypeDAO() {
		return customerTypeDAO;
	}

	public void setCustomerTypeDAO(CustomerTypeDAO customerTypeDAO) {
		this.customerTypeDAO = customerTypeDAO;
	}
	
	public CustomerType findCustomerTypeById(Integer customertypeId) {
		return customerTypeDAO.findById(CustomerType.class,
				customertypeId);
	}

/*	public Department findDepartmentById(Integer departmentId) {
		return (Department) departmentDAO.findById(Department.class,
				departmentId);
	}*/
}
