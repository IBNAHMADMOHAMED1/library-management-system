package com.library.librarymanagementsystem.Service.Impl;

import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Entity.User;
import com.library.librarymanagementsystem.Mapper.UserMapper;
import com.library.librarymanagementsystem.Repository.UserRepository;
import com.library.librarymanagementsystem.Service.AuthService;
import com.library.librarymanagementsystem.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public String login(LoginRequestDto loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return jwtUtil.generateToken(userMapper.userToUserDto(user.get()));
        }
        return null;
    }
}
