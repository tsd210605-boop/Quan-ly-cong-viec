package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/work_management?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASS = "123456";

    public static Connection getConnection() {

        Connection conn = null;

        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối Database
            conn = DriverManager.getConnection(URL, USER, PASS);

            if (conn != null) {
                System.out.println("Kết nối MySQL thành công");
            }

        } catch (Exception e) {
            System.out.println("Lỗi kết nối MySQL:");
            e.printStackTrace();
        }

        return conn;
    }
}