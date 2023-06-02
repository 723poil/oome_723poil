package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @deprecated JwtEntrypoint작성으로 제거예정
 * @author 한주성
 */
@Deprecated(since = "2023-06-02", forRemoval = true)
@Slf4j
@RequiredArgsConstructor
@Component
public class OomeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(log.isErrorEnabled()) {
            log.error("Authentication inalid", authException);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write("authentication failure");
        response.flushBuffer();
    }
}
