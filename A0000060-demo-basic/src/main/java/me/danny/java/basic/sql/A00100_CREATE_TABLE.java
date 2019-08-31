package me.danny.java.basic.sql;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

public class A00100_CREATE_TABLE {
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

            int tableCount = 1000;
            int recordCount = 10;

            swRoot.start();
            sw.start();

            // create table
            for (int i=0; i<tableCount; i++) {
                sql = "CREATE TABLE my_table_" + i + "(CMDB_ID RAW(16) not null, A_DATA_TESTISNEW NUMBER(1) null, A_DATA_UPDATED_BY VARCHAR2(512) null, A_CITY VARCHAR2(50) null, A_DATA_OPERATIONCORRSTATE NUMBER(11) null, A_PRODUCT_NAME VARCHAR2(50) null, A_DATA_OPERATIONSTATE NUMBER(11) null, A_TENANTOWNER VARCHAR2(32) null, A_DISCOVERED_PRODUCT_NAME VARCHAR2(900) null, A_DATA_OPERATIONISNEW NUMBER(1) null, A_ROOT_CONTAINER VARCHAR2(32) null, A_HAS_CONFIG_FILES NUMBER(1) null, A_DATA_EXTERNALID VARCHAR2(50) null, A_DATA_ORIGIN VARCHAR2(100) null, A_DESCRIPTION VARCHAR2(1000) null, A_STARTUP_TIME NUMBER(22) null, A_ACK_CLEARED_TIME NUMBER(22) null, A_LANGUAGE VARCHAR2(50) null, A_APPLICATION_INSTANCENAME VARCHAR2(100) null, A_DIGEST VARCHAR2(40) null, A_DATA_CHANGESTATE NUMBER(11) null, A_APPLICATION_IP_TYPE VARCHAR2(50) null, A_DATA_CHANGEISNEW NUMBER(1) null, A_CODEPAGE VARCHAR2(50) null, A_TRACK_CHANGES NUMBER(1) null, A_ROOT_CONTAINER_NAME VARCHAR2(900) null, A_DISPLAY_LABEL VARCHAR2(900) null, A_NAME VARCHAR2(900) null, A_WEBSERVER_TYPE VARCHAR2(100) null, A_APPLICATION_PATH VARCHAR2(500) null, A_DATA_CHANGECORRSTATE NUMBER(11) null, A_APPLICATION_PASSWORD RAW(50) null, A_DATA_ALLOW_AUTO_DISCOVERY NUMBER(1) null, A_SOFTWARE_EDITION VARCHAR2(50) null, A_APPLICATION_USERNAME VARCHAR2(200) null, A_USER_LABEL VARCHAR2(900) null, A_CONFIGURATION_FILE_PATH VARCHAR2(256) null, A_DATA_TESTCORRSTATE NUMBER(11) null, A_VERSION VARCHAR2(50) null, A_APPLICATION_VERSION VARCHAR2(500) null, A_WEBSERVER_VERSION VARCHAR2(256) null, A_DOCUMENT_ROOT VARCHAR2(256) null, A_WEBSERVER_CONFI_285048642 VARCHAR2(256) null, A_ROOT_ICONPROPERTIES VARCHAR2(100) null, A_ACK_ID VARCHAR2(80) null, A_DATA_SOURCE VARCHAR2(512) null, A_COUNTRY VARCHAR2(50) null, A_DATA_NOTE VARCHAR2(250) null, A_APPLICATION_TIMEOUT NUMBER(11) null, A_APPLICATION_CATEGORY VARCHAR2(50) null, A_VENDOR VARCHAR2(200) null, A_CREDENTIALS_ID VARCHAR2(50) null, A_STATE VARCHAR2(50) null, A_IS_SAVE_PERSISTENCY NUMBER(1) null, A_SERVER_ROOT VARCHAR2(256) null, A_APPLICATION_IP_DOMAIN VARCHAR2(50) null, A_DATA_ADMINSTATE NUMBER(11) null, A_DOCUMENT_LIST VARCHAR2(50) null, A_APPLICATION_PORT NUMBER(11) null, A_DATA_TESTSTATE NUMBER(11) null, A_APPLICATION_IP VARCHAR2(50) null)";
                status = s.executeUpdate(sql);
            }


            sw.stop();
            System.out.println("create: " + sw.toString());

            sw.reset();
            sw.start();
            // alter table
            for (int i=0; i<tableCount; i++) {
                sql = "alter table my_table_" + i + " ADD COLUMN colb VARCHAR(50)";
                status = s.executeUpdate(sql);
            }
            sw.stop();
            System.out.println("alter: " + sw.toString());



            // insert
            sw.reset();
            sw.start();
            PreparedStatement ps = null;

            for (int i=0; i<tableCount; i++) {
                sql = "insert into my_table_" + i + "(cola, colb) values (?, ?)";
                ps = (PreparedStatement) conn.prepareStatement(sql);

                for (int j=0; j<recordCount; j++) {
                    ps.setString(1, "aaa");
                    ps.setString(2, "bbb");
                    status = ps.executeUpdate();
                }
                ps.close();
            }
            sw.stop();
            System.out.println("insert: " + sw.toString());

            // update
            sw.reset();
            sw.start();
            for (int i=0; i<tableCount; i++) {
                for (int j=0; j<recordCount; j++) {
                    sql = "update my_table_" + i + " set cola='" + j + "'";
                    ps = (PreparedStatement) conn.prepareStatement(sql);
                    status = ps.executeUpdate();
                }
                ps.close();
            }
            sw.stop();
            System.out.println("update: " + sw.toString());

            swRoot.stop();
            System.out.println("total: " + swRoot.toString());

            // delete


            // drop table
//            for (int i=0; i<tableCount; i++) {
//                sql = "drop table my_table_" + i;
//                status = s.executeUpdate(sql);
//            }

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

