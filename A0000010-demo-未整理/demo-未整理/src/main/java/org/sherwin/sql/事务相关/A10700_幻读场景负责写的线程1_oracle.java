package org.sherwin.sql.事务相关;

import java.sql.*;


/**
 * TODO 未完成，运行时复制为两个类，更新的数据值要不一样才好分析
 * 打断点看线程1先执行，但比线程2后结束，最终结果是什么
 * @author Administrator
 *
 */
public class A10700_幻读场景负责写的线程1_oracle {
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
