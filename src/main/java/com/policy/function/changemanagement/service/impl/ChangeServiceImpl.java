package com.policy.function.changemanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.domain.ChangeStatus;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.repository.ChangeRepository;
import com.policy.function.changemanagement.repository.ChangeStatusRepository;
import com.policy.function.changemanagement.service.ChangeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChangeServiceImpl implements ChangeService {


    private final ChangeRepository changeRepository;


    private final ObjectMapper objectMapper;
    private final ChangeStatusRepository changeStatusRepository;

    public ChangeServiceImpl(ChangeRepository changeRepository, ObjectMapper objectMapper, ChangeStatusRepository changeStatusRepository) {
        this.changeRepository = changeRepository;
        this.objectMapper = objectMapper;
        this.changeStatusRepository = changeStatusRepository;
    }

    /**
     * Create Change and Save in the Database.
     * @param changeDto > Request
     * @return {@link ChangeResponse}
     */
    @Override
    public ChangeResponse createChange(ChangeRequest changeDto) {
        Change change = objectMapper.convertValue(changeDto, Change.class);
        ChangeStatus status = changeStatusRepository.findById(changeDto.getStatus().getChangeStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ChangeStatus ID"));
        change.setStatus(status);
        change.setCreatedDate(LocalDateTime.now());
        change.setUpdatedDate(LocalDateTime.now());
        Change createdChange =  changeRepository.save(change);
        // Prepare the response
        ChangeResponse changeResponse = objectMapper.convertValue(createdChange, ChangeResponse.class);
        changeResponse.setMessage("Successfully Created Change!");
        return changeResponse;
    }

    /**
     * Get all the change records.
     * @return List<Change>
     */
    @Override
    public List<Change> getAllChanges() {
        return changeRepository.findAll();
    }

    /**
     * Get a specific change record
     * @param id > Requested change record.
     * @return Optional<Change>
     */
    @Override
    public Optional<Change> getChangeById(Long id) {
        return changeRepository.findById(id);
    }

    /**
     * Approver approve the change record.
     * @param changeId > Change record ID
     * @param statusId > Approver can either "Approve" or "Reject"
     * @return {@link ChangeResponse}
     */
    @Override
    public ChangeResponse approveChange(Long changeId, Long statusId) {
        Change change = changeRepository.findById(changeId)
                .orElseThrow(() -> new IllegalArgumentException("Change not found"));

        ChangeStatus approvedStatus = changeStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID"));

        change.setStatus(approvedStatus);
        change.setUpdatedDate(LocalDateTime.now());
        Change approvedChange =  changeRepository.save(change);
        ChangeResponse changeResponse = objectMapper.convertValue(approvedChange, ChangeResponse.class);
        changeResponse.setMessage("Successfully Approved Change!");
        return changeResponse;
    }
}
