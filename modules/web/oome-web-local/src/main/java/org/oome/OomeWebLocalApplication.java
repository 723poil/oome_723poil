package org.oome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.entity.enums.MemberRole;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.entity.question.Question;
import org.oome.entity.question.repository.QuestionJpaRepository;
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

    private final QuestionJpaRepository questionJpaRepository;

    private final PasswordEncoder passwordEncoder;

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
                .password(passwordEncoder.encode("1234"))
                .nickname("user")
                .roles(commonRole)
                .build());

        memberList.add(Member.builder()
                .username("admin")
                .password(passwordEncoder.encode("1234"))
                .nickname("admin")
                .roles(adminRole)
                .build());

        memberList.add(Member.builder()
                .username("dev")
                .password(passwordEncoder.encode("1234"))
                .nickname("dev3")
                .roles(developerRole)
                .build());

        memberJpaRepository.saveAll(memberList);
        log.debug("dummy member created!!");

        List<Question> questionList = new ArrayList<>();
        questionList.add(Question.builder()
                .title("test title 1")
                .contents("<h1>hello world</h1>")
                .creater(memberJpaRepository.findByUsername("user").orElseThrow())
                .build());

        questionList.add(Question.builder()
                .title("test title 2")
                .contents("<h1>hello world</h1>")
                .creater(memberJpaRepository.findByUsername("user").orElseThrow())
                .build());

        questionList.add(Question.builder()
                .title("test title 3")
                .contents("<h1>hello world</h1>")
                .creater(memberJpaRepository.findByUsername("user").orElseThrow())
                .build());

        questionJpaRepository.saveAll(questionList);
        log.debug("dummy question created!!");
    }
}
