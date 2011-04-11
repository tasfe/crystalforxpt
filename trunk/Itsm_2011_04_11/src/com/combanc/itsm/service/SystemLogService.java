package com.combanc.itsm.service;

import com.combanc.itsm.dao.SystemLogDAO;
import com.combanc.itsm.pojo.SystemLog;

public class SystemLogService {

	private SystemLogDAO systemLogDAO;

	public SystemLogDAO getSystemLogDAO() {
		return systemLogDAO;
	}

	public void setSystemLogDAO(SystemLogDAO systemLogDAO) {
		this.systemLogDAO = systemLogDAO;
	}

	public void save(SystemLog systemLog) {
		systemLogDAO.save(systemLog);
	}
}
