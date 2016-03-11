package com.sherwin.examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 创建一个数据库
 * 创建角色表
 * id,name,hp,ap,mp
 * 插入几个角色
 *
 * @author sherwin wu
 */
public class CreateDB {
	
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create() {
		Connection conn = null;
		Statement stmt = null;
		try {
			//mysql是默认的数据库schema
			conn = DriverManager.getConnection("jdbc:mysql://134.64.86.98:3306/mysql","root","111111");

			stmt = conn.createStatement();

			stmt.executeUpdate("create database sanguo");
			stmt.executeUpdate("use sanguo");
			stmt.executeUpdate("create table general(" +
					"id int not null primary key," +
					"name varchar(30) not null," +
					"hp int," +
					"ap int," +
					"mp int)");
			
			//批量执行
			stmt.addBatch("insert into general values(1,'刘备',10,5,2)");
			stmt.addBatch("insert into general values(2,'关羽',20,15,12)");
			stmt.addBatch("insert into general values(3,'张飞',30,25,22)");
			stmt.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stmt = null;
			}
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	static public void main(String[] args) {
		CreateDB createDB = new CreateDB();
		createDB.init();
		createDB.create();
	}

}
