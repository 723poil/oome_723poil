package org.oome.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
@SpringBootConfiguration
public class OomeWebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SecurityFilterChain filterChain = http.httpBasic().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .cors().disable()
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .build();

        log.debug("Security FilterChaining complete");
        return filterChain;
    }
}
