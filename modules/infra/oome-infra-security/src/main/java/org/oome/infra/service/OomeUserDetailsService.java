package org.oome.infra.service;

import lombok.RequiredArgsConstructor;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OomeUserDetailsService implements UserDetailsService {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);

        return createUserDetails(member);
    }

    private UserDetails createUserDetails(Member member) {
        List<GrantedAuthority> grantedAuthority = member.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());

        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                grantedAuthority
        );
    }
}