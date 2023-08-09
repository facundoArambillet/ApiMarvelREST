package com.app.ApiMarvelRest.controllers;

import com.app.ApiMarvelRest.models.User;
import com.app.ApiMarvelRest.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private User user;
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        user.setEmail("test@test.com");
        user.setPassword("123456789");
    }
    @Test
    public void givenValidUser_whenLogin_thenReturnOk() {

        when(authService.login(user)).thenReturn(user);

        ResponseEntity<User> response = authController.login(user);

        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void givenInvalidUser_whenLogin_thenReturnNotFound() {
        user.setEmail("wrong@test.com");
        user.setPassword("wrongpassword");

        when(authService.login(user)).thenReturn(null);
        ResponseEntity<User> response = authController.login(user);

        assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }
}
