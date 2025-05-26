package com.policy.function.changemanagement.controller;

import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/changes")
public class ChangeController {

    private final ChangeService changeService;

    public ChangeController(ChangeService changeService) {
        this.changeService = changeService;
    }

    @PostMapping
    public ResponseEntity<ChangeResponse> insertChange(@RequestBody ChangeRequest changeDto) {
        ChangeResponse saved = changeService.createChange(changeDto);
        return ResponseEntity.ok(saved);
    }

}
