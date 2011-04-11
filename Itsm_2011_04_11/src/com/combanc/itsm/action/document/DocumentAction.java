/**
 * 
 */
package com.combanc.itsm.action.document;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

import net.fckeditor.response.GetResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.transaction.annotation.Transactional;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.Document;
import com.combanc.itsm.pojo.DocumentCat;
import com.combanc.itsm.pojo.DocumentOld;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.DocumentCatService;
import com.combanc.itsm.service.DocumentService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.FileStorage;
import com.combanc.itsm.util.FileUtility;
import com.combanc.itsm.util.StringUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Administrator
 * 
 */
public class DocumentAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2285903557415825601L;
	private Document dc;
	private DocumentOld dcold;
	private int document_id;
	private int acessory_id;

	private DocumentService documentService;
	private DocumentCatService documentCatService;
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private List<DocumentCat> documentCats;
	private DocumentCat cat;
	private int pid;
	private File[] file;
	private String[] fileName;
	private String dlFileName;
	private String trueName;
	private FileUpDownService fileUpDownService;
	private String actionURI;
	private List<Accessory> accessoryList;
	private static String DOCUMENT = "document";
	private static String DOCUMENTOLD = "documentold";
	private String msg;
	private int version;
	private String delstrings;



	/**
	 * @return the delstrings
	 */
	public String getDelstrings() {
		return delstrings;
	}

	/**
	 * @param delstrings
	 *            the delstrings to set
	 */
	public void setDelstrings(String delstrings) {
		this.delstrings = delstrings;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the acessory_id
	 */
	public int getAcessory_id() {
		return acessory_id;
	}

	/**
	 * @param acessoryId
	 *            the acessory_id to set
	 */
	public void setAcessory_id(int acessoryId) {
		acessory_id = acessoryId;
	}

	/**
	 * @return the dcold
	 */
	public DocumentOld getDcold() {
		return dcold;
	}

	/**
	 * @param dcold
	 *            the dcold to set
	 */
	public void setDcold(DocumentOld dcold) {
		this.dcold = dcold;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the accessoryList
	 */
	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	/**
	 * @param accessoryList
	 *            the accessoryList to set
	 */
	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	/**
	 * @return the actionURI
	 */
	public String getActionURI() {
		return actionURI;
	}

	/**
	 * @param actionURI
	 *            the actionURI to set
	 */
	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	/**
	 * @return the fileUpDownService
	 */
	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	/**
	 * @param fileUpDownService
	 *            the fileUpDownService to set
	 */
	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	/**
	 * @return the fileName
	 */
	public String[] getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the dlFileName
	 */
	public String getDlFileName() {
		return dlFileName;
	}

	/**
	 * @param dlFileName
	 *            the dlFileName to set
	 */
	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	/**
	 * @return the trueName
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * @param trueName
	 *            the trueName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * @return the file
	 */
	public File[] getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File[] file) {
		this.file = file;
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * @return the cat
	 */
	public DocumentCat getCat() {
		return cat;
	}

	/**
	 * @param cat
	 *            the cat to set
	 */
	public void setCat(DocumentCat cat) {
		this.cat = cat;
	}

	/**
	 * @return the documentCats
	 */
	public List<DocumentCat> getDocumentCats() {
		return documentCats;
	}

	/**
	 * @param documentCats
	 *            the documentCats to set
	 */
	public void setDocumentCats(List<DocumentCat> documentCats) {
		this.documentCats = documentCats;
	}

	/**
	 * @return the documentCatService
	 */
	public DocumentCatService getDocumentCatService() {
		return documentCatService;
	}

	/**
	 * @param documentCatService
	 *            the documentCatService to set
	 */
	public void setDocumentCatService(DocumentCatService documentCatService) {
		this.documentCatService = documentCatService;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean
	 *            the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the documentService
	 */
	public DocumentService getDocumentService() {
		return documentService;
	}

	/**
	 * @param documentService
	 *            the documentService to set
	 */
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	/**
	 * @return the dc
	 */
	public Document getDc() {
		return dc;
	}

	/**
	 * @param dc
	 *            the dc to set
	 */
	public void setDc(Document dc) {
		this.dc = dc;
	}

	/**
	 * @return the document_id
	 */
	public int getDocument_id() {
		return document_id;
	}

	/**
	 * @param documentId
	 *            the document_id to set
	 */
	public void setDocument_id(int documentId) {
		document_id = documentId;
	}

	public String save() {
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));

		if (dc != null) {

			DocumentCat cat = new DocumentCat();

			if (pid > 0)
				cat.setId(pid);
			else {
				cat.setId(0);
			}
			dc.setCat(cat);

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			dc.setCreateTime(timestamp);
			dc.setSubmitTime(timestamp);
			dc.setUsers(u);
			dc.setUserName(u.getTruename());
			dc.setDepartment(u.getDepartment());
			dc.setDepartmentName(u.getDepartment().getName());
			dc.setVersion(1);
			dc.setVersionChian("%" + dc.getVersion());
			String num = StringUtil.getRequestNo();
			dc.setNum(num);
			dc.setCatcode(dc.getCat().getCode());

			documentService.save(dc);
			// dc=documentService
			

			if (this.getFile() != null) {
				dc = documentService.getByNum(num);
				fileUpload(dc);// 调用上传文件方法
			}

			return SUCCESS;

		} else {

			addActionMessage("dc is null!");
			return ERROR;
		}

	}

	public String add() {
		return SUCCESS;
	}

	public String update() {

		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		if (dc != null) {

			Document tmpdc = documentService.get(dc.getId());

			// 保存旧版本
			DocumentOld newDocument = new DocumentOld();
			// newDocument.setCat(tmpdc.getCat());
			newDocument.setContext(tmpdc.getContext());
			newDocument.setCreateTime(tmpdc.getCreateTime());
			// newDocument.setDepartment(tmpdc.getDepartment());
			newDocument.setDepartmentName(tmpdc.getDepartmentName());
			newDocument.setDepartmentId(tmpdc.getDepartment().getId());
			newDocument.setKeyword(tmpdc.getKeyword());
			newDocument.setNum(tmpdc.getNum());
			newDocument.setSubmitTime(tmpdc.getSubmitTime());
			newDocument.setSummary(tmpdc.getSummary());
			newDocument.setTitle(tmpdc.getTitle());
			newDocument.setUserName(tmpdc.getUserName());
			newDocument.setVersion(tmpdc.getVersion());
			newDocument.setVersionChain(tmpdc.getVersionChian());
			newDocument.setCatId(tmpdc.getCat().getId());
			documentService.saveOld(newDocument);

			// 附件库增加历史版本记录
			List<Accessory> accessories = fileUpDownService
					.getAccessoryByTableNameAndTableIdAndVersion(DOCUMENT,
							tmpdc.getId(), tmpdc.getVersion());
			if (accessories != null) {
				for (Accessory a : accessories) {
					a.setTableId(newDocument.getId());
					a.setVersion(newDocument.getVersion());
					a.setTableName(DOCUMENTOLD);
					fileUpDownService.addUpFileInfo(a);

				}
				accessories = fileUpDownService
						.getAccessoryByTableNameAndTableIdAndVersion(DOCUMENT,
								tmpdc.getId(), tmpdc.getVersion());
				for (Accessory b : accessories) {
					b.setTableName(DOCUMENT);
					b.setVersion(tmpdc.getVersion() + 1);
					fileUpDownService.updateAccessory(b);
				}
			}

			// 删除就附件
			if (delstrings!=null&&!delstrings.equals("")) {
				String[] delAccessrieStrings = delstrings.split("%");
				for (String s : delAccessrieStrings) {
					fileUpDownService.delAcessoryByAcessoryId(Integer
							.parseInt(s));
				}

			}
			// 增加新版本
			tmpdc.setVersionChian(tmpdc.getVersionChian() + "%"
					+ (tmpdc.getVersion() + 1));
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			tmpdc.setVersion(tmpdc.getVersion() + 1);
			tmpdc.setSubmitTime(timestamp);
			tmpdc.setUsers(u);
			tmpdc.setUserName(u.getTruename());
			tmpdc.setDepartment(u.getDepartment());
			tmpdc.setDepartmentName(u.getDepartment().getName());

			tmpdc.setContext(dc.getContext());
			tmpdc.setKeyword(dc.getKeyword());
			tmpdc.setSummary(dc.getSummary());
			tmpdc.setTitle(dc.getTitle());
			documentService.update(tmpdc);
			// 上传新附件
			if (this.getFile() != null) {

				fileUpload(tmpdc);// 调用上传文件方法
			}

			msg = "修改成功！";
			return SUCCESS;

		} else {

			msg = "修改失败！";
			return ERROR;
		}

	}

	public String showById() {

		if (document_id > 0) {
			dc = documentService.get(document_id);
			return SUCCESS;
		} else {
			addActionError("has no document");
			return ERROR;
		}
	}

	public String queryByDocumentParms() {
		DocumentCat docts=null;
		String catcodeString=null;
		if(pid>0)
		{
		docts= documentCatService.findById(pid);
		catcodeString=docts.getCode();
		}
		this.pageBean = documentService.queryForPage(dc, pageSize, page, catcodeString);
		String aString = getRequest().getParameter("msg");
		return "success";

	}

	public String top() {

		documentCats = documentCatService.findAll();
		return SUCCESS;

	}

	public String main() {
		return SUCCESS;
	}

	public String updateInput() {

		dc = documentService.get(document_id);

		return SUCCESS;
	}

	public String delete() {
		Document tmpdocument = documentService.get(document_id);
		String dnum = tmpdocument.getNum();

		boolean fileisok = fileUpDownService.deleteByIdAndTableName(
				document_id, DOCUMENT, FileStorage
						.getUpLoadPath(ServletActionContext.getRequest()));
		List<DocumentOld> oldlistDocumentOlds = documentService
				.getOldByNum(dnum);
		boolean oldfileisok = true;
		for (DocumentOld oo : oldlistDocumentOlds) {
			oldfileisok = fileUpDownService.deleteByIdAndTableName(oo.getId(),
					DOCUMENTOLD, FileStorage.getUpLoadPath(ServletActionContext
							.getRequest()));
		}
		boolean isok = documentService.del(document_id);
		if (isok && fileisok && oldfileisok) {
			msg = "删除成功！";

			return SUCCESS;
		} else {
			msg = "删除失败！";
			return ERROR;
		}
	}

	public String show() {

		dc = documentService.get(document_id);
		accessoryList = fileUpDownService
				.getAccessoryByTableNameAndTableIdAndVersion(DOCUMENT, dc
						.getId(), dc.getVersion());
		if (dc == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	public void fileUpload(Document dt) {
		System.out.println("文件大小" + this.getFile().length);
		System.out.println("文件有" + this.getFile());
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String[] paths = request.getParameterValues("file");
		// this.setFile(paths);
		fileName = fileName[0].toString().split("%");
		for (int i = 0; i < this.getFile().length; i++) {
			String name = StringUtil.generateFileName(fileName[i + 1]);
			String tmpurl = "/document/" + name;
			String url = FileStorage.getUpLoadPath(ServletActionContext
					.getRequest())
					+ tmpurl;

			File dstFile = new File(url);
			try {
				FileUtils.forceMkdir(dstFile.getParentFile());
			} catch (IOException e) {
				logger.error("创建文件失败！");
				e.printStackTrace();
			}// 创建上传tempFile文件所在的父目录文件;
			System.out.println("文件路径" + dstFile);
			FileUtility.copy(file[i], dstFile);
			Accessory accessory = new Accessory();
			accessory.setName(name);
			accessory.setTrueName(fileName[i + 1]);

			accessory.setTableId(dt.getId());
			accessory.setTableName("document");
			accessory.setUploadDate(dt.getSubmitTime());
			accessory.setUrl(tmpurl);
			accessory.setVersion(dt.getVersion());
			fileUpDownService.addUpFileInfo(accessory);
		}

	}

	public String down() throws Exception {
		this.trueName = fileUpDownService.getAccessoryByName(dlFileName)
				.getTrueName();
		this.dlFileName = "/upload/document/" + this.dlFileName;

		return SUCCESS;

	}

	public InputStream getInputStream() {

		return ServletActionContext.getServletContext().getResourceAsStream(
				"/" + dlFileName);

	}

	public String index_update() {
		dc = documentService.get(document_id);
		accessoryList = fileUpDownService
				.getAccessoryByTableNameAndTableIdAndVersion(DOCUMENT, dc
						.getId(), dc.getVersion());
		if (dc == null) {
			return ERROR;
		}
		return SUCCESS;

	}

	public String queryHistoryByDocumentParms() {

		this.pageBean = documentService.queryHistoryForPage(dc, pageSize, page,
				pid);
		String aString = getRequest().getParameter("msg");
		return "success";

	}

	public String showhistory() {

		dcold = documentService.gethistory(dc.getNum(), dc.getVersion());
		if (dcold == null) {
			msg = "查询无结果！";
			return ERROR;
		}
		accessoryList = fileUpDownService
				.getAccessoryByTableNameAndTableIdAndVersion(DOCUMENTOLD, dcold
						.getId(), dcold.getVersion());

		return SUCCESS;
	}

	public String deleteAcessoryByAcessoryId() {

		boolean isok = fileUpDownService.delAcessoryByAcessoryId(acessory_id);
		if (isok) {

			msg = "删除附件成功！";
			return SUCCESS;
		} else {
			msg = "";
			return ERROR;
		}
	}

	public String downhistory() throws Exception {
		this.trueName = fileUpDownService.getAccessoryByNameAndVersion(
				dlFileName, version).getTrueName();
		this.dlFileName = "/upload/document/" + this.dlFileName;

		return SUCCESS;

	}
}
