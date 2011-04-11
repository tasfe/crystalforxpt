package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Report;
import com.combanc.itsm.service.ReportService;

public class ReportAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private Report report;
	private List reports;
	private String flag;
	private String actionURI;
	private Integer reportId;
	private ReportService reportService;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public List getReports() {
		return reports;
	}

	public void setReports(List reports) {
		this.reports = reports;
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

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public String list() throws Exception {

		reports = reportService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		reportService.save(report);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		Integer id = new Integer(Integer.parseInt(getRequest().getParameter(
				"reportId")));
		report = reportService.findById(id);
		return "success";
	}

	public String update() throws Exception {
		reportService.update(report);
		return "list";
	}

	public String delete() throws Exception {
		Integer id = new Integer(Integer.parseInt(getRequest().getParameter(
				"reportId")));
		reportService.deleteById(id);
		return "list";
	}

}
