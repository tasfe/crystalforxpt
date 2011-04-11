package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.IpNetcenterDAO;
import com.combanc.itsm.pojo.IpAddress;
import com.combanc.itsm.pojo.IpNetcenter;

public class IpNetcenterService {
	private IpNetcenterDAO ipNetcenterDAO;


	public IpNetcenterDAO getIpNetcenterDAO() {
		return ipNetcenterDAO;
	}

	public void setIpNetcenterDAO(IpNetcenterDAO ipNetcenterDAO) {
		this.ipNetcenterDAO = ipNetcenterDAO;
	}

	public List<IpNetcenter> findAll() {
		return ipNetcenterDAO.findAll();
	}

	public void save(IpNetcenter ipNetcenter) {
		if (ipNetcenter != null) {
			ipNetcenterDAO.save(ipNetcenter);
		}
	}

	public void update(IpNetcenter ipNetcenter) {
		if (ipNetcenter != null) {
			ipNetcenterDAO.update(ipNetcenter);
		}
	}

	public void delete(Integer ipNetcenterId) {
		IpNetcenter ipNetcenter=null;
		if (ipNetcenterId != null) {
			ipNetcenter = (IpNetcenter) ipNetcenterDAO.findById(ipNetcenterId);
		}
		if (ipNetcenter != null) {
			ipNetcenterDAO.delete(ipNetcenter);
		}
	}
	
	public IpNetcenter findById(Integer ipNetcenterId){
		return (IpNetcenter)ipNetcenterDAO.findById(ipNetcenterId);
	}

	public PageBean query(IpNetcenter ipNetcenter, int pageSize, int page) {
		String sql = "from IpNetcenter as model where 1=1";
		if (ipNetcenter != null) {
			if(ipNetcenter.getName()!=null&&ipNetcenter.getName().trim().length()!=0){
				sql=sql+" and model.name= "+ipNetcenter.getName()+" ";
			}
			if(ipNetcenter.getType()!=null){
				sql=sql+" and model.type= "+ipNetcenter.getType()+" ";
			}
			if(ipNetcenter.getPhone()!=null&&ipNetcenter.getPhone().trim().length()!=0){
				sql=sql+" and model.phone= "+ipNetcenter.getPhone()+" ";
			}
			if(ipNetcenter.getEmail()!=null&&ipNetcenter.getEmail().trim().length()!=0){
				sql=sql+" and model.email= "+ipNetcenter.getEmail()+" ";
			}
		}
		int allRow = ipNetcenterDAO.getAllRowCount(sql);// 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		sql = sql + " order by model.id desc";
		List<IpAddress> list = ipNetcenterDAO.queryForPage(sql, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
}

