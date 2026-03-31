package com.Infohedron.assessment.controller;

import com.Infohedron.assessment.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class GitHubWebhookController {

    @Autowired
    private GitHubService gitHubService;

    @PostMapping("/github")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        gitHubService.processPayload(payload);
        return ResponseEntity.ok("Received");
    }
}