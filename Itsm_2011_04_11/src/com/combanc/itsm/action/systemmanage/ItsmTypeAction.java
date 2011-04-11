package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ItsmType;
import com.combanc.itsm.service.ItsmTypeService;

public class ItsmTypeAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private ItsmTypeService itsmTypeService;
	private String actionURI;
	private String itsmtypeid;
	private String title;
	public String getTitle() {
		return title;
	}
	public String getItsmtypeid() {
		return itsmtypeid;
	}
	public ItsmTypeService getItsmTypeService() {
		return itsmTypeService;
	}
	public void setItsmTypeService(ItsmTypeService itsmTypeService) {
		this.itsmTypeService = itsmTypeService;
	}
	public void setItsmtypeid(String itsmtypeid) {
		this.itsmtypeid = itsmtypeid;
	}
	public void setTitle(String title) {
		this.title = title;
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
	private List  ItsmTypelist;
	public List getItsmTypelist() {
		return ItsmTypelist;
	}
	public void setItsmTypelist(List itsmTypelist) {
		ItsmTypelist = itsmTypelist;
	}
	private ItsmType itsmType;
	public ItsmType getItsmType() {
		return itsmType;
	}
	public void setItsmType(ItsmType itsmType) {
		this.itsmType = itsmType;
	}
	private String pidname;
	public String getPidname() {
		return pidname;
	}
	public void setPidname(String pidname) {
		this.pidname = pidname;
	}
	private int pid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String save()throws Exception{
		ItsmType build=itsmTypeService.findByName(itsmType.getName());
		if(build==null){
			itsmTypeService.save(itsmType);
			message=2;
			return "success";
		}else{
			message=1;
			return "fail";
		}
	}
	public String updateInput() throws Exception {
		actionURI = "update";
		message=message;
		itsmtypeid=itsmtypeid;
		itsmType = itsmTypeService.findlocationById(Integer.parseInt(itsmtypeid));
		if(itsmType.getPid()==0){
			pidname="根目录";
		}else{
			pidname=itsmTypeService.findlocationById(itsmType.getPid()).getName();
		}
		return "success";
	}
	
	public String update()throws Exception{
		ItsmType itsms=itsmTypeService.findbyid(itsmType.getId().toString());
		ItsmType itsm=itsmTypeService.findByName(itsmType.getName());
		if(itsm!=null){
			if(itsm.getId().equals(itsms.getId())){
				if(!itsms.getPid().equals(itsmType.getPid())){
					itsms.setPid(itsmType.getPid());
					ItsmType parent=itsmTypeService.findbyid(itsmType.getPid().toString());
					itsms.setCode(itsmTypeService.generateCode(parent));
				}
				message=2;
				itsmTypeService.update(itsms);
			}else{
				message=1;
				itsmtypeid=itsmtypeid;
				return "fail";
			}
		}else{
			itsms.setName(itsmType.getName());
			if(!itsms.getPid().equals(itsmType.getPid())){
				itsms.setPid(itsmType.getPid());
				ItsmType parent=itsmTypeService.findbyid(itsmType.getPid().toString());
				itsms.setCode(itsmTypeService.generateCode(parent));
			}
			message=2;
			itsmTypeService.update(itsms);
		}
		return "success";
	}
	public String main() throws Exception {
		return "success";
	}
	
	public String title() throws Exception {
		title="运维类别管理";
		return "success";
	}
	
	public String top() throws Exception {
		ItsmTypelist = itsmTypeService.findAll();
		return "success";
	}
	public String list() throws Exception {
		ItsmTypelist = itsmTypeService.findAllByPid(pid);
		return "success";
	}
	public String addInput()throws Exception{
		actionURI = "save";
		return "success";
	}
	public String delete()throws Exception{
		List list=itsmTypeService.findAllByPid(Integer.parseInt(itsmtypeid));
		if(list.size()==0){
			message=2;
			ItsmType build=itsmTypeService.findbyid(itsmtypeid);
			itsmTypeService.delete(build);
		}else{
			message=1;
		}
		return "success";
	}
}
