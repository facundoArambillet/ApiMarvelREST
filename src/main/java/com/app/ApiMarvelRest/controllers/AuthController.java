package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.models.User;
import com.app.ApiMarvelRest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping()
    public ResponseEntity<User> login(@RequestBody User user){
        if(authService.login(user) != null) {
            return  new ResponseEntity<>(authService.login(user), HttpStatus.OK);
        }
        else  {
            return  new ResponseEntity<>(authService.login(user), HttpStatus.NOT_FOUND);
        }

    }
}
