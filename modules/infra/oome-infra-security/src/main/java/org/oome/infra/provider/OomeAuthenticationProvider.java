package org.oome.infra.provider;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OomeAuthenticationProvider implements AuthenticationProvider {

    private final MemberJpaRepository memberJpaRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        LoginResVo loginResVo = null;


        try {
            MemberRs rs = modelMapper.map(memberJpaRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new), MemberRs.class);

            // TODO : block YN check

            if (!passwordEncoder.matches(password, rs.getPassword())) {
                // TODO : fail count + or block

                // TODO : if fail update and block throw LoginCheckException
                throw new AuthenticationLoginInfoCheckRuntimeException("비밀번호가 일치하지 않습니다.");
            } else {
                // TODO : reset fail count

                loginResVo = modelMapper.map(rs, LoginResVo.class);
            }
        } catch (IllegalArgumentException ex) {
            throw new AuthenticationLoginInfoCheckRuntimeException("계정이 존재하지 않습니다.", ex);
        } catch (AuthenticationLoginInfoCheckRuntimeException ex) {
            throw new AuthenticationLoginInfoCheckRuntimeException(ex.getMessage(), ex);
        }

        List<SimpleGrantedAuthority> authorities = loginResVo.getMemberRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        AuthorizedUser user = new AuthorizedUser(loginResVo, false, false, false, false, authorities);
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
