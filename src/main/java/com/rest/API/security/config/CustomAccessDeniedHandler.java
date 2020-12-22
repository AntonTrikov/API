package com.rest.API.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG= Logger.getLogger(CustomAccessDeniedHandler.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warning("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", new Date());
        data.put("status", HttpStatus.FORBIDDEN.value());
        data.put("errors",Arrays.asList(exc.getMessage()));

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }
}
