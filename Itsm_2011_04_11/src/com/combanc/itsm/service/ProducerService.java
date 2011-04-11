package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsProducerDAO;
import com.combanc.itsm.pojo.AssetsProducer;

public class ProducerService {
	private AssetsProducerDAO assetsProducerDAO;
	private AssetsBaseDAO assetsBaseDAO;

	public AssetsBaseDAO getAssetsBaseDAO() {
		return assetsBaseDAO;
	}


	public void setAssetsBaseDAO(AssetsBaseDAO assetsBaseDAO) {
		this.assetsBaseDAO = assetsBaseDAO;
	}


	public PageBean findAll(int pageSize, int page) {
		String queryString = "from AssetsProducer"; // 查询语句
		int currentPage = PageBean.countCurrentPage(page);
		int allRow = assetsProducerDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsProducer> list = assetsProducerDAO.findAll(queryString,
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
	
	public List isnull(String id){
		return assetsBaseDAO.findByProducerid(id);
	}
	
	
	
	public List findAll(){
		return assetsProducerDAO.findAll();
	}

	public boolean save(AssetsProducer producer) {
		try {
			assetsProducerDAO.save(producer);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void deleteById(Integer id) {
		AssetsProducer producer = null;
		if (id != null)
			producer = (AssetsProducer) assetsProducerDAO.findById(
					AssetsProducer.class, id);
		assetsProducerDAO.delete(producer);
	}

	public AssetsProducer findById(Integer id) {
		AssetsProducer producer = null;
		if (id != null)
			producer = (AssetsProducer) assetsProducerDAO.findById(
					AssetsProducer.class, id);
		return producer;
	}

	public void update(AssetsProducer producer) {
		try {
			assetsProducerDAO.update(producer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PageBean query(AssetsProducer producer, int pageSize, int page) {
		String queryString = "from AssetsProducer as model where 1=1 ";
		if (producer.getName() != null
				&& producer.getName().trim().length() != 0) {
			producer.setName(producer.getName().trim());
			queryString = queryString + " and model.name like '%"
					+ producer.getName() + "%'";
		}
		if (producer.getType() != null && producer.getType() != 0) {
			queryString = queryString + " and model.type=" + producer.getType();
		}
		if (producer.getLevel() != null && producer.getLevel() != 0) {
			queryString = queryString + " and model.level="
					+ producer.getLevel();
		}
		if (producer.getPersion() != null
				&& producer.getPersion().trim().length() != 0) {
			producer.setPersion(producer.getPersion().trim());
			queryString = queryString + " and model.persion like '%"
					+ producer.getPersion() + "%'";
		}

		int currentPage = PageBean.countCurrentPage(page);
		int allRow = assetsProducerDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsProducer> list = assetsProducerDAO.findByProperties(
				queryString, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public AssetsProducerDAO getAssetsProducerDAO() {
		return assetsProducerDAO;
	}

	public void setAssetsProducerDAO(AssetsProducerDAO assetsProducerDAO) {
		this.assetsProducerDAO = assetsProducerDAO;
	}
}
