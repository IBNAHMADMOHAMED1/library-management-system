package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.LoginRequestDto;

public interface AuthService {
    public String login(LoginRequestDto loginRequest);
}

