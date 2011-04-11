package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.db.ExportMySQL;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.service.AssetsTypeService;

public class AssetsTypeAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private AssetsTypeService assetsTypeService;
	private String actionURI;
	private Integer flag = 0;
	private AssetsType assetsType;
	private List assetsTypeList;
	private Integer assetsTypeId;
	private Integer pid = 0;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private ExportMySQL backupAssets;
	private String message=0+"";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExportMySQL getBackupAssets() {
		return backupAssets;
	}

	public void setBackupAssets(ExportMySQL backupAssets) {
		this.backupAssets = backupAssets;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public AssetsType getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public List getAssetsTypeList() {
		return assetsTypeList;
	}

	public Integer getAssetsTypeId() {
		return assetsTypeId;
	}

	public void setAssetsTypeId(Integer assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	public String list() throws Exception {
		if (0 == flag){
			assetsTypeList = assetsTypeService.findAllByPid(pid);
		}else{
			assetsTypeList = assetsTypeService.findAll();
		}
		return "success";
	}

	public String main() throws Exception {
		return "success";
	}

	public String save() throws Exception {
		assetsTypeService.save(assetsType);
		message=2+"";
		return "success";
	}
	
	
	public String title() throws Exception{
		title="资产类别管理";
		return "success";
	}

	public String top() throws Exception {
		assetsTypeList = assetsTypeService.findAll();
		return "success";

	}

	public String backup() throws Exception {

		System.out.print("asd");
		backupAssets.backup();
		// 找下载链接

		addActionMessage("资产数据备份成功！<br>" + "默认备份在C盘根目录下，<br>" + "如要下载，请点击下面链接！");
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		assetsType = assetsTypeService.findById(assetsTypeId);
		pid = assetsType.getPid();
		return "success";
	}

	public String delete() throws Exception {
		List list=assetsTypeService.isnull(assetsTypeId.toString());
		List lists=assetsTypeService.findAllByPid(assetsTypeId);
		if(list.size()==0&&lists.size()==0){
			assetsTypeService.deleteById(assetsTypeId);
			message=2+"";
		}else{
			message=1+"";
		}
		return "success";
	}

	public String update() throws Exception {
		assetsTypeService.update(assetsType);
		message=2+"";
		return "success";
	}

	public void setAssetsTypeList(List assetsTypeList) {
		this.assetsTypeList = assetsTypeList;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
