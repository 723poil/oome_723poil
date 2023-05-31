package org.oome.entity.member.vo.res;

import lombok.*;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@Setter
@Getter
public class MemberRs implements Serializable {

    private static final long serialVersionUID = 8762936622622238661L;

    private String username;

    private String password;

    private Set<MemberRole> roles;

    @Builder
    public MemberRs(Member entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.roles = entity.getRoles();
    }
}
