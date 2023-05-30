package org.oome.infra.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public JsonLoginProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // Extract the username and password from the JSON request
        String username = null;
        String password = null;
        try {
            // Parse the JSON request body and retrieve the username and password fields
            // Adjust this logic based on the structure of your JSON request
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(request.getReader());
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
            log.debug("username => {} password => {}", username, password);
        } catch (JsonProcessingException e) {
            throw new AuthenticationServiceException("Invalid JSON request", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create an Authentication object using the extracted username and password
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        log.debug("Authentication => {}", authentication);
        // Delegate the authentication to your custom authentication provider
        return getAuthenticationManager().authenticate(authentication);
    }
}