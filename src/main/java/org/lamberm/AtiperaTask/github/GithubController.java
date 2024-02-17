package org.lamberm.AtiperaTask.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.lamberm.AtiperaTask.github.model.GithubUserReposResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GithubController {
    private final GithubService githubService;

    @GetMapping("/users/{userName}/repos")
    public ResponseEntity<List<GithubUserReposResponse>> getGithubUserRepos(@PathVariable("userName") String userName) throws JsonProcessingException {
        return ResponseEntity.ok(githubService.getGithubUserRepos(userName));
    }
}
