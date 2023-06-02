package org.oome.infra.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oome.entity.member.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberLoginResDto {
    private String username;
    private String nickname;

    public static MemberLoginResDto of(Member entity) {
        return MemberLoginResDto.builder()
                .username(entity.getUsername())
                .nickname(entity.getUsername())
                .build();
    }
}