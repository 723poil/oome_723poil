package org.oome.infra.jwt;

import lombok.extern.slf4j.Slf4j;
import org.oome.infra.exception.AuthenticationJwtExpiredException;
import org.oome.infra.exception.JwtAuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends BasicAuthenticationFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    public JwtTokenFilter(AuthenticationManager authenticationManager,
                          AuthenticationEntryPoint authenticationEntryPoint,
                          JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager, authenticationEntryPoint);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);
        boolean isValid = false;

        try {
            isValid = jwtTokenProvider.validateToken(jwt, response);
        } catch (AuthenticationJwtExpiredException e) {
            getAuthenticationEntryPoint().commence(request, response, new JwtAuthenticationException("JWT EXPIRED", e));
            return;
        }
        if (StringUtils.hasText(jwt) && isValid) {
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }
}