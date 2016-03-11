package com.sherwin.examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * CreateDB执行过后才能执行
 * @author sherwin wu
 */
public class PrepareStatementUsage {
	
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
		PreparedStatement stmt = null;
		Statement stmt2 = null;
		long start, end;
		try {
			//mysql是默认的数据库schema
			conn = DriverManager.getConnection("jdbc:mysql://134.64.86.98:3306/sanguo","root","111111");

			stmt = conn.prepareStatement("insert into general values(?,?,?,?,?)");
			start = System.currentTimeMillis();
			for (int i=0; i<100; i++) {
				stmt.setInt(1, i+4);
				stmt.setString(2, "曹操" + i);
				stmt.setInt(3, i+15);
				stmt.setInt(4, i+10);
				stmt.setInt(5, i+30);
				stmt.executeUpdate();
			}
			end = System.currentTimeMillis();
			System.out.println("PreparedStatement use " + (end - start));
			
			stmt2 = conn.createStatement();
			start = System.currentTimeMillis();
			for (int i=200; i<300; i++) {
				stmt2.addBatch("insert into general values(" + i + ",'孙权" + i + "',10,5,2)");
			}
			stmt2.executeBatch();
			end = System.currentTimeMillis();
			System.out.println("Batch use " + (end - start));
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
		PrepareStatementUsage createDB = new PrepareStatementUsage();
		createDB.init();
		createDB.create();
	}

}
