package com.policy.function.changemanagement.dto;

import com.policy.function.changemanagement.domain.ChangeStatus;
import com.policy.function.changemanagement.enums.CriticalityLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRequest {
    private String changeName;
    private String changeDescription;
    private CriticalityLevel criticalityLevel;
    private Long createdBy;
    private Long approverId;
    private ChangeStatus status;
}
