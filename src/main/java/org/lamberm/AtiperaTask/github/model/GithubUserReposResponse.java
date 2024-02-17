package org.lamberm.AtiperaTask.github.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GithubUserReposResponse {
    private String repositoryName;

    private Owner ownerLogin;

    private List<BranchDetailsResponse> branchDetailsResponses;
}
