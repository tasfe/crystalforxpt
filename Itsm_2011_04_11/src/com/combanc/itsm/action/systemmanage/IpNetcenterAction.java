package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.IpNetcenter;
import com.combanc.itsm.service.IpNetcenterService;

public class IpNetcenterAction  extends BaseActionSupport implements 
ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IpNetcenterService ipNetcenterService;
	private Integer ipNetcenterId;
	private IpNetcenter ipNetcenter;
	private List<IpNetcenter> ipNetcenters;
	
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	

	public String list(){
		this.pageBean= ipNetcenterService.query(ipNetcenter, pageSize, page);
		return "success";
	}
	
	public String delete(){
	    ipNetcenterService.delete(ipNetcenterId);
		return "success";
	}
	
	public String save(){
		ipNetcenterService.save(ipNetcenter);
		return "success";
	}
	
	public String saveInput(){
		return "success";
	}
	
	public String updateInput(){
		ipNetcenter=ipNetcenterService.findById(ipNetcenterId);
		return "success";
	}
	
	public String update(){
		ipNetcenter=ipNetcenterService.findById(ipNetcenterId);
		ipNetcenterService.update(ipNetcenter);
		return "list";
	}
	
	public IpNetcenterService getIpNetcenterService() {
		return ipNetcenterService;
	}

	public void setIpNetcenterService(IpNetcenterService ipNetcenterService) {
		this.ipNetcenterService = ipNetcenterService;
	}

	public Integer getIpNetcenterId() {
		return ipNetcenterId;
	}

	public void setIpNetcenterId(Integer ipNetcenterId) {
		this.ipNetcenterId = ipNetcenterId;
	}

	public IpNetcenter getIpNetcenter() {
		return ipNetcenter;
	}

	public void setIpNetcenter(IpNetcenter ipNetcenter) {
		this.ipNetcenter = ipNetcenter;
	}

	public List<IpNetcenter> getIpNetcenters() {
		return ipNetcenters;
	}

	public void setIpNetcenters(List<IpNetcenter> ipNetcenters) {
		this.ipNetcenters = ipNetcenters;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
