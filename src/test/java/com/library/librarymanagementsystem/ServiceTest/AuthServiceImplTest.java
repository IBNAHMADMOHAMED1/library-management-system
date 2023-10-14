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
        LoginRequestDto loginRequest = new LoginRequestDto("admin", "d");
        User user = new User("admin", "d", ROLE.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        when(jwtUtil.generateToken(Mockito.any())).thenReturn("token");
        String token = authService.login(loginRequest);
        assertEquals("token", token);


    }

    @Test
    void testLoginUserNotFound() {

    }
}
