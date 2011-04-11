package com.combanc.itsm.service.addressbookmanage;

import java.util.ArrayList;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.AddressBookManageDAO;
import com.combanc.itsm.pojo.AddressBookManage;

public class AddressBookManageService {

	private AddressBookManageDAO addressBookManageDAO;

	public AddressBookManageDAO getAddressBookManageDAO() {
		return addressBookManageDAO;
	}

	public void setAddressBookManageDAO(
			AddressBookManageDAO addressBookManageDAO) {
		this.addressBookManageDAO = addressBookManageDAO;
	}

	// 增
	public void saveAddressBook(AddressBookManage addressBook) {

		this.addressBookManageDAO.saveAddressBook(addressBook);
	}

	// 分页显示
	@SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize, int page) {

		String hql = "from AddressBookManage as bean where 1=1 and bean.department != null";
		hql = hql + " order by bean.id desc";// 查询语句
		int allRow = addressBookManageDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<AddressBookManage> list = addressBookManageDAO.queryForPage(hql,
				offset, length); // "一页"的记录
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public PageBean queryForPage(int pageSize, int page, long userId) {

		String hql = "from AddressBookManage as bean where 1=1 and bean.department is null and bean.users = "
				+ userId;
		hql = hql + " order by bean.id desc";// 查询语句

		int allRow = addressBookManageDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		List<AddressBookManage> list = addressBookManageDAO.queryForPage(hql,
				offset, length); // "一页"的记录
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	public PageBean queryForPage(int pageSize, int page, String hql) {

		
		int allRow = addressBookManageDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		List<AddressBookManage> list = addressBookManageDAO.queryForPage(hql,
				offset, length); // "一页"的记录
		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	//查询公共通讯录添加的用户是否存在;
	public boolean isExit(String str){
	
		String hql = "from AddressBookManage as bean where 1=1 and bean.department != null and bean.username = '"+str+ "'";
		hql = hql + " order by bean.id desc";
		//List abmList = new ArrayList<AddressBookManage>();
		List abmList = addressBookManageDAO.findBySql(hql);	
	   
		if(!abmList.isEmpty()){
	    	
	    	  boolean flag = true;//表示不存在
	    }
			return false;//表示存在;
	}
	
	public boolean isExitP(String str){
		String hql = "from AddressBookManage as bean where 1=1 and bean.department is null and bean.username = '"+str+ "'";
		hql = hql + " order by bean.id desc";
		List abmList = addressBookManageDAO.findBySql(hql);
		if(!abmList.isEmpty()){
	    	
	    	  boolean flag = true;//表示不存在
	    }
			return false;//表示存在;
		
	}
	public AddressBookManage getAddressBookById(long id) {

		return this.addressBookManageDAO.getAddressBookById(id);
	}

	// 更新
	public void updateAddressBook(AddressBookManage addressBook) {
		this.addressBookManageDAO.updateAddressBook(addressBook);
	}

	// 删除
	public void deleteAddressBookById(long id) {

		this.addressBookManageDAO.deleteAddressBookById(id);
	}

	// 文件导出
	public List<AddressBookManage> querys(AddressBookManage addressBook) {

		return addressBookManageDAO.queryByHqls(addressBook);
	}

	// 按部门或人名查询

	public List<AddressBookManage> listAddressBookByHql(String hql) {

		return this.addressBookManageDAO.listAddressBookManageByHql(hql);
	}

	public List<AddressBookManage> getAddressBookByUsersId(long userId) {
		return addressBookManageDAO.findByUserId(userId);
	}

	public List<AddressBookManage> getAddressBookByDepartmentId(
			long departmentId) {
		return addressBookManageDAO.findByGroupId(departmentId);
	}

	public List findByProperty(String code, String value) {
		return addressBookManageDAO.findByProperty(code, value);
	}

	public boolean batchInserts(final List<AddressBookManage> addressBooks) {
		boolean a = addressBookManageDAO.batchInsert(addressBooks);
		return a;
	}

	public List<AddressBookManage> findAll() {

		return addressBookManageDAO.findAll();
	}

}
