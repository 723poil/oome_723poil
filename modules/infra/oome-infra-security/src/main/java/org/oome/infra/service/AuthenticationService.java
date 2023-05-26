package org.oome.infra.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oome.infra.user.AuthorizedUser;
import org.oome.infra.vo.LoginReqVo;
import org.oome.infra.vo.LoginResVo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final HttpSession httpSession;

    public LoginResVo signin(HttpServletResponse res, LoginReqVo loginReqVo) throws Exception {

        LoginResVo loginResVo = null;

        String username = loginReqVo.getUsername();
        String password = loginReqVo.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        AuthorizedUser principal = (AuthorizedUser)authentication.getPrincipal();

        LoginResVo data = principal.getData();

        // TODO : 패스워드 만료 처리

        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        loginResVo = data;

        httpSession.setAttribute("user", loginResVo);

        return loginResVo;
    }
}
