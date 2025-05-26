package com.policy.function.changemanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.repository.ChangeRepository;
import com.policy.function.changemanagement.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChangeServiceImpl implements ChangeService {


    private final ChangeRepository changeRepository;


    private final ObjectMapper objectMapper;

    public ChangeServiceImpl(ChangeRepository changeRepository, ObjectMapper objectMapper) {
        this.changeRepository = changeRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Create Change and Save in the Database.
     * @param changeDto > Request
     * @return {@link ChangeResponse}
     */
    @Override
    public ChangeResponse createChange(ChangeRequest changeDto) {
        Change change = objectMapper.convertValue(changeDto, Change.class);
        change.setCreatedDate(LocalDateTime.now());
        change.setUpdatedDate(LocalDateTime.now());
        Change createdChange =  changeRepository.save(change);
        // Prepare the response
        ChangeResponse changeResponse = objectMapper.convertValue(createdChange, ChangeResponse.class);
        changeResponse.setMessage("Successfully Created Change!");
        return changeResponse;
    }
}
