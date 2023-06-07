package org.oome.api.common.dto.req;

import lombok.*;
import org.oome.core.api.validation.annotations.Email;
import org.oome.entity.common.enums.YN;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.HashSet;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberSaveReqDto {

    @Email(message = "이메일 형식이어야합니다.")
    private String username;

    @NotEmpty(message = "패스워드 : {invalid.NotEmpty}")
    private String password;

    @NotEmpty(message = "닉네임 : {invalid.NotEmpty}")
    private String nickname;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .roles(new HashSet<>(Collections.singleton(MemberRole.MEMBER)))
                .isLock(YN.N)
                .isBlock(YN.N)
                .isUsernameValid(YN.N)
                .failCount(0)
                .build();
    }
}
