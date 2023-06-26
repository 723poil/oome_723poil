package org.oome.infra.utils;

import lombok.RequiredArgsConstructor;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.infra.exception.AuthenticationLoginInfoCheckRuntimeException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityUtil {

    private final MemberJpaRepository memberJpaRepository;

    public Long getCurrentMemberId() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }

    /**
     * 현재 SecurityContextHolder에 있는 유저를 반환한다.
     * @return 로그인된 유저 정보
     */
    public Member getAuthorizedUser() {
        return memberJpaRepository.findById(getCurrentMemberId()).orElseThrow(AuthenticationLoginInfoCheckRuntimeException::new);
    }
}