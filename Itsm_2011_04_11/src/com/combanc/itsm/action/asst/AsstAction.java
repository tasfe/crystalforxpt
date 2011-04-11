package com.combanc.itsm.action.asst;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.action.systemmanage.AssetsAction;
import com.combanc.itsm.pojo.SystemNotice;
import com.combanc.itsm.service.SystemNoticeService;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class AsstAction extends BaseActionSupport implements
ServletRequestAware {
	
	

	
	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(AsstAction.class);
	private static final long serialVersionUID = 1L;
	private SystemNoticeService systemNoticeService;
    private List<SystemNotice> systemNotices;
    private SystemNotice systemNotice;
    private String id;
    
    
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the systemNotice
	 */
	public SystemNotice getSystemNotice() {
		return systemNotice;
	}

	/**
	 * @param systemNotice the systemNotice to set
	 */
	public void setSystemNotice(SystemNotice systemNotice) {
		this.systemNotice = systemNotice;
	}

	/**
	 * @return the systemNotices
	 */
	public List<SystemNotice> getSystemNotices() {
		return systemNotices;
	}

	/**
	 * @param systemNotices the systemNotices to set
	 */
	public void setSystemNotices(List<SystemNotice> systemNotices) {
		this.systemNotices = systemNotices;
	}

	/**
	 * @return the systemNoticeService
	 */
	public SystemNoticeService getSystemNoticeService() {
		return systemNoticeService;
	}

	/**
	 * @param systemNoticeService the systemNoticeService to set
	 */
	public void setSystemNoticeService(SystemNoticeService systemNoticeService) {
		this.systemNoticeService = systemNoticeService;
	}
	
	@Action(description="用户查看当前公告")
	public String findNowNotices()
	{
		
		systemNotices= systemNoticeService.findAll();
		
		return SUCCESS;
		
		
	}
	@Action(description="用户查看历史公告")
	public String  fingHistoryNotices()
	{
		
		systemNotices =systemNoticeService.findAll();
		
		return SUCCESS;
	}
	
	public String noticeInfo()
	{
		
		if(id!=null)
		{
			try {
				Long idLong=Long.valueOf(id);
				systemNotice=systemNoticeService.getSystemNoticeById(idLong);
				return SUCCESS;
			} catch (NumberFormatException e) {
				this.addActionError("id is not right!");
				return ERROR;
			}
		}
		else{
			this.addActionError("id is null!");
			return ERROR;
			
		}
		
		
	}
	
	
	
	
	

}
