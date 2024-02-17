package org.lamberm.AtiperaTask.github.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.lamberm.AtiperaTask.UnitTest;
import org.lamberm.AtiperaTask.github.model.BranchDetailsResponse;
import org.lamberm.AtiperaTask.github.model.Commit;
import org.lamberm.AtiperaTask.github.model.ListRepoResponse;
import org.lamberm.AtiperaTask.github.model.Owner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class QueryApiMakerTest implements UnitTest {
    @InjectMocks
    QueryApiMaker systemUnderTest;

    @Mock
    RestTemplate restTemplateMock;

    @Mock
    ObjectMapper objectMapperMock;

    String userName = "test";

    String url = "https://api.github.com/users/%s/repos".formatted(userName);

    String jsonResponseRepo = "[{\"name\":\"Repo1\",\"owner\": {\"login\": \"test\",\"id\": 114869530},\"fork\":false},{\"name\":\"Repo2\",\"owner\": {\"login\": \"test\",\"id\": 114869530},\"fork\":true}]";

    String jsonResponseBranch = "[{\"name\":\"main\",\"commit\":{\"sha\":\"abc123\",\"url\":\"https://api.github.com/repos/test/Repo1/commits/abc123\"},\"protected\":false}]";

    @Nested
    class MakeListRepoResponseListWithFilterTest {
        @Test
        void shouldMakeRequestListWithFilterWhenGetForkEqualsTrue() throws JsonProcessingException {
            //given
            when(restTemplateMock.getForObject(anyString(), Mockito.eq(String.class))).thenReturn(jsonResponseRepo);
            when(objectMapperMock.readValue(any(String.class),any(TypeReference.class))).thenReturn(Arrays.asList(new ListRepoResponse("Repo1", false,new Owner()), new ListRepoResponse("Repo2", true,new Owner())));
            //when
            var result = systemUnderTest.makeListRepoResponseWithFilter(userName);
            //then
            Assertions.assertEquals(1,result.size());
            Assertions.assertEquals("Repo1", result.get(0).getName());
            Assertions.assertFalse(result.get(0).getFork());
        }
    }
    @Nested
    class MakeBranchDetailsResponseListTest {
        @Test
        void shouldMakeBranchDetailsResponseList()throws JsonProcessingException{
            //given
            when(restTemplateMock.getForObject(anyString(), Mockito.eq(String.class))).thenReturn(jsonResponseBranch);
            when(objectMapperMock.readValue(any(String.class),any(TypeReference.class))).thenReturn(Arrays.asList(new BranchDetailsResponse("main",new Commit())));
            //when
            var result = systemUnderTest.makeBranchDetailsResponseList(url);
            //then
            Assertions.assertEquals(1,result.size());
            Assertions.assertEquals("main",result.get(0).getName());
        }
    }
}