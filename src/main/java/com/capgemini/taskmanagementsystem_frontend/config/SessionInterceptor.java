package com.capgemini.taskmanagementsystem_frontend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@Component
public class SessionInterceptor implements ClientHttpRequestInterceptor {

    private static String backendSessionId;

    public static void setBackendSessionId(String sessionId) {
        backendSessionId = sessionId;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Use the stored backend session ID instead of the frontend's session ID
        if (backendSessionId != null && !backendSessionId.isEmpty()) {
            request.getHeaders().add("Cookie", "JSESSIONID=" + backendSessionId);
        }

        return execution.execute(request, body);
    }
}