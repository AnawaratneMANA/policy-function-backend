package com.policy.function.changemanagement.service;

import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;

import java.util.List;
import java.util.Optional;

public interface ChangeService {
    ChangeResponse createChange(ChangeRequest changeRequest);
    List<Change> getAllChanges();
    Optional<Change> getChangeById(Long id);
    // TODO: View the changes submit with the status
    // TODO: Approver approve the submitted requests
    // TODO: Get a list of Levels
    // TODO: Get a list of Approver
}
