package com.library.librarymanagementsystem.ControllerTest;

import com.library.librarymanagementsystem.Controller.UserController;
import com.library.librarymanagementsystem.Dto.APIResponse;
import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Service.Impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private AuthServiceImpl authService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(authService);
    }

    @Test
    void testLoginSuccess() {
        LoginRequestDto loginRequest = new LoginRequestDto("admn", "d");
        String token = "mockedToken";
        when(authService.login(loginRequest)).thenReturn(token);

        ResponseEntity<APIResponse<?>> responseEntity = userController.login(loginRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals(token, responseEntity.getBody().getResult());
    }

    @Test
    void testLoginFailed() {
        LoginRequestDto loginRequest = new LoginRequestDto("admn", "d");
        when(authService.login(loginRequest)).thenReturn(null);

        ResponseEntity<APIResponse<?>> responseEntity = userController.login(loginRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

}
