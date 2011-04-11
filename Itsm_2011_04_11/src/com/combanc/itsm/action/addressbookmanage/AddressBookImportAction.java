package com.combanc.itsm.action.addressbookmanage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AddressBookManage;
import com.combanc.itsm.pojo.CustomerType;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.addressbookmanage.AddressBookManageService;
import com.combanc.itsm.service.addressbookmanage.CustomerTypeService;
import com.combanc.itsm.util.ChineseSpelling;
import com.combanc.itsm.util.StringUtil;

@SuppressWarnings("serial")
public class AddressBookImportAction extends BaseActionSupport implements
		ServletRequestAware {

	private File file;
	private String fileName;
	private AddressBookManageService addressBookManageService;
	private DepartmentService departmentService;
	private CustomerTypeService customerTypeService;
	private List<String> errorList = new ArrayList<String>();
	
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public AddressBookManageService getAddressBookManageService() {
		return addressBookManageService;
	}

	public void setAddressBookManageService(
			AddressBookManageService addressBookManageService) {
		this.addressBookManageService = addressBookManageService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public CustomerTypeService getCustomerTypeService() {
		return customerTypeService;
	}

	public void setCustomerTypeService(CustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}

	public String download() throws Exception {

		return SUCCESS;
	}

	public InputStream getDownloadFile() {
		String idStr = getRequest().getParameter("id");
		int id = Integer.parseInt(idStr);

		if (id == 1) {
			return ServletActionContext.getServletContext()
					.getResourceAsStream(
							"/templates/publicAddressBookExcelTemplate.xls");
		} else {
			return ServletActionContext.getServletContext()
					.getResourceAsStream(
							"/templates/personalAddressBookExcelTemplate.xls");
		}
	}

	public InputStream getDownloadFile2() {
		return ServletActionContext.getServletContext().getResourceAsStream(
				"/templates/personalAddressBookExcelTemplate.xls");
	}

	public String getFileName() throws UnsupportedEncodingException {

		String idStr = getRequest().getParameter("id");
		int id = Integer.parseInt(idStr);

		if (id == 1) {
			fileName = new String("公共通讯录Excel导入模板.xls".getBytes(), "ISO-8859-1");
			return fileName;
		} else {
			fileName = new String("个人通讯录Excel导入模板.xls".getBytes(), "ISO-8859-1");
			return fileName;
		}
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String addressBookImport() throws Exception{
		
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("/templates");
		List<AddressBookManage> listStr =  addressBookManageService.findAll();
		
		if(file != null){
			String targetFileName = StringUtil.generateFileName(fileName);
			File target = new File(targetDirectory,targetFileName);
			try{
				FileUtils.copyFile(file, target);
			}catch(IOException e){
				e.printStackTrace();
			}
			List<AddressBookManage> abList = new ArrayList<AddressBookManage>();
			try{
				Workbook book = Workbook.getWorkbook(new File(targetDirectory + "\\" +targetFileName));
				Sheet sheet = book.getSheet(0);
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				System.out.println("列数----" + columns);
				for(int i = 1;i < rows ;i++){
					String name = sheet.getCell(1, i).getContents().trim();
					String phone = sheet.getCell(2,i).getContents().trim();
					String initials = ChineseSpelling.getFirstAlpha(name);
					AddressBookManage ab = new AddressBookManage();
						if(name != null && phone!= null){
							boolean tag = true;
							for(AddressBookManage abm :listStr){
								if(abm.getUsername().equals(name)&&abm.getPhone().equals(phone)){
									tag = false;
									errorList.clear();
									errorList.add("导入文件内容重复!!");
								 	}
								}
							if(tag){
								if(columns>=1&&columns<11){
										 //导入的公共的通讯录;
									if(!sheet.getCell(0, i).getContents().trim().equals("")&&sheet.getCell(0, i).getContents().trim()!= null){
										int departmentId = Integer.parseInt(sheet.getCell(0, i).getContents().trim());
										Department department = departmentService.findDepartmentById(departmentId);
										ab.setDepartment(department);
										}
										ab.setCustomertype(null);
										ab.setInitials(initials.substring(0, 1));
										if(!sheet.getCell(1, i).getContents().trim().equals("")&&sheet.getCell(1, i).getContents().trim() != null){
										ab.setUsername(sheet.getCell(1, i).getContents().trim());
										}else{
										errorList.add("username is null！");
										}
										ab.setPhone(phone);
										if(sheet.getCell(3,i).getContents().trim()!= null&&!sheet.getCell(3,i).getContents().trim().equals("")){
										ab.setOfficePhone(sheet.getCell(3,i).getContents().trim());
										}else{
										errorList.add("OfficePhone is null！");
										}
										if(sheet.getCell(4, i).getContents().trim()!=null&&!sheet.getCell(4, i).getContents().trim().equals("")){
										ab.setFax(sheet.getCell(4, i).getContents().trim());
										}else{
										errorList.add("Fax is null！");
										}
										if(sheet.getCell(5,i).getContents().trim()!=null&&!sheet.getCell(5,i).getContents().trim().equals("")){
										ab.setEmail(sheet.getCell(5,i).getContents().trim());
										}else{
										errorList.add("Email is null！");
										}
										if(sheet.getCell(6,i).getContents().trim()!=null&&!sheet.getCell(6,i).getContents().trim().equals("")){
										ab.setAddress(sheet.getCell(6,i).getContents().trim());
										}else{
										errorList.add("Address is null！");
										}
										if(sheet.getCell(7, i).getContents().trim()!=null&&!sheet.getCell(7, i).getContents().trim().equals("")){
										ab.setZipCode(sheet.getCell(7, i).getContents().trim());
										}else{
										errorList.add("ZipCode is null！");
										}
										
										if(sheet.getCell(8,i).getContents().trim()!=null&&!sheet.getCell(8,i).getContents().trim().equals("")){
										ab.setRemarks(sheet.getCell(8,i).getContents().trim());
										}else{
										errorList.add("Remarks is null！");
										}
										abList.add(ab);
										errorList.clear();
										errorList.add("公共通讯录导入成功！！");
										}else{
											ab.setDepartment(null);
											if(sheet.getCell(0, i).getContents().trim()!=null&&!sheet.getCell(0, i).getContents().trim().equals("")){
												int customTypeId =Integer.parseInt(sheet.getCell(0, i).getContents().trim());
												CustomerType customerType = customerTypeService.findCustomerTypeById(customTypeId);
												ab.setCustomertype(customerType);
											}
											ab.setInitials(initials.substring(0, 1));
											ab.setUsername(name);
											ab.setPhone(phone);
											if(sheet.getCell(3, i).getContents().trim()!=null&&!sheet.getCell(3, i).getContents().trim().equals("")){
												ab.setDuties(sheet.getCell(3, i).getContents().trim());
											}else{
												errorList.add("Duties is null!");
											}
											if(!sheet.getCell(4, i).getContents().trim().equals("")&&sheet.getCell(4, i).getContents().trim()!=null){
												ab.setCompanyName(sheet.getCell(4, i).getContents().trim());
											}else{
												errorList.add("CompanyName is null!");
											}
											if(sheet.getCell(5, i).getContents().trim()!=null&&!sheet.getCell(5, i).getContents().trim().equals("")){
												ab.setPersonalWebsite(sheet.getCell(5, i).getContents().trim());
											}else{
												errorList.add("PersonalWebsite is null!");
											}
											
											if(sheet.getCell(6, i).getContents().trim()!=null&&!sheet.getCell(6, i).getContents().trim().equals("")){
												ab.setOfficePhone(sheet.getCell(6, i).getContents().trim());
											}else{
												errorList.add("OfficePhone is null!");
											}
											
											if(sheet.getCell(7, i).getContents().trim()!=null&&!sheet.getCell(7, i).getContents().trim().equals("")){
												
												ab.setFax(sheet.getCell(7, i).getContents().trim());
											}else{
												errorList.add("Fax is null!");
											}
											if(sheet.getCell(8,i).getContents().trim()!=null&&!sheet.getCell(8,i).getContents().trim().equals("")){
												
												ab.setEmail(sheet.getCell(8,i).getContents().trim());
											}else{
												errorList.add("Email is null!");
											}
											if(sheet.getCell(9,i).getContents().trim()!=null&&!sheet.getCell(9,i).getContents().trim().equals("")){
												ab.setAddress(sheet.getCell(9,i).getContents().trim());
											}else{
												errorList.add("Address is null!");
											}
											
											if(sheet.getCell(10,i).getContents().trim()!=null &&!sheet.getCell(10,i).getContents().trim().equals("")){
												
												ab.setZipCode(sheet.getCell(10,i).getContents().trim());
											}else{
												errorList.add("ZipCode is null!");
											}
											
											if(!sheet.getCell(11, i).getContents().trim().equals("")&&sheet.getCell(11, i).getContents().trim()!=null){
												
												ab.setWebsite(sheet.getCell(11, i).getContents().trim());
											}else{
												errorList.add("Website is null!");
											}
											if(sheet.getCell(12, i).getContents().trim()!=null&&!sheet.getCell(12, i).getContents().trim().equals("")){
												
												ab.setRemarks(sheet.getCell(12, i).getContents().trim());
											}else{
												errorList.add("Remarks is null!");
											}
											HttpSession session = ServletActionContext.getRequest().getSession();
											Users u = (Users) session.getAttribute("user");
											ab.setUsers(u);
											abList.add(ab);
											errorList.clear();
											errorList.add("个人通讯录导入成功！！");
											}
										}
									}
						}
						book.close();
						file.delete();
						//target.delete();
						boolean bl = addressBookManageService.batchInserts(abList);
						if(!bl){
							errorList.clear();
							errorList.add("数据导入失败，格式出错,请导入正确的数据格式！");
							}
						}catch(Exception e){
						target.delete();
						e.printStackTrace();
						}
						target.delete();
						}
						getRequest().setAttribute("errorList", errorList);
						return SUCCESS;
						}
					}
