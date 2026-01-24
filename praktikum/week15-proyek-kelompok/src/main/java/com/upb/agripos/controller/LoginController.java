package com.upb.agripos.controller;

import com.upb.agripos.model.User;
import com.upb.agripos.service.AuthService;

public class LoginController {
    private AuthService service = new AuthService();

    public User doLogin(String username, String password) {
        return service.login(username, password);
    }
}