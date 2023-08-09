package com.app.ApiMarvelRest.services;
import com.app.ApiMarvelRest.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private User mockUser;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        mockUser.setEmail("test@test.com");
        mockUser.setPassword("123456789");
    }
    @Test
    public void givenValidUserWithCorrectEmailAndPassword_whenLogin_thenReturnUser() {
        when(mockUser.getEmail()).thenReturn("test@test.com");
        when(mockUser.getPassword()).thenReturn("123456789");

        User result = authService.login(mockUser);

        assertNotNull(result, "El usuario no debería ser nulo");
        assertEquals(mockUser.getEmail(), result.getEmail(), "El correo del usuario no coincide");
        assertEquals(mockUser.getPassword(), result.getPassword(), "La contraseña del usuario no coincide");
    }
    @Test
    public void givenInvalidUserWithIncorrectEmailAndPassword_whenLogin_thenReturnNull() {
        //Si le vuelvo a pasar otras credenciales me da error de Stubbing
//        when(mockUser.getEmail()).thenReturn("wrongEmail@.com");
//        when(mockUser.getPassword()).thenReturn("wrongPassword");

        mockUser.setEmail("wrong@test.com");
        mockUser.setPassword("wrongpassword");
        User result = authService.login(mockUser);

        assertNull(result, "El usuario debería ser nulo");
    }

}
