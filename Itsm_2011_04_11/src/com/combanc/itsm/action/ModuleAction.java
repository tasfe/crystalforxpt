package com.combanc.itsm.action;

import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Module;
import com.combanc.itsm.service.ModuleService;

public class ModuleAction extends BaseActionSupport implements
		ServletRequestAware {

	private Integer moduleId;
	private Module module;
	private List<Module> moduleList;
	private String actionURI;
	private ModuleService moduleService;
	private Integer pid = 0;

	// 用于输出左侧的树
	public String leftTree() {
		// StringBuffer sb = new StringBuffer();
		// sb.append("<tr>" +
		// "<td><table width=\"151\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
		// +
		// "<tr><td width=\"29\"><img src=\"images/main20100521_28.gif\" width=\"29\" height=\"23\" alt=\"\" /></td>");
		// for(int i=0;i<moduleList.size();i++){
		// Module module = moduleList.get(i);
		// //顶级目录
		// if(module.getPid()==null||module.getPid().equals("")){
		// sb.append("<td width=\"71\" background=\"images/main20100521_30.gif\" class=\"con-white\" style=\"cursor:hand;\" onclick=\"javascript:trshowandoff(mytr"+i+")\">"+module.getName()+"</td>");
		// sb.append("<td width=\"51\" align=\"right\" background=\"images/main20100521_30.gif\"><img src=\"images/main20100521_32.gif\" width=\"9\" height=\"23\" alt=\"\"/></td>"+
		// "</tr>"+
		// "</table></td>"+
		// "</tr>");
		// sb.append("<tr id=\"mytr"+i+"\">"+
		// "<td align=\"center\"><table width=\"147\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
		// "<tr>"+
		// "<td><img src=\"images/space.gif\" width=\"1\" height=\"1\"/></td>"+
		// "</tr>");
		// }
		// //三级目录
		// if(module.getUrl()!=null||module.getPid()!=null){
		// sb.append("<tr>"+
		// "<td height=\"22\" align=\"left\" background=\"images/main20100521_45.gif\" class=\"lefttreefirst\" onmouseover=\"this.background='images/main20100521_452.gif'\" onmouseout=\"this.background='images/main20100521_45.gif'\"><img src=\"images/main20100521dot01.gif\" width=\"10\" height=\"10\" />&nbsp;<a href="+module.getUrl()+" target=\"mainFrame\">&nbsp;"+module.getName()+"</td>"+
		// "</tr>");
		// }
		// //二级目录
		// if(module.getUrl()==null||module.getPid()!=null){
		// sb.append("<tr>"+
		// "<td height=\"22\" align=\"left\" background=\"images/main20100521_45.gif\" class=\"lefttreefirst\" onmouseover=\"this.background='images/main20100521_452.gif'\" onmouseout=\"this.background='images/main20100521_45.gif'\" style=\"cursor:hand;\" onclick=\"javascript:trsecshowandoff(mytr"+i+""+i+",closeorpadding)\"><img id=\"closeorpadding\" src=\"images/main20100521dot02.gif\" width=\"9\" height=\"9\" />&nbsp;&nbsp;"+module.getName()+"</td>"+
		// "</tr>");
		// sb.append("<tr id=\"mytr11\">"+
		// "<td height=\"22\" align=\"left\" background=\"images/main20100521_61.gif\"><table width=\"147\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		// }
		// }

		return "<td height=\"22\" align=\"left\" class=\"lefttreesecond\"><a href=\"/itsm/systemmanage/importconfig/filemanage.jsp\" target=\"mainFrame\">文档库管理</a></td>";
		// return sb;
	}

	public String main() throws Exception {
		moduleList = moduleService.findAllModule();
		return "success";
	}
	
	public String user() throws Exception {
		moduleList = moduleService.findAllModule();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "add";
		return "success";
	}

	public String add() throws Exception {
		moduleService.saveModule(module);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		module = moduleService.findModuleById(moduleId);
		return "success";
	}

	public String update() throws Exception {
		moduleService.update(module);
		return "list";
	}

	public String delete() throws Exception {
		moduleService.deleteById(moduleId);
		return "list";
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
