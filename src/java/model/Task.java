package model;
import java.sql.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private String status;
    private int userId;
    private String username;
    private java.sql.Date deadline;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getUserId() {
    return userId;
}

    public void setUserId(int userId) {
    this.userId = userId;
}   
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    public java.sql.Date getDeadline(){
        return deadline;
    }
    public void setDeadline(java.sql.Date deadline){
        this.deadline = deadline;
    }
    
}
    
