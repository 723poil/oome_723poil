package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.core.properties.CommonUrlProperties;
import org.oome.entity.enums.MemberRole;
import org.oome.infra.jwt.JwtAccessDeniedHandler;
import org.oome.infra.jwt.JwtAuthenticationEntryPoint;
import org.oome.infra.jwt.JwtTokenProvider;
import org.oome.infra.utils.TraceLogger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

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

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        List<String> urlList = Stream.of(
                        commonUrlProperties.getCommonUrl(),
                        commonUrlProperties.getQnaUrl(),
                        commonUrlProperties.getBlogUrl(),
                        commonUrlProperties.getExtUrl(),
                        commonUrlProperties.getUtilUrl()
                ).filter(Objects::nonNull)
                .collect(Collectors.toList());

        SecurityFilterChain filterChain = http
                .httpBasic()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(commonUrlProperties.getCommonApi("/auth/authcheck")).hasAuthority(MemberRole.DEVELOPER.getRole())
                .antMatchers(urlList.stream()
                        .map(url -> url + "/admin/**").toArray(String[]::new)).hasAnyAuthority(MemberRole.ADMIN.getRole(), MemberRole.DEVELOPER.getRole())
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .cors().disable()
                .csrf().ignoringAntMatchers("/h2-console/**").disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(new JwtSecurityConfig(authenticationManagerBuilder.getObject(), jwtAuthenticationEntryPoint, jwtTokenProvider))
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .build();

        log.debug("Security FilterChaining complete");
        return filterChain;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("BCryptPasswordEncoder Bean Created");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TraceLogger traceLogger() {
        log.debug("TraceLogger Bean Created");
        return new TraceLogger();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->
            web.ignoring()
                    .antMatchers("/h2-console/**");

    }
}
