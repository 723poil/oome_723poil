package org.oome.infra.user;

import lombok.Getter;
import org.oome.infra.vo.LoginResVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthorizedUser extends User {

    private LoginResVo data;

    public AuthorizedUser(
            LoginResVo data,
            boolean disabled,
            boolean accountExpired,
            boolean credentialExpired,
            boolean accountLocked,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(
                data.getUsername(),
                data.getPassword(),
                !disabled,
                !accountExpired,
                !credentialExpired,
                !accountLocked,
                authorities);
        this.data = data;
    }
}
