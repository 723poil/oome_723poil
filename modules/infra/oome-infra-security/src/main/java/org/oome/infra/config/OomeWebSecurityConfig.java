package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.core.properties.CommonUrlProperties;
import org.oome.entity.enums.MemberRole;
import org.oome.infra.utils.TraceLogger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
@SpringBootConfiguration
public class OomeWebSecurityConfig {

    private final CommonUrlProperties commonUrlProperties;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        List<String> urlList = Stream.of(
                        commonUrlProperties.getCommonUrl(),
                        commonUrlProperties.getQnaUrl(),
                        commonUrlProperties.getBlogUrl(),
                        commonUrlProperties.getExtUrl(),
                        commonUrlProperties.getUtilUrl()
                ).filter(Objects::nonNull)
                .collect(Collectors.toList());

        SecurityFilterChain filterChain = http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/authcheck").hasAnyRole("ROLE_DEVELOPER")
                .antMatchers(urlList.stream()
                        .map(url -> url + "/admin/**").toArray(String[]::new)).hasAnyRole(MemberRole.ADMIN.getRole(), MemberRole.DEVELOPER.getRole())
                .anyRequest().permitAll()
                .and()
                .cors().disable()
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(savedRequestAwareAuthenticationSuccessHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .build();

        log.debug("Security FilterChaining complete");
        return filterChain;
    }

    @Bean
    public AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setUseReferer(true);
        return successHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TraceLogger traceLogger() {
        log.debug("TraceLogger Bean Created");
        return new TraceLogger();
    }
}
