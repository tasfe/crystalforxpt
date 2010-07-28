package crystal.service;

import java.util.List;

import crystal.hibernate.dao.ProductDAO;
import crystal.hibernate.po.Product;


public class ProductService{
	
	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public List findBySql(String hql) {
		return productDAO.findBySql(hql);
	}
	
	public Product findById(Integer id) {
		return productDAO.findById(id);
	}
	
	public List findAll() {
		return productDAO.findAll();
	}

	public void save(Product product){
		productDAO.save(product);
	}
	
	public void update(Product product){
		productDAO.update(product);
	}
	
	public void delete(Product product){
		productDAO.delete(product);
	}
	
//	public void deleteByType(Integer type){
//		productDAO.deleteByType(type);
//	}
//
//	public void batchInsert(List<Product> productList) {
//		productDAO.batchInsert(productList);
//	}

}
