package com.combanc.itsm.service.addressbookmanage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.registry.infomodel.User;
import com.combanc.itsm.pojo.AddressBookManage;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.UserService;

public class ContactsTreeService {

	private AddressBookManageService addressBookManageService;
	private DepartmentService departmentService;
	private UserService userService;

	public AddressBookManageService getAddressBookManageService() {
		return addressBookManageService;
	}

	public void setAddressBookManageService(
			AddressBookManageService addressBookManageService) {
		this.addressBookManageService = addressBookManageService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<AddressBookManage> getDate(int userId, int type,
			int departmentId) {
		if (type == 1) {
			return addressBookManageService.getAddressBookByUsersId(userId);
		}
		if (type == 2) {
			return addressBookManageService
					.getAddressBookByDepartmentId(departmentId);
		} else {
			return null;
		}

	}

	public String getTreeString(User u) {
		
		List<Department> dpList = departmentService.findAll();
		StringBuffer sb = new StringBuffer("<script type=\"text/javascript\">");
		sb.append("d = new dTree('d');");

		sb.append("d.add(0,-1,'康邦科技','','','listFrame');"); // 添加根节点
		// sb.append("d.add(0,-1,'org')"); //添加根节点
		int index = 1;
		int parentid = 0;
		int userpid = 0;
		for (int i = 0; i < dpList.size(); i++) {
			Department dp = dpList.get(i);
			sb.append("d.add('" + (index) + "','" + parentid + "','"
					+ dp.getName() + "','" + "query.action?departmentId="
					+ dp.getId() + "&type=1" + "','','listFrame');");

			userpid = index;
			index++;
			Set<Users> uSet = dp.getUserses();
			Iterator<Users> iterator = uSet.iterator();

			while (iterator.hasNext()) {

				Users users = iterator.next();
				sb.append("d.add('" + (index++) + "','" + userpid + "','"
						+ users.getTruename() + "','" + "query.action?userId="
						+ users.getId() + "&type=2" + "','','listFrame');");

			}

		}

		sb.append("document.write(d);");
		sb.append("d.closeAll();");
		sb.append("</script>");
		return sb.toString();
	}
	/*public List<String> getTreeStr(){
		List<AddressBookManage> abmList = this.addressBookManageService.findAll();
		List<String> strList = new ArrayList<String>();
		for(AddressBookManage abm:abmList){
			String pingying = abm.getPinying();
			
		}
		return null;
	}*/
	public List<Department> getChildrenXml(int root) {

		List<Department> dpList = departmentService.findAllByPid(root);

		return dpList;
	}

}
