package com.combanc.itsm.action.systemmanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.KnowledgeBase;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.service.KnowledgeBaseService;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.FileUtility;
import com.combanc.itsm.util.StringUtil;
import com.combanc.itsm.util.ZhangYongUtil;

import flex.messaging.util.URLDecoder;

public class KnowledgeBaseAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<KnowledgeBase> knowledgeBases;
	private String flag;
	private String actionURI;
	private Integer knowledgeBaseId;
	private KnowledgeBaseService knowledgeBaseService;
	private KnowledgeBase knowledgeBase;
	private List<ServiceCategory> serviceCategory;
	private ServiceCategoryService serviceCategoryService;
	private String message;
	private String type;
	
	
	private String trueName;
	private FileUpDownService fileUpDownService;
	private static final int BUFFER_SIZE = 16 * 1024;
	private File[] file;
	private String[] fileFileName;
	private String[] contentType;
	private List<Accessory> accessoryList;
	private String dlFileName;

	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private Integer accessoryId;
	public Integer getAccessoryId() {
		return accessoryId;
	}
	public void setAccessoryId(Integer accessoryId) {
		this.accessoryId = accessoryId;
	}
	public String deleteaccessary(){
		if(accessoryId!=null&&accessoryId!=0) fileUpDownService.delAcessoryByAcessoryId(accessoryId);	
		return "success";
	}
	public String list() throws Exception {//转到工程师模式
//		this.pageBean = knowledgeBaseService.query(knowledgeBase, pageSize,
//				page);

		return "success";
	}
	
	public String list2() throws Exception {//转到用户模式
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		if(knowledgeBase==null){
			this.setMessage(ZhangYongUtil.SAVE_ERROR);
			return "input";
		}else{
			if(
				knowledgeBase.getTitle() == null ||knowledgeBase.getTitle().equals("")
				||knowledgeBase.getSymptom() == null ||knowledgeBase.getSymptom().equals("")
				||knowledgeBase.getCategoryId()==null ||knowledgeBase.getCategoryId().getId()==null
				||knowledgeBase.getCategoryId().getId() == -1 ||knowledgeBase.getEngineerId()==null
				||knowledgeBase.getEngineerId().getId()==null ||knowledgeBase.getEngineerId().getId() == -1) {
				this.setMessage(ZhangYongUtil.SAVE_ERROR);
			    return "input";
			}
		}
		String formatDate = new SimpleDateFormat("yyMMddHHmmss") .format(new Date());
		int random = new Random().nextInt(10000);
		knowledgeBase.setIndexcode("Combanc"+formatDate+random);
		
		String solution = knowledgeBase.getSolution().replaceAll( "((\r?\n)+|\t*)", "");
		knowledgeBase.setSolution(solution);
	
//		KnowledgeBase knowledgeBase1= new KnowledgeBase(knowledgeBase.getId(),knowledgeBase.getTitle(),knowledgeBase.getCategoryId(),
//				knowledgeBase.getSymptom(),knowledgeBase.getFileName(),knowledgeBase.getSolution(),knowledgeBase.getEngineerId(),knowledgeBase.getCurrentTime());
		knowledgeBase.setMode("1");
		knowledgeBaseService.save(knowledgeBase);
		knowledgeBase=knowledgeBaseService.findById(knowledgeBase.getId());
		 if(this.getFile()!=null){
	        	fileUpload();//调用上传文件方法
	        } 
		 
		this.setMessage(ZhangYongUtil.SAVE);
		return "success";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		accessoryList = fileUpDownService.getAccessorys("knowledge_base",knowledgeBaseId);
		knowledgeBase = knowledgeBaseService.findById(knowledgeBaseId);
		return "success";
	}

	public String update() throws Exception {
		knowledgeBaseService.update(knowledgeBase);
		 if(this.getFile()!=null&&this.getFileFileName()!=null&&this.getFileFileName().length>0){
	        	fileUpload();//调用上传文件方法
	        } 
		return "list";
	}

	public String delete() throws Exception {
		knowledgeBaseService.deleteById(knowledgeBaseId);
		return "success";
	}

	public String show() throws Exception {
		//knowledgeBaseService.findById(knowledgeBase.getId());
		accessoryList = fileUpDownService.getAccessorys("knowledge_base",knowledgeBase.getId()); // 和知识库相关联的附件列表
		knowledgeBase = knowledgeBaseService.findById(knowledgeBase.getId());
		return "success";
	}

	public String user_show() throws Exception {
		//knowledgeBaseService.findById(knowledgeBase.getId());
		accessoryList = fileUpDownService.getAccessorys("knowledge_base",knowledgeBaseId); // 和知识库相关联的附件列表
		knowledgeBase = knowledgeBaseService.findById(knowledgeBaseId);
		return "success";
	}

	public String search(){ //工程师-类别下的方案查找
		this.pageBean = knowledgeBaseService.findByCategoryId(knowledgeBase, pageSize,
				page);
		this.setActionURI("search");
		return "success";
	}
	public String search2(){ //用户-类别下的方案查找
		knowledgeBases = knowledgeBaseService.findByCategoryId2(knowledgeBase);
		this.setActionURI("search2");
		return "success";
	}
	public String query() throws Exception { // 工程师角色查询
		//if(knowledgeBase!=null&&knowledgeBase.getTitle()!=null&&knowledgeBase.getTitle().trim().length()!=0)
			//knowledgeBase.setTitle( new String(knowledgeBase.getTitle().getBytes("ISO-8859-1"),"UTF-8"));   
		this.pageBean = knowledgeBaseService.query2(knowledgeBase, pageSize,
				page,type,flag);
		this.setActionURI("query");
		return "success";
	}

	public String query2() throws Exception { // 用户查询  
		//if(knowledgeBase!=null&&knowledgeBase.getTitle()!=null&&knowledgeBase.getTitle().trim().length()!=0)
			//knowledgeBase.setTitle( new String(knowledgeBase.getTitle().getBytes("ISO-8859-1"),"UTF-8")); 
		this.pageBean = knowledgeBaseService.query2(knowledgeBase, pageSize,
				page,type,flag);
		this.setActionURI("query2");
		return "success";
	}
	
	public String queryFromIncident() throws Exception { // 用户查询  
		if(knowledgeBase!=null&&knowledgeBase.getTitle()!=null&&knowledgeBase.getTitle().trim().length()!=0)
			knowledgeBase.setTitle( new String(knowledgeBase.getTitle().getBytes("ISO-8859-1"),"UTF-8")); 
		this.pageBean = knowledgeBaseService.query2(knowledgeBase, pageSize,
				page,type,flag);
		this.setActionURI("query2");
		return "success";
	}
	
	public String main() throws Exception {
		return "success";
	}
	
	public String download() throws Exception {// 文件下载
		try{
			String url=ServletActionContext.getServletContext().getRealPath("/upload")+ "/" +this.dlFileName;
			File   file=new   File(url); 
            if(!file.exists()||!file.isFile()) return ERROR;
			this.dlFileName="/upload/"+this.dlFileName;			
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	
	public InputStream getDownloadFile() {
		return ServletActionContext.getServletContext().getResourceAsStream(this.getDlFileName());
	}

//	public String download() throws Exception {
//		this.trueName = fileUpDownService.getAccessoryByName(dlFileName)
//				.getTrueName();
//		this.dlFileName = "/upload/" + this.dlFileName;
//
//		return SUCCESS;
//
//	}

 // 文件上传
		public void fileUpload() {
			System.out.println("文件大小" + this.getFile().length);
			System.out.println("文件有" + this.getFile());
			for (int i = 0; i < this.getFile().length; i++) {
				String name = StringUtil.generateFileName(fileFileName[i]);
				String url = ServletActionContext.getServletContext().getRealPath("/Upload")+ "/" + name;
				File dstFile = new File(url);
				System.out.println("文件路径" + dstFile);
				FileUtility.copy(file[i], dstFile);
				
				Accessory accessory = new Accessory();
				accessory.setName(name);
				accessory.setTrueName(fileFileName[i ]);
				accessory.setTableId(knowledgeBase.getId());
				accessory.setTableName("knowledge_base");
				Date date = new Date();
				Timestamp ts = new Timestamp(date.getTime());
				knowledgeBase.setCurrentTime(ts);
				accessory.setUploadDate(knowledgeBase.getCurrentTime());
				accessory.setUrl(url);
				fileUpDownService.addUpFileInfo(accessory);
			}

		}
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				// 输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int i = 0;
				while ((i = in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
					i = 0;
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ServiceCategory> getServiceCategory() {
		return serviceCategory;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public List<KnowledgeBase> getKnowledgeBases() {
		return knowledgeBases;
	}

	public void setKnowledgeBases(List<KnowledgeBase> knowledgeBases) {
		this.knowledgeBases = knowledgeBases;
	}

	public Integer getKnowledgeBaseId() {
		return knowledgeBaseId;
	}

	public void setKnowledgeBaseId(Integer knowledgeBaseId) {
		this.knowledgeBaseId = knowledgeBaseId;
	}

	public KnowledgeBaseService getKnowledgeBaseService() {
		return knowledgeBaseService;
	}

	public void setKnowledgeBaseService(
			KnowledgeBaseService knowledgeBaseService) {
		this.knowledgeBaseService = knowledgeBaseService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public void setServiceCategory(List<ServiceCategory> serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}



	public String[] getContentType() {
		return contentType;
	}

	public void setContentType(String[] contentType) {
		this.contentType = contentType;
	}

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public String getDlFileName() {
		return dlFileName;
	}

	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
