package crystal.service;

import java.util.List;

import crystal.hibernate.dao.MaterialCategoryDAO;
import crystal.hibernate.po.MaterialCategory;


public class MaterialCategoryService{
	
	private MaterialCategoryDAO materialCategoryDAO;

	public MaterialCategoryDAO getMaterialCategoryDAO() {
		return materialCategoryDAO;
	}

	public void setMaterialCategoryDAO(MaterialCategoryDAO materialCategoryDAO) {
		this.materialCategoryDAO = materialCategoryDAO;
	}
	
	public List findBySql(String hql) {
		return materialCategoryDAO.findBySql(hql);
	}
	
	public MaterialCategory findById(Integer id) {
		return materialCategoryDAO.findById(id);
	}
	
	public List findAll() {
		return materialCategoryDAO.findAll();
	}

	public void save(MaterialCategory materialCategory){
		materialCategoryDAO.save(materialCategory);
	}
	
	public void update(MaterialCategory materialCategory){
		materialCategoryDAO.update(materialCategory);
	}
	
	public void delete(MaterialCategory materialCategory){
		materialCategoryDAO.delete(materialCategory);
	}
	
//	public void deleteByType(Integer type){
//		materialCategoryDAO.deleteByType(type);
//	}
//
//	public void batchInsert(List<MaterialCategory> materialCategoryList) {
//		materialCategoryDAO.batchInsert(materialCategoryList);
//	}

}
