package com.upb.agripos.service;

import com.upb.agripos.model.User;

public class AuthService {

    public User login(String username, String password) {
        // Hardcode untuk praktikum
        if (username.equals("kasir") && password.equals("123")) {
            return new User(1, "kasir", "kasir");
        }
        if (username.equals("admin") && password.equals("admin123")) {
            return new User(2, "admin", "admin");
        }
        return null;
    }
}