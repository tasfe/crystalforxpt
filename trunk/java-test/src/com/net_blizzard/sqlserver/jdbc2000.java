package com.net_blizzard.sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc2000 {
	public jdbc2000() {
		
	}
	
	public static void main(String args[]) {
		 String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //加载JDBC驱动
		  String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=sample";  //连接服务器和数据库sample
		  String userName = "sa";  //默认用户名
		  String userPwd = "123456";  //密码
		  Connection dbConn;
		  Statement smt;
		  ResultSet rs;

		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			// 如果连接成功 控制台输出Connection Successful!
			System.out.println("Connection Successful!");
			smt = dbConn.createStatement();
			rs = smt.executeQuery("select * from sample");
			while (rs.next())
				System.out.println(rs.getString("name"));
			smt.execute("insert into sample (name) values('test')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
