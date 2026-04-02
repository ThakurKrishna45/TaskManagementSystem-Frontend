package com.capgemini.taskmanagementsystem_frontend.service;

import com.capgemini.taskmanagementsystem_frontend.entity.NotificationDto;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NotificationService {


    private String baseUrl = "http://localhost:8085";
    private RestTemplate restTemplate = new RestTemplate();

     public NotificationDto getNotificationById(Integer id) {
        String url = baseUrl + "/notification/getnotif/" + id;
        return restTemplate.getForObject(url, NotificationDto.class);
    }

    public List<NotificationDto> getNRecentNotifications(Integer userId, Integer n) {
        String url = baseUrl + "/notification/getnrecentnotif/" + userId + "/" + n;
        ResponseEntity<List<NotificationDto>> response = restTemplate.exchange(
            url, HttpMethod.GET, null,
            new ParameterizedTypeReference<List<NotificationDto>>() {});
        return response.getBody();
    }
}
