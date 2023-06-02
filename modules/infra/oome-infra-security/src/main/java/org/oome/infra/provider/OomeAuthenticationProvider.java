package org.oome.infra.provider;

import lombok.RequiredArgsConstructor;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.entity.member.vo.res.MemberRs;
import org.oome.infra.exception.AuthenticationLoginInfoCheckRuntimeException;
import org.oome.infra.user.AuthorizedUser;
import org.oome.infra.vo.LoginResVo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @deprecated JwtProvider사용으로 제거예정
 * @author 한주성
 */
@Deprecated(since = "2023-06-02", forRemoval = true)
@RequiredArgsConstructor
public class OomeAuthenticationProvider implements AuthenticationProvider {

    private final MemberJpaRepository memberJpaRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        LoginResVo loginResVo = null;


        try {
            Member member = memberJpaRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
            MemberRs rs = MemberRs.builder()
                    .entity(member)
                    .build();
            // TODO : block YN check
            if (!passwordEncoder.matches(password, rs.getPassword())) {
                // TODO : fail count + or block

                // TODO : if fail update and block throw LoginCheckException
                throw new AuthenticationLoginInfoCheckRuntimeException("비밀번호가 일치하지 않습니다.");
            } else {
                // TODO : reset fail count

                loginResVo = LoginResVo.builder()
                        .username(rs.getUsername())
                        .password(rs.getPassword())
                        .roles(rs.getRoles())
                        .build();
            }
        } catch (IllegalArgumentException ex) {
            throw new AuthenticationLoginInfoCheckRuntimeException("계정이 존재하지 않습니다.", ex);
        } catch (AuthenticationLoginInfoCheckRuntimeException ex) {
            throw new AuthenticationLoginInfoCheckRuntimeException(ex.getMessage(), ex);
        }

        List<SimpleGrantedAuthority> authorities = loginResVo.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        AuthorizedUser user = new AuthorizedUser(loginResVo, false, false, false, false, authorities);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
