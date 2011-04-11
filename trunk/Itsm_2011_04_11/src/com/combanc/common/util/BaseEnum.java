/**
 * 
 */
package com.combanc.common.util;

/**
 * 枚举基类
 *
 */
public abstract class BaseEnum {

	private int index;
	private String name;
	private int value;
	private String resKey;
	
	public int getIndex() {
		return index;
	}
	protected void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	protected void setValue(int value) {
		this.value = value;
	}
	public String getResKey() {
		return resKey;
	}
	protected void setResKey(String resKey) {
		this.resKey = resKey;
	}
	
	protected BaseEnum(int index, String name, int value, String resKey) {
		this.setIndex(index);
		this.setName(name);
		this.setValue(value);
		this.setResKey(resKey);
	}

	@Override
	public boolean equals(Object object) {
		if(this.getClass().getName() != object.getClass().getName())
			return false;
		BaseEnum objEnum = (BaseEnum)object;
		return this.getValue() == objEnum.getValue();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
