package com.policy.function.changemanagement.controller;

import com.policy.function.changemanagement.domain.User;
import com.policy.function.changemanagement.dto.LoginRequestDto;
import com.policy.function.changemanagement.dto.UserDto;
import com.policy.function.changemanagement.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class LoginController {


    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequestDto loginRequest) {
        boolean isAuthenticated = loginService.login(loginRequest);
        return ResponseEntity.ok(isAuthenticated);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto user = loginService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/approvers")
    public ResponseEntity<List<UserDto>> getApprovers() {
        return ResponseEntity.ok(loginService.getAllApprovers());
    }
}