package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.snmp4j.security.PrivAES;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.RoleGroup;
import com.combanc.itsm.service.RoleGroupService;
@SuppressWarnings("all")
public class RoleGroupAction extends BaseActionSupport implements
ServletRequestAware{
	private RoleGroupService roleGroupService;
	private List roleGroupList;
	private String message;
	private RoleGroup roleGroup;
	private Integer pid;
	private Integer id;
	private String parentname;
	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public RoleGroup getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(RoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}

	public List getRoleGroupList() {
		return roleGroupList;
	}

	public void setRoleGroupList(List roleGroupList) {
		this.roleGroupList = roleGroupList;
	}

	public RoleGroupService getRoleGroupService() {
		return roleGroupService;
	}

	public void setRoleGroupService(RoleGroupService roleGroupService) {
		this.roleGroupService = roleGroupService;
	}
	
	public String main(){
		return "success";
	}
	public String list(){
		roleGroupList=roleGroupService.getListByPid(pid);
		return "success";
	}
	public String top(){
		roleGroupList=roleGroupService.findAll();
		return "success";
	}
	public String addInput(){
		roleGroup=roleGroupService.findById(id);
		if(null!=roleGroup&&null!=roleGroup.getPid()&&roleGroup.getPid()!=-1){
			parentname=roleGroupService.findById(roleGroup.getPid()).getName();
		}
		return "success";
	}
	public String save(){
		if(null!=roleGroup&&null!=roleGroup.getName()&&!roleGroup.getName().equals("")){
			RoleGroup r=this.roleGroupService.findByName(roleGroup.getName());
			if(null!=r && null==roleGroup.getId()&&r.getId()!=roleGroup.getId()){
				message="1";
				return "fail";
			}
		}
		roleGroupService.save(roleGroup);
		return "success";
	}
	public String delete(){
		message=roleGroupService.delete(id);
		if(!message.equals("success")){
			message="1";
		}
		return "success";
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
