package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import org.oome.infra.jwt.JwtAuthenticationEntryPoint;
import org.oome.infra.jwt.JwtFilter;
import org.oome.infra.jwt.TokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationManager authenticationManager;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final TokenProvider tokenProvider;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter filter = new JwtFilter(authenticationManager, jwtAuthenticationEntryPoint, tokenProvider);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
