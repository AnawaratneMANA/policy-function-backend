package com.policy.function.changemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private Long userId;
    private Long roleId;
    private String email;
    private String userName;
}
