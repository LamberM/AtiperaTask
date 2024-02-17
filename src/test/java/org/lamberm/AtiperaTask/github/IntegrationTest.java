package org.lamberm.AtiperaTask.github;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.lamberm.AtiperaTask.AtiperaTaskApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = AtiperaTaskApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class GetGithubUserReposResponseList {
        @Test
        void shouldGetResultList() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/github/users/{userName}/repos","LamberM")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("LamberM"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].repositoryName").value("AtiperaTask"))
                    .andExpect(jsonPath("$[0].ownerLogin.login").value("LamberM"))
                    .andExpect(jsonPath("$[0].branchDetailsResponses[0].name").value("main"))
                    .andExpect(jsonPath("$[0].branchDetailsResponses[0].commit.sha").exists());
        }
        @Test
        void shouldNotGetResultListUserNameNotFound() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/github/users/{userName}/repos","test2312312")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("test2312312"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.status").value("404"))
                    .andExpect(jsonPath("$.message").value("Github user does not exist"));
        }
    }
}
