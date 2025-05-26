package com.policy.function.changemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String userName;
    private String userPassword;
    private String userStatus;
    private Long roleId;
    private String message;
}
