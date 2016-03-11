package net.sherwin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUsage {
	
	static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	static String protocol = "jdbc:derby:";
	static String dbName = "D:/derby_db/testdb";
	
	Connection conn = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JdbcUsage jdbcUsage = new JdbcUsage();
		jdbcUsage.loadDbDriver();
		
		jdbcUsage.initConnection();
		try {
			jdbcUsage.doService("select * from tb_test");
		} finally {
			jdbcUsage.closeConnection();
		}		
	}
	
	public void loadDbDriver() {
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection initConnection() {
		try {
			conn = DriverManager.getConnection(protocol + dbName + ";create=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void doService(String sql) {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				rs = null;
				s.close();
				s = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = null;
	}
}
