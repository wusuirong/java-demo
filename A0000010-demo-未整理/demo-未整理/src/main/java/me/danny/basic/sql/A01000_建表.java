package me.danny.basic.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class A01000_建表 {
    public static void main(String[] args) {

        Connection conn = null;
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:postgresql://192.168.1.50:5432:postgres";
            String user = "dev";
            String password = "aaaaaa";

            if (driver != null) {
                Class.forName(driver);
            }

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
