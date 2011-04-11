package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.RoleteamDAO;
import com.combanc.itsm.pojo.Roleteam;

public class RoleteamService extends BaseService<Roleteam, Integer> {
	public static final String ROLE_TYPE = "0";
	public static final String TEAM_TYPE = "1";
	private RoleteamDAO roleteamDAO;

	public Roleteam findRoleteamWithUsersById(Integer roleteamId) {
		return roleteamDAO.findRoleteamWithUsersById(roleteamId);
	}

	public Roleteam findById(Integer id) {
		return roleteamDAO.findById(id);
	}

	public List<Roleteam> findAllByType(String type) {
		return roleteamDAO
				.findBySql("from Roleteam s where s.id!=1 and s.type=" + type
						+ " order by s.useFor asc");
	}
	public List findAllByType1(String type) {
		return roleteamDAO
		.findBySql("from Roleteam s where s.id!=1 and s.type=" + type
				+ " order by s.useFor asc");
}
	public List findByLocaton(Integer locationId) {
		return roleteamDAO.findByLocationID(locationId);
	}

	public List findAll() {
		return roleteamDAO.findAll();
	}

	public void save(Roleteam roleteam) {

		roleteamDAO.save(roleteam);
	}

	public void update(Roleteam roleteam) {
		roleteamDAO.update(roleteam);
	}

	public void saveOrUpdate(Roleteam roleteam) {
		roleteamDAO.saveOrUpdate(roleteam);
	}

	public void deleteById(Integer roleteamId) {
		Roleteam roleteam = null;
		if (roleteamId != null)
			roleteam = roleteamDAO.findById(roleteamId);
		if (roleteam != null)
			roleteamDAO.delete(roleteam);
	}

	public RoleteamDAO getRoleteamDAO() {
		return roleteamDAO;
	}

	public void setRoleteamDAO(RoleteamDAO roleteamDAO) {
		this.roleteamDAO = roleteamDAO;
	}

	public List<List<Roleteam>> findAllRoleByType(String type) {
		List<Roleteam> allRoleteam = roleteamDAO
				.findBySql("from Roleteam s where s.id!=1 and s.type=" + type
						+ " order by s.useFor asc");
		return sortAllWithUseFor(allRoleteam);
	}

	public List<List<Roleteam>> findAllTeamByTypeOrderbyLocationId(String type) {
		List<Roleteam> allRoleteam = roleteamDAO
				.findBySql("from Roleteam s where s.id!=1 and s.type=" + type
						+ " order by s.location.id asc");
		return sortAllWithLocationId(allRoleteam);
	}

	private List<List<Roleteam>> sortAllWithLocationId(
			List<Roleteam> allRoleteam) {
		List<List<Roleteam>> roleteamList = new ArrayList<List<Roleteam>>();
		List<Integer> locationIdList = roleteamDAO
				.findBySql("select distinct s.location.id from Roleteam s where s.location.id is not null order by s.location.id");
		for (Integer i : locationIdList) {
			List<Roleteam> list = new ArrayList<Roleteam>();
			for (Iterator<Roleteam> it = allRoleteam.iterator(); it.hasNext();) {
				Roleteam s = it.next();
				if (i.equals(s.getLocation().getId())) {
					list.add(s);
				}
			}
			roleteamList.add(list);
		}
		return roleteamList;
	}

	private List<List<Roleteam>> sortAllWithUseFor(List<Roleteam> allRoleteam) {
		List<List<Roleteam>> roleteamList = new ArrayList<List<Roleteam>>();
		List<Integer> useForList = roleteamDAO
				.findBySql("select distinct s.useFor from Roleteam s where s.useFor is not null order by s.useFor");
		for (Integer i : useForList) {
			List<Roleteam> list = new ArrayList<Roleteam>();
			for (Iterator<Roleteam> it = allRoleteam.iterator(); it.hasNext();) {
				Roleteam s = it.next();
				if (i.equals(s.getUseFor())) {
					list.add(s);
				}
			}
			roleteamList.add(list);
		}
		return roleteamList;
	}

	public PageBean query(int type, int pageSize, int page) {

		String sql = "from Roleteam s where s.type=" + type;

		int allRow = roleteamDAO.getAllRowCount(sql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		System.out.println(sql);
		sql = sql + " order by s.location.id asc";
		List<Roleteam> list = roleteamDAO.queryForPage(sql, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public List<Roleteam> query(Roleteam team) {

		String sql = "from Roleteam as model where 1=1";

		if (team != null) {
			if (team.getId() != null) {
				sql = sql + " and model.id=" + team.getId();

			}
			if (team.getType() != null) {
				sql = sql + " and model.type=" + team.getType();

			}
			if (team.getRoleType() >= 0) {
				sql = sql + " and model.roleType=" + team.getRoleType();
			}
			List rList = roleteamDAO.getHibernateTemplate().find(sql);

			return rList;
		} else {
			return null;
		}
	}
}
