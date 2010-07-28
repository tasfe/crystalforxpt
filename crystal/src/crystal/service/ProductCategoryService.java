package crystal.service;

import java.util.List;

import crystal.hibernate.dao.ProductCategoryDAO;
import crystal.hibernate.po.ProductCategory;


public class ProductCategoryService{
	
	private ProductCategoryDAO productCategoryDAO;

	public ProductCategoryDAO getProductCategoryDAO() {
		return productCategoryDAO;
	}

	public void setProductCategoryDAO(ProductCategoryDAO productCategoryDAO) {
		this.productCategoryDAO = productCategoryDAO;
	}
	
	public List findBySql(String hql) {
		return productCategoryDAO.findBySql(hql);
	}
	
	public ProductCategory findById(Integer id) {
		return productCategoryDAO.findById(id);
	}
	
	public List findAll() {
		return productCategoryDAO.findAll();
	}

	public void save(ProductCategory productCategory){
		productCategoryDAO.save(productCategory);
	}
	
	public void update(ProductCategory productCategory){
		productCategoryDAO.update(productCategory);
	}
	
	public void delete(ProductCategory productCategory){
		productCategoryDAO.delete(productCategory);
	}
	
//	public void deleteByType(Integer type){
//		productCategoryDAO.deleteByType(type);
//	}
//
//	public void batchInsert(List<ProductCategory> productCategoryList) {
//		productCategoryDAO.batchInsert(productCategoryList);
//	}

}
