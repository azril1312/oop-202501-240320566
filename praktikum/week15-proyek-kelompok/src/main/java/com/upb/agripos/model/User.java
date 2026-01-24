package com.upb.agripos.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    // Constructor untuk login (tanpa ID)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "user";
    }

    // Constructor dari database dengan role
    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.password = "";
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public void setPassword(String password) { this.password = password; }
}