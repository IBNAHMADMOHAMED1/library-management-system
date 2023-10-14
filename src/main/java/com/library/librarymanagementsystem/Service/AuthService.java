package com.library.librarymanagementsystem.Service;

import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Entity.User;

public interface AuthService {
    public String login(LoginRequestDto loginRequest);
    User getAuthenticatedUser();
}

