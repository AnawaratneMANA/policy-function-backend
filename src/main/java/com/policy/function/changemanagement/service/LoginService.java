package com.policy.function.changemanagement.service;

import com.policy.function.changemanagement.domain.User;
import com.policy.function.changemanagement.dto.LoginRequestDto;
import com.policy.function.changemanagement.dto.UserDto;

import java.util.List;

public interface LoginService {
    UserDto createUser(UserDto userDto);
    public boolean login(LoginRequestDto loginRequest);

    List<UserDto> getAllApprovers();
}
