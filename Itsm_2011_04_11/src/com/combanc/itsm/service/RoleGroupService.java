package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.RoleGroupDao;
import com.combanc.itsm.dao.RoleteamDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.RoleGroup;
@SuppressWarnings("all")
public class RoleGroupService {
	private RoleGroupDao roleGroupDao;
	private RoleteamDAO roleteamDAO;
	public RoleteamDAO getRoleteamDAO() {
		return roleteamDAO;
	}

	public void setRoleteamDAO(RoleteamDAO roleteamDAO) {
		this.roleteamDAO = roleteamDAO;
	}

	public RoleGroupDao getRoleGroupDao() {
		return roleGroupDao;
	}

	public void setRoleGroupDao(RoleGroupDao roleGroupDao) {
		this.roleGroupDao = roleGroupDao;
	}
	/*
	 * 生成编码,使用三位编码，如:001002
	 */
	public String generateCode(Integer pid){
		List<RoleGroup> departments=null;
		departments=this.getListByPidNotAll(pid);//找兄弟节点
		if(departments==null||departments.isEmpty()){//不存在兄弟
			if(null==pid || pid==-1){//父亲也为空的话，代表第一次添加
				return "001";
			}else{
				return this.roleGroupDao.findByID(pid).getCode()+"001";
			}
		}
		String code="";
		//
		RoleGroup max=departments.get(0);
		int maxCode=0;
		for(RoleGroup department:departments){
			int currCode=Integer.valueOf(department.getCode().substring(department.getCode().length()-3, department.getCode().length()));
			if(currCode>maxCode){
				maxCode=currCode;
			}
		}
		if(String.valueOf(max.getCode()).length()==3){
			code=valueOf(String.valueOf(maxCode+1));
		}else{
			code=max.getCode().substring(0,max.getCode().length()-3)+valueOf(String.valueOf(maxCode+1));
		}
		return code;
	}
	public String valueOf(String str){
		if(str==null||str.isEmpty()){
			return "000";
		}
		try{
			int code=Integer.valueOf(str);	
			if(code<10&&code>0){
				return "00"+code;
			}
			if(code<100&&code>=10){
				return "0"+code;
			}else{
				return String.valueOf(code);
			}
		}catch (Exception e) {
			return "000";
		}
	}
	
	public List getListByPid(Integer pid){
		if(null==pid||pid==-1 ){
			return this.roleGroupDao.findAll("");
		}else{
			return this.roleGroupDao.findAll("pid=?", new Object[]{pid});
		}
	}
	public List getListByPidNotAll(Integer pid){
		if(null==pid){
			return this.roleGroupDao.findAll("pid=?", new Object[]{-1});
		}else{
			return this.roleGroupDao.findAll("pid=?", new Object[]{pid});
		}
	}
	public List findAll(){
		return this.roleGroupDao.findAll("");
	}
	
	public void save(RoleGroup roleGroup){
		if(roleGroup.getPid()==null){
			roleGroup.setPid(-1);
		}
		roleGroup.setCode(generateCode(roleGroup.getPid()));
		this.roleGroupDao.save(roleGroup);
	}
	
	public String delete(Integer id){
		if(getListByPid(id)!=null&&getListByPid(id).size()>0){//应该再加上角色有否占用
			return "删除出错，该组别正在被使用!";
		}
		List list=this.roleteamDAO.findByHql(" from Roleteam where roleGroup=?", new Object[]{id});
		if(null!=list&&list.size()>0){
			return "删除出错，该组别正在被使用!";
		}
		this.roleGroupDao.delete(this.roleGroupDao.findByID(id));
		return "success";
	}
	public RoleGroup findById(Integer id){
		if(null==id){
			return null;
		}
		return this.roleGroupDao.findByID(id);
	}
	public RoleGroup findByName(String name){
		return this.roleGroupDao.find("name=?", new Object[]{name});
	}
}
