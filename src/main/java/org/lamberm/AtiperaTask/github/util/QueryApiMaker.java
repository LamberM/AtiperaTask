package org.lamberm.AtiperaTask.github.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.lamberm.AtiperaTask.github.model.BranchDetailsResponse;
import org.lamberm.AtiperaTask.github.model.ListRepoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class QueryApiMaker {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<BranchDetailsResponse> makeBranchDetailsResponseList(String url) throws JsonProcessingException {
        var jsonResponse = restTemplate.getForObject(url, String.class);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public List<ListRepoResponse> makeListRepoResponseWithFilter(String url) throws JsonProcessingException {
        var jsonResponse = restTemplate.getForObject(url, String.class);
        List<ListRepoResponse> list = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
        return list.stream().filter(listRepoResponse -> !listRepoResponse.getFork()).toList();
    }
}
