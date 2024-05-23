package router.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySql {
    public static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlconnect", "root", "");
        } catch (Exception e) {
            System.out.println("Loi khong the ket noi den database");
        }
    }
}
