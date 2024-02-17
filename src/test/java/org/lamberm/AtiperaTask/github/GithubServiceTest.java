package org.lamberm.AtiperaTask.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.AtiperaTask.UnitTest;
import org.lamberm.AtiperaTask.github.model.BranchDetailsResponse;
import org.lamberm.AtiperaTask.github.model.Commit;
import org.lamberm.AtiperaTask.github.model.ListRepoResponse;
import org.lamberm.AtiperaTask.github.model.Owner;
import org.lamberm.AtiperaTask.github.util.QueryApiMaker;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class GithubServiceTest implements UnitTest {
    @InjectMocks
    GithubService systemUnderTest;

    @Mock
    QueryApiMaker queryApiMakerMock;

    String userName = "test";

    @Nested
    class GetGithubUserReposResponseListTest {
        @Test
        void shouldGetResultList() throws JsonProcessingException {
            //given
            when(queryApiMakerMock.makeListRepoResponseWithFilter(anyString())).thenReturn(List.of(new ListRepoResponse("Repo1", false, new Owner()), new ListRepoResponse("Repo2", true, new Owner())));
            when(queryApiMakerMock.makeBranchDetailsResponseList(anyString())).thenReturn(List.of(new BranchDetailsResponse("main", new Commit())));
            //when
            var result = systemUnderTest.getGithubUserRepos(userName);
            //then
            Assertions.assertEquals(2, result.size());
            Assertions.assertEquals("Repo1", result.get(0).getRepositoryName());
            Assertions.assertEquals(1, result.get(0).getBranchDetailsResponses().size());
            Assertions.assertEquals("main", result.get(0).getBranchDetailsResponses().get(0).getName());
        }
    }
}