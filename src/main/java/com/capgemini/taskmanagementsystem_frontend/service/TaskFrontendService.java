package com.capgemini.taskmanagementsystem_frontend.service;

import com.capgemini.taskmanagementsystem_frontend.entity.TaskFilterRequest;
import com.capgemini.taskmanagementsystem_frontend.entity.TaskResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class TaskFrontendService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL =
            "http://localhost:8085/task/getTaskByPriorityAndStatus";

    public List<TaskResponseDto> getTasks(TaskFilterRequest request) {
            String dbPriority = request.getPriority();
            String dbStatus = request.getStatus();


        String url = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("priority", dbPriority)
                .queryParam("status", dbStatus)
                .build(false)
                .toUriString();
        System.out.println(url);
        try {
            TaskResponseDto[] responseArray = restTemplate.getForObject(url, TaskResponseDto[].class);

            return responseArray != null ? Arrays.asList(responseArray) : List.of();
        } catch (HttpClientErrorException e) {
            String errorMessage = extractErrorMessage(e.getResponseBodyAsString());
            throw new RuntimeException(errorMessage, e);
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String extractErrorMessage(String responseBody) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            if (jsonNode.has("errorMessage")) {
                return jsonNode.get("errorMessage").asText();
            }
        } catch (Exception e) {
            System.err.println("Failed to parse error message: " + e.getMessage());
        }
        return responseBody;
    }
}
