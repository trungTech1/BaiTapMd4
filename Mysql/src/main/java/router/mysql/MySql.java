package router.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/user_db?createDatabaseIfNotExist=true";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection (){

            try {
                Class.forName(driverClassName);
                return DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public static void closeConnection(Connection connec){
        try {
            if(!connec.isClosed()){
                connec.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





}
