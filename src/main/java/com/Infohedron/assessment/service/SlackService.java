package com.Infohedron.assessment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlackService {

    private final String WEBHOOK_URL = "YOUR_SLACK_WEBHOOK_URL";

    public void sendMessage(String author, List<String> commits) {

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder message = new StringBuilder();
        message.append("*").append(author).append("* pushed code:\n");

        for (String commit : commits) {
            message.append("• ").append(commit).append("\n");
        }

        Map<String, String> payload = new HashMap<>();
        payload.put("text", message.toString());

        restTemplate.postForObject(WEBHOOK_URL, payload, String.class);
    }
}
