package com.policy.function.changemanagement.service;

import com.policy.function.changemanagement.domain.User;
import com.policy.function.changemanagement.dto.LoginRequestDto;
import com.policy.function.changemanagement.dto.LoginResponseDto;
import com.policy.function.changemanagement.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface LoginService {
    UserDto createUser(UserDto userDto);
    Optional<LoginResponseDto> login(LoginRequestDto loginRequest);

    List<UserDto> getAllApprovers();
}
