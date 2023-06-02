package org.oome.infra.service;

import lombok.RequiredArgsConstructor;
import org.oome.infra.jwt.TokenProvider;
import org.oome.infra.vo.MemberLoginReqDto;
import org.oome.infra.vo.TokenDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManagerBuilder managerBuilder;

    private final TokenProvider tokenProvider;

    public TokenDto login(MemberLoginReqDto reqDto) {
        UsernamePasswordAuthenticationToken authenticationToken = reqDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }
}
