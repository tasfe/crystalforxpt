package com.combanc.commonsupport.dataaccess.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.RowSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.commonsupport.dataaccess.Abstract.AbstractDataAccessFacade;
import com.combanc.commonsupport.dataaccess.bean.Parameter;
import com.combanc.commonsupport.dataaccess.factory.DataBase;
import com.combanc.commonsupport.dataaccess.wrapper.ResultSetWrapper;
import com.sun.rowset.CachedRowSetImpl;

/**
 * MySql数据库操作外观类
 * @author jy
 *
 */
public class MySqlDataAccessFacade extends AbstractDataAccessFacade{
	private static final Log log = LogFactory.getLog(MySqlDataAccessFacade.class);
	
	DataBase db;
	private Statement stmt; // 普通SQL执行
	private CallableStatement cstmt; // 可调用的SQL执行（存储过程或函数）
	
	@SuppressWarnings("unused")
	private String dbDriver;
	@SuppressWarnings("unused")
	private String url;
	@SuppressWarnings("unused")
	private String userName;
	@SuppressWarnings("unused")
	private String password;
	public MySqlDataAccessFacade(String dbDriver, String url, String userName, String password){
		
		this.dbDriver = dbDriver;
		this.url = url;
		this.userName = userName;
		this.password = password;
		
		// 创建DataBase实例
		this.db = new DataBase(dbDriver, url, userName, password);
		
		// 用户定义
		this.abstractChart=new MysqlChart();
                this.abstractRequestAssets=new MysqlRequestAssets();
	}
	
	///-------------------------------------  常规SQL语句执行
	/**
	 * 执行非查询操作
	 * @param sqlString
	 * @return
	 */
	public boolean executeNonQuery(String sqlString) {
		boolean result = false;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return result;
		}
		
