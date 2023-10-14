package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Entity.User;
import com.library.librarymanagementsystem.Mapper.UserMapper;
import com.library.librarymanagementsystem.Repository.UserRepository;
import com.library.librarymanagementsystem.Service.AuthService;
import com.library.librarymanagementsystem.Util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
    }

    @Override
    public String login(LoginRequestDto loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        return user.map(value -> jwtUtil.generateToken(userMapper.userToUserDto(value))).orElse(null);
    }
}
