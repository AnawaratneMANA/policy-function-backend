package com.policy.function.changemanagement.controller;

import com.policy.function.changemanagement.dto.EmailRequest;
import com.policy.function.changemanagement.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailServiceImpl mailService;

    @PostMapping("/send-test")
    public String sendTestEmail(
            @RequestParam String to, @RequestBody EmailRequest request) {
        mailService.sendTestEmail(to, request.getSubject(), request.getBody());
        return "Email sent to " + to;
    }
}
