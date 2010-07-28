package crystal.service;

import java.util.List;

import crystal.hibernate.dao.ProductSellDAO;
import crystal.hibernate.po.ProductSell;


public class ProductSellService{
	
	private ProductSellDAO productSellDAO;

	public ProductSellDAO getProductSellDAO() {
		return productSellDAO;
	}

	public void setProductSellDAO(ProductSellDAO productSellDAO) {
		this.productSellDAO = productSellDAO;
	}
	
	public List findBySql(String hql) {
		return productSellDAO.findBySql(hql);
	}
	
	public ProductSell findById(Integer id) {
		return productSellDAO.findById(id);
	}
	
	public List findAll() {
		return productSellDAO.findAll();
	}

	public void save(ProductSell productSell){
		productSellDAO.save(productSell);
	}
	
	public void update(ProductSell productSell){
		productSellDAO.update(productSell);
	}
	
	public void delete(ProductSell productSell){
		productSellDAO.delete(productSell);
	}
}
