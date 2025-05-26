package com.policy.function.changemanagement.service;

import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;

public interface ChangeService {
    // TODO: Insert Change to the data base
    ChangeResponse createChange(ChangeRequest changeRequest);
    // TODO: View the changes submit with the status
    // TODO: Approver approve the submitted requests
    // TODO: Get a list of Levels
    // TODO: Get a list of Approver
    // TODO: TBA
}
