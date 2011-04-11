package com.combanc.itsm.action.addressbookmanage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.struts2.ServletActionContext;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AddressBookManage;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.CustomerType;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.addressbookmanage.AddressBookManageService;
import com.combanc.itsm.service.addressbookmanage.ContactsTreeService;
import com.combanc.itsm.service.addressbookmanage.CustomerTypeService;
import com.combanc.itsm.service.tree.TreeDateService;
import com.combanc.itsm.util.ChineseSpelling;
import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
public class AddressBookManageAction extends BaseActionSupport{

	private long id;
	private long departmentId;
	private long customertypeId;
	private String username;
	private String phone;
	private String telephone;
	private String officePhone;
	private String homephone;
	private String fax;
	private String email;
	private String homepage;
	private String qq;
	private String msn;
	private String address;
	private String zipCode;
	private String remarks;
	private String companyName;
	private String duties;
	private String personalWebsite;
	private String website;
	private String initials;// 首字母;
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private String treeString;// treeString 是树结构查询相关的参数;
	private AddressBookManage addressBook;
	private Department department;
	private long userContactId;
	private long userId;
	private String departName;
	private String addressBookIds;
	private DepartmentService departmentService;
	private TreeDateService treeDateService;
	private UserService userService;
	private Users users;
	private CustomerType customType;
	private AddressBookManageService addressBookManageService;
	private ContactsTreeService contactsTreeService;//
	private CustomerTypeService customerTypeService;
	HttpServletRequest request = null;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public CustomerType getCustomType() {

		return customType;
	}

	public void setCustomType(CustomerType customType) {
		this.customType = customType;
	}

	public long getCustomertypeId() {
		return customertypeId;
	}

	public void setCustomertypeId(long customertypeId) {
		this.customertypeId = customertypeId;
	}

	public CustomerTypeService getCustomerTypeService() {
		return customerTypeService;
	}

	public void setCustomerTypeService(CustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}

	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getTreeString() {
		return treeString;
	}

	public void setTreeString(String treeString) {
		this.treeString = treeString;
	}

