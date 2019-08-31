package me.danny.java.basic.sql;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.sql.*;

public class A00100_CALL_PROC {
    public static void main(String[] args) {

        StopWatch swRoot = new StopWatch();
        StopWatch sw = new StopWatch();

        Connection conn = null;
        try {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://16.187.188.232:5433/wsr_test_1";
            String user = "postgres";
            String password = "Admin_1234";

            if (driver != null) {
                Class.forName(driver);
            }

            conn = DriverManager.getConnection(url, user, password);

            Statement s = conn.createStatement();

            String sql = "";
            int status = 0;

            int tableCount = 2;
            int recordCount = 1;

//            sw.start();
//
//            CallableStatement cs = conn.prepareCall("{call perf_ddl()}");
//            boolean rs = cs.execute();
//
//            sw.stop();
//            System.out.println("result: " + rs + ", time: " + sw.toString());



//            sw.reset();
//            sw.start();
//
//            CallableStatement cs = conn.prepareCall("{call perf_dml()}");
//            boolean rs = cs.execute();
//
//            sw.stop();
//            System.out.println("result: " + rs + ", time: " + sw.toString());



            sw.start();

            CallableStatement cs = conn.prepareCall("{call perf_select()}");
            boolean rs = cs.execute();

            sw.stop();
            System.out.println("result: " + rs + ", time: " + sw.toString());


//            swRoot.stop();
//            System.out.println("total: " + swRoot.toString());
            // create index
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
                conn.close();
            } catch (Exception e) {
            }


        }
    }
}

