package com.combanc.itsm.action.systemmanage;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Authority;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AuthorityService;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.UserService;

import flex.messaging.io.ArrayList;

public class EngineerAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Roleteam engineer;
	private List<List<Roleteam>> engineerList;
	private String flag;
	private String actionURI;
	private Integer engineerId;
	private RoleteamService roleteamService;
	private List<Users> userList;
	private UserService userService;
	private String showUsernames;// 显示已经选中的用户的用户名
	private String memberIds;// 页面点击事件已经选中的用户ids
	private String idsList = ""; // 从数据库查到的已经选中的用户ids
	private List<Authority> authorityList;
	private AuthorityService authorityService;
	private Integer pid = 0;
	private String authorityIds;// 页面点击事件已经选中的权限ids
	private String authorityIdsList = ""; // 从数据库查到的已经选中的权限ids
	private int page;
	private PageBean pageBean;
	private int pageSize=10;
	private String leadUsernames;// 显示已经选中的用户的用户名
	private String teamlead;// 页面点击事件已经选中的用户ids
	private Users teamLeaderUsers;
	private int teamleaderId;
	
	
	public int getTeamleaderId() {
		return teamleaderId;
	}

	public void setTeamleaderId(int teamleaderId) {
		this.teamleaderId = teamleaderId;
	}

	public Users getTeamLeaderUsers() {
		return teamLeaderUsers;
	}

	public void setTeamLeaderUsers(Users teamLeaderUsers) {
		this.teamLeaderUsers = teamLeaderUsers;
	}

	public String getLeadUsernames() {
		return leadUsernames;
	}

	public void setLeadUsernames(String leadUsernames) {
		this.leadUsernames = leadUsernames;
	}

	public String getTeamlead() {
		return teamlead;
	}

	public void setTeamlead(String teamlead) {
		this.teamlead = teamlead;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public Integer getEngineerId() {
		return engineerId;
	}

	public void setEngineerId(Integer engineerId) {
		this.engineerId = engineerId;
	}

	public String list() throws Exception {
		
	
		pageBean = roleteamService.query(1,pageSize,page);
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		if (!memberIds.equals("")) {
			String[] s = memberIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Users u = new Users();
				u.setId((Integer) (Integer.parseInt(s[i])));
				engineer.getUserses().add(u);// 保存用户的信息
			}
		}
		if (!teamlead.equals("")) {
			String[] s = teamlead.split(",");
			for (int i = 0; i < s.length; i++) {
				engineer.setTeamLeader(Integer.valueOf(s[i]));
			}
		}

		if (authorityIds!=null&&!authorityIds.equals("")) {
			String[] s = authorityIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Authority authority = new Authority();
				authority.setId((Integer) (Integer.parseInt(s[i])));
				engineer.getAuthorities().add(authority);// 保存权限的信息
			}
		}

		roleteamService.save(engineer);
		return "list";
	}

	public String user() throws Exception {
		userList = new ArrayList();
		Roleteam sqlRoleteam=new Roleteam();
		sqlRoleteam.setType("0");
		sqlRoleteam.setRoleType(1);
		List<Roleteam> roleteam=roleteamService.query(sqlRoleteam);
		for(Roleteam t:roleteam)
		{
		   Set<Users> uSet=	t.getUserses();
		   for(Users u:uSet)
		   {
			   if(!userList.contains(u))
				   userList.add(u);
		   }
		}
		
		return "success";
	}

	public String authority() throws Exception {
		if (pid != 0) {
			authorityList = authorityService.findAllByPid(pid);
		} else {
			authorityList = authorityService.findAll();
		}
		return "success";
	}

	public String update() throws Exception {
		if (memberIds!=null&&!memberIds.equals("")) {
			String[] s = memberIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Users u = new Users();
				try {
					Integer integer = Integer.parseInt(s[i]);
					u.setId(integer);
					engineer.getUserses().add(u);// 保存用户的信息
				} catch (Exception e) {
				}
			}
		}
		if (!teamlead.equals("")) {
			String[] s = teamlead.split(",");
			for (int i = 0; i < s.length; i++) {
				engineer.setTeamLeader(Integer.valueOf(s[i]));
			}
		}
		if (authorityIds!=null&&!authorityIds.equals("")) {
			String[] s = authorityIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Authority authority = new Authority();
				try {
					Integer integer = Integer.parseInt(s[i]);
					authority.setId(integer);
					engineer.getAuthorities().add(authority);// 保存权限的信息
				} catch (Exception e) {
				}
			}
		}
		roleteamService.update(engineer);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		engineer = roleteamService.findById(engineerId);
		Set<Users> set = engineer.getUserses();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Users temp = (Users) it.next();
			idsList = idsList + temp.getId() + ","; // 得到从数据库查到的已经选中的用户ids
		}
		if(engineer.getTeamLeader()!=null)
		{
        teamleaderId=engineer.getTeamLeader();
//        if(teamleaderId>0)
//        {
//        	 Users teamLeaderUsers =userService.findUserById(teamleaderId);
//        	 leadUsernames=teamLeaderUsers.getUsername();
//        }
		}
        
        
//		Set<Authority> set2 = engineer.getAuthorities();
//		Iterator it2 = set2.iterator();
//		while (it2.hasNext()) {
//			Authority temp = (Authority) it2.next();
//			authorityIdsList = authorityIdsList + temp.getId() + ","; // 得到从数据库查到的已经选中的权限ids
//		}
		return "success";
	}

	public String delete() throws Exception {
		roleteamService.deleteById(engineerId);
		return "list";
	}

	public Roleteam getEngineer() {
		return engineer;
	}

	public void setEngineer(Roleteam engineer) {
		this.engineer = engineer;
	}

	public RoleteamService getEngineerService() {
		return roleteamService;
	}

	public void setEngineerService(RoleteamService engineerService) {
		this.roleteamService = engineerService;
	}

	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<List<Roleteam>> getEngineerList() {
		return engineerList;
	}

	public void setEngineerList(List<List<Roleteam>> engineerList) {
		this.engineerList = engineerList;
	}

	public String getShowUsernames() {
		return showUsernames;
	}

	public void setShowUsernames(String showUsernames) {
		this.showUsernames = showUsernames;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public String getIdsList() {
		return idsList;
	}

	public void setIdsList(String idsList) {
		this.idsList = idsList;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(String authorityIds) {
		this.authorityIds = authorityIds;
	}

	public String getAuthorityIdsList() {
		return authorityIdsList;
	}

	public void setAuthorityIdsList(String authorityIdsList) {
		this.authorityIdsList = authorityIdsList;
	}

}
