package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.IpAddressDAO;
import com.combanc.itsm.pojo.IpAddress;
import com.combanc.itsm.pojo.Schedule;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.UserTypeUtil;

public class IpAddressService {
	private IpAddressDAO ipAddressDAO;

	public IpAddressDAO getIpAddressDAO() {
		return ipAddressDAO;
	}

	public void setIpAddressDAO(IpAddressDAO ipAddressDAO) {
		this.ipAddressDAO = ipAddressDAO;
	}

	public List<IpAddress> findAll() {
		return ipAddressDAO.findAll();
	}

	public void save(IpAddress ipAddress) {
		if (ipAddress != null) {
			ipAddressDAO.save(ipAddress);
		}
	}

	public void update(IpAddress ipAddress) {
		if (ipAddress != null) {
			ipAddressDAO.update(ipAddress);
		}
	}

	public void delete(Integer ipAddressId) {
		IpAddress ipAddress = null;
		if (ipAddressId != null) {
			ipAddress = (IpAddress) ipAddressDAO.findById(ipAddressId);
		}
		if (ipAddress != null) {
			ipAddressDAO.delete(ipAddress);
		}
	}

	public IpAddress findById(Integer ipAddressId) {
		return (IpAddress) ipAddressDAO.findById(ipAddressId);
	}

	// 文件导出
	public List<IpAddress> querys(IpAddress ipAddress) {

		return ipAddressDAO.queryByHqls(ipAddress);
	}

	public PageBean query(IpAddress ipAddress, int pageSize, int page,
			Users user) {
		String sql = "from IpAddress as model where 1=1";
		if (user.getUsertype().equals(UserTypeUtil.USER)) {
			sql = sql + " and model.sumbitUsers.id=" + user.getId();
		}
		if (ipAddress != null) {
			if (ipAddress.getSerialNumber() != null
					&& ipAddress.getSerialNumber().trim().length() != 0) {
				sql = sql + " and  model.serialNumber like '%"
						+ ipAddress.getSerialNumber() + "%'";

			}
			if (ipAddress.getIpUseRoom() != null
					&& ipAddress.getIpUseRoom().trim().length() != 0) {
				sql = sql + " and  model.ipUseRoom like '%"
						+ ipAddress.getIpUseRoom() + "%'";

			}
			if (ipAddress.getUnitsFullName() != null
					&& ipAddress.getUnitsFullName().trim().length() != 0) {
				sql = sql + " and  model.unitsFullName like '%"
						+ ipAddress.getUnitsFullName() + "%'";

			}
			// if (ipAddress.getTechnicalContact() != null
			// && ipAddress.getTechnicalContact().trim().length() != 0) {
			// sql = sql + " and model.technical_contact="
			// + ipAddress.getTechnicalContact() + " ";
			// }
			if (ipAddress.getPhone() != null
					&& ipAddress.getPhone().trim().length() != 0) {
				sql = sql + " and  model.phone like '%" + ipAddress.getPhone()
						+ "%'";
			}
			if (ipAddress.getUnitsLeader() != null
					&& ipAddress.getUnitsLeader().trim().length() != 0) {
				sql = sql + " and  model.unitsLeader like '%"
						+ ipAddress.getUnitsLeader() + "%'";
			}
			if (ipAddress.getApplication() != null
					&& ipAddress.getApplication().trim().length() != 0) {
				sql = sql + " and  model.application like '%"
						+ ipAddress.getApplication() + "%'";
			}
		}
		int allRow = ipAddressDAO.getAllRowCount(sql);// 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		sql = sql + " order by model.id desc";
		List<IpAddress> list = ipAddressDAO.queryForPage(sql, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public PageBean query(IpAddress ipAddress, int pageSize, int page) {
		String sql = "from IpAddress as model where 1=1";
		if (ipAddress != null) {
			if (ipAddress.getSerialNumber() != null
					&& ipAddress.getSerialNumber().trim().length() != 0) {
				sql = sql + " and  model.serialNumber like '%"
						+ ipAddress.getSerialNumber() + "%'";

			}
			if (ipAddress.getIpUseRoom() != null
					&& ipAddress.getIpUseRoom().trim().length() != 0) {
				sql = sql + " and  model.ipUseRoom like '%"
						+ ipAddress.getIpUseRoom() + "%'";

			}
			if (ipAddress.getUnitsFullName() != null
					&& ipAddress.getUnitsFullName().trim().length() != 0) {
				sql = sql + " and  model.unitsFullName like '%"
						+ ipAddress.getUnitsFullName() + "%'";

			}
			// if (ipAddress.getTechnicalContact() != null
			// && ipAddress.getTechnicalContact().trim().length() != 0) {
			// sql = sql + " and model.technical_contact="
			// + ipAddress.getTechnicalContact() + " ";
			// }
			if (ipAddress.getPhone() != null
					&& ipAddress.getPhone().trim().length() != 0) {
				sql = sql + " and  model.phone like '%" + ipAddress.getPhone()
						+ "%'";
			}
			if (ipAddress.getUnitsLeader() != null
					&& ipAddress.getUnitsLeader().trim().length() != 0) {
				sql = sql + " and  model.unitsLeader like '%"
						+ ipAddress.getUnitsLeader() + "%'";
			}
			if (ipAddress.getApplication() != null
					&& ipAddress.getApplication().trim().length() != 0) {
				sql = sql + " and  model.application like '%"
						+ ipAddress.getApplication() + "%'";
			}
		}
		int allRow = ipAddressDAO.getAllRowCount(sql);// 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		sql = sql + " order by model.id desc";
		List<IpAddress> list = ipAddressDAO.queryForPage(sql, offset, length);

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
