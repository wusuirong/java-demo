package com.sherwin.examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sherwin wu
 */
public class QueryData {
	
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
			conn = DriverManager.getConnection("jdbc:mysql://134.64.86.98:3306/sanguo","root","111111");

			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("select * from general");
			while (false != rs.next()) {
				String name = rs.getString("name");
				int hp = rs.getInt("hp");
				int ap = rs.getInt("ap");
				int mp = rs.getInt("mp");
				System.out.println(name + " 体力：" + hp
						+ " 攻击力：" + ap + " 魔法：" + mp);
			}
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
		QueryData createDB = new QueryData();
		createDB.init();
		createDB.create();
	}

}
