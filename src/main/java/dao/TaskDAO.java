package dao;

import model.Task;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // =========================
    // LẤY DANH SÁCH TASK (ADMIN / USER)
    // =========================
 public List<Task> getTasksByUser(int userId, String role) {
    List<Task> list = new ArrayList<>();
    String sql;

    // ADMIN xem tất cả
    if ("ADMIN".equalsIgnoreCase(role)) {
        sql = "SELECT * FROM tasks ORDER BY created_at DESC";
    } else {
        sql = "SELECT * FROM tasks WHERE user_id = ? ORDER BY created_at DESC";
    }

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            ps.setInt(1, userId);
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setDescription(rs.getString("description"));
            task.setStatus(rs.getString("status"));
            task.setDeadline(rs.getDate("deadline"));
            task.setUserId(rs.getInt("user_id"));

            list.add(task);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}


    // =========================
    // THÊM TASK
    // =========================
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks(title, description, status, user_id, deadline) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());
            ps.setInt(4, task.getUserId());

            if (task.getDeadline() != null) {
                ps.setDate(5, task.getDeadline());
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // XÓA TASK
    // =========================
    public void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // UPDATE TASK (title, description, status, deadline)
    // =========================
    public boolean updateTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ?, status = ?, deadline = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getStatus());

            if (task.getDeadline() != null) {
                ps.setDate(4, task.getDeadline());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setInt(5, task.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // LẤY TASK THEO ID
    // =========================
    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setUserId(rs.getInt("user_id"));
                t.setStatus(rs.getString("status"));
                t.setDeadline(rs.getDate("deadline"));
                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // UPDATE STATUS
    // =========================
    public void updateStatus(int id, String status) {
        String sql = "UPDATE tasks SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // TIM KIEM
    public List<Task> searchTasks(int userId, String role, 
                              String keyword, 
                              String statusFilter, 
                              String userFilter) {

    List<Task> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM tasks WHERE 1=1 ");

    if (!"ADMIN".equals(role)) {
        sql.append("AND user_id = ? ");
    }

    if (keyword != null && !keyword.isEmpty()) {
        sql.append("AND title LIKE ? ");
    }

    if (statusFilter != null && !statusFilter.isEmpty()) {
        sql.append("AND status = ? ");
    }

    if ("ADMIN".equals(role) && userFilter != null && !userFilter.isEmpty()) {
        sql.append("AND user_id = ? ");
    }

    sql.append("ORDER BY deadline ASC");

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql.toString())) {

        int index = 1;

        if (!"ADMIN".equals(role)) {
            ps.setInt(index++, userId);
        }

        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(index++, "%" + keyword + "%");
        }

        if (statusFilter != null && !statusFilter.isEmpty()) {
            ps.setString(index++, statusFilter);
        }

        if ("ADMIN".equals(role) && userFilter != null && !userFilter.isEmpty()) {
            ps.setInt(index++, Integer.parseInt(userFilter));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Task t = new Task();
            t.setId(rs.getInt("id"));
            t.setTitle(rs.getString("title"));
            t.setDescription(rs.getString("description"));
            t.setUserId(rs.getInt("user_id"));
            t.setStatus(rs.getString("status"));
            t.setDeadline(rs.getDate("deadline"));
            list.add(t);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
    public int countByStatus(int userId, String role, String status) {
        String sql;
        if("ADMIN".equalsIgnoreCase(role)){
            sql = "SELECT COUNT(*) FROM tasks WHERE status = ?";
        }
        else{
            sql = "SELECT COUNT(*) FROM tasks WHERE user_id = ? AND status = ?";
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if("ADMIN".equalsIgnoreCase(role)){
                ps.setString(1, status);    
            }
            else{
                ps.setInt(1, userId);
                ps.setString(2,status);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    //TONG SO TASK
    public int countAll(int userId, String role){
        String sql;
        if("ADMIN".equalsIgnoreCase(role)){
            sql = "SELECT COUNT(*) FROM tasks";
        }
        else{
            sql = "SELECT COUNT(*) FROM tasks WHERE user_id = ?";
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if(!"ADMIN".equalsIgnoreCase(role)){
                ps.setInt(1, userId);
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
