package com.capgemini.taskmanagementsystem_frontend.service;

import com.capgemini.taskmanagementsystem_frontend.entity.CommentDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentFrontendService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL =
            "http://localhost:8085/comment/getallcommentsbytaskid";

    // ✅ Get Comments by Task ID
    public List<CommentDto> getCommentsByTaskId(Integer taskId) {

        String url = BASE_URL + "/" + taskId;

        try {
            CommentDto[] responseArray =
                    restTemplate.getForObject(url, CommentDto[].class);

            return responseArray != null ? Arrays.asList(responseArray) : List.of();

        } catch (HttpClientErrorException e) {
            String errorMessage = extractErrorMessage(e.getResponseBodyAsString());
            throw new RuntimeException(errorMessage, e);

        } catch (Exception e) {
            System.err.println("CRITICAL ERROR: " + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    // ✅ Extract error message from backend JSON
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