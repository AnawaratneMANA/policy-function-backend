package com.policy.function.changemanagement.service;

import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ChangeService {
    ChangeResponse createChange(ChangeRequest changeRequest);
    List<Change> getAllChanges();
    Optional<Change> getChangeById(Long id);
    ChangeResponse approveChange(Long changeId, Long statusId);

    List<ChangeResponse> getChangesByCreatedUserId(Long userId);

    List<ChangeResponse> getChangesByApproverId(Long approverId);

    // TODO: Get a list of Levels
    // TODO: Get a list of Approver
}
