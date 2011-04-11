package com.combanc.monitor.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorKeyInterface;

public class MonitorKeyInterfaceDAO extends BaseHibernateDAO<MonitorKeyInterface, Integer> {
	private static final Log log = LogFactory.getLog(MonitorKeyInterfaceDAO.class);
	// property constants

	public static final String INTERFACE_NUM = "interfaceNum";
	public static final String IS_MONITOR = "isMonitor";
	public static final String INTERFACE_DESC = "interfaceDesc";
	public static final String NOTE = "note";
	protected void initDao() {
		// do nothing
	}

}
