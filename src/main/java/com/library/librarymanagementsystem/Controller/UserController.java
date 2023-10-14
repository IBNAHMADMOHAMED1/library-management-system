package com.library.librarymanagementsystem.Controller;

import com.library.librarymanagementsystem.Dto.APIResponse;
import com.library.librarymanagementsystem.Dto.ErrorDto;
import com.library.librarymanagementsystem.Dto.LoginRequestDto;
import com.library.librarymanagementsystem.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final AuthService authService;
    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        String token = authService.login(loginRequest);
        if (token == null) {
            ErrorDto.builder().field("username").message("Invalid username or password").build();
            return ResponseEntity.badRequest().body(APIResponse.builder().status("error").status("Invalid username or password").build());
        }
        return ResponseEntity.ok(APIResponse.builder().status("success").result(token).build());
    }

}
