package crystal.service;

import java.util.List;

import crystal.hibernate.dao.MaterialBuyDAO;
import crystal.hibernate.po.MaterialBuy;


public class MaterialBuyService{
	
	private MaterialBuyDAO materialBuyDAO;

	public MaterialBuyDAO getMaterialBuyDAO() {
		return materialBuyDAO;
	}

	public void setMaterialBuyDAO(MaterialBuyDAO materialBuyDAO) {
		this.materialBuyDAO = materialBuyDAO;
	}
	
	public List findBySql(String hql) {
		return materialBuyDAO.findBySql(hql);
	}
	
	public MaterialBuy findById(Integer id) {
		return materialBuyDAO.findById(id);
	}
	
	public List findAll() {
		return materialBuyDAO.findAll();
	}

	public void save(MaterialBuy materialBuy){
		materialBuyDAO.save(materialBuy);
	}
	
	public void update(MaterialBuy materialBuy){
		materialBuyDAO.update(materialBuy);
	}
	
	public void delete(MaterialBuy materialBuy){
		materialBuyDAO.delete(materialBuy);
	}
}
