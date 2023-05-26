package org.oome.infra.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.oome.entity.enums.MemberRole;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginResVo {
    private String username;

    private String password;

    private Set<MemberRole> memberRoles;
}
