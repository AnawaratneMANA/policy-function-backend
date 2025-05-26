package com.policy.function.changemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.policy.function.changemanagement.domain.ChangeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeResponse {
    private String id;
    private String message;
    private ChangeStatus status;
}
