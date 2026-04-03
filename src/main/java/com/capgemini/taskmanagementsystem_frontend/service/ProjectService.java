package com.capgemini.taskmanagementsystem_frontend.service;


import com.capgemini.taskmanagementsystem_frontend.entity.ProjectSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProjectService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    public List<ProjectSummaryDto> getProjectSummary(Integer projectId) {
        String url = baseUrl + "/project/summary/" + projectId;
        ResponseEntity<List<ProjectSummaryDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProjectSummaryDto>>() {});
        return response.getBody();
    }
}