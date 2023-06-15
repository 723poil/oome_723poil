package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.api.common.dto.req.EmailAuthReqDto;
import org.oome.api.common.dto.req.MemberInfoUpdateReqDto;
import org.oome.api.common.dto.req.MemberSaveReqDto;
import org.oome.api.common.services.MemberService;
import org.oome.entity.enums.MemberRole;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param reqDto 회원정보
     * @return 가입된 id (로그인창으로 redirect등 에서 사용)
     */
    @PostMapping("/regist")
    public ResponseEntity<String> saveMember(@Valid @RequestBody MemberSaveReqDto reqDto) {

        return ResponseEntity.ok(memberService.saveMember(reqDto));
    }

    /**
     * 이메일 인증번호 전송
     * @param emailAuthReqDto 이메일 정보
     * @return 성공여부
     */
    @PostMapping("/authcode")
    public ResponseEntity<String> sendAuthCode(@RequestBody EmailAuthReqDto emailAuthReqDto) {

        memberService.sendAuthCode(emailAuthReqDto.getEmail());

        return ResponseEntity.ok("SENDED");
    }

    /**
     * 이메일 인증
     * @param emailAuthReqDto 인증번호 정보
     * @return 인증여부
     */
    @PostMapping("/verify")
    public ResponseEntity<String> matchAuthCode(@RequestBody EmailAuthReqDto emailAuthReqDto) {

        return ResponseEntity.ok(memberService.matchAuthCode(emailAuthReqDto));
    }

    /**
     * 개인 유저정보를 변경한다
     * @param reqDto 변경할 데이터
     * @return 성공여부
     */
    @Secured({"ROLE_MEMBER", "ROLE_ADMIN", "ROLE_DEVELOPER"})
    @PutMapping("/update")
    public ResponseEntity<String> updateMemberInfo(@Valid @RequestBody MemberInfoUpdateReqDto reqDto) {
        return ResponseEntity.ok(memberService.updateMemberInfo(reqDto));
    }
}
