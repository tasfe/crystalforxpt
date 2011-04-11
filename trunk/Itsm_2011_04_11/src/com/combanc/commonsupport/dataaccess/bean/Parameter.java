package com.combanc.commonsupport.dataaccess.bean;

/**
 * 参数实体
 * @author 
 *
 */
public class Parameter {

	/**
	 * 参数索引
	 */
	private int index; 
	/**
	 * 参数名
	 */
	private String paramterName; 
	/**
	 * 参数类型 (类型为java.sql.Types 枚举值)
	 * @category java.sql.Types.INTEGER  int
	 * @category java.sql.Types.BIGINT long
	 * @category java.sql.Types.VARCHAR string
	 * @category java.sql.Types.BOOLEAN boolean
	 * @category 其他为 object
	 */
	private Integer type; 
	/**
	 * 参数值
	 */
	private String value;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getParamterName() {
		return paramterName;
	}
	public void setParamterName(String paramterName) {
		this.paramterName = paramterName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	protected Parameter(){}
	
	protected Parameter(int index, String paramterName, Integer type, String value){
		this.setIndex(index);
		this.setParamterName(paramterName);
		this.setType(type);
		this.setValue(value);
	}
	
	protected Parameter(int index, String paramterName, Integer type){
		this.setIndex(index);
		this.setParamterName(paramterName);
		this.setType(type);
	}
	
	/**
	 * 
	 * @param index 参数索引
	 * @param paramterName 参数名
	 * @param type 参数类型
	 * @param value 参数值
	 * @return
	 */
	public static Parameter setParameter(int index, String paramterName, Integer type, String value){
		return new Parameter(index, paramterName, type, value);
	}
	
	/**
	 * 
	 * @param index 参数索引
	 * @param paramterName 参数名
	 * @param type 参数类型
	 * @return
	 */
	public static Parameter setParameter(int index, String paramterName, Integer type){
		return new Parameter(index, paramterName, type);
	}
	
}
