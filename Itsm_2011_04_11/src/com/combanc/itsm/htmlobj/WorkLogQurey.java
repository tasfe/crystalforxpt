/**
 * 
 */
package com.combanc.itsm.htmlobj;

import java.sql.Date;

import com.combanc.itsm.pojo.WorkLog;

/**
 * @author Administrator
 * 
 */
public class WorkLogQurey  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8724189630025345799L;
	private Date startDate;
	private Date endDate;
	private WorkLog workLog;
	
	
	/**
	 * @return the workLog
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}
	/**
	 * @param workLog the workLog to set
	 */
	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
