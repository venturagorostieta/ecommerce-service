package com.tech.trove.infrastructure.web.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Tech custom access denied handler.
 */
@Slf4j
@Component
public class TechCustomAccessDeniedHandler implements AccessDeniedHandler {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        String endpoint = request.getMethod() + " " + request.getRequestURI();

        ErrorNotification errorNotification = ErrorNotification.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(accessDeniedException.getLocalizedMessage())
                .reason("Current JWT provided to the service does not have sufficient permissions to access the current path")
                .path(request.getRequestURI())
                .build();

        String jsonNotification = objectMapper.writeValueAsString(errorNotification);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        writer.print(jsonNotification);
        log.info("Authentication error, provided JWT does not contain scope or role in order to access this resource {}", endpoint);
        writer.flush();
    }

}
