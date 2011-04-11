package com.combanc.itsm.dao;

import java.util.List;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.SystemNotice;


public class SystemNoticeDAO extends BaseHibernateDAO<SystemNotice, Integer> {
	// 1.将系统公告对象保存到数据库中;
	public void saveSystemNotice(SystemNotice systemNotice) {
		// this.getHibernateTemplate().getSessionFactory().openSession().lock(systemNotice,
		// LockMode.NONE);
		this.getHibernateTemplate().save(systemNotice);

	}

	// 保存个人通知到数据库中;
	public void savePersonalNotice(PersonalNotice bean) {
		this.getHibernateTemplate().save(bean);
	}

	// 2.根据id来查询系统公告对象;
	public SystemNotice getSystemNoticeById(long id) {
		return (SystemNotice) this.getHibernateTemplate().get(
				SystemNotice.class, id);
	}
	
	//根据id来查询个人通知对象;
	public PersonalNotice getPersonalNoticeById(long id)
	{
		return (PersonalNotice)this.getHibernateTemplate().get(PersonalNotice.class, id);
	}

	// 3.根据id来删除公告对象;
	public void deleteSystemNotice(long id) 
	{
		SystemNotice systemNotice = this.getSystemNoticeById(id);
		this.getHibernateTemplate().delete(systemNotice);
	}
	
	public PersonalNotice getPersonalNoticeByUserId(long userId)
	{
		return (PersonalNotice)this.getHibernateTemplate().get(PersonalNotice.class, userId);
		
	}
	public void deletePersonalNoticeByUserId(long id)
	{
		PersonalNotice personalNotice = this.getPersonalNoticeByUserId(id);
		this.getHibernateTemplate().delete(personalNotice);
	}

	// 4.更新对象;

	public void updateSystemNotice(SystemNotice systemNotice)
	{
		this.getHibernateTemplate().update(systemNotice);
	}

	public void updatePersonNotice(PersonalNotice personalNotice)
	{
		this.getHibernateTemplate().update(personalNotice);
	}
	
	// 5.根据id和文件名来查询数据库中上传的文件是否存在;
	@SuppressWarnings("unchecked")
	public boolean isSystemNoticeExistByIdAndName(long id, String fileName) {
		
		String hql = "from SystemNotice as bean where bean.id ="
				+ id + " and bean.fileName="+"'"+fileName+
				"'";

		List<SystemNotice> list = this.getHibernateTemplate().find(hql);

		return 0 == list.size() ? false : true;
	}

	// 6.根据id,来查找某一个对象;
	public SystemNotice SystemNoticeById(long id) {
		String hql = "from SystemNotice as bean where bean.id=" + id;
		SystemNotice systemNotice = (SystemNotice) this.getHibernateTemplate()
				.find(hql);
		return systemNotice;
	}

	// 用来查询某个字段或某几个字段所采用的方法;
	@SuppressWarnings("unchecked")
	public List<SystemNotice> listSystemNoticeByHql(String hql) {
		List<SystemNotice> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<SystemNotice>
	 * listSystemNoticeByAuthorName(String authorName) { String hql =
	 * "from SystemNotice as bean where bean.authorName="+ authorName;
	 * List<SystemNotice> list = this.getHibernateTemplate().find(hql); return
	 * list; }
	 * 
	 * @SuppressWarnings("unchecked") public List<SystemNotice>
	 * listSystemNoticeByTitle(String title) { String hql =
	 * "from SystemNotice as bean where bean.title=" + title; List<SystemNotice>
	 * list = this.getHibernateTemplate().find(hql); return list; }
	 */

	// 得到系统公告的总数;
	public long getSystemNoticeCount(String queryString) {
		return retrieveObjsCount(queryString);
	}

	public long getSystemNoticeCount(String queryString, String value)

	{
		return retrieveObjsCount(queryString, value);
	}

	public long getSystemNoticeCount(String queryString, String[] value) {
		return retrieveObjsCount(queryString, value);
	}

	// 查询
	public List<SystemNotice> searchSystemNotices(String query) {
		return retrieveObjs(query);
	}

	public SystemNotice searchSystemNotice(String query) {
		return retrieveObj(query);
	}

	public List<SystemNotice> searchSystemNotices(String query, String value) {
		return retrieveObjs(query, value);
	}

	public List<SystemNotice> searchSystemNotices(String query, String[] value) {
		return retrieveObjs(query, value);
	}

	public SystemNotice searchSystemNotice(String query, String value) {
		return retrieveObj(query, value);
	}

	public SystemNotice searchSystemNotice(String query, String[] value) {
		return retrieveObj(query, value);
	}

	public List<SystemNotice> searchSystemNotices(String query, String[] value,
			int start, int number) {
		return retrieveObjs(query, value, start, number);
	}

	public List<SystemNotice> searchSystemNotice(String query, String value,
			int start, int number) {
		return retrieveObjs(query, value, start, number);
	}

	public long getPersonalNoticeCount(String queryString) {
		return retrieveObjsCount(queryString);
	}

	public long getPersonalNoticeCount(String queryString, String value) {
		return retrieveObjsCount(queryString, value);
	}

	public long getPersonalNoticeCount(String queryString, String[] value) {
		return retrieveObjsCount(queryString, value);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PersonalNotice> listPersonalNoticeByUserId(long userId)
	{
		String hql = "from PersonalNotice as bean where bean.userId = " + userId +" order by bean.id desc"; 
		
		List<PersonalNotice> list = this.getHibernateTemplate().find(hql);
		
		return list;
	}

	public List<PersonalNotice> listNewPersonalNoticeByUserId(long userId)
	{
		String hql = "from PersonalNotice as bean where bean.userId = " + userId +" and bean.read=0 order by bean.id desc"; 
		
		List<PersonalNotice> list = this.getHibernateTemplate().find(hql);
		
		return list;
	}
	
}
