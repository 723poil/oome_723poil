package org.oome.infra.service;

import lombok.RequiredArgsConstructor;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
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

    private final MemberJpaRepository memberJpaRepository;

    private final TokenProvider tokenProvider;

    public TokenDto login(MemberLoginReqDto reqDto) {
        UsernamePasswordAuthenticationToken authenticationToken = reqDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        Member entity = memberJpaRepository.findById(Long.valueOf(authentication.getName())).orElseThrow();
        entity.setFailCount(0);
        entity.setIsLock(YN.N);

        return tokenProvider.generateTokenDto(authentication);
    }
}
