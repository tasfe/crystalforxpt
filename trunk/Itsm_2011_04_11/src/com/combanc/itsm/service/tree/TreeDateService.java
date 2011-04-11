/**
 * 
 */
package com.combanc.itsm.service.tree;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.registry.infomodel.User;

import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.BuildlocationService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.ItsmTypeService;
import com.combanc.itsm.service.LocationService;
import com.combanc.itsm.service.ProducerService;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.worklog.WorkLogService;
import com.ibm.db2.jcc.a.s;

/**
 * @author Administrator
 * 
 */

public class TreeDateService {

	private WorkLogService workLogService;
	private DepartmentService departmentService;
	private BuildlocationService buildlocationService;
	private ItsmTypeService itsmTypeService;
	private AssetsStateService assetsStateService;
	private LocationService locationService;
	private ProducerService producerService;
	
	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}

	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public ProducerService getProducerService() {
		return producerService;
	}

	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	public ItsmTypeService getItsmTypeService() {
		return itsmTypeService;
	}

	public void setItsmTypeService(ItsmTypeService itsmTypeService) {
		this.itsmTypeService = itsmTypeService;
	}

	public BuildlocationService getBuildlocationService() {
		return buildlocationService;
	}

	public void setBuildlocationService(BuildlocationService buildlocationService) {
		this.buildlocationService = buildlocationService;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	private UserService userService;
	private AssetsTypeService assetsTypeService;

	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	private ServiceCategoryService serviceCategoryService;

	@Resource
	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService
	 *            the departmentService to set
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @return the workLogService
	 */
	public WorkLogService getWorkLogService() {
		return workLogService;
	}

	/**
	 * @param workLogService
	 *            the workLogService to set
	 */
	public void setWorkLogService(WorkLogService workLogService) {
		this.workLogService = workLogService;
	}

	public List<WorkLog> getDate(int userId, int type, int departmentId) {
		if (type == 1) {
			return workLogService.getLogsByUsersId(userId);
		}
		if (type == 2) {
			return workLogService.getLogsByDepartmentId(departmentId);
		} else {
			return null;
		}

	}

	public String getTreeString(User u) {
		List<Department> dpList = departmentService.findAll();
		StringBuffer sb = new StringBuffer("<script type=\"text/javascript\">");
		sb.append("d = new dTree('d');");

	///	sb.append("d.add(0,-1,'康邦科技','','','listFrame');"); // 添加根节点
		// sb.append("d.add(0,-1,'org')"); //添加根节点
		int index = 1;
		int parentid = 0;
		int userpid = 0;
		for (int i = 0; i < dpList.size(); i++) {
			Department dp = dpList.get(i);
			if(dp.getId()==1)		
			{				//d.add('1'-1,'0'-1,'部门','list.action?pid=1','','listFrame');

				sb.append("d.add(0,-1,'"+dp.getName()+"','','','listFrame');");
						continue;			
			}
			sb.append("d.add('" + (index) + "','" + parentid + "','"
					+ dp.getName() + "','"
					+ "list.action?workLogQurey.workLog.department.id="
					+ dp.getId() + "&type=1" + "','','listFrame');");

			userpid = index;
			index++;
			Set<Users> uSet = dp.getUserses();
			Iterator<Users> iterator = uSet.iterator();

			while (iterator.hasNext()) {

				Users users = iterator.next();
				if(users.getUsername().equals("admin"))
				{
					continue;
					 
				}
				 sb.append("d.add('" + (index++) + "','" + userpid + "','"
							+ users.getTruename() + "','"
							+ "list.action?workLogQurey.workLog.users.id="
							+ users.getId() + "&type=2" + "','','listFrame');");

			}

		}

		sb.append("document.write(d);");
		sb.append("d.closeAll();");
		sb.append("</script>");
		return sb.toString();
	}

	public List<Department> getChildrenXml(int root) {

		List<Department> dpList = departmentService.findAllByPid(root);

		return dpList;
	}

	public void getSubTeamListString(Department department, StringBuffer sb) {
		List<Department> list0 = departmentService.findAllByPid(department
				.getId());
		if (list0 != null && !list0.isEmpty()) {
			for (Department d : list0) {

				List<Department> list1 = departmentService.findAllByPid(d
						.getId());
				if (list1 != null && !list1.isEmpty()) {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
							+ d.getName()
							+ "';if(window.parent.document.getElementById('Layer1')){window.parent.document.getElementById('Layer1').style.visibility='hidden';};window.parent.document.getElementById('department.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"folder\">" + d.getName()
							+ "</span>");
					sb.append("<ul>");
					getSubTeamListString(d, sb);
					sb.append("</ul>");

				} else {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
							+ d.getName()
							+ "';if(window.parent.document.getElementById('Layer1')){window.parent.document.getElementById('Layer1').style.visibility='hidden';};window.parent.document.getElementById('department.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"file\">" + d.getName() + "</span>");
					sb.append("</li>");

				}

			}

		}

	}

	public String getTeamListString(int nodeId) {
		List<Department> list0;
		StringBuffer sb = new StringBuffer(
				"<ul id=\"browser\" class=\"filetree\">");
		list0 = departmentService.findAllByPid(nodeId);
		if (list0 != null && !list0.isEmpty()) {
			for (Department d : list0) {
				List<Department> list1 = departmentService.findAllByPid(d
						.getId());

				if (list1 != null && !list1.isEmpty()) {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
							+ d.getName()
							+ "';if(window.parent.document.getElementById('Layer1')){window.parent.document.getElementById('Layer1').style.visibility='hidden';};window.parent.document.getElementById('department.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"folder\">" + d.getName()
							+ "</span>");
					sb.append("<ul>");
					getSubTeamListString(d, sb);
					sb.append("</ul>");
				} else {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
							+ d.getName()
							+ "';if(window.parent.document.getElementById('Layer1')){window.parent.document.getElementById('Layer1').style.visibility='hidden';};window.parent.document.getElementById('department.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"file\">" + d.getName()
							+ "</font></span>");
					sb.append("</li>");

				}

			}

		}

		sb.append("</ul>");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	public String getServiceCategoryListString(int nodeId) {
		List<ServiceCategory> list0;
		StringBuffer sb = new StringBuffer(
				"<ul id=\"browser\" class=\"filetree\">");
		list0 = serviceCategoryService.findAllByPid(nodeId);
		if (list0 != null && !list0.isEmpty()) {
			for (ServiceCategory d : list0) {
				if(d.getType()!=1){//目前只取出事件类别
					continue;
				}
				List<ServiceCategory> list1 = serviceCategoryService
						.findAllByPid(d.getId());

				if (list1 != null && !list1.isEmpty()) {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('cat.name').value= '"
							+ d.getItemZh()
							+ "';if(window.parent.document.getElementById('Layer2')){window.parent.document.getElementById('Layer2').style.visibility='hidden';};"
							+"if(window.parent.document.getElementById('essential')){window.parent.document.getElementById('essential').options["+d.getDefEss()+"].selected=true;};" +
							"if(window.parent.document.getElementById('emergency')){window.parent.document.getElementById('emergency').options["+d.getDefEme()+"].selected=true;};" +
							"window.parent.document.getElementById('cat.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"folder\">" + d.getItemZh()
							+ "</span>");
					sb.append("<ul>");
					getSubServiceCategoryListString(d, sb);
					sb.append("</ul>");
				} else {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('cat.name').value= '"
							+ d.getItemZh()
							+ "';if(window.parent.document.getElementById('Layer2')){window.parent.document.getElementById('Layer2').style.visibility='hidden';};" +
									"if(window.parent.document.getElementById('essential')){window.parent.document.getElementById('essential').options["+d.getDefEss()+"].selected=true;};" +
											"if(window.parent.document.getElementById('emergency')){window.parent.document.getElementById('emergency').options["+d.getDefEme()+"].selected=true;};" +
											"window.parent.document.getElementById('cat.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"file\">" + d.getItemZh()
							+ "</font></span>");
					sb.append("</li>");

				}

			}

		}

		sb.append("</ul>");
		// System.out.println(sb.toString());
		return sb.toString();
	}

	public void getSubServiceCategoryListString(ServiceCategory department,
			StringBuffer sb) {
		List<ServiceCategory> list0 = serviceCategoryService
				.findAllByPid(department.getId());
		if (list0 != null && !list0.isEmpty()) {
			for (ServiceCategory d : list0) {
				if(d.getType()!=1){//目前只取出事件类别
					continue;
				}
				List<ServiceCategory> list1 = serviceCategoryService
						.findAllByPid(d.getId());
				if (list1 != null && !list1.isEmpty()) {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('cat.name').value= '"
							+ d.getItemZh()
							+ "';if(window.parent.document.getElementById('Layer2')){window.parent.document.getElementById('Layer2').style.visibility='hidden';};" +
									"if(window.parent.document.getElementById('essential')){window.parent.document.getElementById('essential').options["+d.getDefEss()+"].selected=true;};" +
											"if(window.parent.document.getElementById('emergency')){window.parent.document.getElementById('emergency').options["+d.getDefEme()+"].selected=true;};" +
											"window.parent.document.getElementById('cat.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"folder\">" + d.getItemZh()
							+ "</span>");
					sb.append("<ul>");
					getSubServiceCategoryListString(d, sb);
					sb.append("</ul>");

				} else {
					sb.append("<li>");
					sb.append("<input type=\"radio\" name=\"c\" value=\""
							+ d.getId()
							+ "\" onClick=\"window.parent.document.getElementById('cat.name').value= '"
							+ d.getItemZh()
							+ "';if(window.parent.document.getElementById('Layer2')){window.parent.document.getElementById('Layer2').style.visibility='hidden';};" +
									"if(window.parent.document.getElementById('essential')){window.parent.document.getElementById('essential').options["+d.getDefEss()+"].selected=true;};" +
											"if(window.parent.document.getElementById('emergency')){window.parent.document.getElementById('emergency').options["+d.getDefEme()+"].selected=true;};" +
											"window.parent.document.getElementById('cat.id').value='"
							+ d.getId() + "'\"/>");
					sb.append("<span class=\"file\">" + d.getItemZh()
							+ "</span>");
					sb.append("</li>");

				}

			}

		}

	}

	// public String getPersionDepartmentListString(int nodeId, int userType) {
	// List<Department> list0;
	// StringBuffer sb = new StringBuffer(
	// "<ul id=\"browser\" class=\"filetree\">");
	// list0 = departmentService.findAllByPid(nodeId);
	// if (list0 != null && !list0.isEmpty()) {
	// for (Department d : list0) {
	// List<Department> list1 = departmentService.findAllByPid(d
	// .getId());
	//
	// if (list1 != null && !list1.isEmpty()) {
	// sb.append("<li>");
	// sb.append("<input type=\"radio\" name=\"c\" value=\""
	// + d.getId()
	// +
	// "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
	// + d.getName()
	// + "';window.parent.document.getElementById('department.id').value='"
	// + d.getId() + "'\"/>");
	// sb.append("<span class=\"folder\">" + d.getName()
	// + "</span>");
	//
	// sb.append("<ul>");
	// getSubTeamListString(d, sb);
	// sb.append("</ul>");
	// } else {
	// sb.append("<li>");
	// sb.append("<input type=\"radio\" name=\"c\" value=\""
	// + d.getId()
	// +
	// "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
	// + d.getName()
	// + "';window.parent.document.getElementById('department.id').value='"
	// + d.getId() + "'\"/>");
	// sb.append("<span class=\"file\">" + d.getName()
	// + "</font></span>");
	// List userList = userService.getUsersByDepartmentID(d
	// .getId());
	// if (userList != null && !userList.isEmpty()) {
	// sb.append("<ul>");
	// sb.append("<li>");
	// sb.append("<input type=\"radio\" name=\"c\" value=\""
	// + d.getId()
	// +
	// "\" onClick=\"window.parent.document.getElementById('department.name').value= '"
	// + d.getName()
	// + "';window.parent.document.getElementById('department.id').value='"
	// + d.getId() + "'\"/>");
	// sb.append("</li>");
	// sb.append("</ul>");
	//
	// }
	//
	// sb.append("</li>");
	//
	// }
	//
	// }
	//
	// }
	//
	// sb.append("</ul>");
	//
	// return sb.toString();
	// }
}
