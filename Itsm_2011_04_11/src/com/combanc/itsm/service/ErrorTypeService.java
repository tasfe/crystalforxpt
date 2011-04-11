package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.ErrorTypeDAO;
import com.combanc.itsm.pojo.ErrorType;

public class ErrorTypeService {
	private ErrorTypeDAO errorTypeDAO;

	public List<ErrorType> findAll() {
		return errorTypeDAO.findAll();
	}

	public ErrorType findById(Integer errorTypeId) {
		return errorTypeDAO.findById(errorTypeId);
	}

	public void save(ErrorType errorType) {
		errorTypeDAO.save(errorType);
	}

	public void update(ErrorType errorType) {
		errorTypeDAO.update(errorType);
	}

	public void saveOrUpdate(ErrorType errorType) {
		errorTypeDAO.saveOrUpdate(errorType);
	}

	public void deleteById(Integer errorTypeId) {
		ErrorType errorType = null;
		if (errorTypeId != null)
			errorType = errorTypeDAO.findById(errorTypeId);
		if (errorType != null)
			errorTypeDAO.delete(errorType);
	}

	public ErrorTypeDAO getErrorTypeDAO() {
		return errorTypeDAO;
	}

	public void setErrorTypeDAO(ErrorTypeDAO errorTypeDAO) {
		this.errorTypeDAO = errorTypeDAO;
	}

}
