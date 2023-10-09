package com.tech.trove.infrastructure.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Unauthorized response util.
 */
@UtilityClass
public class UnauthorizedResponseUtil {

    /**
     * Sets unauthorized response.
     *
     * @param response the response
     * @param ex       the ex
     * @throws IOException the io exception
     */
    public void setUnauthorizedResponse(HttpServletResponse response, Exception ex) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> data = new HashMap<>();
        data.put("error", HttpStatus.UNAUTHORIZED.name().toLowerCase());
        data.put("error_description", ex.getMessage());

        new ObjectMapper().writeValue(response.getOutputStream(), data);

    }

}
