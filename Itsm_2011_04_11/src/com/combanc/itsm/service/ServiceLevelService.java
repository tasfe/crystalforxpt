/**
 * 
 */
package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.ServiceLevelDAO;

/**
 * @author Administrator
 * 
 */
public class ServiceLevelService {

	private ServiceLevelDAO serviceLevelDAO;

	/**
	 * @return the serviceLevelDAO
	 */
	public ServiceLevelDAO getServiceLevelDAO() {
		return serviceLevelDAO;
	}

	/**
	 * @param serviceLevelDAO
	 *            the serviceLevelDAO to set
	 */
	public void setServiceLevelDAO(ServiceLevelDAO serviceLevelDAO) {
		this.serviceLevelDAO = serviceLevelDAO;
	}

	public boolean saveServiceLevel(ServiceLevelService serviceLevelService) {

		if (serviceLevelService != null) {
			
			try {
				serviceLevelDAO.save(serviceLevelService);
			} catch (Exception e) {
			
				e.printStackTrace();
				return false;
			}

			return true;
		} else {

			return false;
		}

	}
	public ServiceLevelService getLevelServiceByRequestNo(String requestNo)
	{
		
		if(requestNo!=null&&!requestNo.equals(""))
		
		{
			List instanlist=serviceLevelDAO.findByRequestNo(requestNo);
			if(instanlist!=null&&!instanlist.isEmpty())
			{
				return (ServiceLevelService) instanlist.get(0);
			}else{
				return null;
			}
		}
		return null;
		}

	public List findByDepartId(Object departId) {
		return serviceLevelDAO.findByDepartId(departId);
	}
}
