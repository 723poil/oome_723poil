package org.oome.api.common.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.oome.core.api.validation.annotations.Email;
import org.oome.entity.common.enums.Gender;
import org.oome.entity.common.enums.YN;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

@ToString
@Setter
@Getter
@AllArgsConstructor
@Builder
public class MemberSaveReqDto {

    @Email(message = "이메일 형식이어야합니다.")
    @NotEmpty(message = "유저아이디 : {invalid.NotEmpty}")
    private String username;

    @NotEmpty(message = "패스워드 : {invalid.NotEmpty}")
    private String password;

    @NotEmpty(message = "닉네임 : {invalid.NotEmpty}")
    private String nickname;

    @NotNull(message = "인증정보 : 필수값입니다.")
    private YN isUsernameValid;

    private String phoneNumber;

    private Gender gender;

    private String githubUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birth;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .roles(new HashSet<>(Collections.singleton(MemberRole.MEMBER)))
                .isLock(YN.N)
                .isBlock(YN.N)
                .isUsernameValid(isUsernameValid)
                .failCount(0)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .githubUrl(githubUrl)
                .birth(birth.atStartOfDay())
                .build();
    }
}
