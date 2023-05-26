package org.oome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.core.api.utils.OomeStringUtils;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class OomeWebLocalApplication implements CommandLineRunner {

    private final MemberJpaRepository memberJpaRepository;

    public static void main(String[] args) {
        SpringApplication.run(OomeWebLocalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Set<MemberRole> commonRole = new HashSet<>();
        commonRole.add(MemberRole.MEMBER);
        Set<MemberRole> adminRole = new HashSet<>();
        adminRole.add(MemberRole.MEMBER);
        adminRole.add(MemberRole.ADMIN);
        Set<MemberRole> developerRole = new HashSet<>();
        developerRole.add(MemberRole.MEMBER);
        developerRole.add(MemberRole.ADMIN);
        developerRole.add(MemberRole.DEVELOPER);

        List<Member> memberList = new ArrayList<>();
        memberList.add(Member.builder()
                .username("user")
                .password("1234")
                .memberRole(commonRole)
                .build());

        memberList.add(Member.builder()
                .username("admin")
                .password("1234")
                .memberRole(adminRole)
                .build());

        memberList.add(Member.builder()
                .username("dev")
                .password("1234")
                .memberRole(developerRole)
                .build());

        memberJpaRepository.saveAll(memberList);
        log.debug("dummy member created!!");
    }
}
