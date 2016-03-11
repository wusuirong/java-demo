package com.sherwin.examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 把驱动引入lib
 *
 * @author sherwin wu
 */
public class UsageSample1 {
	public void simpleQuery() throws ClassNotFoundException, SQLException {
		//1 加载并注册驱动，名字就是驱动包里的类路径
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		/*
		 * 2 通过JDBC的URL建立连接
		 * URL格式为jdbc:厂商:驱动类型:地址端口等
		 */
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@134.64.75.10:1522:sjzgx","develop_telemt","17081708abc");
		
		/*
		 * 3 用连接建立会话
		 */
		Statement stmt = conn.createStatement();
		
		/*
		 * 4 执行增删改查
		 */
		ResultSet rs = stmt.executeQuery("select * from bm_projecttemplate");
	}
	
	static public void main(String[] args) throws ClassNotFoundException, SQLException {
		UsageSample1 accessSample1 = new UsageSample1();
		accessSample1.simpleQuery();
	}
}
