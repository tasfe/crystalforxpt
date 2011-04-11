package com.combanc.itsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.LicenseInfo;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ConfigRecord;
import com.combanc.itsm.pojo.RoleTablePrivilege;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.ConfigRecordService;
import com.combanc.itsm.service.RoleTablePrivilegeService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.util.Messages;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session = null;
	private UserService userService;
	private String username;
	private String password;
	private Users user;
	private Map menuMap;
	private int roleType;
	private String msg;
	private RoleTablePrivilegeService roleTablePrivilegeService;
	private ConfigRecordService configrecordService;
	

	public ConfigRecordService getConfigrecordService() {
		return configrecordService;
	}

	public void setConfigrecordService(ConfigRecordService configrecordService) {
		this.configrecordService = configrecordService;
	}

	public RoleTablePrivilegeService getRoleTablePrivilegeService() {
		return roleTablePrivilegeService;
	}

	public void setRoleTablePrivilegeService(
			RoleTablePrivilegeService roleTablePrivilegeService) {
		this.roleTablePrivilegeService = roleTablePrivilegeService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	HttpServletRequest request = null;
	private String wap;

	public String getWap() {
		return wap;
	}

	public void setWap(String wap) {
		this.wap = wap;
	}

	public String login() throws Exception {
		/*
		 * 增加LICENSE授权限制
		 */
		if(LicenseInfo.verifyLicense()== false || LicenseInfo.verifyDate() == false){
			return "license";
		}
		wap = (String) request.getSession().getAttribute("wap"); //$NON-NLS-1$
		String result = userService.validateLogin(username, password, wap);
		if(result.equals("login")) //$NON-NLS-1$
		{
			msg=Messages.getString("com.combanc.itsm.action.LoginAction.1"); //$NON-NLS-1$
			return result;
		}
		if(result.equals("-1")){//LDAP用户初始化失败
			msg="LDAP用户初始化失败!";
			return "-1";
		}
		if(result.equals("6")){
			msg="暂无任何权限，请联系系统管理员!";
			return "6";
		}
		roleType=Integer.valueOf(result);
		Map session = ActionContext.getContext().getSession();
		Users user=(Users) session.get("user");
		if(null!=user){
			if(this.getListPrivilege("asset", user.getRoleteams(),roleType)!=-1){
				session.put("asset", this.getListPrivilege("asset", user.getRoleteams(),roleType));
			}
			
		}
		ConfigRecord cord=new ConfigRecord();
		List list=configrecordService.findbysql("from ConfigRecord where isAssetsinfo='1'");
		cord=(ConfigRecord)list.get(0);
		session.put("record",cord.getRecordDescription());
		return result;
	}
	public int getListPrivilege(String modual,Set<Roleteam> roleSet,Integer roletype){
		//应该根据表名找对应列表权限，目前只有资产
		int type=-1;
		if(modual==null||modual.equals("")||roletype==null){
			System.out.println("资产对应权限----###"+type);
			return type;
		}
		if(roleSet!=null&&roleSet.size()>0){
			for (Roleteam t : roleSet) {
				if(roletype!=-1){
					if(t.getRoleType()!=roletype){
						continue;
					}
				}
				List<RoleTablePrivilege> pris=this.roleTablePrivilegeService.getByRoleId(t.getId());
				if(pris!=null && pris.size()>0){
					for(RoleTablePrivilege pri:pris){
						if(type<pri.getPrivilege()){
							type=pri.getPrivilege();
						}
						if(type==2){
							System.out.println("资产对应权限----###"+type);
							return type;
						}
					}
				}
			}
		}
		System.out.println("资产对应权限----###"+type);
		return type;
	}
	public String logout() throws Exception {
		Map session = ActionContext.getContext().getSession();
		session.clear();
		
		return "input"; //$NON-NLS-1$
	}

	public String menu() {
		menuMap = userService.menu(roleType);
		return "success"; //$NON-NLS-1$
	}

	public String roleChange() {
		Users user=(Users) ActionContext.getContext().getSession().get("user"); //$NON-NLS-1$
		Map session = ActionContext.getContext().getSession();
		if(null!=user){
			if(this.getListPrivilege("asset", user.getRoleteams(),roleType)!=-1){
				session.put("asset", this.getListPrivilege("asset", user.getRoleteams(),roleType));
			}
		}
		if (roleType == 0) {
			session.put("currentrole", Messages.getString("com.combanc.itsm.action.LoginAction.2")); //$NON-NLS-1$ //$NON-NLS-2$
			userService.privilege(user, roleType);
			return "0"; //$NON-NLS-1$
		} else if (roleType == 1) {
			session.put("currentrole", Messages.getString("com.combanc.itsm.action.LoginAction.3")); //$NON-NLS-1$ //$NON-NLS-2$
			userService.privilege(user, roleType);
			return "1"; //$NON-NLS-1$
		} else if (roleType == 2) {
			session.put("currentrole", Messages.getString("com.combanc.itsm.action.LoginAction.4")); //$NON-NLS-1$ //$NON-NLS-2$
			userService.privilege(user, roleType);
			return "2"; //$NON-NLS-1$
		} else if (roleType == 3) {
			session.put("currentrole", Messages.getString("com.combanc.itsm.action.LoginAction.5")); //$NON-NLS-1$ //$NON-NLS-2$
			userService.privilege(user, roleType);
			return "3"; //$NON-NLS-1$
		} else if (roleType == 4) {
			session.put("currentrole", Messages.getString("com.combanc.itsm.action.LoginAction.6")); //$NON-NLS-1$ //$NON-NLS-2$
			userService.privilege(user, roleType);
			return "4"; //$NON-NLS-1$
		} else {
			return ERROR;
		}
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

}
