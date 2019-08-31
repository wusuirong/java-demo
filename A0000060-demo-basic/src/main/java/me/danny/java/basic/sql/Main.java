package me.danny.java.basic.sql;

import org.apache.commons.lang3.RandomStringUtils;

import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        int tableCount = 1000;
        int recordCount = 10;

//        for (int i=0; i<tableCount; i++) {
//            System.out.println("CREATE TABLE my_table_" + i + "(cola VARCHAR(200));");
//
//
//        }
//
//        for (int i=0; i<tableCount; i++) {
//
//            System.out.println("alter table my_table_" + i + " ADD COLUMN colb VARCHAR(50);");
//        }

//        for (int i=0; i<tableCount; i++) {
//            System.out.println("DROP TABLE my_table_" + i + ";");
//        }


//        for (int i=0; i<tableCount; i++) {
//            String sql = "insert into my_table_" + i + "(cola, colb) values ('a', 'b');";
//
//            for (int j=0; j<recordCount; j++) {
//                System.out.println(sql);
//            }
//        }
//
//        for (int i=0; i<tableCount; i++) {
//            String sql = "update my_table_" + i + " set cola='c';";
//
//            for (int j=0; j<recordCount; j++) {
//                System.out.println(sql);
//            }
//        }

        for (int i=0; i<tableCount; i++) {
            String sql = "select * from my_table_" + i + ";";

            for (int j=0; j<recordCount; j++) {
                System.out.println(sql);
            }
        }
    }
}
