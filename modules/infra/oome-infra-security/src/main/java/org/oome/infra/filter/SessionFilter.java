package org.oome.infra.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class SessionFilter extends BasicAuthenticationFilter {

    private final HttpSession httpSession;

    public SessionFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint, HttpSession httpSession) {
        super(authenticationManager, authenticationEntryPoint);
        this.httpSession = httpSession;
    }

    private String[] uriExcludePatterns;

    public String[] getUriExcludePatterns() {
        return uriExcludePatterns;
    }

    public void setUriExcludePatterns(String[] uriExcludePatterns) {
        this.uriExcludePatterns = uriExcludePatterns;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(ObjectUtils.isEmpty(uriExcludePatterns)) {
            return false;
        }else {
            return Arrays.stream(uriExcludePatterns)
                    .anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

//        if (ObjectUtils.isEmpty(httpSession.getAttribute("user"))) {
//            getAuthenticationEntryPoint().commence(request, response, new UsernameNotFoundException("User not found"));
//            return;
//        }

        filterChain.doFilter(request, response);
    }
}
