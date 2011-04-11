/**
 * 
 */
package com.combanc.itsm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class ServiceLv {
	
	private int value;
	private String name;
	private String des;//描述
	
	
	
	
	
	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}
	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static List<ServiceLv> getServiceLv()
	{
		List<ServiceLv> sl=new ArrayList<ServiceLv>();
		ServiceLv lv1=new ServiceLv();
		lv1.setDes("非常满意");
		lv1.setName("非常满意");
		lv1.setValue(1);
		sl.add(lv1);
		ServiceLv lv2=new ServiceLv();
		lv2.setDes("非常满意");
		lv2.setName("非常满意");
		lv2.setValue(2);
		
		sl.add(lv2);
		ServiceLv lv3=new ServiceLv();
		lv3.setDes("不满意");
		lv3.setName("不满意");
		lv3.setValue(3);
		
		sl.add(lv3);
		return sl;
		
	}
}