		int i = getNonQueryRowCount(sqlString);
		if(i!=0){
			result = true;
		}	
		return result;
	}

	/**
	 * 获取执行非查询操作影响行数
	 * @param sqlString
	 * @return
	 */
	public int getNonQueryRowCount(String sqlString) {
		int result = 0;
		Connection conn = null;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return result;
		}
		
		try {
			// 取得数据库连接器
			conn = db.getConnection();
			// 简单声明（连接器有两种调用SQL的方法：简单声明与预编译声明）
			stmt = conn.createStatement();
			// 查询操作（JDBC提供两种操作：查询操作与更新操作）
			result = stmt.executeUpdate(sqlString);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {			
				if(stmt!=null)
				{
					stmt.close();	
				}
				if(conn!=null)
				{
					// 关闭数据库链接
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
		return result;
	}
	
	
	/**
	 * 获取执行查询操作影响行数
	 * @param sqlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getQueryRowCount(String sqlString) {
		int result = 0;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return result;
		}
		
		List list = getList(sqlString);
		if(list!=null){
			result = list.size(); 
		}
		return result;
	}


	/**
	 * 通过查询字符串返回List数据集
	 * @param sqlString 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sqlString) {
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return new ArrayList();
		}
		
		return getList(sqlString, 0, 0);
	}

	
	/**
	 * 通过查询字符串和页大小返回List数据集
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getList(String sqlString, int pageSize, int pageNum) {
        List list = new ArrayList();
        RowSet rowSet;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return list;
		}
        
		try {	
			rowSet = getRowSet(sqlString, pageSize, pageNum);
			while(rowSet.next())
			{
				Map map = new HashMap();
				for(int i=1;i<=rowSet.getMetaData().getColumnCount();i++)
				{
					// getColumnName(column)与getColumnLabel(column)的区别是是否支持重命名
					map.put(rowSet.getMetaData().getColumnLabel(i).toUpperCase(), rowSet.getString(i));
				}
				list.add(map);
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		return list;
	}
	

	/**
	 * 通过查询字符串返回RowSet数据集
	 * @param sqlString
	 * @return
	 */
	public RowSet getRowSet(String sqlString) throws SQLException {
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return new CachedRowSetImpl();
		}
		
		return getRowSet(sqlString, 0, 0);
	}

	/**
	 * 通过查询字符串和页大小返回RowSet数据集
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	public RowSet getRowSet(String sqlString, int pageSize, int pageNum)
			throws SQLException {
		log.debug(sqlString);
		
        ResultSet rs = null;
        Connection conn = null;
        CachedRowSetImpl rowSet = new CachedRowSetImpl();
        
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return rowSet;
		}
		
		try {	
			// 取得数据库连接器
			conn = db.getConnection();
			// 简单声明（连接器有两种调用SQL的方法：简单声明与预编译声明）
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// 查询操作（JDBC提供两种操作：查询操作与更新操作）
			rs = stmt.executeQuery(sqlString);

			if(pageSize > 0 && pageNum > 0){
				rowSet.setPageSize(pageSize);
				int skip = (pageNum - 1) * pageSize + 1;
//				rowSet.populate(rs, skip);
				rowSet.populate(new ResultSetWrapper(rs), skip);
			}
			else{
//				rowSet.populate(rs);
				rowSet.populate(new ResultSetWrapper(rs));
			}

		} catch (Exception e) {
			System.out.println("-----error----->>"+sqlString);
			log.error(e);
			//e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
				{				
					rs.close();					
				}
				if(stmt!=null)
				{
					stmt.close();	
				}
				if(conn!=null)
				{
					// 关闭数据库链接
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowSet;
	}

	
	///----------------------------------------- 事务处理
	/**
	 * 开启事务，返回开启了事务的连接
	 * @return
	 */
	public Connection beginTransaction() {
		 Connection conn = null;
		 try {	
				// 取得数据库连接器
				conn = db.getConnection();
				 //设置不自动提交，相当于开启了事务
	            conn.setAutoCommit(false);
	            //设置JDBC事务的级别
	            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 
	            
	            System.out.println("beginTransaction");
				
		 }
		 catch (Exception e) {
				e.printStackTrace();
				try
				{
					if(conn!=null)
					{
						// 关闭数据库链接
						conn.close();
					}
				} catch (Exception ex) {
					e.printStackTrace();
				}
				conn = null;
		 }
		 
		 return conn;
	}

	/**
	 * 提交事务
	 * @param conn 事务所在的连接
	 * @return
	 */
	public void commitTransaction(Connection conn) {
		try
		{
			conn.commit();
			System.out.println("commitTransaction");
		}
		catch (Exception e) {
			e.printStackTrace();
		 }finally{
				try {
					if(conn!=null)
					{
						// 关闭数据库链接
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
	}
	
	/**
	 * 回滚事务
	 * @param conn 事务所在的连接
	 */
	public void roolbackTransaction(Connection conn) {
		
		try
		{
			// 当本事务中的插入和更新SQL其中一个执行异常的时候，整个事务回滚 
			conn.rollback();
			System.out.println("roolbackTransaction");
		}
		catch (Exception e) {
			e.printStackTrace();
		 }finally{
				try {
					if(conn!=null)
					{
						// 关闭数据库链接
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
	}

	/**
	 * 事务中执行非查询操作，执行完成后不关闭连接
	 * @param sqlString
	 * @return
	 */
	public boolean executeNonQueryInTransaction(String sqlString,
			Connection conn) {
		boolean result = false;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return result;
		}
		
		int i = getNonQueryRowCountInTransaction(sqlString,conn);
		if(i!=0){
			result = true;
		}
		return result;
	}

	/**
	 * 事务中获取执行非查询操作影响行数，执行完成后不关闭连接
	 * @param sqlString
	 * @return
	 */
	public int getNonQueryRowCountInTransaction(String sqlString,
			Connection conn) {
		int result = 0;
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return result;
		}
		
		try {
			// 简单声明（连接器有两种调用SQL的方法：简单声明与预编译声明）
			stmt = conn.createStatement();
			// 查询操作（JDBC提供两种操作：查询操作与更新操作）
			result = stmt.executeUpdate(sqlString);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {			
				if(stmt!=null)
				{
					stmt.close();	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
		return result;
	}

	/**
	 * 事务中通过查询字符串返回RowSet数据集，完成后不关闭连接
	 * @param sqlString
	 * @return
	 */
	public RowSet getRowSetInTransaction(String sqlString, Connection conn)
			throws SQLException {
		
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return new CachedRowSetImpl();
		}
		
		return getRowSetInTransaction(sqlString, 0, 0,conn);
	}

	/**
	 * 事务中通过查询字符串和页大小返回RowSet数据集，完成后不关闭连接
	 * @param sqlString
	 * @param pageSize 
	 * @param pageNum
	 * @return
	 */
	public RowSet getRowSetInTransaction(String sqlString, int pageSize,
			int pageNum, Connection conn) throws SQLException {

        ResultSet rs = null;
        CachedRowSetImpl rowSet = new CachedRowSetImpl();
        
		if(null==sqlString||sqlString.length()==0){ // 执行SQL语句不为空
			log.warn("执行SQL语句为空！");
			return rowSet;
		}
		
		try {	
			// 简单声明（连接器有两种调用SQL的方法：简单声明与预编译声明）
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// 查询操作（JDBC提供两种操作：查询操作与更新操作）
			rs = stmt.executeQuery(sqlString);

			if(pageSize > 0 && pageNum > 0){
				rowSet.setPageSize(pageSize);
				int skip = (pageNum - 1) * pageSize + 1;
//				rowSet.populate(rs, skip);
				rowSet.populate(new ResultSetWrapper(rs), skip);
			}
			else{
//				rowSet.populate(rs);
				rowSet.populate(new ResultSetWrapper(rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null)
				{				
					rs.close();					
				}
				if(stmt!=null)
				{
					stmt.close();	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rowSet;
	}

	
	// ---------------------------------------------- 执行回调存储过程
	/****
	 * 执行回调存储过程
	 * @param pName 回调存储过程或方法名 
	 * @param inParas 输入参数
	 * @param outPara 输出参数
	 * @return
	 */
	public List<Object> CallProcedure(String pName, List<Parameter> inParas, List<Parameter> outParas)
			throws SQLException {
		
		Connection conn = null;
		List<Object> result = new ArrayList<Object>();
		
		if(null==pName||pName.length()==0){ // 确保回调存储过程或方法名不为空
			log.warn("回调存储过程或方法名为空！");
			return result;
		}
		
		if(null!=inParas && inParas.size()>0){
			if(inParas.get(0).getIndex()<=0){ // 确保参数索引从1开始
				log.warn("存储过程或方法名的参数索引必须大于0 ！");
				return  result;
			}
		}
		
		if(null!=outParas && outParas.size()>0){
			if(outParas.get(0).getIndex()<=0){ // 确保参数索引从1开始
				log.warn("存储过程或方法名的参数索引必须大于0 ！");
				return  result;
			}
		}	
		
		try {	
			// 取得数据库连接器
			conn = db.getConnection();
			// 执行调用的SQL
			cstmt = conn.prepareCall(pName);
			
			// -------------------------------------------------
			// 设置输入参数
			//cstmt.setString("parameterName", null);
			if(null!=inParas&&inParas.size()>0) {
				for(int i=0;i<inParas.size();i++){
					Parameter inpara = inParas.get(i);
					Integer intype = inpara.getType();
					
					if(intype==java.sql.Types.INTEGER){ 
						cstmt.setInt(inpara.getIndex(), Integer.parseInt(inpara.getValue()));
					}else if(intype==java.sql.Types.BIGINT){
						cstmt.setLong(inpara.getIndex(), Long.parseLong(inpara.getValue()));
					}else if(intype==java.sql.Types.VARCHAR){
						cstmt.setString(inpara.getIndex(), inpara.getValue());
					}else if(intype==java.sql.Types.BOOLEAN){
						cstmt.setBoolean(inpara.getIndex(), Boolean.parseBoolean(inpara.getValue()));
					}else {
						cstmt.setObject(inpara.getParamterName(), inpara.getValue());
					}
	
				}
			}
			
			// -------------------------------------------------
			// 注册输出参数
			//cstmt.registerOutParameter("parameterName", java.sql.Types.BOOLEAN); 
			if(null!=outParas&&outParas.size()>0) {
				for(int i=0;i<outParas.size();i++){
					Parameter outpara = outParas.get(i);
	
					cstmt.registerOutParameter(outpara.getIndex(), outpara.getType());
				}
			}
			
			//执行
			cstmt.execute();
			
			//------------------------------------------------
			// 返回值   
			//boolean result = cstmt.getBoolean("parameterName");
			if(null!=outParas&&outParas.size()>0) {
				for(int i=0;i<outParas.size();i++){
					Parameter outpara = outParas.get(i);
					Integer outtype = outpara.getType();
					
					if(outtype==java.sql.Types.INTEGER){ 
						int rs = cstmt.getInt(outpara.getParamterName());
						result.add(rs);
					}else if(outtype==java.sql.Types.BIGINT){
						long rs = cstmt.getLong(outpara.getParamterName());
						result.add(rs);
					}else if(outtype==java.sql.Types.VARCHAR){
						String rs = cstmt.getString(outpara.getParamterName());
						result.add(rs);
					}else if(outtype==java.sql.Types.BOOLEAN){
						Boolean rs = cstmt.getBoolean(outpara.getParamterName());
						result.add(rs);
					}else {
						Object rs = cstmt.getObject(outpara.getParamterName());
						result.add(rs);
					}
					
				}
			}
			
		} catch (Exception e) {
			System.out.println("-----error----->>"+pName);
			log.error(e);
			//e.printStackTrace();
		}finally{
			try {
				if(stmt!=null)
				{
					cstmt.close();	
				}
				if(conn!=null)
				{
					// 关闭数据库链接
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	
}
