package com.combanc.itsm.pojo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.combanc.common.util.BaseEnum;

public class TablePrivilegeType extends BaseEnum {

	protected TablePrivilegeType(int index, String name, int value,
			String resKey) {
		super(index, name, value, resKey);
	}
	
	public static final TablePrivilegeType self = new TablePrivilegeType(0, "个人", 0, "TablePrivilegeType.self");
	public static final TablePrivilegeType department = new TablePrivilegeType(1, "部门", 1, "TablePrivilegeType.department");
	public static final TablePrivilegeType all = new TablePrivilegeType(2, "所有", 2, "TablePrivilegeType.all");
	
	private static Hashtable<Integer, TablePrivilegeType> allItems = new Hashtable<Integer, TablePrivilegeType>();
	static {
		allItems.put(0, self);
		allItems.put(1, department);
		allItems.put(2, all);
	}
	
	public static TablePrivilegeType getItemByValue(int value) {
		if(allItems.containsKey(new Integer(value)))
			return allItems.get(new Integer(value));
		return null;
	}
	
	public static List<TablePrivilegeType> getMembers() {
		List<TablePrivilegeType> results = new ArrayList<TablePrivilegeType>();
		results.add(0, self);
		results.add(1, department);
		results.add(2, all);
		return results;
	}
}
