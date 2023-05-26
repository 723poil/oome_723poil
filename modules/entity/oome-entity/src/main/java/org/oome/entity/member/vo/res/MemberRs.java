package org.oome.entity.member.vo.res;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.oome.entity.enums.MemberRole;

import java.io.Serializable;
import java.util.Set;

@Data
@Setter
@Getter
public class MemberRs implements Serializable {

    private static final long serialVersionUID = 8762936622622238661L;

    private String username;

    private String password;

    private Set<MemberRole> memberRoles;
}
