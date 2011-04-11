package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.KnowledgeBaseDAO;
import com.combanc.itsm.pojo.KnowledgeBase;

public class KnowledgeBaseService {
	private KnowledgeBaseDAO knowledgeBaseDAO;


	public KnowledgeBaseDAO getKnowledgeBaseDAO() {
		return knowledgeBaseDAO;
	}

	public void setKnowledgeBaseDAO(KnowledgeBaseDAO knowledgeBaseDAO) {
		this.knowledgeBaseDAO = knowledgeBaseDAO;
	}

	public List<KnowledgeBase> findAll() {
		return knowledgeBaseDAO.findAll();
	}

	public void save(KnowledgeBase knowledgeBase) {
		knowledgeBaseDAO.save(knowledgeBase);
	}

	public void saveOrUpdate(KnowledgeBase knowledgeBase) {
		knowledgeBaseDAO.attachDirty(knowledgeBase);
	}

	public KnowledgeBase findById(Integer knowledgeBaseId) {
		return (KnowledgeBase) knowledgeBaseDAO.findById(knowledgeBaseId);
	}
	public PageBean findByCategoryId(KnowledgeBase knowledgeBase, int pageSize, int page) {
		String sql = "from KnowledgeBase as model where 1=1 and model.mode=1 ";
		if (knowledgeBase != null) {			
			if (knowledgeBase.getCategoryId() != null && knowledgeBase.getCategoryId().getId() != null && knowledgeBase.getCategoryId().getId() != -1) {
				sql = sql + " and model.categoryId =" + knowledgeBase.getCategoryId().getId() + " ";
			}
		}
		int allRow = knowledgeBaseDAO.getAllRowCount(sql); //总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); //总页数
		final int offset = PageBean.countOffset(pageSize, page); //当前页开始记录
		final int length = pageSize; //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		//sql = sql + " order by model.id";
		List<KnowledgeBase> list = knowledgeBaseDAO.queryForPage(sql, offset,length);
		
		//List<KnowledgeBase> list = knowledgeBaseDAO.findByProperties(sql, offset, length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	public List findByCategoryId2(KnowledgeBase knowledgeBase) {
		String sql = "from KnowledgeBase as model where 1=1 and model.mode=1 ";
		if (knowledgeBase != null) {			
			if (knowledgeBase.getCategoryId() != null && knowledgeBase.getCategoryId().getId() != null && knowledgeBase.getCategoryId().getId() != -1) {
				sql = sql + " and model.categoryId =" + knowledgeBase.getCategoryId().getId() + " ";
			}
		}
		
		List<KnowledgeBase> list = knowledgeBaseDAO.findByHql(sql);		
		return list;
	}
	public void update(KnowledgeBase knowledgeBase) {
		knowledgeBaseDAO.update(knowledgeBase);
	}

	public void deleteById(Integer knowledgeBaseId) {
		KnowledgeBase knowledgeBase = null;
		if (knowledgeBaseId != null)
			knowledgeBase = (KnowledgeBase) knowledgeBaseDAO.findById(knowledgeBaseId);
		if (knowledgeBase != null)
			knowledgeBaseDAO.delete(knowledgeBase);
	}

	public PageBean query(KnowledgeBase knowledgeBase, int pageSize, int page) {
		String sql = "from KnowledgeBase as model where 1=1";
		if (knowledgeBase != null) {
			if (knowledgeBase.getIndexcode() != null&& knowledgeBase.getIndexcode().trim().length()!=0) {
				sql = sql + " and model.indexcode like '%" + knowledgeBase.getIndexcode() + "%'";
			}
			if (knowledgeBase.getTitle() != null && knowledgeBase.getTitle().trim().length() != 0) {
				sql = sql + " and model.title like '%" + knowledgeBase.getTitle() + "%'";
			}
			if (knowledgeBase.getSymptom() != null && knowledgeBase.getSymptom().trim().length() != 0) {
				sql = sql + " and  model.symptom like '%" + knowledgeBase.getSymptom() + "%'";
			}
			if (knowledgeBase.getCategoryId() != null && knowledgeBase.getCategoryId().getId() != null && knowledgeBase.getCategoryId().getId() != -1) {
				sql = sql + " and model.categoryId =" + knowledgeBase.getCategoryId().getId() + " ";
			}
		}

		int allRow = knowledgeBaseDAO.getAllRowCount(sql); //总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); //总页数
		final int offset = PageBean.countOffset(pageSize, page); //当前页开始记录
		final int length = pageSize; //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		//sql = sql + " order by model.id";
		List<KnowledgeBase> list = knowledgeBaseDAO.queryForPage(sql, offset,length);
		
		//List<KnowledgeBase> list = knowledgeBaseDAO.findByProperties(sql, offset, length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public PageBean query2(KnowledgeBase knowledgeBase, int pageSize, int page,String type,String flag) {//工程师模式搜索
		String sql = "from KnowledgeBase as model where 1=1";
		if (knowledgeBase != null) {
			if (knowledgeBase.getTitle() != null && knowledgeBase.getTitle().trim().length() != 0) {
				if(type!=null&&!type.equals("")) {
					if(type.equals("Title"))
						sql = sql + " and model.title like '%" + knowledgeBase.getTitle() + "%'";
					else if(type.equals("Content")){
							sql=sql+" and ( model.title like '%" + knowledgeBase.getTitle() + "%'";
							sql=sql+" or model.symptom like '%"+knowledgeBase.getTitle()+"%' "; 
							sql=sql+" or model.solution like '%"+knowledgeBase.getTitle()+"%' ) ";
					}else if(type.equals("ITer")){
						sql=sql+" and model.engineerId.truename like '%"+knowledgeBase.getTitle()+"%' ";			
					}
				}
				
			}			
		}
		
		if(flag!=null&&!flag.equals("")&&flag.equals("Prob")) {			
				sql=sql+" and model.mode=2";
		}else sql=sql+" and model.mode=1";
		
		int allRow = knowledgeBaseDAO.getAllRowCount(sql); //总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); //总页数
		final int offset = PageBean.countOffset(pageSize, page); //当前页开始记录
		final int length = pageSize; //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

		System.out.println(sql);
		//sql = sql + " order by model.id";
		List<KnowledgeBase> list = knowledgeBaseDAO.queryForPage(sql, offset,length);
		
		//List<KnowledgeBase> list = knowledgeBaseDAO.findByProperties(sql, offset, length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	public List<KnowledgeBase> getByEngineerId(int userId)
	{
		return knowledgeBaseDAO.findByEngineerId(userId);
	}
}
