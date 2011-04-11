package com.combanc.itsm.action.systemnotice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.SystemNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.notice.SystemNoticeService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.FileStorage;
import com.combanc.itsm.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import static java.io.File.separator;

@SuppressWarnings("serial")
public class SystemNoticeAction extends ActionSupport {
	private long id;
	private String authorName;
	private String title;
	private String content;
	private File file;// 对应jsp中file文件名字;
	private String fileFileName;// 表示从客户端更新时,上传的文件名;
	private SystemNoticeService systemNoticeService;
	private int page;
	private PageBean pageBean;
	private int pageSize = 6;
	private SystemNotice systemNotice;
	private String fileName;
	private String randomName;
	private String randomFileName;
	private static final String SYSTEMNOTICE = "system_notice";
	private static String SYSTEM_NOTICE_STORAGE = "systemNoticeStorage";
	private List<SystemNotice> snList;
	private String dlFileName;
	private FileUpDownService fileUpDownService;
	private List<Accessory> accessoryList;
	
	public String getRandomFileName() {
		return randomFileName;
	}

	public void setRandomFileName(String randomFileName) {
		this.randomFileName = randomFileName;
	}

	public static String getSYSTEM_NOTICE_STORAGE() {
		return SYSTEM_NOTICE_STORAGE;
	}

	public static void setSYSTEM_NOTICE_STORAGE(String sYSTEMNOTICESTORAGE) {
		SYSTEM_NOTICE_STORAGE = sYSTEMNOTICESTORAGE;
	}

