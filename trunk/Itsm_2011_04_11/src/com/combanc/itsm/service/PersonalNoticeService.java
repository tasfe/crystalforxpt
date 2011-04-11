package com.combanc.itsm.service;

import java.util.Date;
import java.util.List;
import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.SystemNoticeDAO;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.SystemNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.CharacterUtil;

public class PersonalNoticeService {

	private SystemNoticeDAO systemNoticeDAO;
	
	private UserService userService;

	private String list_all_desc = "from PersonalNotice bean order by bean.id desc";

	private String list_all_asc = "from PersonalNotice bean order by bean.id asc";
	
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

	//增
	public void savePersonalNotice(PersonalNotice bean)
	{
		this.systemNoticeDAO.savePersonalNotice(bean);
	}

	//删
	public void deletePersonalNoticeByUserId(long id)
	{
		this.systemNoticeDAO.deletePersonalNoticeByUserId(id);
	}

	@SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize, int page,long userId) {

		String hql = "from PersonalNotice as bean where 1=1 and bean.userId = " + userId;
		hql = hql + " order by bean.id desc";// 查询语句
		
		int allRow = systemNoticeDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		
		List<SystemNotice> list = systemNoticeDAO.queryForPage(hql, offset,length); // "一页"的记录
		
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
		for(String o:tos)//收件箱(收件)
		{
			PersonalNotice bean = new PersonalNotice();
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
	
	public PersonalNotice getPersonalNoticeById(long id)
	{
		return this.systemNoticeDAO.getPersonalNoticeByUserId(id);
	}
	
	

}
