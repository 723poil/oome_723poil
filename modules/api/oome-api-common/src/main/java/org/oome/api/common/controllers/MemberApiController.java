package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.api.common.dto.req.MemberSaveReqDto;
import org.oome.api.common.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/regist")
    public ResponseEntity<String> saveMember(@Valid MemberSaveReqDto reqDto) {

        return ResponseEntity.ok(memberService.saveMember(reqDto));
    }
}
