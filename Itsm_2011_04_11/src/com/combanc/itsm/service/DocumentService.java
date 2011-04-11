/**
 * 
 */
package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.DocumentDAO;
import com.combanc.itsm.dao.DocumentOldDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Document;
import com.combanc.itsm.pojo.DocumentOld;
import com.combanc.itsm.pojo.ServiceRequest;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Administrator
 * 
 */
public class DocumentService {

	private DocumentDAO documentDAO;
	private DocumentOldDAO documentOldDAO;

	/**
	 * @return the documentOldDAO
	 */
	public DocumentOldDAO getDocumentOldDAO() {
		return documentOldDAO;
	}

	/**
	 * @param documentOldDAO
	 *            the documentOldDAO to set
	 */
	public void setDocumentOldDAO(DocumentOldDAO documentOldDAO) {
		this.documentOldDAO = documentOldDAO;
	}

	/**
	 * @return the documentDAO
	 */
	public DocumentDAO getDocumentDAO() {
		return documentDAO;
	}

	/**
	 * @param documentDAO
	 *            the documentDAO to set
	 */
	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	/**
	 * @param document
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean queryForPage(Document document, int pageSize, int page,
			String catcode) {

		String sqlString = "from Document as model where 1=1";
		if (document != null) {
			if (document.getId()!=null&&document.getId()>0) {
				sqlString = sqlString + " and model.id=" + document.getId();
			}
			if (document.getDepartment() != null&& document.getDepartment().getId() != null&&document.getDepartment().getId()>0) {
				sqlString = sqlString + " and model.department="
						+ document.getDepartment().getId();
			}
			if (document.getDepartmentName() != null) {
				sqlString = sqlString + " and model.departmentName="
						+ document.getDepartmentName();
			}
			if(document.getTitle()!=null&&!document.getTitle().equals(""))
			{
				sqlString = sqlString +" and model.title like '%"+document.getTitle()+"%'";
			}
//			if(document.getUsers()!=null&&document.getUsers().getId()>0)
//			{
//				sqlString =sqlString +" and model.users="+document.getUsers().getId();
//			}
			
		}
		if (catcode!=null) {

			sqlString = sqlString + " and model.cat.code like '%" + catcode+"%'";
			
		}
		sqlString = sqlString + " order by model.submitTime desc";

		int allRow = documentDAO.getAllRowCount(sqlString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sqlString);
		// sql = sql + " order by model.requestNo";
		List<ServiceRequest> list = documentDAO.queryForPage(sqlString, offset,
				length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void save(Document transientInstance) {
		documentDAO.save(transientInstance);
	}

	/**
	 * @param dc
	 */
	public void update(Document dc) {
		documentDAO.update(dc);

	}

	/**
	 * @param documentId
	 * @return
	 */
	public Document get(int documentId) {

		return documentDAO.findById(documentId);
	}

	/**
	 * @param documentId
	 * @return
	 */
	public boolean del(int documentId) {
		Document document = documentDAO.findById(documentId);
		boolean isok = true;
		if (document != null) {
			documentDAO.delete(document);
			isok = delAllOld(document.getNum());

		}
		return isok;
	}

	public Document getByNum(String num) {

		List dList = documentDAO.findByNum(num);
		if (dList != null && !dList.isEmpty()) {
			return (Document) dList.get(0);
		} else {
			return null;
		}
	}

	public List<DocumentOld> getOldByNum(String num) {
		return documentOldDAO.findByNum(num);

	}

	public boolean saveOld(DocumentOld old) {
		documentOldDAO.save(old);
		return true;
	}

	public boolean delAllOld(String num) {
		List<DocumentOld> odlList = documentOldDAO.findByNum(num);
		for (DocumentOld old : odlList) {
			documentOldDAO.delete(old);
		}

		return true;
	}

	/**
	 * @param document
	 * @param pageSize
	 * @param page
	 * @param cat_id
	 * @return
	 */
	public PageBean queryHistoryForPage(Document document, int pageSize,
			int page, int cat_id) {

		String sqlString = "from DocumentOld as model where 1=1";
		if (document != null) {
			if (document.getNum() != null) {
				sqlString = sqlString + " and model.num=" + document.getNum();
			}

		}

		sqlString = sqlString + " order by model.submitTime desc";

		int allRow = documentOldDAO.getAllRowCount(sqlString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sqlString);
		// sql = sql + " order by model.requestNo";
		List<ServiceRequest> list = documentOldDAO.queryForPage(sqlString,
				offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}

	/**
	 * @param num
	 * @param version
	 * @return
	 */
	public DocumentOld gethistory(String num, Integer version) {

		return documentOldDAO.gethistory(num, version);
	}

	/**
	 * @param documentCatId
	 */
	public boolean contains(Integer documentCatId) {

		List dList = documentDAO.findByCat(documentCatId);
		if (dList != null && !dList.isEmpty()) {
			return true;

		} else {
			return false;
		}
	}
	public List<Document> getDocumentsByDepId(int depId)
	{
	return  documentDAO.findByDepartmentId(depId);
	}
	public List<Document> getDocumentsByUserId(int userId)
	{
	return  documentDAO.findByUserId(userId);
	}
}
