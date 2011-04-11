package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.directory.DirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.DepartmentDAO;
import com.combanc.itsm.dao.MenuDAO;
import com.combanc.itsm.dao.PrivilegeDAO;
import com.combanc.itsm.dao.RoleteamDAO;
import com.combanc.itsm.dao.UserDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.Menu;
import com.combanc.itsm.pojo.Privilege;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.StringUtil;
import com.combanc.itsm.util.SysConfig;
import com.combanc.itsm.util.SysConfigItem;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("all")
public class UserService extends BaseService<Users, Integer> {
	private static final Log log = LogFactory.getLog(UserService.class);
	private String list_all_desc = "from Users as bean order by bean.id desc";
	private String list_all_asc = "from Users as bean order by bean.id asc";

	private UserDAO userDAO;
	private PrivilegeDAO privilegeDAO;
	private MenuDAO menuDAO;
	private DepartmentDAO departmentDAO;
	private RoleteamDAO roleteamDAO;
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	public RoleteamDAO getRoleteamDAO() {
		return roleteamDAO;
	}
	public void setRoleteamDAO(RoleteamDAO roleteamDAO) {
		this.roleteamDAO = roleteamDAO;
	}
	public PageBean queryForPage(int pageSize, int page, String condition) {

		String hql = "from Users as p where 1=1 and p.id!=1";
		if(condition!=null&&!condition.equals("")){
			hql+=condition;
		}
		hql = hql + " order by p.id desc";// 查询语句
		int allRow = userDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<Users> list = userDAO.queryForPage(hql, offset, length); // "一页"的记录

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
	
	public List<Users> getUsersByCondition(int pageSize,int page,String condition){
		String hql = "from Users  where  id!=1";
		if(condition!=null&&!condition.equals("")){
			hql+=condition;
		}
		//final int offset = PageBean.countOffset(pageSize, page); // 
		//final int length = pageSize; // 
		return  userDAO.queryForPage(hql, page, pageSize); // 
	}
	public int getCount(String condition){
		String hql = "from Users  where id!=1";
		if(condition!=null&&!condition.equals("")){
			hql+=condition;
		}
		return userDAO.getAllRowCount(hql); // 总记录数
	}
	public PageBean queryForPage(int pageSize, int page, Users condition,String keyword) {

		String hql = "from Users as p where 1=1 and p.id!=1";

		if (condition != null) {

			if (keyword != null
					&& !keyword.equals("")) {
				keyword=StringUtil.decrateString(keyword);
				hql = hql + " and (p.truename like '%" + keyword
						+ "%' ";
				hql = hql + " or p.username like '%" + keyword
				+ "%' )";
			}
			if (condition.getDepartment() != null
					&&condition.getDepartment().getId()!=null&& condition.getDepartment().getId() >0) {

				hql = hql + " and p.department.id ="
						+ condition.getDepartment().getId();

			}
			if (condition.getLocation() != null&&condition.getLocation().getId()!=-1
					&& condition.getLocation().getId() > 0) {
				hql = hql + " and p.location.id ="
						+ condition.getLocation().getId();

			}
			if (condition.getUsertype() != null
					&& !condition.getUsertype().equals("")) {
				hql = hql + " and p.usertype =" + "'" + condition.getUsertype()
						+ "'";

			}
		}

		hql = hql + " order by p.id desc";// 查询语句
		int allRow = userDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<Users> list = userDAO.queryForPage(hql, offset, length); // "一页"的记录

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
	public PageBean queryForPage(int pageSize, int page, Users condition) {

		String hql = "from Users as p where 1=1 and p.id!=1";

		if (condition != null) {

			if (condition.getTruename() != null
					&& !condition.getTruename().equals("")) {
				condition.setTruename(StringUtil.decrateString(condition.getTruename()));
				hql = hql + " and (p.truename like '%" + condition.getTruename()
						+ "%' ";
				hql = hql + " or p.username like '%" + condition.getTruename()
				+ "%' )";
			}
			if (condition.getDepartment() != null
					&& condition.getDepartment().getId() >0) {

				hql = hql + " and p.department.id ="
						+ condition.getDepartment().getId();

			}
			if (condition.getLocation() != null
					&& condition.getLocation().getId() > 0) {
				hql = hql + " and p.location.id ="
						+ condition.getLocation().getId();

			}
			if (condition.getUsertype() != null
					&& !condition.getUsertype().equals("")) {
				hql = hql + " and p.usertype =" + "'" + condition.getUsertype()
						+ "'";

			}
		}

		hql = hql + " order by p.id desc";// 查询语句
		int allRow = userDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<Users> list = userDAO.queryForPage(hql, offset, length); // "一页"的记录

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

	private String getDnForUser(String userName) {
		EqualsFilter f = new EqualsFilter("uid", userName);
		List result = userDAO.getLdapTemplate().search(
				DistinguishedName.EMPTY_PATH, f.toString(),
				new AbstractContextMapper() {
					protected Object doMapFromContext(DirContextOperations ctx) {
						return ctx.getNameInNamespace();
					}
				});
		if (result.size() != 1) {
			return null;
		}
		return (String) result.get(0);
	}

	private class PersonAttributesMapper implements AttributesMapper {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.ldap.core.AttributesMapper#mapFromAttributes(
		 * javax.naming.directory.Attributes)
		 */
		public Object mapFromAttributes(javax.naming.directory.Attributes arg0)
				throws javax.naming.NamingException {
			Users users = new Users();
			users.setTruename(arg0.get("sn").get().toString());
			// users.setPassword(arg0.get("userPassword").toString());
			users.setUsername(arg0.get("uid").get().toString());
			return users;
		}
	}

	// 用户登录验证

	public boolean authenticate(String userDn, String credentials) {
		DirContext ctx = null;
		try {
			ctx = userDAO.getLdapTemplate().getContextSource()
					.getContext(userDn, credentials);
			return true;
		} catch (Exception e) {
			// Contextcreationfailed-authenticationdidnotsucceed

			return false;
		} finally {
			// ItisimperativethatthecreatedDirContextinstanceisalwaysclosed
			LdapUtils.closeContext(ctx);
		}
	}

	public void privilege(Users user, int roleType) { // 获取用户的按钮权限
		Iterator it = user.getRoleteams().iterator();
		List<Integer> list = new ArrayList();
		while (it.hasNext()) {
			Roleteam rt = (Roleteam) it.next();
			if ((rt.getType().equals("0")) && (rt.getRoleType() == roleType)) {
				list.add(rt.getId());
			}
		}
		Map map = new HashMap();
		for (Integer id : list) {
			List priList = privilegeDAO.findByOwenr(id);
			for (int i = 0; i < priList.size(); i++) {
				Privilege temp = (Privilege) priList.get(i);
				if (map.containsKey(temp.getMid())) {
					String s = (String) map.get(temp.getMid());
					if (!s.contains("," + temp.getCode() + ",")) {
						s = s + "," + temp.getCode() + ",";
						map.put(temp.getMid(), s);
					}
				} else {
					String s = "," + temp.getCode() + ",";
					map.put(temp.getMid(), s);
				}
			}
		}
		ActionContext.getContext().getSession().put("privilege", map);
	}

	public Map menu(int roleType) {
		Users user = (Users) ActionContext.getContext().getSession()
				.get("user");
		Iterator it = user.getRoleteams().iterator();
		List<Integer> list = new ArrayList(); // roleID List
		while (it.hasNext()) {
			Roleteam rt = (Roleteam) it.next();
			if ((rt.getType().equals("0")) && (rt.getRoleType() == roleType)) {
				list.add(rt.getId());
			}
		}
		List priList = new ArrayList();
		for (Integer roleId : list) {
			priList.addAll(privilegeDAO.findByOwenr(roleId));
		}
		Set set = new HashSet();
		for (int j = 0; j < priList.size(); j++) {
			Privilege privi = (Privilege) priList.get(j);
			set.add(privi.getMid());
		}
		List menuList = menuDAO.findAll(); // menu List
		List tempList = new ArrayList();
		Map<Menu, Map<Menu, List>> map = new LinkedHashMap();
		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = (Menu) menuList.get(i);
			if (!set.contains(String.valueOf(menu.getId()))) {
				tempList.add(menu);
			} else {
				if (menu.getType().equals("1")) {
					map.put(menu, null);
				}
			}
		}
		menuList.removeAll(tempList);
		Iterator key = map.keySet().iterator();
		while (key.hasNext()) {
			Menu keyMenu = (Menu) key.next();
			Map<Menu, List> subMap = new LinkedHashMap<Menu, List>();
			for (int i = 0; i < menuList.size(); i++) {
				Menu temp = (Menu) menuList.get(i);
				if (temp.getType().equals("2")
						&& temp.getParentId().equals(keyMenu.getId())) {
					List listType3 = menuDAO.findByParentId(temp.getId());
					List list3 = new ArrayList();
					for (int j = 0; j < listType3.size(); j++) {
						Menu type3 = (Menu) listType3.get(j);
						if (set.contains(String.valueOf(type3.getId()))) {
							list3.add(type3);
						}
					}
					subMap.put(temp, list3);
				}
			}
			map.put(keyMenu, subMap);
		}
		return map;
	}
	/*
	public String getResultForLdap(){
		String result="login";
		return result;
	}
	public String getResult(String username, String password, String wap){
		String result="login";
		List list = userDAO.findByUsername(username);
		if (list != null && list.size() > 0) {
			Users user = (Users) list.get(0);
			Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
			Set<Roleteam> roleSet = user.getRoleteams();
			System.out.println(roleSet.size());
			if (user != null && password.equals(user.getPassword())) {
				int i = 0;
				for (Roleteam t : roleSet) {
					if (t.getType().equals("1"))
						continue;
					if (!rolesize.containsKey(t.getRoleType()))
						rolesize.put(t.getRoleType(), t);
				}
				Map session = ActionContext.getContext().getSession();
				session.put("user", user);
				session.put("rolesize", rolesize);
				if (wap != null && wap.equals("wap")) {
					return "5";
				}
				if (rolesize.containsKey(1)) {
					privilege(user, 1);
					session.put("currentrole", "工程师");
					return "1";

				} else if (rolesize.containsKey(2)) {
					privilege(user, 2);
					session.put("currentrole", "经理");
					return "2";
				} else if (rolesize.containsKey(3)) {
					privilege(user, 3);
					session.put("currentrole", "用户");
					return "3";
				} else if (rolesize.containsKey(4)) {
					session.put("currentrole", "管理员");
					privilege(user, 4);
					return "4";
				} else {
					return "6";
				}

			}

		}
		return result;
	}*/
	public int getDepartment(){
		try{
			List<Department> departsList=this.departmentDAO.findByHql(" from Department where pid=0 or pid is null");
			if(departsList!=null&& departsList.size()>0){
				return departsList.get(0).getId();
			}
		}catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
		
		return -1;
	}
	public int getRole(){
		try{
			Roleteam roleteam=this.roleteamDAO.findById(2);//ID为2的角色为系统预置的用户角色
			if(roleteam!=null){
				return 2;
			}else{
				List<Roleteam> roles=this.roleteamDAO.findByProperty("roleType", 3);
				if(roles!=null&& roles.size()>0){
					return roles.get(0).getId();
				}
			}
		}catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
		return -1;
	}
	public String validateLogin(String username, String password, String wap) {
		String result = "login";
		String filter = "";
		/*
		 * 
		 * 是否ldap验证
		 */
		boolean isLdap=true;
		List<SysConfigItem> items=SysConfig.getSysItems();
		if(null!=items && items.size()>0){
			isLdap=items.get(0).isSysLdap();
			System.out.println("-------ldap验证:"+isLdap);
		}
		if (isLdap) {
			List list = userDAO.findByUsername(username.trim());

			if (list == null || list.size() == 0) {
				return result;
			}
			if (list != null && list.size() > 0) {
				Users user = (Users) list.get(0);
				Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
				Set<Roleteam> roleSet = user.getRoleteams();
				System.out.println(roleSet.size());
				if (user != null && password.trim().equals(user.getPassword())) {
					int i = 0;
					for (Roleteam t : roleSet) {
						if (t.getType().equals("1"))
							continue;
						if (!rolesize.containsKey(t.getRoleType()))
							rolesize.put(t.getRoleType(), t);
					}
					Map session = ActionContext.getContext().getSession();
					session.put("user", user);
					session.put("rolesize", rolesize);
					if (wap != null && wap.equals("wap")) {

						return "5";
					}
					if (rolesize.containsKey(1)) {
						privilege(user, 1);
						session.put("currentrole", "工程师");
						return "1";

					} else if (rolesize.containsKey(2)) {
						privilege(user, 2);
						session.put("currentrole", "经理");
						return "2";
					} else if (rolesize.containsKey(3)) {
						privilege(user, 3);
						session.put("currentrole", "用户");
						return "3";
					} else if (rolesize.containsKey(4)) {
						session.put("currentrole", "管理员");
						privilege(user, 4);
						return "4";
					} else {
						return "6";
					}

				}

			}

		}
		if (username.equals("admin")) {

			List list = userDAO.findByUsername(username);
			if (list != null && list.size() > 0) {
				Users user = (Users) list.get(0);
				Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
				Set<Roleteam> roleSet = user.getRoleteams();
				System.out.println(roleSet.size());
				if (user != null && password.equals(user.getPassword())) {
					int i = 0;
					for (Roleteam t : roleSet) {
						if (t.getType().equals("1"))
							continue;
						if (!rolesize.containsKey(t.getRoleType()))
							rolesize.put(t.getRoleType(), t);
					}
					Map session = ActionContext.getContext().getSession();
					session.put("user", user);
					session.put("rolesize", rolesize);
					if (wap != null && wap.equals("wap")) {

						return "5";
					}
					if (rolesize.containsKey(1)) {
						privilege(user, 1);
						session.put("currentrole", "工程师");
						return "1";

					} else if (rolesize.containsKey(2)) {
						privilege(user, 2);
						session.put("currentrole", "经理");
						return "2";
					} else if (rolesize.containsKey(3)) {
						privilege(user, 3);
						session.put("currentrole", "用户");
						return "3";
					} else if (rolesize.containsKey(4)) {
						session.put("currentrole", "管理员");
						privilege(user, 4);
						return "4";
					} else {
						return "6";
					}

				}

			}

		}
		String dn = null;
		try {
			dn = getDnForUser(username);
		} catch (Exception e) {
			log.error("login ldap fail!");
		}
		if (dn != null) {
			filter = dn;
			if (authenticate(dn, password)) {
				List list = userDAO.findByUsername(username);
				if (list != null && list.size() > 0) {
					Users user = (Users) list.get(0);
					Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
					Set<Roleteam> roleSet = user.getRoleteams();
					System.out.println(roleSet.size());
					//if (user != null && password.equals(user.getPassword())) {
						int i = 0;
						for (Roleteam t : roleSet) {
							if (t.getType().equals("1"))
								continue;
							if (!rolesize.containsKey(t.getRoleType()))
								rolesize.put(t.getRoleType(), t);
						}
						Map session = ActionContext.getContext().getSession();
						session.put("user", user);
						session.put("rolesize", rolesize);
						if (wap != null && wap.equals("wap")) {

							return "5";
						}
						if (rolesize.containsKey(1)) {
							privilege(user, 1);
							session.put("currentrole", "工程师");
							return "1";

						} else if (rolesize.containsKey(2)) {
							privilege(user, 2);
							session.put("currentrole", "经理");
							return "2";
						} else if (rolesize.containsKey(3)) {
							privilege(user, 3);
							session.put("currentrole", "用户");
							return "3";
						} else if (rolesize.containsKey(4)) {
							session.put("currentrole", "管理员");
							privilege(user, 4);
							return "4";
						} else {
							return "6";
						}

					//}

				} else {
					AndFilter af = new AndFilter();
					// af.and(new EqualsFilter("objectclass", "person"));
					af.and(new EqualsFilter("uid", username));
					List<Users> usersList = userDAO.getLdapTemplate().search(
							"", af.encode(), new PersonAttributesMapper());
					if (usersList != null && usersList.size() > 0) {
						if(getDepartment()==-1 ||getRole()==-1){
							return "-1";
						}
						Users u = usersList.get(0);
						Location location = new Location();
						location.setId(1);
						u.setLocation(location);
						Department department = new Department();
						department.setId(getDepartment());
						u.setDepartment(department);
						u.setUsertype("user");
						Roleteam team = new Roleteam();
						team.setId(getRole());
						userDAO.save(u);

						list = userDAO.findByUsername(username);
						if (list != null && list.size() > 0) {

							Users user = (Users) list.get(0);
							Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
							Set<Roleteam> roleSet = user.getRoleteams();
							System.out.println(roleSet.size());
							if (user != null && password.equals(user.getPassword())) {
								int i = 0;
								for (Roleteam t : roleSet) {
									if (t.getType().equals("1"))
										continue;
									if (!rolesize.containsKey(t.getRoleType()))
										rolesize.put(t.getRoleType(), t);
								}
								Map session = ActionContext.getContext().getSession();
								session.put("user", user);
								session.put("rolesize", rolesize);
								if (wap != null && wap.equals("wap")) {

									return "5";
								}
								if (rolesize.containsKey(1)) {
									privilege(user, 1);
									session.put("currentrole", "工程师");
									return "1";

								} else if (rolesize.containsKey(2)) {
									privilege(user, 2);
									session.put("currentrole", "经理");
									return "2";
								} else if (rolesize.containsKey(3)) {
									privilege(user, 3);
									session.put("currentrole", "用户");
									return "3";
								} else if (rolesize.containsKey(4)) {
									session.put("currentrole", "管理员");
									privilege(user, 4);
									return "4";
								} else {
									return "6";
								}

							}

						
						}
					}

				}
			}

		} else {
			List list = userDAO.findByUsername(username);
			if (list != null && list.size() > 0) {
				Users user = (Users) list.get(0);
				Map<Integer, Roleteam> rolesize = new HashMap<Integer, Roleteam>();
				Set<Roleteam> roleSet = user.getRoleteams();
				System.out.println(roleSet.size());
				if (user != null && password.equals(user.getPassword())) {
					int i = 0;
					for (Roleteam t : roleSet) {
						if (t.getType().equals("1"))
							continue;
						if (!rolesize.containsKey(t.getRoleType()))
							rolesize.put(t.getRoleType(), t);
					}
					Map session = ActionContext.getContext().getSession();
					session.put("user", user);
					session.put("rolesize", rolesize);
					if (wap != null && wap.equals("wap")) {

						return "5";
					}
					if (rolesize.containsKey(1)) {
						privilege(user, 1);
						session.put("currentrole", "工程师");
						return "1";

					} else if (rolesize.containsKey(2)) {
						privilege(user, 2);
						session.put("currentrole", "经理");
						return "2";
					} else if (rolesize.containsKey(3)) {
						privilege(user, 3);
						session.put("currentrole", "用户");
						return "3";
					} else if (rolesize.containsKey(4)) {
						session.put("currentrole", "管理员");
						privilege(user, 4);
						return "4";
					} else {
						return "input";
					}

				}

			}
		}
		return result;
	}

	public PageBean findUsers(Users user, int pageSize, int page) {

		String queryString = "from Users as model where 1=1 and model.id!=1";
		if (user != null) {
			if (user.getId() != null) {
				queryString = queryString + " and model.id='" + user.getId()
						+ "'";
			}
			if(user.getUsername()!=null&&!user.getUsername().equals("")){
				queryString = queryString + " and model.username like '%"+user.getUsername()+"%'";
			}
			if (user.getTruename() != null&&!user.getTruename().equals("")) {
				queryString = queryString + " and model.truename like '%"+user.getTruename()+"%'";
			}
			if (user.getDepartment() != null) {
				if (user.getDepartment().getId() != null
						&& user.getDepartment().getId() > 0) {
					queryString = queryString + " and model.department.id='"
							+ user.getDepartment().getId() + "'";
				}
			}
			if (user.getLocation() != null) {
				if (user.getLocation().getId() != null
						&& user.getLocation().getId() > 0) {
					queryString = queryString + " and model.location.id='"
							+ user.getLocation().getId() + "'";
				}
			}
		}
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = userDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<Users> list = userDAO.findBySql(queryString, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public List<Users> findAllUser() {
		return userDAO.findAll(Users.class);
	}

	public Users findUserById(Integer userId) {
		return (Users) userDAO.findById(Users.class, userId);
	}

	public Users getUserWithDeptById(Integer userId) {
		return userDAO.getUserWithDeptById(userId);
	}

	public Users getUserWithLocationById(Integer userId) {
		return userDAO.getUserWithLocationById(userId);
	}

	public List<Users> findByLocation(Integer location) {
		return userDAO.findByLocation(location);
	}

	public List<Users> findByDepartment(Integer department) {
		return userDAO.findByDepartment(department);
	}

	public List<Users> findByProperty(String property, String department) {
		return userDAO.findByProperty(property, department);
	}

	public Users getUserWithDeptAndLocationById(Integer userId) {
		return userDAO.getUserWithDeptAndLocationById(userId);
	}

	public List findByRoleId(Integer roleId) {
		return userDAO.findByRole(roleId);
	}

	public void saveUser(Users user) {

		userDAO.save(user);
	}

	public void update(Users user) {
		userDAO.update(user);
	}

	public void saveOrUpdate(Users user) {
		userDAO.saveOrUpdate(user);
	}

	// find by username
	public Users findByUsername(String username) {
		List list = new ArrayList();
		list = userDAO.findByUsername(username);
		if (list.isEmpty())
			return null;
		else
			return (Users) list.get(0);
	}

	// find by username and password
	public Users findByUsernameAndPassword(String username, String password) {
		String[] propertyNames = new String[] { "username", "password" };
		Object[] values = new Object[] { username, password };
		List list = new ArrayList();
		list = userDAO.findByPropertys(Users.class, propertyNames, values);
		if (list.isEmpty())
			return null;
		else
			return (Users) list.get(0);
	}

	public void deleteById(Integer userId) {
		Users user = null;
		if (userId != null)
			user = (Users) userDAO.findById(Users.class, userId);
		if (user != null)
			userDAO.delete(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 根据用户名来找的用户id;
	public Users getUserByTrueName(String truename) {
		String hql = "from Users as bean where and bean.id!=1 and bean.truename='" + truename
				+ "'";
		return this.userDAO.searchUser(hql);
	}

	// 得到用户总数;
	public long getUserCount() {
		return this.userDAO.getUserCount(list_all_desc);
	}

	public List<Users> listUserAsc(int start, int range) {
		return this.userDAO.searchUsers(list_all_asc, new String[] {}, start,
				range);

	}

	public List<Users> getUsersByType(String type) {

		return this.userDAO.findByUsertype(type);
	}

	public PrivilegeDAO getPrivilegeDAO() {
		return privilegeDAO;
	}

	public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
		this.privilegeDAO = privilegeDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public List<Users> getUsersByDepartmentID(int depId) {
		return userDAO.findByDepartment(depId);
	}

	public List<Users> findByHql(String hql) {
		return userDAO.findByHql(hql);
	}
}
