package org.oome.infra.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.infra.jwt.JwtTokenProvider;
import org.oome.infra.vo.MemberLoginReqDto;
import org.oome.infra.vo.TokenDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManagerBuilder managerBuilder;

    private final MemberJpaRepository memberJpaRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public TokenDto login(MemberLoginReqDto reqDto) {
        try {
            Member loginRequestMember = memberJpaRepository.findByUsername(reqDto.getUsername()).orElse(null);

            if (loginRequestMember != null) {
                if (loginRequestMember.getIsBlock().equals(YN.Y)) {
                    return TokenDto.builder()
                            .grantType("BLOCK")
                            .build();
                }

                if (loginRequestMember.getIsLock().equals(YN.Y)) {
                    return TokenDto.builder()
                            .grantType("LOCKED")
                            .build();
                }
            }

            UsernamePasswordAuthenticationToken authenticationToken = reqDto.toAuthentication();

            Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

            Member entity = memberJpaRepository.findById(Long.valueOf(authentication.getName())).orElseThrow();
            entity.setFailCount(0);
            entity.setIsLock(YN.N);
            memberJpaRepository.save(entity);

            return jwtTokenProvider.generateTokenDto(authentication);
        } catch (BadCredentialsException e) {
            log.debug("자격증명 실패");
            Member member = memberJpaRepository.findByUsername(reqDto.getUsername()).orElse(null);

            if (member != null) {
                member.setFailCount(member.getFailCount() + 1);

                if (member.getFailCount() >= 5) {
                    member.setIsLock(YN.Y);
                }

                memberJpaRepository.save(member);
            }

            return TokenDto.builder().grantType("INVALID_PASSWORD").build();
        }
    }
}
