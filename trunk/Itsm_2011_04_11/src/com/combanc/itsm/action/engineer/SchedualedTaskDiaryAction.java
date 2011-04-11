package com.combanc.itsm.action.engineer;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.SchedualedTaskDiary;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.SchedualedTaskDiaryService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.FileUtility;
import com.combanc.itsm.util.StringUtil;

public class SchedualedTaskDiaryAction extends BaseActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	private SchedualedTaskDiaryService stdService;
	private FileUpDownService fileUpDownService;
	
	private SchedualedTaskDiary std;
	private List<SchedualedTaskDiary> stdList;
	
	private static final int BUFFER_SIZE = 16 * 1024;
	private File[] file;
	private String[] fileFileName;
	
	public String input(){
		return SUCCESS;
	}
	
	public String save(){
		if(std==null||std.getTitle()==null||std.getTitle().trim().equals("")||std.getContent()==null) {
			return ERROR;				
		}else {
			try{
				Users user=(Users) ServletActionContext.getRequest().getSession().getAttribute("user");
				std.setUsers(user);
				std.setSubmitTime(new Timestamp(new Date().getTime()));
				stdService.save(std);
				if(this.getFile()!=null){
		        	fileUpload(std.getId());//调用上传文件方法
		        } 
				return SUCCESS;
			}catch(Exception e) {
				return ERROR;
			}
		}
	}
	
	public String list(){
		try{
			stdList=stdService.findByTaskDetailId(std.getTaskDetailId());
			for(SchedualedTaskDiary std:stdList){
				std.setAccessoryList(fileUpDownService.getAccessorys("schedualed_task_diary", std.getId()));
			}
			return SUCCESS;
		}catch(Exception e) {
			return ERROR;
		}		
	}
	
	public void fileUpload(Integer id) {		
		Timestamp ts=new Timestamp(new Date().getTime());
    	for(int i=0;i<this.getFile().length;i++){ 
    		String fileName= StringUtil.generateFileName(fileFileName[i]);
    		String url=ServletActionContext.getServletContext().getRealPath("/upload")+ "/" +fileName;
			File dstFile = new File(url);
			FileUtility.copy(file[i], dstFile);	
			
			Accessory accessory=new Accessory();
			accessory.setName(fileName);
			accessory.setTrueName(fileFileName[i]);
			accessory.setTableId(id);
			accessory.setTableName("schedualed_task_diary");
			accessory.setUploadDate(ts);
			accessory.setUrl("/upload/"+fileName);
			fileUpDownService.addUpFileInfo(accessory);			
    	}
	}

	public SchedualedTaskDiaryService getStdService() {
		return stdService;
	}

	public void setStdService(SchedualedTaskDiaryService stdService) {
		this.stdService = stdService;
	}

	public SchedualedTaskDiary getStd() {
		return std;
	}

	public void setStd(SchedualedTaskDiary std) {
		this.std = std;
	}

	public List<SchedualedTaskDiary> getStdList() {
		return stdList;
	}

	public void setStdList(List<SchedualedTaskDiary> stdList) {
		this.stdList = stdList;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}
	
}
