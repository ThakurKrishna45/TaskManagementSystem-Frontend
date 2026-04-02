package com.capgemini.taskmanagementsystem_frontend.service;

import com.capgemini.taskmanagementsystem_frontend.entity.TaskFilterRequest;
import com.capgemini.taskmanagementsystem_frontend.entity.TaskResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        String url = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("priority", request.getPriority())
                .queryParam("status", request.getStatus())
                .toUriString();

        try {
            // Let RestTemplate handle the conversion to the array directly
            TaskResponseDto[] responseArray = restTemplate.getForObject(url, TaskResponseDto[].class);

            return responseArray != null ? Arrays.asList(responseArray) : List.of();
        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Backend call failed", e);
        }
    }
}