package com.tech.trove.infrastructure.web.security.handler;

import com.tech.trove.infrastructure.common.util.UnauthorizedResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Custom access denied handler.
 */
public class CustomAccessDeniedHandler  implements AccessDeniedHandler  {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        UnauthorizedResponseUtil.setUnauthorizedResponse(response, accessDeniedException);
    }
}
