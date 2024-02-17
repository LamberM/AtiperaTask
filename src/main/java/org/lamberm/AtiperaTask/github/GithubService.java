package org.lamberm.AtiperaTask.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.lamberm.AtiperaTask.github.model.GithubUserReposResponse;
import org.lamberm.AtiperaTask.github.model.ListRepoResponse;
import org.lamberm.AtiperaTask.github.util.QueryApiMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubService {
    private final QueryApiMaker queryApiMaker;

    private final String githubApiHostname;

    public GithubService(QueryApiMaker queryApiMaker, @Value("${github.api.hostname}") String githubApiHostname) {
        this.queryApiMaker = queryApiMaker;
        this.githubApiHostname = githubApiHostname;
    }

    public List<GithubUserReposResponse> getGithubUserRepos(String userName) throws JsonProcessingException {
        List<GithubUserReposResponse> finalGithubUserReposResponseList = new ArrayList<>();
        var urlWithRepos = "%s/users/%s/repos".formatted(githubApiHostname, userName);
        var requestList = queryApiMaker.makeListRepoResponseWithFilter(urlWithRepos);

        for (ListRepoResponse listRepoResponse : requestList) {
            var urlWithBranchesDetails = "%s/repos/%s/%s/branches".formatted(githubApiHostname, userName, listRepoResponse.getName());
            var branchDetailsResponseList = queryApiMaker.makeBranchDetailsResponseList(urlWithBranchesDetails);
            finalGithubUserReposResponseList.add(new GithubUserReposResponse(listRepoResponse.getName(), listRepoResponse.getOwner(), branchDetailsResponseList));
        }

        return finalGithubUserReposResponseList;
    }
}
