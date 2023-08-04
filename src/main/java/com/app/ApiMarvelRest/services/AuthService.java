package com.app.ApiMarvelRest.services;

import com.app.ApiMarvelRest.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public User login(User user) {
        if ("test@test.com".equals(user.getEmail()) && "123456789".equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}

