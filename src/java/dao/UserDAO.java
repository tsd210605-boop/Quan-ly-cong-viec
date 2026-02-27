package dao;

import model.User;
import util.DBConnection;
import java.sql.*;

public class UserDAO {

    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
               
                u.setRole(rs.getString("role"));
                return u; // ✅ trả về user tương ứng (id khác nhau)
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
