package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.MenuDAO;
import com.combanc.itsm.pojo.Menu;

public class MenuService extends BaseService<Menu, Integer> {

	private MenuDAO menuDAO;
	public static final String TYPE = "type";
	public static final String PARENT_ID = "parentId";

	public List findByType(String type) {
		return menuDAO.findByProperty(TYPE, type);
	}

	public List findByParentId(Integer id) {
		return menuDAO.findByProperty(PARENT_ID, id);
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		super.setDao(menuDAO);
		this.menuDAO = menuDAO;
	}

	public List<Menu> findByTypeAndRoleType(String type, String roleType) {
		String sqlString = "from Menu p where p.type=" + type
				+ " and INSTR(p.roleType,'"+roleType+"')>0";
		return menuDAO.findByHql(sqlString);
	}

}
