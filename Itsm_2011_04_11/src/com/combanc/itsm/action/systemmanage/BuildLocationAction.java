package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Buildlocation;
import com.combanc.itsm.service.BuildlocationService;

public class BuildLocationAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private BuildlocationService buildlocationService;
	private String actionURI;
	private String buildid;
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBuildid() {
		return buildid;
	}
	public void setBuildid(String buildid) {
		this.buildid = buildid;
	}
	private int message=0;
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public String getActionURI() {
		return actionURI;
	}
	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}
	public BuildlocationService getBuildlocationService() {
		return buildlocationService;
	}
	public void setBuildlocationService(BuildlocationService buildlocationService) {
		this.buildlocationService = buildlocationService;
	}
	private List  buildLocationlist;
	private Buildlocation buildlocation;
	private String pidname;
	public String getPidname() {
		return pidname;
	}
	public void setPidname(String pidname) {
		this.pidname = pidname;
	}
	public Buildlocation getBuildlocation() {
		return buildlocation;
	}
	public void setBuildlocation(Buildlocation buildlocation) {
		this.buildlocation = buildlocation;
	}
	private int pid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List getBuildLocationlist() {
		return buildLocationlist;
	}
	public void setBuildLocationlist(List buildLocationlist) {
		this.buildLocationlist = buildLocationlist;
	}
	public String save()throws Exception{
		buildlocationService.save(buildlocation);
		message=2;
		return "success";
	}
	public String updateInput() throws Exception {
		actionURI = "update";
		message=message;
		buildid=buildid;
		buildlocation = buildlocationService.findlocationById(Integer.parseInt(buildid));
		if(buildlocation.getPid()==0){
			pidname="根目录";
		}else{
			pidname=buildlocationService.findlocationById(buildlocation.getPid()).getName();
		}
		return "success";
	}
	
	public String update()throws Exception{
		Buildlocation buildlocations=buildlocationService.findbyid(buildid);
		buildlocations.setName(buildlocation.getName());
		if(!buildlocations.getPid().equals(buildlocation.getPid())){
			buildlocations.setPid(buildlocation.getPid());
			Buildlocation parent=buildlocationService.findbyid(buildlocation.getPid().toString());
			buildlocations.setCode(buildlocationService.generateCode(parent));
		}
		buildlocations.setAllname(buildlocationService.genrateName(buildlocation));
		buildlocationService.update(buildlocations);
		message=2;
		return "success";
	}
	public String main() throws Exception {
		return "success";
	}
	
	public String title() throws Exception {
		title="位置管理";
		return "success";
	}
	
	public String top() throws Exception {
		buildLocationlist = buildlocationService.findAll();
		return "success";
	}
	public String list() throws Exception {
		buildLocationlist = buildlocationService.findAllByPid(pid);
		return "success";
	}
	public String addInput()throws Exception{
		actionURI = "save";
		message=message;
		return "success";
	}
	public String delete()throws Exception{
		List list=buildlocationService.findAllByPid(Integer.parseInt(buildid));
		if(list.size()==0){
			Buildlocation build=buildlocationService.findbyid(buildid);
			buildlocationService.delete(build);
			message=2;
		}else{
			message=1;
		}
		return "success";
	}
}
