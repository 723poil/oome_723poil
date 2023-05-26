package org.oome.infra.vo;

import lombok.Getter;
import lombok.Setter;
import org.oome.entity.enums.MemberRole;

import java.util.Set;

@Getter
@Setter
public class LoginResVo {
    private String username;

    private String password;

    private Set<MemberRole> memberRoles;
}
