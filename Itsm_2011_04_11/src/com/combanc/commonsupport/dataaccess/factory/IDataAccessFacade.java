package com.combanc.commonsupport.dataaccess.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.RowSet;

import com.combanc.commonsupport.dataaccess.bean.Parameter;


/**
 * 数据库外观类
 * @author jy
 */
public interface IDataAccessFacade {
	 
	///-------------------------------------  常规SQL语句执行
	/**
	 * 执行非查询操作
	 * @param sqlString
	 * @return
	 */
	public boolean executeNonQuery(String sqlString);
	
	/**
	 * 获取执行非查询操作影响行数
	 * @param sqlString
	 * @return
	 */
	public int getNonQueryRowCount(String sqlString);
	
	/**
	 * 获取执行查询操作影响行数
	 * @param sqlString
	 * @return
	 */
	public int getQueryRowCount(String sqlString);
	
	/**
	 * 通过查询字符串返回List数据集
	 * @param sqlString 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sqlString);
	   

	/**
	 * 通过查询字符串返回RowSet数据集
	 * @param sqlString
	 * @return
	 */
	public RowSet getRowSet(String sqlString) throws SQLException;
	   
	
	/**
	 * 通过查询字符串和页大小返回List数据集
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sqlString, int pageSize, int pageNum);
	   
	/**
	 * 通过查询字符串和页大小返回RowSet数据集
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	public RowSet getRowSet(String sqlString, int pageSize, int pageNum) throws SQLException;

	
	
	///----------------------------------------- 事务处理
	/**
	 * 开启事务，返回开启了事务的连接
	 * @return
	 */
	public Connection beginTransaction();
	
	/**
	 * 提交事务
	 * @param conn 事务所在的连接
	 */
	public void commitTransaction(Connection conn);
	
	/**
	 * 回滚事务
	 * @param conn 事务所在的连接
	 */
	public void roolbackTransaction(Connection conn);
	/**
	 * 事务中执行非查询操作，执行完成后不关闭连接
	 * @param sqlString 
	 * @return
	 */
	public boolean executeNonQueryInTransaction(String sqlString,Connection conn) ;
	
	/**
	 * 事务中获取执行非查询操作影响行数，执行完成后不关闭连接
	 * @param sqlString
	 * @return
	 */
	public int getNonQueryRowCountInTransaction(String sqlString,Connection conn);
	
	/**
	 * 事务中通过查询字符串返回RowSet数据集，完成后不关闭连接
	 * @param sqlString
	 * @return
	 */
	public RowSet getRowSetInTransaction(String sqlString,Connection conn) throws SQLException ;
	
	/**
	 * 事务中通过查询字符串和页大小返回RowSet数据集，完成后不关闭连接
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	public RowSet getRowSetInTransaction(String sqlString, int pageSize, int pageNum,Connection conn) throws SQLException ;
	
	
	// ---------------------------------------------- 执行回调存储过程
	/****
	 * 执行回调存储过程
	 * @param pName 回调存储过程或方法名 
	 * @param inParas 输入参数
	 * @param outPara 输出参数
	 * @return
	 */
	public List<Object> CallProcedure(String pName, List<Parameter> inParas, List<Parameter> outParas) throws SQLException;
	
	
	
}
