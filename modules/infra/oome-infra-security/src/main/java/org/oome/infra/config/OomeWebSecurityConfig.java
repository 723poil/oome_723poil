package org.oome.infra.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oome.core.properties.CommonUrlProperties;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.infra.filter.JsonLoginProcessingFilter;
import org.oome.infra.provider.OomeAuthenticationProvider;
import org.oome.infra.service.AuthenticationService;
import org.oome.infra.utils.TraceLogger;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpSession;
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

    private final MemberJpaRepository memberJpaRepository;

    private final ModelMapper modelMapper;

    private final HttpSession httpSession;

    private final OomeAuthenticationEntryPoint authenticationEntryPoint;

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

        SecurityFilterChain filterChain = http.httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/authcheck").authenticated()
                .antMatchers(urlList.stream()
                        .map(url -> url + "/admin/**").toArray(String[]::new)).hasAnyRole(MemberRole.ADMIN.getRole(), MemberRole.DEVELOPER.getRole())
                .anyRequest().permitAll()
                .and()
                .cors().disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authenticationProvider(oomeAuthenticationProvider())
                .addFilterBefore(jsonLoginProcessingFilter(authenticationConfiguration), UsernamePasswordAuthenticationFilter.class)
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .build();

        log.debug("Security FilterChaining complete");
        return filterChain;
    }

    @Bean
    public JsonLoginProcessingFilter jsonLoginProcessingFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        JsonLoginProcessingFilter filter = new JsonLoginProcessingFilter("/api/v1/common/auth/authorize");
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        filter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler());
        return filter;
    }

    @Bean
    public OomeAuthenticationProvider oomeAuthenticationProvider() {
        log.debug("OomeAuthenticationProvider Bean Created");
        return new OomeAuthenticationProvider(
                memberJpaRepository,
                modelMapper,
                passwordEncoder()
        );
    }

    @Bean
    public AuthenticationService authenticationService(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.debug("AuthenticationService Bean Created");
        return new AuthenticationService(
                authenticationManager(authenticationConfiguration),
                passwordEncoder(),
                modelMapper,
                httpSession
        );
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.debug("AuthenticationManager Bean Created");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setUseReferer(true);
        log.debug("SavedRequestAwareAuthenticationSuccessHandler Bean Created");
        return successHandler;
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
}
