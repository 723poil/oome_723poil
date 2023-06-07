package org.oome.infra.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.infra.exception.JwtAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        if (authException instanceof JwtAuthenticationException) {
            log.debug("authentication error");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("applicaiton/json");
            response.getWriter().write("JWT_EXPIRED");
            response.flushBuffer();
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}