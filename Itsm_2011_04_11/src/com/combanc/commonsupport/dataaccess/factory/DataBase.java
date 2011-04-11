package com.combanc.commonsupport.dataaccess.factory;

import java.sql.*;

/**
 * JDBC连接类
 * @author jy
 */
public class DataBase {
	   private String dbDriver;
	   private String url;
	   private String userName;
	   private String password;
	   
	   public DataBase(String dbDriver, String url, String userName, String password){
		   this.dbDriver = dbDriver;
		   this.url = url;
		   this.userName = userName;
		   this.password = password;
	   }

	   /**
	    * 建立默认JDBC连接
	    */
	   public Connection getConnection() {
	      return getConnection(this.dbDriver, this.url, this.userName, this.password);
	   }
	   
	   /** 
	    * 建立带参数的JDBC连接
	    * @param dbDriver 数据库驱动
	    * @param url 数据库连接URL
	    * @param userName 用户名
	    * @param password 密码
	    * */
	   public Connection getConnection(String dbDriver, String url, String userName, String password) {
			Connection conn = null;
			try {
				// 加载JDBC驱动
				Class.forName(dbDriver);
				// 建立数据库连接
				conn = DriverManager.getConnection(url,userName,password);
			} catch (Exception e) {
				System.out.println("数据库链接失败！");
				e.printStackTrace();
			}
			return conn;		
	   }
}
