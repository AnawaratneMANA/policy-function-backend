package com.policy.function.changemanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.domain.ChangeStatus;
import com.policy.function.changemanagement.domain.User;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.misc.EmailTemplateUtil;
import com.policy.function.changemanagement.repository.ChangeRepository;
import com.policy.function.changemanagement.repository.ChangeStatusRepository;
import com.policy.function.changemanagement.repository.UserRepository;
import com.policy.function.changemanagement.service.ChangeService;
import com.policy.function.changemanagement.service.EmailService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChangeServiceImpl implements ChangeService {


    private static final Logger log = LoggerFactory.getLogger(ChangeServiceImpl.class);
    private final ChangeRepository changeRepository;


    private final ObjectMapper objectMapper;
    private final ChangeStatusRepository changeStatusRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public ChangeServiceImpl(
            ChangeRepository changeRepository,
            ObjectMapper objectMapper, ChangeStatusRepository changeStatusRepository, UserRepository userRepository, EmailService emailService) {
        this.changeRepository = changeRepository;
        this.objectMapper = objectMapper;
        this.changeStatusRepository = changeStatusRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
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
        String operationType = "";
        Change change = changeRepository.findById(changeId)
                .orElseThrow(() -> new IllegalArgumentException("Change not found"));

        ChangeStatus approvedStatus = changeStatusRepository.findById(statusId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid status ID"));
        if(approvedStatus.getChangeStatusId().equals(1L)) {
            throw new RuntimeException("Change status not found");
        }
        change.setStatus(approvedStatus);
        change.setUpdatedDate(LocalDateTime.now());
        // Send the email to the creator.
        User creator = userRepository.findById(change.getCreatedBy())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + change.getCreatedBy()));
        // Get the name of the approver to be used in the mail.
        User approver = userRepository.findById(change.getApproverId()).orElseThrow(
                () -> new EntityNotFoundException("User not found with ID: " + change.getApproverId())
        );
        // Prepare your parameter map
        Map<String, String> params = Map.of(
                "creatorName", creator.getUserName(),
                "changeName", change.getChangeName(),
                "approverName", approver.getUserName(),
                "status", approvedStatus.getChangeStatusName()
        );

        switch (statusId.intValue()) {
            case 2 -> {
                log.info("Log: Approved!");
                operationType = "approval";
            }
            case 3 -> {
                log.info("Log: Rejected!");
                operationType = "rejection";
            }
        };
        String subject = EmailTemplateUtil.getSubject(operationType, params);
        String body = EmailTemplateUtil.getBody(operationType, params);
        emailService.sendTestEmail(creator.getEmail(), subject, body);
        Change approvedChange =  changeRepository.save(change);
        ChangeResponse changeResponse = objectMapper.convertValue(approvedChange, ChangeResponse.class);
        changeResponse.setMessage("Successfully Approved Change!");
        return changeResponse;
    }

    /**
     * This method is used to get the changes given the user who created them.
     * @param userId > Created Users ID
     * @return List<ChangeResponse>
     */
    @Override
    public List<ChangeResponse> getChangesByCreatedUserId(Long userId) {
        List<Change> changes = changeRepository.findByCreatedBy(userId);
        return changes.stream()
                .map(change -> objectMapper.convertValue(change, ChangeResponse.class))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to get the changes given the approver ID
     * @param approverId > Assigned approver
     * @return List<ChangeResponse>
     */
    @Override
    public List<ChangeResponse> getChangesByApproverId(Long approverId) {
        List<Change> changes = changeRepository.findByApproverId(approverId);
        return changes.stream()
                .map(change -> objectMapper.convertValue(change, ChangeResponse.class))
                .collect(Collectors.toList());
    }
}
