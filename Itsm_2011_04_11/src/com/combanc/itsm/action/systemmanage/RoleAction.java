package com.combanc.itsm.action.systemmanage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.snmp4j.security.PrivAES;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Menu;
import com.combanc.itsm.pojo.Privilege;
import com.combanc.itsm.pojo.RoleGroup;
import com.combanc.itsm.pojo.RoleTable;
import com.combanc.itsm.pojo.RoleTablePrivilege;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.MenuService;
import com.combanc.itsm.service.PrivilegeService;
import com.combanc.itsm.service.RoleGroupService;
import com.combanc.itsm.service.RoleTablePrivilegeService;
import com.combanc.itsm.service.RoleTableService;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.util.UserTypeUtil;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Roleteam role;
	private List<RoleTable> roleTables;
	private List<List<Roleteam>> roleList;
	private List<Roleteam> roles;
	private List<RoleGroup> roleGroups;
	private String actionURI;
	private Integer roleId;
	private RoleteamService roleteamService;
	private UserService userService;
	private RoleGroupService roleGroupService;
	private RoleTablePrivilegeService roleTablePrivilegeService;
	private String roleTableNames;
	private RoleTableService roleTableService;
	private String roleTablePrivi;
	private ServiceRequestService serviceRequestService;
	private List<Users> userList;
	private String showUsernames;// 显示已经选中的用户的用户名
	private String memberIds;// 页面点击事件已经选中的用户ids
	private String idsList = ""; // 从数据库查到的已经选中的用户ids
	private String namesList="";
	private MenuService menuService;
	private List<Menu> menuList;
	private List<Privilege> privilegeList;
	private PrivilegeService privilegeService;
	private String[] MenuID;
	private Map privilegeMap;
	private Map menuMap;
	private int roleType;
	private String groupname;
	private List list;
	public String getRoleTablePrivi() {
		return roleTablePrivi;
	}

	public void setRoleTablePrivi(String roleTablePrivi) {
		this.roleTablePrivi = roleTablePrivi;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public MenuService getMenuService() {
		return menuService;
	}
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	public List<Menu> getMenuList() {
		return menuList;
	}
	
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public String main() throws Exception {
		roleGroups=roleGroupService.findAll();
		roles = roleteamService.findAllByType("0");
		/*
		if(roles!=null&&roles.size()>0){
			try{
				Cookie cookie=new Cookie("Role.LastRoleCode",roles.get(0).getId()+"$"+roles.get(0).getRoleType());
				ActionContext ctx = ActionContext.getContext();
				HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
				response.addCookie(cookie);
			}catch (Exception e) {
				System.out.println("set cookie failed!");
			}
		}
		*/
		return "success";
	}
	
	public String basic() throws Exception {
		actionURI = "updateForPri";
		if(roleId!=null){
			role = roleteamService.findById(roleId);
			if(null==role){
				return "success";
			}
			Set<Users> set = role.getUserses();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Users temp = (Users) it.next();
				idsList = idsList + temp.getId() + ",";
				namesList=namesList+temp.getUsername()+",";
			}
			if(role.getRoleGroup()!=0){
				groupname=this.roleGroupService.findById(role.getRoleGroup())==null?"":this.roleGroupService.findById(role.getRoleGroup()).getName();
			}
			roleTables=roleTableService.getAll();
			if(roleTables!=null&&roleTables.size()>0){
				roleTableNames="";
				for(RoleTable roleTable:roleTables){
					roleTableNames+=String.valueOf(roleTable.getId());
					roleTableNames+="~";
				}
			}
			if(roleTableNames.lastIndexOf("~")>0){
				roleTableNames=roleTableNames.substring(0, roleTableNames.length()-1);
			}
			List<RoleTablePrivilege> roleList=this.roleTablePrivilegeService.getByRoleId(roleId);
			if(null!=roleList&&roleList.size()>0){
				roleTablePrivi="";
				for(RoleTablePrivilege pri:roleList){
					roleTablePrivi+=pri.getPrivilege().toString();
					roleTablePrivi+="_";
					roleTablePrivi+=pri.getTableId().toString();
					roleTablePrivi+="~";
				}
			}
			if(roleTablePrivi!=null&&!roleTablePrivi.equals("")&&roleTablePrivi.lastIndexOf("~")>0){
				roleTablePrivi=roleTablePrivi.substring(0, roleTablePrivi.length()-1);
			}
		}
		return "success";
	}
	
	public Map buttonMap(Menu menu) {
		Map map=new LinkedHashMap();
		String[] array=menu.getPrivField().split(",");
		for(String str:array){
			String[] subArray=str.split(":");
			if(subArray.length>=2) map.put(subArray[0], subArray[1]);
		}
		return map;
	}
	
	public String menu() throws Exception {
		//List menuList=menuService.findByType("1");  //menu List
		List menuList=menuService.findByTypeAndRoleType("1",String.valueOf(roleType) );  //menu List
		Map<Menu,Map<Menu,List>> map=new LinkedHashMap();
		for(int i=0;i<menuList.size();i++){
			Menu menu=(Menu) menuList.get(i);
			if(menu.getPrivField()!=null&&!menu.getPrivField().equals(""))
				menu.setButtonMap(buttonMap(menu));
			map.put(menu, null);
		}
		Iterator key=map.keySet().iterator();
		while(key.hasNext()){
			Menu keyMenu=(Menu) key.next();
			Map<Menu,List> subMap=new LinkedHashMap<Menu,List>();
			List listType2=menuService.findByParentId(keyMenu.getId());
			for(int i=0;i<listType2.size();i++){
				Menu temp=(Menu) listType2.get(i);
				if(temp.getRoleType().indexOf(String.valueOf(roleType))<0){
					continue;
				}
				if(temp.getPrivField()!=null&&!temp.getPrivField().equals(""))
					temp.setButtonMap(buttonMap(temp));
				List listType3=menuService.findByParentId(temp.getId());
				for(int j=0;j<listType3.size();j++) {
					Menu me=(Menu) listType3.get(j);
					if(me.getRoleType().indexOf(String.valueOf(roleType))<0){
						continue;
					}
					if(me.getPrivField()!=null&&!me.getPrivField().equals(""))
						me.setButtonMap(buttonMap(me));
				}
				subMap.put(temp, listType3);					
			}
			map.put(keyMenu, subMap);
		}
		this.setMenuMap(map);
		
//		menuList = menuService.getAll();
//		for(int i=0;i<menuList.size();i++){
//			Menu menu=menuList.get(i);
//			if(menu.getPrivField()!=null&&!menu.getPrivField().equals("")) {
//				Map map=new LinkedHashMap();
//				String[] array=menu.getPrivField().split(",");
//				for(String str:array){
//					String[] subArray=str.split(":");
//					if(subArray.length>=2) map.put(subArray[0], subArray[1]);
//				}
//				menu.setButtonMap(map);
//			}			
//		}
		
		if(roleId!=null){
			privilegeList=privilegeService.findByOwenr(roleId);
			Map mapTemp=new HashMap();
			for(int i=0;i<privilegeList.size();i++){
				Privilege temp=privilegeList.get(i);				
				if(mapTemp.containsKey(temp.getMid())){
					String s=(String) mapTemp.get(temp.getMid());
					s=s+","+temp.getCode();
					mapTemp.put(temp.getMid(), s);
				}else {
					String s=temp.getMid()+":"+temp.getCode();
					mapTemp.put(temp.getMid(), s);
				}
			}
			this.setPrivilegeMap(mapTemp);
		}
		return "success";
	}
	
	public String savePrivilege() throws Exception {
		if(roleId==0||MenuID.length==0) return "success";
		privilegeList=privilegeService.findByOwenr(roleId);
		for(Privilege pri:privilegeList) {
			privilegeService.delete(pri);
		}
		for(String str:MenuID){
			String[] array=str.split(":");
			generatorPrivilege(array[0],"browse");
			if(array.length<2) continue;
			String[] subArray=array[1].split(",");
			for(String s:subArray) {
				if(s!=null&&!s.equals("")) {
					generatorPrivilege(array[0],s);
				}
			}			
		}
		return "success";
	}
	
	public Privilege generatorPrivilege(String mid,String code){
		Privilege pri=new Privilege();
		pri.setOwnertype("R");
		pri.setOwenr(roleId);
		pri.setPrivtype("menu");
		pri.setMid(mid);
		pri.setCode(code);
		pri.setValue("1");
		privilegeService.save(pri);
		return pri;
	}
	

	public String list() throws Exception {
		list=roleteamService.findAllByType1("0");
		return "success";
	}
	public String forUserSelect() throws Exception{
		roles = roleteamService.findAllByType("0");
		return "forUserSelect";
	}
	public String addInput() throws Exception {
		roleTables=roleTableService.getAll();
		if(roleTables!=null&&roleTables.size()>0){
			roleTableNames="";
			for(RoleTable roleTable:roleTables){
				roleTableNames+=String.valueOf(roleTable.getId());
				roleTableNames+="~";
			}
		}
		if(roleTableNames.lastIndexOf("~")>0){
			roleTableNames=roleTableNames.substring(0, roleTableNames.length()-1);
		}
		roleTablePrivi="";
		actionURI = "save";
		return "success";
	}

	public String user() throws Exception {
		userList = userService.findAllUser();
		return "success";
	}

	public String save() throws Exception {
		if (!memberIds.equals("")) {
			String[] s = memberIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Users u = new Users();
				u.setId((Integer) (Integer.parseInt(s[i])));
				role.getUserses().add(u);
			}
		}
		role.setRoleTypeName(UserTypeUtil.getCHStringByInt(role.getRoleType()));
		roleteamService.save(role);
		if(null!=roleTablePrivi&&!roleTablePrivi.equals("")){
			String [] strings=roleTablePrivi.split("~");
			if(null!=strings&&strings.length>0){
				for(String string:strings){
					String[] pris=string.split("_");
					if(null!=pris&&pris.length>0){
						if(pris.length==2){
							RoleTablePrivilege roleTablePrivilege=new RoleTablePrivilege();
							roleTablePrivilege.setPrivilege(Integer.valueOf(pris[0]));
							roleTablePrivilege.setTableId(Integer.valueOf(pris[1]));
							roleTablePrivilege.setRoleId(role.getId());
							this.roleTablePrivilegeService.save(roleTablePrivilege);
						}
					}
				}
			}
		}
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		roleTables=roleTableService.getAll();
		if(roleTables!=null&&roleTables.size()>0){
			roleTableNames="";
			for(RoleTable roleTable:roleTables){
				roleTableNames+=String.valueOf(roleTable.getId());
				roleTableNames+="~";
			}
		}
		if(roleTableNames.lastIndexOf("~")>0){
			roleTableNames=roleTableNames.substring(0, roleTableNames.length()-1);
		}
		role = roleteamService.findById(roleId);
		Set<Users> set = role.getUserses();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Users temp = (Users) it.next();
			idsList = idsList + temp.getId() + ",";
			namesList=namesList+temp.getUsername()+",";
		}
		if(role.getRoleGroup()!=0){
			groupname=this.roleGroupService.findById(role.getRoleGroup())==null?"":this.roleGroupService.findById(role.getRoleGroup()).getName();
		}
		List<RoleTablePrivilege> roleList=this.roleTablePrivilegeService.getByRoleId(roleId);
		if(null!=roleList&&roleList.size()>0){
			roleTablePrivi="";
			for(RoleTablePrivilege pri:roleList){
				roleTablePrivi+=pri.getPrivilege().toString();
				roleTablePrivi+="_";
				roleTablePrivi+=pri.getTableId().toString();
				roleTablePrivi+="~";
			}
		}
		if(roleTablePrivi!=null&&!roleTablePrivi.equals("")&&roleTablePrivi.lastIndexOf("~")>0){
			roleTablePrivi=roleTablePrivi.substring(0, roleTablePrivi.length()-1);
		}
		return "success";
	}

	public String update() throws Exception {
		if (!memberIds.equals("")) {
			String[] s = memberIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Users u = new Users();
				u.setId((Integer) (Integer.parseInt(s[i])));
				role.getUserses().add(u);
			}
		}
		Roleteam roleteam =roleteamService.findById(role.getId());
		
		roleteam.setUserses(role.getUserses());
		roleteam.setName(role.getName());
		roleteam.setType(role.getType());
		roleteam.setRoleType(role.getRoleType());
		roleteam.setRoleTypeName(UserTypeUtil.getCHStringByInt(role.getRoleType()));
		roleteam.setRoleGroup(role.getRoleGroup());
		roleteamService.update(roleteam);
		if(null!=roleTablePrivi&&!roleTablePrivi.equals("")){
			List<RoleTablePrivilege> priis=this.roleTablePrivilegeService.getByRoleId(role.getId());
			if(null!=priis&&priis.size()>0){
				this.roleTablePrivilegeService.delete(priis);
			}
			String [] strings=roleTablePrivi.split("~");
			if(null!=strings&&strings.length>0){
				for(String string:strings){
					String[] pris=string.split("_");
					if(null!=pris&&pris.length>0){
						if(pris.length==2){
							RoleTablePrivilege roleTablePrivilege=new RoleTablePrivilege();
							roleTablePrivilege.setPrivilege(Integer.valueOf(pris[0]));
							roleTablePrivilege.setTableId(Integer.valueOf(pris[1]));
							roleTablePrivilege.setRoleId(role.getId());
							this.roleTablePrivilegeService.save(roleTablePrivilege);
						}
					}
				}
			}
		}
		return "list";
	}
	public String updateForPri() throws Exception{
		if (!memberIds.equals("")) {
			String[] s = memberIds.split(",");
			for (int i = 0; i < s.length; i++) {
				Users u = new Users();
				u.setId((Integer) (Integer.parseInt(s[i])));
				role.getUserses().add(u);
			}
		}
		Roleteam roleteam =roleteamService.findById(role.getId());
		
		roleteam.setUserses(role.getUserses());
		roleteam.setName(role.getName());
		roleteam.setType(role.getType());
		roleteam.setRoleType(role.getRoleType());
		roleteam.setRoleTypeName(UserTypeUtil.getCHStringByInt(role.getRoleType()));
		roleteam.setRoleGroup(role.getRoleGroup());
		roleteamService.update(roleteam);
		if(null!=roleTablePrivi&&!roleTablePrivi.equals("")){
			List<RoleTablePrivilege> priis=this.roleTablePrivilegeService.getByRoleId(role.getId());
			if(null!=priis&&priis.size()>0){
				this.roleTablePrivilegeService.delete(priis);
			}
			String [] strings=roleTablePrivi.split("~");
			if(null!=strings&&strings.length>0){
				for(String string:strings){
					String[] pris=string.split("_");
					if(null!=pris&&pris.length>0){
						if(pris.length==2){
							RoleTablePrivilege roleTablePrivilege=new RoleTablePrivilege();
							roleTablePrivilege.setPrivilege(Integer.valueOf(pris[0]));
							roleTablePrivilege.setTableId(Integer.valueOf(pris[1]));
							roleTablePrivilege.setRoleId(role.getId());
							this.roleTablePrivilegeService.save(roleTablePrivilege);
						}
					}
				}
			}
		}
		return "success";
	}
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String delete() throws Exception {
		message="";
		if(null!=roleId){
			if(roleId.equals(2)||roleId.equals(3)||roleId.equals(4)){
				message="1";
				return "list";
			}
		}
		roleteamService.deleteById(roleId);
		List<RoleTablePrivilege> priis=this.roleTablePrivilegeService.getByRoleId(roleId);
		if(null!=priis&&priis.size()>0){
			this.roleTablePrivilegeService.delete(priis);
		}
		return "list";
	}

	public String query() {
		role = roleteamService.findById(role.getId());
		if(role!=null) {
			Set users=new HashSet();
			Iterator it=role.getUserses().iterator();
			while(it.hasNext()) {
				Users u=(Users) it.next();
				u.setPending(serviceRequestService.findByStateAndUserId(1, u.getId()).size());
				u.setProcessing(serviceRequestService.findByStateAndUserId(2, u.getId()).size());
				users.add(u);
			}
			role.setUserses(users);
		}
		return "success";
	}
	public String queryforTask() {//计划任务指派工程师
		if(this.getRole()!=null&&role.getId()!=null)
		role = roleteamService.findById(role.getId());		
		return "success";
	}
	public String queryByRoleteam() {
		List<Roleteam> rList= roleteamService.query(role);
		if(rList!=null&&!rList.isEmpty())
		{
			Roleteam tRoleteam=rList.get(0);
			Set users=new HashSet();
			Iterator it=tRoleteam.getUserses().iterator();
			while(it.hasNext()) {
				Users u=(Users) it.next();
				u.setPending(serviceRequestService.findByStateAndUserId(1, u.getId()).size());
				u.setProcessing(serviceRequestService.findByStateAndUserId(2, u.getId()).size());
				users.add(u);
			}
			role.setUserses(users);
		}
	
		return "success";
	}
	public String query2(){
		role = roleteamService.findById(role.getId());
		Set users=new HashSet();
		Iterator it=role.getUserses().iterator();
		while(it.hasNext()) {
			Users u=(Users) it.next();
			u.setPending(serviceRequestService.findByStateAndUserId(1, u.getId()).size());
			u.setProcessing(serviceRequestService.findByStateAndUserId(2, u.getId()).size());
			users.add(u);
		}
		role.setUserses(users);
		return "success";
	}

	public Roleteam getRole() {
		return role;
	}

	public void setRole(Roleteam role) {
		this.role = role;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	public List<List<Roleteam>> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<List<Roleteam>> roleList) {
		this.roleList = roleList;
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

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
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

	public ServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public List<Roleteam> getRoles() {
		return roles;
	}

	public void setRoles(List<Roleteam> roles) {
		this.roles = roles;
	}

	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public void setPrivilegeList(List<Privilege> privilegeList) {
		this.privilegeList = privilegeList;
	}

	public String[] getMenuID() {
		return MenuID;
	}

	public void setMenuID(String[] menuID) {
		MenuID = menuID;
	}

	public Map getPrivilegeMap() {
		return privilegeMap;
	}

	public void setPrivilegeMap(Map privilegeMap) {
		this.privilegeMap = privilegeMap;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

	public String getNamesList() {
		return namesList;
	}

	public void setNamesList(String namesList) {
		this.namesList = namesList;
	}

	public RoleGroupService getRoleGroupService() {
		return roleGroupService;
	}

	public void setRoleGroupService(RoleGroupService roleGroupService) {
		this.roleGroupService = roleGroupService;
	}

	public List<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}

	public RoleTablePrivilegeService getRoleTablePrivilegeService() {
		return roleTablePrivilegeService;
	}

	public void setRoleTablePrivilegeService(
			RoleTablePrivilegeService roleTablePrivilegeService) {
		this.roleTablePrivilegeService = roleTablePrivilegeService;
	}

	public RoleTableService getRoleTableService() {
		return roleTableService;
	}

	public void setRoleTableService(RoleTableService roleTableService) {
		this.roleTableService = roleTableService;
	}

	public List<RoleTable> getRoleTables() {
		return roleTables;
	}

	public void setRoleTables(List<RoleTable> roleTables) {
		this.roleTables = roleTables;
	}

	public String getRoleTableNames() {
		return roleTableNames;
	}

	public void setRoleTableNames(String roleTableNames) {
		this.roleTableNames = roleTableNames;
	}

}
