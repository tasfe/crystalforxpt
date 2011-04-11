package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.DomainRegisterDAO;
import com.combanc.itsm.pojo.DomainRegister;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.UserTypeUtil;

public class DomainRegisterService {
	private DomainRegisterDAO domainRegisterDAO;
	private DomainRegister domainRegister;

	public DomainRegister getDomainRegister() {
		return domainRegister;
	}

	public void setDomainRegister(DomainRegister domainRegister) {
		this.domainRegister = domainRegister;
	}

	public DomainRegisterDAO getDomainRegisterDAO() {
		return domainRegisterDAO;
	}

	public void setDomainRegisterDAO(DomainRegisterDAO domainRegisterDAO) {
		this.domainRegisterDAO = domainRegisterDAO;
	}

	public List<DomainRegister> findAll() {
		return domainRegisterDAO.findAll();
	}

	public void save(DomainRegister domainRegister) {
		if (domainRegister != null) {
			domainRegisterDAO.save(domainRegister);
		}
	}

	public void update(DomainRegister domainRegister) {
		if (domainRegister != null) {
			domainRegisterDAO.update(domainRegister);
		}
	}

	public void delete(Integer domainRegisterId) {
		DomainRegister domainRegister = null;
		if (domainRegisterId != null) {
			domainRegister = (DomainRegister) domainRegisterDAO
					.findById(domainRegisterId);
		}
		if (domainRegister != null) {
			domainRegisterDAO.delete(domainRegister);
		}
	}

	public DomainRegister findById(Integer domainRegisterId) {
		return (DomainRegister) domainRegisterDAO.findById(domainRegisterId);
	}

	public PageBean query(DomainRegister domainRegister, int pageSize,
			int page, Users u) {
		String sql = "from DomainRegister as model where 1=1";
		if (u.getUsertype().equals(UserTypeUtil.USER)) {
			sql = sql + " and model.sumbitUsers.id=" + u.getId();
		}

		if (domainRegister != null) {
			if (domainRegister.getSerialNumber() != null
					&& domainRegister.getSerialNumber().trim().length() != 0) {
				sql = sql + " and  model.serialNumber like '%"
						+ domainRegister.getSerialNumber() + "%'";

			}
			if (domainRegister.getDomain() != null
					&& domainRegister.getDomain().trim().length() != 0) {
				sql = sql + " and  model.domain like '%"
						+ domainRegister.getDomain() + "%'";

			}
			if (domainRegister.getServerLocation() != null
					&& domainRegister.getServerLocation().trim().length() != 0) {
				sql = sql + " and  model.serverLocation like '%"
						+ domainRegister.getServerLocation() + "%'";

			}
			if (domainRegister.getUnitsFullName() != null
					&& domainRegister.getUnitsFullName().trim().length() != 0) {
				sql = sql + " and  model.unitsFullName like '%"
						+ domainRegister.getUnitsFullName() + "%'";

			}
			if (domainRegister.getUnitsAddress() != null
					&& domainRegister.getUnitsAddress().trim().length() != 0) {
				sql = sql + " and  model.unitsAddress like '%"
						+ domainRegister.getUnitsAddress() + "%'";

			}
			// if (Domain.getTechnicalContact() != null
			// && Domain.getTechnicalContact().trim().length() != 0) {
			// sql = sql + " and model.technical_contact="
			// + Domain.getTechnicalContact() + " ";
			// }
			if (domainRegister.getPhone() != null
					&& domainRegister.getPhone().trim().length() != 0) {
				sql = sql + " and  model.phone like '%"
						+ domainRegister.getPhone() + "%'";
			}
			if (domainRegister.getUnitsLeader() != null
					&& domainRegister.getUnitsLeader().trim().length() != 0) {
				sql = sql + " and  model.unitsLeader like '%"
						+ domainRegister.getUnitsLeader() + "%'";
			}
			if (domainRegister.getApplication() != null
					&& domainRegister.getApplication().trim().length() != 0) {
				sql = sql + " and  model.application like '%"
						+ domainRegister.getApplication() + "%'";
			}
		}
		int allRow = domainRegisterDAO.getAllRowCount(sql);// 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		sql = sql + " order by model.id desc";
		List<DomainRegister> list = domainRegisterDAO.queryForPage(sql, offset,
				length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	// 文件导出
	public List<DomainRegister> querys(DomainRegister domainRegister) {

		return domainRegisterDAO.queryByHqls(domainRegister);
	}

	public boolean isExistDomain(String domain) {
		List list = new ArrayList();
		list = domainRegisterDAO.findByDomain(domain);
		if (list.isEmpty()) {
			return true;
		} else {
			return false;
		}
		// return true;
	}

	// public DomainRegister findByDomain(String domain) {
	// List list = new ArrayList();
	// list = domainRegisterDAO.findByDomain(domain);
	// if (list.isEmpty())
	// return null;
	// else
	// return (DomainRegister) list.get(0);
	// }
}
