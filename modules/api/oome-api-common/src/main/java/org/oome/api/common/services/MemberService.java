package org.oome.api.common.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.oome.api.common.dto.req.EmailAuthReqDto;
import org.oome.api.common.dto.req.MemberSaveReqDto;
import org.oome.core.utils.S;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.infra.email.dto.EmailSendDto;
import org.oome.infra.email.service.MailSendService;
import org.oome.infra.redis.hash.server1.AuthCode;
import org.oome.infra.redis.hash.server1.AuthCodeRedisRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailSendService mailSendService;

    private final AuthCodeRedisRepository authCodeRedisRepository;

    @Transactional
    public String saveMember(MemberSaveReqDto reqDto) {
        Member entity = reqDto.toEntity(passwordEncoder);

        return memberJpaRepository.save(entity).getUsername();
    }

    public void sendAuthCode(String email) {

        int authCode = ThreadLocalRandom.current().nextInt(100000, 1000000);

        authCodeRedisRepository.save(
                AuthCode.builder()
                        .id(email)
                        .authcode(String.valueOf(authCode))
                        .expiration(System.currentTimeMillis() + 300000)
                        .build()
        );

        mailSendService.sendEmail(
                EmailSendDto.builder()
                        .to(email)
                        .subject("[OOME] 다음 인증번호를 입력하세요.")
                        .message(S.f("인증번호 : {0}", String.valueOf(authCode)))
                        .build()
        );

    }

    public String matchAuthCOde(EmailAuthReqDto reqDto) {

        AuthCode redisHash = authCodeRedisRepository.findById(reqDto.getEmail()).orElse(null);

        if (ObjectUtils.isNotEmpty(redisHash)) { // 대조결과가 맞으면
            if (redisHash.getAuthcode().equals(reqDto.getAuthCode())) {
                return "VALID";
            }
            return "INVALID";
        } else {
            return "INVALID";
        }
    }
}
