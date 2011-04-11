package com.combanc.itsm.service.projectmanage;

import java.text.SimpleDateFormat;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.ProjectManageDAO;
import com.combanc.itsm.pojo.ProjectManage;
import com.combanc.itsm.pojo.SystemNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.StringUtil;

public class ProjectManageService {

	private ProjectManageDAO projectManageDAO;

	public ProjectManageDAO getProjectManageDAO() {
		
		return projectManageDAO;
	}

	public void setProjectManageDAO(ProjectManageDAO projectManageDAO) {
		this.projectManageDAO = projectManageDAO;
	}

	public void saveProject(ProjectManage project) {
		this.projectManageDAO.saveProject(project);
	}

	// 查
	public ProjectManage getProjectById(long id) {
		return this.projectManageDAO.getProjectById(id);
	}

	// 删
	public void deleteProject(long id) {
		this.projectManageDAO.deleteProject(id);
	}

	// 改;
	public void updateProject(ProjectManage project) {

		this.projectManageDAO.update(project);
	}

	// 分页显示
	@SuppressWarnings("unchecked")
	public PageBean queryProject(int pageSize, int page) {

		String hql = "from ProjectManage as bean where 1=1 ";
		hql = hql + " order by bean.id asc";// 查询语句
		int allRow = projectManageDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<SystemNotice> list = projectManageDAO.queryForPage(hql, offset,
				length); // "一页"的记录
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}
	
	//得到当前系统登录的用户;
	public String getProjectCreater(Users users) {
		
		String name = users.getTruename();
		String departmant = users.getDepartment().getName();
		String userTypeString = users.getUsertype();
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy年MM月dd日 E");
		//String date = DateFormat.format(Calendar.getInstance().getTime())
			//	.toString().substring(0, 12);
		String title = name + "("
				+ StringUtil.getUserTypeNameByUserType(userTypeString) + "--"
				+ departmant + ")";
				//+ date;

		return title;

	}

}
