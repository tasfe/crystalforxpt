package com.combanc.itsm.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.SystemNoticeDAO;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.SystemNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.CharacterUtil;

public class SystemNoticeService {

	private SystemNoticeDAO systemNoticeDAO;
	
	private UserService userService;

	private String list_all_desc = "from SystemNotice bean order by bean.id desc";

	private String list_all_asc = "from SystemNotice bean order by bean.id asc";
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SystemNoticeDAO getSystemNoticeDAO() {
		return systemNoticeDAO;
	}

	public void setSystemNoticeDAO(SystemNoticeDAO systemNoticeDAO) {
		this.systemNoticeDAO = systemNoticeDAO;
	}

	// 查询
	public SystemNotice getSystemNoticeById(long id) {

		return this.systemNoticeDAO.getSystemNoticeById(id);
	}

	// 增
	public void saveSystemNotice(SystemNotice systemNotice) {

		this.systemNoticeDAO.saveSystemNotice(systemNotice);
	}
	
	public void savePersonalNotice(PersonalNotice bean)
	{
		this.systemNoticeDAO.savePersonalNotice(bean);
	}

	// 删
	public void deleteSystemNoticeById(long id) {
		this.systemNoticeDAO.deleteSystemNotice(id);
	}
	
	public void deletePersonalNoticeByUserId(long id)
	{
		this.systemNoticeDAO.deletePersonalNoticeByUserId(id);
	}

	// 改
	public void updateSystemNotice(SystemNotice bean) {
		this.systemNoticeDAO.updateSystemNotice(bean);
	}

	// 查询
	public SystemNotice SystemNoticeById(long id) {
		return this.systemNoticeDAO.SystemNoticeById(id);
	}

	public List<SystemNotice> findAll() {
		return this.systemNoticeDAO.findAll(SystemNotice.class);
	}

	public boolean isSystemNoticeExistByIdAndName(long id, String fileName) {
		return this.systemNoticeDAO
				.isSystemNoticeExistByIdAndName(id, fileName);
	}
	
	public List<SystemNotice> listSystemNoticeByHql(String hql) {
		return this.systemNoticeDAO.listSystemNoticeByHql(hql);
	}

	public List<SystemNotice> listSystemNoticesDesc(int start, int range) {
		return this.systemNoticeDAO.searchSystemNotices(list_all_desc,
				new String[] {}, start, range);
	}

	public List<SystemNotice> listSystemNoticeAsc(int start, int range) {
		return this.systemNoticeDAO.searchSystemNotices(list_all_asc,
				new String[] {}, start, range);
	}

	public List<SystemNotice> listSystemNotices(int start, int range) {
		return this.listSystemNoticesDesc(start, range);
	}

	public long getSystemNoticeCount() {
		return this.systemNoticeDAO.getSystemNoticeCount(list_all_desc);

	}

	/*
	 * public List<SystemNotice> listSystemNoticeByAuthorName(String authorName)
	 * { return this.systemNoticeDAO.listSystemNoticeByAuthorName(authorName); }
	 * 
	 * public List<SystemNotice> listSystemNoticeByTitle(String title) { return
	 * this.systemNoticeDAO.listSystemNoticeByTitle(title); }
	 */

	// 计算文件的大小;
	public String getSize(long length) 
	{
		double floLength = 0;
		String strLength = null;
		String unit = null;// 描述单位的字符串;

		if (length >= 1024 * 1024) {
			floLength = length / (1024 * 1024.0);
			unit = "MB";

		} else if (length >= 1024) {
			floLength = length / 1024.0;
			unit = "KB";

		} else {
			unit = "B";
			return length + unit;// 如果是B,则直接返回;
		}

		strLength = String.valueOf(floLength);

		strLength = strLength.substring(0, strLength.lastIndexOf(".") + 2);

		strLength = strLength + unit;

		return strLength;

	}

	@SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize, int page) {

		String hql = "from SystemNotice as bean where 1=1 ";
		hql = hql + " order by bean.id desc";// 查询语句
		int allRow = systemNoticeDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<SystemNotice> list = systemNoticeDAO.queryForPage(hql, offset,
				length); // "一页"的记录
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
	

	
	//发送个人通知;
	public void sendNotice(String to,String cc,String title,String content,String from)
	{
		List<String> tos = CharacterUtil.getSplitedList(to, ",");//to 表示信件要发送的人,即收件人;
		//Set<String> toSet = new HashSet<String>(tos);//去除接受人的重复;
		//Date date = new Date();
		//for(int i = 0; i < tos.size() + 1; i++)//都保存到一个发件箱中;
		
		for(String o:tos)//收件箱(收件)
		{
			PersonalNotice bean = new PersonalNotice();
		/*	long userId = 0;
			//int usertype = 1;//type表示是发件箱，还是收件箱;
			
			if(i == tos.size())
			{
				userId = this.userService.getUserByUsername(from).getId();
				//usertype = 3;
			}
			else
			{	
				String username = "";
				//userId = this.userService.getUserByUsername(tos.get(i)).getId();
				if(tos!=null){
					username = tos.get(i);
				}
				Users u = userService.getUserByUsername(username);
				if(u!=null){
					userId = u.getId();
				}
			}*/
			//bean.setUserId(userId);
			bean.setTitle(title);
			bean.setContent(content);
			bean.setDate(new Date());
			bean.setRead(false);
			bean.setTo(to);
			//bean.setCc(cc);
			//bean.setUsertype(usertype);//1.表示收件箱;
			bean.setUsertype(1);
			bean.setFrom(from);
			
			Users user = this.userService.getUserByTrueName(o);
			long userId = user.getId();
			bean.setUserId(userId);
			
			this.systemNoticeDAO.savePersonalNotice(bean);//保存给收到邮件的人;
		}
		
		//long userId = this.userService.getUserByUsername(from).getId();//获得发件人的Id;保存到session里面;
		
		/*PersonalNotice bean = new PersonalNotice();//发件箱
		bean.setFrom(from);
		bean.setTo(to);
		bean.setTitle(title);
		bean.setDate(date);
		bean.setRead(false);
		bean.setUsertype(2);//2.表示发件箱;
		bean.setContent(content);
		Users user = this.userService.getUserByUsername(from);//得到发送者的id;
		long userId = user.getId();
		bean.setUserId(userId);
		
		this.systemNoticeDAO.savePersonalNotice(bean);*/
	}
	
	public long getPersonalNoticeByCondition(long userId,int userType,String read)
	{
		String queryString = null;
		
		if("false".equals(read))
		{
			queryString = "from PersonalNotice as bean where bean.userId='" + userId + "'" + "and bean.read='" + read + "'";  
		}
		else
		{
			queryString = "from PersonalNotice as bean where bean.userId='" + userId + "'" + "and bean.userType='" + userType + "'";
		}
		
		return this.systemNoticeDAO.getPersonalNoticeCount(queryString,new String[]{String.valueOf(userId),String.valueOf(userType)});
		
	}
	
	//根据发送者(from)和这封通知属于谁的(userId),来查出所有的通知;
	public List<PersonalNotice> listPersonalNoticeByUserId(long userId)
	{
		return this.systemNoticeDAO.listPersonalNoticeByUserId(userId);
	}
	

}
