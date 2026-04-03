package com.capgemini.taskmanagementsystem_frontend.service;


import com.capgemini.taskmanagementsystem_frontend.entity.ProjectSummaryDto;
import com.capgemini.taskmanagementsystem_frontend.entity.ProjectSummaryWrapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    public ProjectSummaryWrapperDto getProjectSummary(Integer projectId) {

        String url = UriComponentsBuilder.fromUriString(baseUrl + "/project/summary/" + projectId)
                .build(false)
                .toUriString();

        try {
            ResponseEntity<ProjectSummaryWrapperDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    ProjectSummaryWrapperDto.class
            );

            return response.getBody();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}