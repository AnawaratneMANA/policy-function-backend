package com.policy.function.changemanagement.service.impl;

import com.policy.function.changemanagement.domain.Role;
import com.policy.function.changemanagement.domain.User;
import com.policy.function.changemanagement.dto.LoginRequestDto;
import com.policy.function.changemanagement.dto.UserDto;
import com.policy.function.changemanagement.repository.RoleRepository;
import com.policy.function.changemanagement.repository.UserRepository;
import com.policy.function.changemanagement.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {


    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;

    public LoginServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserPassword(BCrypt.hashpw(userDto.getUserPassword(), BCrypt.gensalt()));
        user.setUserStatus(userDto.getUserStatus());
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());

        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        user.setUserRole(role);

        User registeredUser =  userRepository.save(user);
        // Create the response
        UserDto response = new UserDto();
        response.setId(registeredUser.getUserId());
        response.setMessage("User Created Successfully!");
        return response;
    }

    @Override
    public boolean login(LoginRequestDto loginRequest) {
        return userRepository.findByUserName(loginRequest.getUsername())
                .map(user -> BCrypt.checkpw(loginRequest.getPassword(), user.getUserPassword()))
                .orElse(false);
    }

}
