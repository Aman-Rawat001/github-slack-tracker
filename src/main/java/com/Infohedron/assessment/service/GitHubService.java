package com.Infohedron.assessment.service;

import com.Infohedron.assessment.entity.Author;
import com.Infohedron.assessment.entity.CommitEntity;
import com.Infohedron.assessment.repository.AuthorRepository;
import com.Infohedron.assessment.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GitHubService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private SlackService slackService;

    public void processPayload(Map<String, Object> payload) {

        Map<String, Object> pusher = (Map<String, Object>) payload.get("pusher");
        List<Map<String, Object>> commits = (List<Map<String, Object>>) payload.get("commits");

        String name = (String) pusher.get("name");
        String email = (String) pusher.get("email");

        Author author = authorRepository.findByEmail(email)
                .orElseGet(() -> {
                    Author a = new Author();
                    a.setName(name);
                    a.setEmail(email);
                    return authorRepository.save(a);
                });

        List<String> commitMessages = new ArrayList<>();

        for (Map<String, Object> commit : commits) {
            CommitEntity c = new CommitEntity();
            c.setMessage((String) commit.get("message"));
            c.setCommitId((String) commit.get("id"));
            c.setAuthor(author);

            commitRepository.save(c);

            commitMessages.add(c.getMessage());
        }

        slackService.sendMessage(name, commitMessages);
    }
}