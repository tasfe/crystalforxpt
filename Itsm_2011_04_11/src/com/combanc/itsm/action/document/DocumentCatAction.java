package com.combanc.itsm.action.document;

import java.util.List;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.DocumentCat;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.service.DocumentAuthorityService;
import com.combanc.itsm.service.DocumentCatService;
import com.combanc.itsm.service.DocumentService;
import com.combanc.itsm.service.RoleteamService;

public class DocumentCatAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private DocumentAuthorityService documentAuthorityService;
	private DocumentCatService documentCatService;
	private List<DocumentCat> documentCatList;
	private List<DocumentCat> allDocumentCat;
	private DocumentCat documentCat;
	private Integer documentCatId;
	private List<String> checkbox;
	private String allAuthority;
	private String actionURI;
	private Integer flag = 0;
	private Integer pid = 0;
	private RoleteamService roleteamService;
	private List<Roleteam> allRolteam;
	private DocumentService documentService;
	private String msg;

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the documentService
	 */
	public DocumentService getDocumentService() {
		return documentService;
	}

	/**
	 * @param documentService the documentService to set
	 */
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public String main() throws Exception {
		return "success";
	}

	public String top() throws Exception {
		allDocumentCat = documentCatService.findAll();
		return "success";
	}

	public String list() throws Exception {
		if (0 == flag)
			documentCatList = documentCatService.findAllByPid(pid);
		else
			documentCatList = documentCatService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		allRolteam = roleteamService.findAllByType(RoleteamService.TEAM_TYPE);
		return "success";
	}

	public String save() throws Exception {
		documentCatService.save(documentCat);
		return "success";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		documentCat = documentCatService.findById(documentCatId);
		allRolteam = roleteamService.findAllByType(RoleteamService.TEAM_TYPE);
		allAuthority = documentAuthorityService
				.findAllByDidToString(documentCatId);
		pid = documentCat.getPid();
		return "success";
	}

	public String update() throws Exception {
		
		DocumentCat cat = documentCatService.findById(documentCat.getId());
		cat.setDocument(documentCat.getDocument());
		cat.setDocumentSC(documentCat.getDocumentSC());
		documentCatService.update(cat, checkbox);
		return "success";
	}

	public String delete() throws Exception {
		
		if(!documentService.contains(documentCatId))
		{
			
			if(!documentCatService.containsChild(documentCatId))
			
				{documentCatService.deleteById(documentCatId);
			       msg="删除成功！";
				}
			else{
				msg="目录正在使用，请先删除目录下的子目录再尝试删除目录！";
			}
		}
		else{
			msg="目录正在使用，请先删除目录下的文档再尝试删除目录！";
		}
		return "success";
	}

	public List<DocumentCat> getDocumentCatList() {
		return documentCatList;
	}

	public void setDocumentCatList(List<DocumentCat> documentCatList) {
		this.documentCatList = documentCatList;
	}

	public List<DocumentCat> getAllDocumentCat() {
		return allDocumentCat;
	}

	public void setAllDocumentCat(List<DocumentCat> allDocumentCat) {
		this.allDocumentCat = allDocumentCat;
	}

	public DocumentCat getDocumentCat() {
		return documentCat;
	}

	public void setDocumentCat(DocumentCat documentCat) {
		this.documentCat = documentCat;
	}

	public Integer getDocumentCatId() {
		return documentCatId;
	}

	public void setDocumentCatId(Integer documentCatId) {
		this.documentCatId = documentCatId;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public String getAllAuthority() {
		return allAuthority;
	}

	public void setAllAuthority(String allAuthority) {
		this.allAuthority = allAuthority;
	}

	public List<String> getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(List<String> checkbox) {
		this.checkbox = checkbox;
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

	public List<Roleteam> getAllRolteam() {
		return allRolteam;
	}

	public void setAllRolteam(List<Roleteam> allRolteam) {
		this.allRolteam = allRolteam;
	}

	public void setDocumentAuthorityService(
			DocumentAuthorityService documentAuthorityService) {
		this.documentAuthorityService = documentAuthorityService;
	}

	public void setDocumentCatService(DocumentCatService documentCatService) {
		this.documentCatService = documentCatService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

}
