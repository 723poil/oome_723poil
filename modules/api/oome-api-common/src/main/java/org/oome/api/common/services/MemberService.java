package org.oome.api.common.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.oome.api.common.dto.req.EmailAuthReqDto;
import org.oome.api.common.dto.req.MemberInfoUpdateReqDto;
import org.oome.api.common.dto.req.MemberSaveReqDto;
import org.oome.core.api.exception.runtime.NotValidUsernameAcceptOomeRuntimeException;
import org.oome.core.utils.S;
import org.oome.entity.common.enums.YN;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.oome.infra.email.dto.EmailSendDto;
import org.oome.infra.email.service.MailSendService;
import org.oome.infra.redis.hash.server1.AuthCode;
import org.oome.infra.redis.hash.server1.AuthCodeRedisRepository;
import org.oome.infra.utils.SecurityUtil;
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

    private final SecurityUtil securityUtil;

    @Transactional
    public String saveMember(MemberSaveReqDto reqDto) {
        if (reqDto.getIsUsernameValid().equals(YN.N)) {
            throw new NotValidUsernameAcceptOomeRuntimeException("인증되지 않은 이메일 주소입니다.");
        }

        Member entity = reqDto.toEntity(passwordEncoder);

        return memberJpaRepository.save(entity).getUsername();
    }

    public void sendAuthCode(String email) {

        int authCode = ThreadLocalRandom.current().nextInt(100000, 1000000);
        Member member = securityUtil.getAuthorizedUser();

        authCodeRedisRepository.save(
                AuthCode.builder()
                        .id(email)
                        .authcode(String.valueOf(authCode))
                        .expiration(300000L) // 5분간 유지한다.
                        .build()
        );

        mailSendService.sendEmail(
                EmailSendDto.builder()
                        .to(email)
                        .subject("[OOME] 다음 인증번호를 입력하세요.")
                        .message(S.f(member.getNickname() + "님, 안녕하세요.\n" +
                                "OOME와 함께해주셔서 감사합니다.\n" +
                                "이메일 주소를 인증해주세요.\n" +
                                "인증번호 : {0}", String.valueOf(authCode) +
                                "감사합니다. \n" +
                                "OOME 드림.\n\n" +
                                "문의사항은 고객센터를 이용해주세요."))
                        .build()
        );

    }

    public String matchAuthCode(EmailAuthReqDto reqDto) {

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

    @Transactional
    public String updateMemberInfo(MemberInfoUpdateReqDto reqDto) {

        Member member = securityUtil.getAuthorizedUser();

        member.setPhoneNumber(reqDto.getPhoneNumber());
        member.setGender(reqDto.getGender());
        member.setGithubUrl(reqDto.getGithubUrl());
        member.setBirth(reqDto.getBirth().atStartOfDay());
//        member.updateInfo(reqDto);
        return "SUCCESS";
    }
}
