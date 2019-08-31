package org.sherwin.sql.事务相关;

import java.sql.*;

/**
 * 	CREATE TABLE t_dev_player (
	   ID NUMBER(9) PRIMARY KEY,
	   VERSION NUMBER(9),
	   NAME VARCHAR2(100)
	);
	
	CREATE TABLE t_dev_hero (
	   ID NUMBER(9) PRIMARY KEY,
	   VERSION NUMBER(9),
	   NAME VARCHAR2(100)
	);
 * @author Administrator
 *
 */
public class A10000_数据准备_oracle {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@192.168.6.134:1521:orcl";
			String user = "dev";
			String password = "aaaaaa";

			if (driver != null)
				Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

			Statement s = conn.createStatement();

			String sql = "";
			boolean status = false;

			sql = "update t_dev_player set id=1, version=1, name='wsr'";
			status = s.execute(sql);
			System.out.println("executed: " + sql);

			sql = "update t_dev_hero set id=1, version=1, name='a'";
			status = s.execute(sql);
			System.out.println("executed: " + sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
