package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.ReportDAO;
import com.combanc.itsm.pojo.Report;

public class ReportService {
	public ReportDAO reportDAO;

	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public List<Report> findAll() {
		return reportDAO.findAll();
	}

	public Report findById(Integer reportId) {
		return (Report) reportDAO.findById(reportId);
	}

	public void save(Report report) {
		reportDAO.save(report);
	}

	public void update(Report report) {
		reportDAO.update(report);
	}

	public void saveOrUpdate(Report report) {
		reportDAO.attachDirty(report);
	}

	public void deleteById(Integer reportId) {
		Report report = null;
		if (reportId != null)
			report = (Report) reportDAO.findById(reportId);
		if (report != null)
			reportDAO.delete(report);
	}
}