	public AddressBookManage getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBookManage addressBook) {
		this.addressBook = addressBook;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public long getUserContactId() {
		return userContactId;
	}

	public void setUserContactId(long userContactId) {
		this.userContactId = userContactId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getAddressBookIds() {
		return addressBookIds;
	}

	public void setAddressBookIds(String addressBookIds) {
		this.addressBookIds = addressBookIds;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public TreeDateService getTreeDateService() {
		return treeDateService;
	}

	public void setTreeDateService(TreeDateService treeDateService) {
		this.treeDateService = treeDateService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public AddressBookManageService getAddressBookManageService() {
		return addressBookManageService;
	}

	public void setAddressBookManageService(
			AddressBookManageService addressBookManageService) {
		this.addressBookManageService = addressBookManageService;
	}

	public ContactsTreeService getContactsTreeService() {
		return contactsTreeService;
	}

	public void setContactsTreeService(ContactsTreeService contactsTreeService) {
		this.contactsTreeService = contactsTreeService;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String newAddressBook() throws Exception {

		return SUCCESS;
	}

	// 公共通讯录的添加;
	public String addContact() throws Exception {
		AddressBookManage addressBook = new AddressBookManage();
		
		boolean flag = addressBookManageService.isExit(username);
		if(!flag){
			if (departmentId > 0) {
				Department department = this.departmentService
						.findDepartmentById(Integer.valueOf((String
								.valueOf(departmentId))));
				addressBook.setDepartment(department);
			}
			addressBook.setUsername(username);
			addressBook.setAddress(address);
			addressBook.setEmail(email);
			addressBook.setFax(fax);
			addressBook.setHomepage(homepage);
			addressBook.setHomephone(homephone);
			addressBook.setMsn(msn);
			addressBook.setRemarks(remarks);
			addressBook.setTelephone(telephone);
			addressBook.setPhone(homephone);
			addressBook.setOfficePhone(officePhone);
			addressBook.setZipCode(zipCode);
			addressBook.setQq(qq);
			//addressBook.setInitials((ChineseSpelling.convertName(username)).substring(0, 1));
			String initials = ChineseSpelling.getFirstAlpha(username);
			addressBook.setInitials(initials.substring(0, 1));
			this.addressBookManageService.saveAddressBook(addressBook);
			
			request = ServletActionContext.getRequest();
			request.setAttribute("msg", "添加成功!");
		}else{
			request = ServletActionContext.getRequest();
			request.setAttribute("msg", "您添加的用户已存在!");
		}
		return SUCCESS;
	}

	// 公共通讯录(列表)
	public String myContact() throws Exception {

		if (pageSize < 10) {
			pageSize = 10;
		}
		pageBean = addressBookManageService.queryForPage(Integer
				.valueOf(pageSize), page);
		return SUCCESS;
	}

	// 个人通讯录;
	public String personalAddressBook() throws Exception {

		return SUCCESS;
	}

	// 私有通讯录的添加;
	public String addPersonalContacts() throws Exception {

		AddressBookManage addressBook = new AddressBookManage();
		
		boolean flag = addressBookManageService.isExitP(username);
		
		if(!flag){
			addressBook.setUsername(username);
			addressBook.setPhone(phone);
			addressBook.setEmail(email);
			addressBook.setCompanyName(companyName);
			addressBook.setDuties(duties);
			addressBook.setPersonalWebsite(personalWebsite);
			addressBook.setOfficePhone(officePhone);
			addressBook.setFax(fax);
			addressBook.setAddress(address);
			addressBook.setZipCode(zipCode);
			addressBook.setHomepage(homepage);
			addressBook.setWebsite(website);
			addressBook.setRemarks(remarks);
			String initials = ChineseSpelling.getFirstAlpha(username);
			addressBook.setInitials(initials.substring(0, 1));
			if (customertypeId > 0) {
				CustomerType customerType = this.customerTypeService
						.findCustomerTypeById(Integer.valueOf((String
								.valueOf(customertypeId))));
				addressBook.setCustomertype(customerType);
			}
	       // addressBook.setInitials((ChineseSpelling.convertName(username)).substring(0, 1));
			HttpSession session = ServletActionContext.getRequest().getSession();
			Users u = (Users) session.getAttribute("user");
			addressBook.setUsers(u);
			this.addressBookManageService.saveAddressBook(addressBook);
		}else{
			request = ServletActionContext.getRequest();
			request.setAttribute("msg", "您添加的用户已存在!");
		}
	
		return SUCCESS;
	}

	// 私有通讯录列表
	public String listPersonalContact() throws Exception {
		if (pageSize < 10) {
			pageSize = 10;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users u = (Users) session.getAttribute("user");
		long userId = Long.parseLong(u.getId().toString());
		this.pageBean = this.addressBookManageService.queryForPage(Integer
				.valueOf(pageSize), page, userId);
		return SUCCESS;
	}

	// 修改公共的
	public String updateP() throws Exception {

		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		this.setAddressBook(addressBook);
		return SUCCESS;
	}

	// 修改私有的
	public String updatePPersonalContact() throws Exception {
		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		this.setAddressBook(addressBook);
		return SUCCESS;
	}

	public String updatePersonalContact() throws Exception {
		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		addressBook.setUsername(username);
		addressBook.setCompanyName(companyName);
		addressBook.setPhone(phone);
		addressBook.setDuties(duties);
		addressBook.setOfficePhone(officePhone);
		addressBook.setFax(fax);
		addressBook.setEmail(email);
		addressBook.setHomepage(homepage);
		addressBook.setWebsite(website);
		addressBook.setPersonalWebsite(personalWebsite);
		addressBook.setAddress(address);
		addressBook.setZipCode(zipCode);
		addressBook.setRemarks(remarks);
		this.addressBookManageService.updateAddressBook(addressBook);
		return SUCCESS;
	}

	public String update() throws Exception {
		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		addressBook.setUsername(username);
		addressBook.setAddress(address);
		addressBook.setOfficePhone(officePhone);
		addressBook.setEmail(email);
		addressBook.setFax(fax);
		addressBook.setHomepage(homepage);
		addressBook.setHomephone(homephone);
		addressBook.setMsn(msn);
		addressBook.setOfficePhone(officePhone);
		addressBook.setZipCode(zipCode);
		addressBook.setQq(qq);
		addressBook.setRemarks(remarks);
		this.addressBookManageService.updateAddressBook(addressBook);
		return SUCCESS;
	}

	// 删除公共的
	public String delete() throws Exception {
		StringTokenizer st = new StringTokenizer(this.addressBookIds, "@");
		while (st.hasMoreTokens()) {
			String value = st.nextToken().trim();
			long addressBookId = Long.parseLong(value);
			this.addressBookManageService.deleteAddressBookById(addressBookId);
		}
		return SUCCESS;
	}

	// 删除私有的通讯录
	public String deletePersonalContact() throws Exception {

		StringTokenizer st = new StringTokenizer(this.addressBookIds, "@");
		while (st.hasMoreTokens()) {
			String value = st.nextToken().trim();
			long addressBookId = Long.parseLong(value);
			this.addressBookManageService.deleteAddressBookById(addressBookId);
		}
		return SUCCESS;
	}

	// 导出
	@SuppressWarnings("unchecked")
	public String export() throws Exception {

		List<AddressBookManage> addressBookListExcel = addressBookManageService
				.querys(addressBook);
		HttpServletResponse response = ServletActionContext.getResponse();
		List list = new ArrayList();
		String[] title = new String[] { "姓名", "所在部门编号", "手机", "办公电话", "家庭电话",
				"主页", "Email", "传真", "MSN", "地址", "邮编", "备注" };
		for (AddressBookManage a : addressBookListExcel) {
			if (a.getDepartment() != null && a.getDepartment().getId() != null) {
				int departid = a.getDepartment().getId();
				Department depart = departmentService
						.findDepartmentById(departid);
				a.setDepartName(depart.getName());
			}
			Object[] object = new Object[] { a.getUsername(),
					a.getDepartName(), a.getPhone(), a.getOfficePhone(),
					a.getHomephone(), a.getHomepage(), a.getEmail(),
					a.getFax(), a.getMsn(), a.getAddress(), a.getZipCode(),
					a.getRemarks() };
			list.add(object);
		}
		try {
			String fname = "Excel"; // Excel文件名;
			// String fname = "通讯录";
			OutputStream os = response.getOutputStream();// 取得输出流;
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, list, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void writeExcel(OutputStream out, List datas, String[] title) {
		if (datas == null) {
			throw new IllegalArgumentException("写excel流需要List参数!");
		}
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			WritableSheet ws = workbook.createSheet("sheet 1", 0);
			int rowNum = 0; // 要写的行
			if (title != null) {
				putRow(ws, 0, title);// 压入标题
				rowNum = 1;
			}
			@SuppressWarnings("unused")
			java.lang.reflect.Method[] method = AssetsBase.class
					.getDeclaredMethods();
			for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
				Object[] cells = (Object[]) datas.get(i);
				putRow(ws, rowNum, cells); // 压一行到shee
			}
			workbook.write();
			workbook.close(); // 一定要关闭, 否则没有保存Excel
		} catch (RowsExceededException e) {
			System.out.println("jxl write RowsExceededException:"
					+ e.getMessage());
		} catch (WriteException e) {
			System.out.println("jxl write WriteException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("jxl write file i/o exception!, cause by:"
					+ e.getMessage());
		}
	}

	private void putRow(WritableSheet ws, int rowNum, Object[] cells)
			throws RowsExceededException, WriteException {
		for (int j = 0; j < cells.length; j++) {// 写一行
			Label cell = new Label(j, rowNum, "" + cells[j]);
			ws.addCell(cell);
		}
	}

	// 树查询的公共通讯录
	public String publicAddressBook() throws Exception {
		treeString = contactsTreeService.getTreeString(null);
		return SUCCESS;
	}

	public String top() throws Exception {
		System.out.println(treeString);
		return SUCCESS;
	}


	// 按公司名或者姓名进行查询
	public String queryPersonalContact() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users u = (Users) session.getAttribute("user");
		long userId = Long.parseLong(u.getId().toString());
		String hql = "from AddressBookManage as bean where 1=1 and bean.department is null and bean.users ="
				+ userId;
		if (username != null && !username.equals("")) {
			hql += " and bean.username like'%" + username + "%'";
		}
		if (companyName != null && !companyName.equals("")) {
			hql += " and bean.companyName like'%" + companyName + "%'";
		}
		hql += " order by bean.id desc";
		pageBean = addressBookManageService.queryForPage(pageSize, page, hql);
		return SUCCESS;
	}

	// 按首字母查询
	public String firstQuery() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users u = (Users) session.getAttribute("user");
		long userId = Long.parseLong(u.getId().toString());
		String hql = "from AddressBookManage as bean where 1=1 and bean.department is null and bean.users="+userId+" and bean.initials="+"'"+initials+"'";
		pageBean = addressBookManageService.queryForPage(pageSize, page, hql);
		return SUCCESS;
	}
	//公共通讯录首字母查询
	public String firstQueryContact(){
		
		String hql = "from AddressBookManage as bean where 1=1 and bean.users is null and bean.initials="+"'"+initials+"'";
		pageBean = addressBookManageService.queryForPage(pageSize, page, hql);
		return SUCCESS;
	}

	// 按部门或者姓名进行查询
	public String queryDepOrName() throws Exception {
		
		String hql = "from AddressBookManage as bean where 1=1 and bean.customertype = null";
		
		if (username != null && !username.equals("")) {
			hql += " and bean.username like'%" + username + "%'";
		}
		if (departmentId > 0) {
			hql += " and bean.department.id =" + departmentId;
		}
		hql += " order by bean.id desc";

		pageBean = this.addressBookManageService
				.queryForPage(pageSize, page, hql);

		return SUCCESS;
	}

	// 信息查看
	public String detailAddressBook() throws Exception {

		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		ActionContext.getContext().put("addressBook", addressBook);
		return SUCCESS;
	}

	// 私有通讯录信息查看
	public String detailPersonalContact() throws Exception {

		AddressBookManage addressBook = this.addressBookManageService
				.getAddressBookById(id);
		ActionContext.getContext().put("personalContact", addressBook);
		return SUCCESS;
	}

	// 通讯录导入
	public String addressImport() throws Exception {
		
		return SUCCESS;
	}
	
	//公共通讯录模板下载
	
	public String excelDownString1() throws Exception {
		List<AddressBookManage> addressBookExcel = addressBookManageService
				.querys(addressBook);
		HttpServletResponse response = ServletActionContext.getResponse();
		List ll = new ArrayList();
		String[] title = new String[] { "姓名", "手机", "办公电话", "家庭电话", "主页",
				"Email", "传真", "MSN", "地址", "邮编", "备注" };

		for (AddressBookManage a : addressBookExcel) {
			Object[] object = new Object[] { a.getUsername(), a.getPhone(),
					a.getOfficePhone(), a.getHomephone(), a.getHomepage(),
					a.getEmail(), a.getFax(), a.getMsn(), a.getAddress(),
					a.getZipCode(), a.getRemarks(), };
			ll.add(object);
		}

		try {
			String fname = "个人通讯录Excel模板";// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, ll, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	// 个人通讯录模板下载
	@SuppressWarnings("unchecked")
	public String excelDownString2() throws Exception {
		List<AddressBookManage> addressBookExcel = addressBookManageService
				.querys(addressBook);
		HttpServletResponse response = ServletActionContext.getResponse();
		List ll = new ArrayList();
		String[] title = new String[] { "姓名", "手机", "办公电话", "家庭电话", "主页",
				"Email", "传真", "MSN", "地址", "邮编", "备注" };
		for (AddressBookManage a : addressBookExcel) {
			Object[] object = new Object[] { a.getUsername(), a.getPhone(),
					a.getOfficePhone(), a.getHomephone(), a.getHomepage(),
					a.getEmail(), a.getFax(), a.getMsn(), a.getAddress(),
					a.getZipCode(), a.getRemarks(), };
			ll.add(object);
		}

		try {
			String fname = "公共通讯录Excel模板";// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, ll, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

/*	public String downloads() throws Exception {
		
		return SUCCESS;
	}*/

	public String main() {
		return SUCCESS;
	}

	public String mainContact() {
		return SUCCESS;
	}

}
