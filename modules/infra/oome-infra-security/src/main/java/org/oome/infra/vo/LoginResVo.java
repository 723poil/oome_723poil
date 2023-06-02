package org.oome.infra.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.oome.entity.enums.MemberRole;

import java.util.Set;

/**
 * @deprecated Dto 재생산으로 제거예정
 * @author 한주성
 */
@Deprecated(since = "2023-06-02", forRemoval = true)
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginResVo {
    private String username;

    private String password;

    private Set<MemberRole> roles;
}
