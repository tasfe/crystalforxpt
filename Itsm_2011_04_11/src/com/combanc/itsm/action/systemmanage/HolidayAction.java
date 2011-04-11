package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Holiday;
import com.combanc.itsm.service.HolidayService;

public class HolidayAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Holiday holiday;
	private List holidays;
	private String flag;
	private String actionURI;
	private Integer holidayId;
	private HolidayService holidayService;

	public List getHolidays() {
		return holidays;
	}

	public void setHolidays(List holidays) {
		this.holidays = holidays;
	}

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	public HolidayService getHolidayService() {
		return holidayService;
	}

	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public String list() throws Exception {
		holidays = holidayService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		holidayService.save(holiday);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		holiday = holidayService.findById(holidayId);
		return "success";
	}

	public String update() throws Exception {
		holidayService.update(holiday);
		return "list";
	}

	public String delete() throws Exception {
		holidayService.deleteById(holidayId);
		return "list";
	}

}
