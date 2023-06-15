package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import org.oome.infra.jwt.JwtAuthenticationEntryPoint;
import org.oome.infra.jwt.JwtTokenFilter;
import org.oome.infra.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationManager authenticationManager;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtTokenFilter filter = new JwtTokenFilter(authenticationManager, jwtAuthenticationEntryPoint, jwtTokenProvider);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
