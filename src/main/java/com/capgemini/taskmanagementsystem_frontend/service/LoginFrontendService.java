package com.capgemini.taskmanagementsystem_frontend.service;
import com.capgemini.taskmanagementsystem_frontend.config.SessionInterceptor;
import com.capgemini.taskmanagementsystem_frontend.entity.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.HttpCookie;

@Service
@Slf4j
public class LoginFrontendService {
    @Autowired
    private RestTemplate restTemplate;
    private final String BACKEND_URL = "http://localhost:8085/login";

    public boolean callBackendLogin(LoginRequest loginData) {
        try {
            log.info("Attempting login for user: {}", loginData.getUsername());
            ResponseEntity<Void> response = restTemplate.postForEntity(BACKEND_URL, loginData, Void.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // Extract and store the backend session ID from the Set-Cookie header
                String sessionId = extractSessionId(response);
                if (sessionId != null && !sessionId.isEmpty()) {
                    SessionInterceptor.setBackendSessionId(sessionId);
                    log.info("Login successful. Backend session ID stored.");
                    return true;
                } else {
                    log.warn("Login successful but no session ID found in response");
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage(), e);
            return false;
        }
    }

    private String extractSessionId(ResponseEntity<Void> response) {
        return response.getHeaders().get("Set-Cookie") != null ?
                response.getHeaders().get("Set-Cookie").stream()
                        .filter(c -> c.startsWith("JSESSIONID="))
                        .map(c -> HttpCookie.parse(c).get(0).getValue())
                        .findFirst()
                        .orElse(null) : null;
    }
}