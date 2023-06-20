package org.oome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.entity.common.enums.YN;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class OomeWebLocalApplication implements CommandLineRunner {

    private final MemberJpaRepository memberJpaRepository;

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(OomeWebLocalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        createDummyUser();
    }

    /**
     * 테스트용 더미 유저를 생성한다.
     * h2가 휘발성 인메모리동작을 하지 않기 때문에 최초 1회만 수행한다.
     */
    private void createDummyUser() {
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
                .password(passwordEncoder.encode("1234"))
                .nickname("일반사용자")
                .roles(commonRole)
                .isBlock(YN.N)
                .isLock(YN.N)
                .isUsernameValid(YN.N)
                .failCount(0)
                .build());

        memberList.add(Member.builder()
                .username("admin")
                .password(passwordEncoder.encode("1234"))
                .nickname("관리자")
                .roles(adminRole)
                .isBlock(YN.N)
                .isLock(YN.N)
                .isUsernameValid(YN.N)
                .failCount(0)
                .build());

        memberList.add(Member.builder()
                .username("dev")
                .password(passwordEncoder.encode("1234"))
                .nickname("개발자")
                .roles(developerRole)
                .isBlock(YN.N)
                .isLock(YN.N)
                .isUsernameValid(YN.N)
                .failCount(0)
                .build());

        memberJpaRepository.saveAll(memberList);
        log.debug("dummy member created!!");
    }
}
