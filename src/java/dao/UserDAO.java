package dao;

import model.User;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String username, String password) {

        String sql = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";

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
                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // LẤY TẤT CẢ USER 
    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        String sql = "SELECT id, username, role FROM users";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

// LẤY USER THEO ID
    public User getUserById(int id) {

        String sql = "SELECT id, username, role FROM users WHERE id = ?";
        User u = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setRole(rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }
}