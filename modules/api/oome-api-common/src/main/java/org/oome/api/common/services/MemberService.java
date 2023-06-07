package org.oome.api.common.services;

import lombok.RequiredArgsConstructor;
import org.oome.api.common.dto.req.MemberSaveReqDto;
import org.oome.entity.member.Member;
import org.oome.entity.member.repository.MemberJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String saveMember(MemberSaveReqDto reqDto) {
        Member entity = reqDto.toEntity(passwordEncoder);

        return memberJpaRepository.save(entity).getUsername();
    }
}
