package com.capgemini.taskmanagementsystem_frontend.service;

import com.capgemini.taskmanagementsystem_frontend.entity.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private String baseUrl = "http://localhost:8085";

    @Autowired
    private RestTemplate restTemplate;

    public NotificationDto getNotificationById(Integer id) {
        String url = baseUrl + "/notification/getnotif/" + id;
        logger.info("🔄 Attempting to call backend: {}", url);
        try {
            NotificationDto result = restTemplate.getForObject(url, NotificationDto.class);
            logger.info("✅ Success: {}", result);
            return result;
        } catch (Exception e) {
            logger.error("❌ Error calling backend: {}", e.getMessage(), e);
            throw e;
        }
    }

    public List<NotificationDto> getNRecentNotifications(Integer userId, Integer n) {
        String url = baseUrl + "/notification/getnrecentnotif/" + userId + "/" + n;
        logger.info("🔄 Attempting to call backend: {}", url);
        try {
            ResponseEntity<List<NotificationDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<NotificationDto>>() {});
            logger.info("✅ Success: {} notifications fetched", response.getBody().size());
            return response.getBody();
        } catch (Exception e) {
            logger.error("❌ Error calling backend: {}", e.getMessage(), e);
            throw e;
        }
    }
}