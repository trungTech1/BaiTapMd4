package org.shiba.module.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql {
   static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/baitapmoi";
   static String username = "root";
   static String password = "";

    public static Connection getConnection() {
        try {

            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected"+connection);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
