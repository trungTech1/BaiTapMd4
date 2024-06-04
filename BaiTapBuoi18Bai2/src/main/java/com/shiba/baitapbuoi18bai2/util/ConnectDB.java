package com.shiba.baitapbuoi18bai2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/song_db?createDatabaseIfNotExist=true";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public Connection getConnection (){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public  void  closeConnection(Connection conn){
        try {
            if (!conn.isClosed()){
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
