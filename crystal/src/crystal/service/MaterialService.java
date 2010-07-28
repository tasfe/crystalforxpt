package crystal.service;

import java.util.List;

import crystal.hibernate.dao.MaterialDAO;
import crystal.hibernate.po.Material;


public class MaterialService{
	
	private MaterialDAO materialDAO;

	public MaterialDAO getMaterialDAO() {
		return materialDAO;
	}

	public void setMaterialDAO(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}
	
	public List findBySql(String hql) {
		return materialDAO.findBySql(hql);
	}
	
	public Material findById(Integer id) {
		return materialDAO.findById(id);
	}
	
	public List findAll() {
		return materialDAO.findAll();
	}
	
	public List findByType(Integer type) {
		return materialDAO.findByType(type);
	}

	public void save(Material material){
		materialDAO.save(material);
	}
	
	public void update(Material material){
		materialDAO.update(material);
	}
	
	public void delete(Material material){
		materialDAO.delete(material);
	}
	
//	public void deleteByType(Integer type){
//		materialDAO.deleteByType(type);
//	}
//
//	public void batchInsert(List<Material> materialList) {
//		materialDAO.batchInsert(materialList);
//	}

}
