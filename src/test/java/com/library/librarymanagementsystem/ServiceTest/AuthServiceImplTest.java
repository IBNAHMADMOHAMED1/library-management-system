package com.library.librarymanagementsystem.ServiceTest;

import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Entity.User;
import com.library.librarymanagementsystem.Enum.ROLE;
import com.library.librarymanagementsystem.Mapper.UserMapper;
import com.library.librarymanagementsystem.Repository.UserRepository;
import com.library.librarymanagementsystem.Service.Impl.AuthServiceImpl;
import com.library.librarymanagementsystem.Util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserMapper userMapper;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(userRepository, jwtUtil, userMapper);
    }

    @Test
    void should_Return_Token_when_login() {
        LoginRequestDto loginRequest = new LoginRequestDto("username", "password");
        User user = new User("admin", "admiwn", ROLE.ADMIN);
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(user)); // when userRepository.findByUsername is called, return Optional.of(user)
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("mockedToken");
        String token = authService.login(loginRequest);
        assertNotNull(token);
    }

    @Test
    void testLoginUserNotFound() {
        LoginRequestDto loginRequest = new LoginRequestDto("nonexistentUser", "password");
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());
        String token = authService.login(loginRequest);
        assertNull(token);
    }
}
