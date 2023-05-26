package org.oome.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRole {

    MEMBER("ROLE_MEMBER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    DEVELOPER("ROLE_DEVELOPER", "개발자");

    private final String role;
    private final String name;
}
