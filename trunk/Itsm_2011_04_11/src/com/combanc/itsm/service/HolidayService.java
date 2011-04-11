package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.HolidayDAO;
import com.combanc.itsm.pojo.Holiday;

public class HolidayService {

	private HolidayDAO holidayDAO;

	public HolidayDAO getHolidayDAO() {
		return holidayDAO;
	}

	public void setHolidayDAO(HolidayDAO holidayDAO) {
		this.holidayDAO = holidayDAO;
	}

	public List<Holiday> findAll() {
		return holidayDAO.findAll();
	}

	public void save(Holiday holiday) {
		holidayDAO.save(holiday);
	}

	public void saveOrUpdate(Holiday holiday) {
		holidayDAO.attachDirty(holiday);
	}

	public Holiday findById(Integer holidayId) {
		return (Holiday) holidayDAO.findById(holidayId);
	}

	public void update(Holiday holiday) {
		holidayDAO.update(holiday);
	}

	public void deleteById(Integer holidayId) {
		Holiday holiday = null;
		if (holidayId != null)
			holiday = (Holiday) holidayDAO.findById(holidayId);
		if (holiday != null)
			holidayDAO.delete(holiday);
	}

}