	public static String getSystemnotice() {
		return SYSTEMNOTICE;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRandomName() {
		return randomName;
	}

	public void setRandomName(String randomName) {
		this.randomName = randomName;
	}

	public SystemNotice getSystemNotice() {
		return systemNotice;
	}

	public void setSystemNotice(SystemNotice systemNotice) {
		this.systemNotice = systemNotice;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public SystemNoticeService getSystemNoticeService() {
		return systemNoticeService;
	}

	public void setSystemNoticeService(SystemNoticeService systemNoticeService) {
		this.systemNoticeService = systemNoticeService;
	}

	public InputStream getDownloadFile() {
		return ServletActionContext.getServletContext().getResourceAsStream(
				this.getDlFileName());
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String download() throws Exception {
		if (this.getDlFileName() != null)
			this.dlFileName = "/upload/systemNoticeStorage/" + this.dlFileName;
		return SUCCESS;
	}

	// 列表显示;
	public String listSystemNotice() throws Exception {

		if (pageSize < 10) {
			pageSize = 6;
		}
		this.pageBean = this.systemNoticeService.queryForPage(Integer
				.valueOf(pageSize), page);
		return SUCCESS;
	}
	
	public String listSystemNotice1() throws Exception {		
		this.pageBean = this.systemNoticeService.queryForPage(pageSize, page);
		for(int i=0;i<this.pageBean.getList().size();i++) {
			SystemNotice sn=(SystemNotice) this.pageBean.getList().get(i);
			if(sn.getTitle().length()>22) sn.setTitle(sn.getTitle().substring(0, 21)+"...");
		}
		return SUCCESS;
	}

	public String saveSystemNotice() throws Exception {
		
		Users users =(Users)ServletActionContext.getRequest().getSession().getAttribute("user");
		if (users != null) {
			authorName = systemNoticeService.getCurrentAuthorName(users);
		}
		return SUCCESS;
	}
	// 1.增;
	public String addSystemNotice() throws Exception {
		
		Users user = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		SystemNotice systemNotice = new SystemNotice();
		String fileName = fileFileName;// 获得上传文件的真名;
		systemNotice.setDate(new Date(System.currentTimeMillis()));
		if (user != null) {
			systemNotice.setAuthorName(systemNoticeService
					.getCurrentAuthorName(user));
		}
		systemNotice.setContent(content);
		systemNotice.setTitle(title);
		systemNotice.setFileName(fileFileName);
		if(null!=fileName&&!fileName.equals("")){
			randomFileName = StringUtil.generateFileName(fileName);
			systemNotice.setRandomName(randomFileName);
		}
		// 将上传的文件保存到服务器的硬盘上;
		if (file != null) {
			InputStream is = new FileInputStream(file.getAbsolutePath());
			// 将file对象转化成文件的输入流;
			HttpServletRequest request = ServletActionContext.getRequest();// 获得ServletRequest对象;
			// temFile 对应要写到硬盘上的文件;
			File tempFile = new File(FileStorage
					.getSystemNoticeStorage(request)
					+ separator + randomFileName);
			FileUtils.forceMkdir(tempFile.getParentFile());// 创建上传tempFile文件所在的父目录文件;
			// 构造输出流;
			OutputStream os = new FileOutputStream(tempFile);
			int len = 0;
			byte[] buffer = new byte[500];
			while (-1 != (len = is.read(buffer))) {// 将is读到buffer里面去,判断其长度是否为-1;
				os.write(buffer, 0, len);// 把buffer里面的内容读到buffer里面;
			}
			is.close();
			os.close();
		}
		this.systemNoticeService.saveSystemNotice(systemNotice);//保存公告
		if(file!=null){//当有上传文件进行附件保存
			Accessory accessory = new Accessory();
			accessory.setName(randomFileName);
			accessory.setTrueName(fileName);
			if (systemNotice.getId().intValue() > 0) {
				accessory.setTableId(systemNotice.getId().intValue());
			}
			accessory.setUploadDate(new Timestamp(new Date().getTime()));
			accessory.setUrl("/upload/systemNoticeStorage/" + randomFileName);
			accessory.setTableName(SYSTEMNOTICE);
			fileUpDownService.addUpFileInfo(accessory);
		}
		return SUCCESS;
	}

	// 2.删;
	public String deleteSystemNotice() throws Exception {

		SystemNotice systemNotice = this.systemNoticeService
				.getSystemNoticeById(id);
		HttpServletRequest request = ServletActionContext.getRequest();
		// 表示对应到硬盘上的要删除的文件;
		if(systemNotice.getRandomName()!=null&&!systemNotice.getRandomName().equals("")){
			File file = new File(FileStorage.getSystemNoticeStorage(request)
					+ separator + systemNotice.getRandomName());
			file.delete();
		}
		int sid = systemNotice.getId().intValue();
		List<Accessory> list= fileUpDownService.getAccessorys(SYSTEMNOTICE,sid);
		if(list != null && !list.isEmpty()){
			int tableId = list.get(0).getId();
			fileUpDownService.delAcessoryByAcessoryId(tableId);
		}
		this.systemNoticeService.deleteSystemNoticeById(id);// 删除数据库中得文件;
		return SUCCESS;
	}

	// 3.更新之前先取出来;
	public String updatePSystemNotice() throws Exception {
		// 看看这两周种取文件名的区别:
		// System.out.println("file: " + file.getName());
		// System.out.println("fileFileName: " + fileFileName);
		SystemNotice systemNotice = this.systemNoticeService.getSystemNoticeById(id);
		this.fileName = systemNotice.getFileName();
		int sid = systemNotice.getId().intValue();
		accessoryList = fileUpDownService.getAccessorys("system_notice",sid);
		this.setSystemNotice(systemNotice);// 然后将查出来的SystemNotice对象SET到对象里面去，然后将该对象传到下一个需要更新的页面上去；
		return SUCCESS;
	}

	// 4.然后再更新;
	public String updateSystemNotice() throws Exception {
		SystemNotice systemNotice = this.systemNoticeService.getSystemNoticeById(id);
		String fileName = systemNotice.getFileName();// 获取文件的名字,服务器上已有的文件的名字;fileName表示被更新的文件名;
		String oldRandomFileName = systemNotice.getRandomName();// 获得旧文件的随机文件名;
		systemNotice.setAuthorName(authorName);
		systemNotice.setTitle(title);
		systemNotice.setContent(content);
		// systemNotice.setDate(Date);
		if(fileFileName!=null&& !fileFileName.equals("")){
			systemNotice.setFileName(fileFileName);
			randomFileName = StringUtil.generateFileName(fileFileName);
			systemNotice.setRandomName(randomFileName);
		}
		// 然后调用service方法,将更新完的对象保存到数据库里面;
		HttpServletRequest request = ServletActionContext.getRequest();// 获取ServletRequest对象;
		if(null!=oldRandomFileName&&!oldRandomFileName.equals("")&&null!=file){
			File oldFile = new File(FileStorage.getSystemNoticeStorage(request)
					+ separator + oldRandomFileName);// 构造旧的文件;
			oldFile.delete();// 删除旧文件;
		//附件表里的记录；
		int sid = systemNotice.getId().intValue();
		List<Accessory> list= fileUpDownService.getAccessorys(SYSTEMNOTICE,sid);
		if(list != null && !list.isEmpty()){
			int tableId = list.get(0).getId();
			if(tableId>0){
				fileUpDownService.delAcessoryByAcessoryId(tableId);
				}
			}
		}
		if(null!=file){
			File newFile = new File(FileStorage.getSystemNoticeStorage(request)
					+ separator + randomFileName);// 构造新的文件;
			InputStream is = new FileInputStream(file);// 构造更新的上传文件的输入流;
			FileUtils.forceMkdir(newFile.getParentFile());// 往硬盘上写文件;创建目录;
			OutputStream os = new FileOutputStream(newFile);
			int len = 0;
			byte[] buffer = new byte[500];
			while (-1 != (len = is.read(buffer))) {
				os.write(buffer, 0, len);
			}
			is.close();
			os.close();
			//当有上传文件进行附件保存
			Accessory accessory = new Accessory();
			accessory.setName(randomFileName);
			accessory.setTrueName(fileFileName);
			if (systemNotice.getId().intValue() > 0) {
				accessory.setTableId(systemNotice.getId().intValue());
			}
			accessory.setUploadDate(new Timestamp(new Date().getTime()));
			accessory.setUrl("/upload/systemNoticeStorage/" + randomFileName);
			accessory.setTableName(SYSTEMNOTICE);
			fileUpDownService.addUpFileInfo(accessory);
			
		}
		this.systemNoticeService.updateSystemNotice(systemNotice);
	
		
		return SUCCESS;
	}

	// 5.信息查看;
	public String detailSystemNotice() throws Exception {

		SystemNotice syemNotice = this.systemNoticeService
				.getSystemNoticeById(id);
		accessoryList = fileUpDownService.getAccessorys("system_notice",
				syemNotice.getId().intValue()); // 和工单相关联的附件列表
		ActionContext.getContext().put("syemNotice", syemNotice);// 执行将list放到request里面;
		return SUCCESS;

	}

	// 6.快速查询;
	public String querySystemNotice() throws Exception {

		String hql = "from SystemNotice as bean where 1=1";

		if (title != null && !title.equals("")) {
			title=StringUtil.decrateString(title);
			hql += " and bean.title like '%" + title + "%'";
		}

		if (authorName != null && !authorName.equals("")) {
			authorName=StringUtil.decrateString(authorName);
			hql += " and bean.authorName like '%" + authorName + "%'";
		}
		if(content != null && !content.equals("")){
			content=StringUtil.decrateString(content);
			hql += " and bean.content like '%" + content+ "%'";
		}

		List<SystemNotice> list = this.systemNoticeService
				.listSystemNoticeByHql(hql);
		ActionContext.getContext().put("list", list);// 执行将list放到request里面;
		return SUCCESS;
	}
	
	public String querySystemNotice2() throws Exception {

		String hql = "from SystemNotice as bean where 1=1";

		if (title != null && !title.equals("")) {
			hql += " and bean.title like '%" + title + "%'";
		}

		if (authorName != null && !authorName.equals("")) {
			hql += " and bean.authorName like '%" + authorName + "%'";
		}
		
		if(content != null && !content.equals("")){
			hql += " and bean.content like '%" + content+ "%'";
		}

		List<SystemNotice> list = this.systemNoticeService
				.listSystemNoticeByHql(hql);
		ActionContext.getContext().put("list", list);// 执行将list放到request里面;
		return SUCCESS;
	}

	@Action(description = "用户查看当前公告")
	public String findNowNotices() {
		if (pageSize < 10) {
			pageSize = 10;
		}
		this.pageBean = this.systemNoticeService.queryForPage(Integer
				.valueOf(pageSize), page);
		return SUCCESS;

	}

	public String noticeInfo() {
		try {
			Long idLong = Long.valueOf(id);
			systemNotice = systemNoticeService.getSystemNoticeById(idLong);
			return SUCCESS;
		} catch (NumberFormatException e) {
			this.addActionError("id is not right!");
			return ERROR;
		}
	}

	public List<SystemNotice> getSnList() {
		return snList;
	}

	public void setSnList(List<SystemNotice> snList) {
		this.snList = snList;
	}

	public String getDlFileName() {
		return dlFileName;
	}

	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public InputStream getInputStream() {

		return ServletActionContext.getServletContext().getResourceAsStream(
				"/" + dlFileName);

	}
}
