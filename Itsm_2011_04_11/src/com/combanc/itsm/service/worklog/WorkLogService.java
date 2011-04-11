/**
 * 
 */
package com.combanc.itsm.service.worklog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.lang.DRLParser.and_constr_return;
import org.springframework.transaction.annotation.Transactional;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.UserDAO;
import com.combanc.itsm.dao.WorkLogDAO;
import com.combanc.itsm.htmlobj.WorkLogQurey;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;
import com.combanc.itsm.util.StringUtil;

/**
 * @author Administrator
 * 
 */
@Transactional
public class WorkLogService {

	private static final Log logger = LogFactory.getLog(WorkLogService.class);

	private WorkLogDAO workLogDAO;
	private UserDAO userDAO;

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 *            the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the workLogDAO
	 */
	public WorkLogDAO getWorkLogDAO() {
		return workLogDAO;
	}

	/**
	 * @param workLogDAO
	 *            the workLogDAO to set
	 */
	public void setWorkLogDAO(WorkLogDAO workLogDAO) {
		this.workLogDAO = workLogDAO;
	}

	public boolean save(WorkLog workLog) {

		if (workLog != null) {
			workLogDAO.save(workLog);
			return true;
		} else {
			return false;
		}
	}

	public void update(WorkLog workLog) {

		workLogDAO.update(workLog);
	}

	public boolean deleteById(int id) {
		WorkLog workLog = workLogDAO.findById(id);
		if (workLog != null) {

			workLogDAO.delete(workLog);
			return true;
		} else {

			return false;
		}
	}

	public WorkLog getWorkLogById(int id) {
		if (id > 0) {
			WorkLog workLog = workLogDAO.findById(id);

			return workLog;

		} else {

			return null;
		}
	}

