package com.combanc.itsm.action.systemmanage;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Menu;
import com.combanc.itsm.service.MenuService;

public class MenuAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private MenuService menuService;
	private Map menuMap;
	
	
	public Map getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map menuMap) {
		this.menuMap = menuMap;
	}

	public MenuService getMenuService() {
		return menuService;
	}
	
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
//		menuList = menuService.getAll();
		List menuList=menuService.findByType("1");  //menu List
		Map<Menu,Map<Menu,List>> map=new LinkedHashMap();
		for(int i=0;i<menuList.size();i++){
			Menu menu=(Menu) menuList.get(i);			
			map.put(menu, null);
		}
		Iterator key=map.keySet().iterator();
		while(key.hasNext()){
			Menu keyMenu=(Menu) key.next();
			Map<Menu,List> subMap=new LinkedHashMap<Menu,List>();
			List listType2=menuService.findByParentId(keyMenu.getId());
			for(int i=0;i<listType2.size();i++){
				Menu temp=(Menu) listType2.get(i);
				List listType3=menuService.findByParentId(temp.getId());
				subMap.put(temp, listType3);					
			}
			map.put(keyMenu, subMap);
		}
		this.setMenuMap(map);
		return "success";
	}
}
