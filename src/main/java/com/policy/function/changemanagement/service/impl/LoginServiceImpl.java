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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {


    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository  roleRepository;

    public LoginServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Create user, registration (A simple one without using JWT)
     * @param userDto > Request structure.
     * @return {@link UserDto}
     */
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

    /**
     * Login endpoint, simple one to simulate the user login. (Not secure)
     * @param loginRequest > Login request (Username / Password)
     * @return boolean
     */
    @Override
    public boolean login(LoginRequestDto loginRequest) {
        return userRepository.findByUserName(loginRequest.getUsername())
                .map(user -> BCrypt.checkpw(loginRequest.getPassword(), user.getUserPassword()))
                .orElse(false);
    }

    /**
     * Get a list of Approvers, for the dropdown when creating the change.
     * @return {@link List<UserDto>}
     */
    @Override
    public List<UserDto> getAllApprovers() {
        List<User> users =  userRepository.findAllApprovers().stream()
                .map(user -> new User(user.getUserId(), user.getUserName(), user.getUserRole()))
                .toList();
        return users.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setRoleId(user.getUserRole().getRoleId());
            return userDto;
        }).toList();
    }

}