	public PageBean query(Users users, WorkLogQurey workLog, int pageSize,
			int page, int departmentid) {

		String sql = "from WorkLog as model where 1=1 and model.users.id !="+users.getId();
		if (!users.getUsertype().equals("itmanager")) {
			// 查询自己的日志

			if (workLog != null) {

				if (workLog.getWorkLog() != null) {
					if (workLog.getWorkLog().getDepartment() != null
							&& workLog.getWorkLog().getDepartment().getId() != null
							&& workLog.getWorkLog().getDepartment().getId() >= 0) {
						sql = sql + " and model.department.id="
								+ workLog.getWorkLog().getDepartment().getId();
					}
					if (workLog.getWorkLog().getLocation() != null
							&& workLog.getWorkLog().getLocation().getId() != null
							&& workLog.getWorkLog().getLocation().getId() >= 0) {
						sql = sql + " and model.location.id="
								+ workLog.getWorkLog().getLocation().getId();
					}
					if (workLog.getWorkLog().getTime() != null) {
						sql = sql + " and  model.time like '%"
								+ workLog.getWorkLog().getTime() + "%'";
					}

					if (workLog.getWorkLog().getUsers() != null
							&& workLog.getWorkLog().getUsers().getId() != null) {
						sql = sql + " and  model.users.id="
								+ workLog.getWorkLog().getUsers().getId();
					}

				}

				if (workLog.getEndDate() != null
						&& workLog.getStartDate() != null) {
					sql = sql + " and  model.timesumbit<='"
							+ workLog.getEndDate() + "'"
							+ " and  model.timesumbit>='"
							+ workLog.getStartDate() + "'";
				}
				if (workLog.getWorkLog() != null
						&& workLog.getWorkLog().getTitle() != null
						&& !workLog.getWorkLog().getTitle().equals("")) {
					sql = sql + " and model.title like '%"
							+ workLog.getWorkLog().getTitle() + "%'";
				}

			}

			sql = sql
					+ " and model.id in( select id from WorkLog as model where 1=1";

			//sql = sql + " and model.users.id=" + users.getId();

			sql = sql + " or (model.department.id="
					+ users.getDepartment().getId() + " and model.type=2" + ")";

			sql = sql + " or (model.type=3))";

		} else {
			if (workLog != null) {

				if (workLog.getWorkLog() != null) {
					if (workLog.getWorkLog().getDepartment() != null
							&& workLog.getWorkLog().getDepartment().getId() != null
							&& workLog.getWorkLog().getDepartment().getId() >= 0) {
						sql = sql + " and model.department.id="
								+ workLog.getWorkLog().getDepartment().getId();
					}
					if (workLog.getWorkLog().getLocation() != null
							&& workLog.getWorkLog().getLocation().getId() != null
							&& workLog.getWorkLog().getLocation().getId() >= 0) {
						sql = sql + " and model.location.id="
								+ workLog.getWorkLog().getLocation().getId();
					}
					if (workLog.getWorkLog().getTime() != null) {
						sql = sql + " and  model.time like '%"
								+ workLog.getWorkLog().getTime() + "%'";
					}

					if (workLog.getWorkLog().getUsers() != null
							&& workLog.getWorkLog().getUsers().getId() >= 0) {
						sql = sql + " and  model.users.id="
								+ workLog.getWorkLog().getUsers().getId();
					}

				}

				if (workLog.getEndDate() != null
						&& workLog.getStartDate() != null) {
					sql = sql + " and  model.timesumbit<='"
							+ workLog.getEndDate() + "'"
							+ " and  model.timesumbit>='"
							+ workLog.getStartDate() + "'";
				}
				if (workLog.getWorkLog() != null
						&& workLog.getWorkLog().getTitle() != null
						&& !workLog.getWorkLog().getTitle().equals("")) {
					sql = sql + " and model.title like '%"
							+ workLog.getWorkLog().getTitle() + "%'";
				}

			}
		}

		if (departmentid > 0) {
			sql = sql + " and model.department.id=" + departmentid;
		}
		/*
		 * if (workLog.getWorkLog().getUsers().getId() != null) { sql = sql +
		 * " and  model.users.id=" + workLog.getWorkLog().getUsers().getId(); }
		 */
		 sql=sql+" and model.type!=1";
		sql = sql + " order by model.timesumbit desc";

		int allRow = workLogDAO.getAllRowCount(sql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);

		List<ServiceRequest> list = workLogDAO
				.queryForPage(sql, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public String getTitle(Users users) {
		String name = users.getTruename();
		String departmant = users.getDepartment().getName();
		String userTypeString = users.getUsertype();
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMdd E");
		String date = DateFormat.format(Calendar.getInstance().getTime())
				.toString();
//		String title = name + "("
//				+ StringUtil.getUserTypeNameByUserType(userTypeString) + "--"
//				+ departmant + ")" + date;
	String title = name +"（"+ date+")";

		return title;

	}

	public List<WorkLog> getLogsByDepartmentId(int departmentId) {
		return workLogDAO.findByGroupId(departmentId);
	}

	public List<WorkLog> getLogsByUsersId(int userId) {
		return workLogDAO.findByUserId(userId);
	}

	public List<WorkLog> findByLocation(Integer id) {
		return workLogDAO.findByLocation(id);
	}

	/**
	 * @return
	 */
	public List getHotUsers() {

		return userDAO.getTopByNumWorkLog();

	}

	/**
	 * @return
	 */
	public List getHotLogs() {

		return workLogDAO.getTopByNumWordPress();

	}

	public void updateNumWorkLog(Users u)

	{
		Users sUser = userDAO.findById(u.getId());
		if(sUser.getAdd1()==null)
		{
			sUser.setAdd1(1);
		}
		else{
			sUser.setAdd1(sUser.getAdd1() + 1);
		}
		
		userDAO.update(sUser);
	}

	public PageBean myquery(Users users, WorkLogQurey workLog, int pageSize,
			int page, Integer departmentid) {

            
		String sql = "from WorkLog as model where 1=1";

		sql = sql + " and model.users=" + users.getId();
		if (workLog != null) {

			if (workLog.getWorkLog() != null) {

				if (workLog.getWorkLog().getTime() != null) {
					sql = sql + " and  model.time like '%"
							+ workLog.getWorkLog().getTime() + "%'";
				}
				if(workLog.getWorkLog().getType()>0)
				{
					sql = sql +" and model.type="+workLog.getWorkLog().getType();
				}

			}

			if (workLog.getEndDate() != null && workLog.getStartDate() != null) {
				sql = sql + " and  model.timesumbit<='" + workLog.getEndDate()
						+ "'" + " and  model.timesumbit>='"
						+ workLog.getStartDate() + "'";
			}
			if (workLog.getWorkLog() != null
					&& workLog.getWorkLog().getTitle() != null
					&& !workLog.getWorkLog().getTitle().equals("")) {
				sql = sql + " and model.title like '%"
						+ workLog.getWorkLog().getTitle() + "%'";
			}

		}
       
		sql = sql + " order by model.timesumbit desc";

		int allRow = workLogDAO.getAllRowCount(sql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);

		List<ServiceRequest> list = workLogDAO
				.queryForPage(sql, offset, length);

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
