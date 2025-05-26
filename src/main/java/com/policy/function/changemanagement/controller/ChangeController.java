package com.policy.function.changemanagement.controller;

import com.policy.function.changemanagement.domain.Change;
import com.policy.function.changemanagement.dto.ChangeRequest;
import com.policy.function.changemanagement.dto.ChangeResponse;
import com.policy.function.changemanagement.service.ChangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Change>> getAllChanges() {
        return ResponseEntity.ok(changeService.getAllChanges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Change> getChangeById(@PathVariable Long id) {
        return changeService.getChangeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<ChangeResponse> approveChange(
            @PathVariable Long id,
            @RequestParam("statusId") Long statusId) {
        ChangeResponse approvedChange = changeService.approveChange(id, statusId);
        return ResponseEntity.ok(approvedChange);
    }


}
