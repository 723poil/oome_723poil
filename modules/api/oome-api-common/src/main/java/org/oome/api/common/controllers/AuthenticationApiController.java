package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.infra.service.AuthenticationService;
import org.oome.infra.utils.SecurityUtil;
import org.oome.infra.vo.MemberLoginReqDto;
import org.oome.infra.vo.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/auth")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

    private final HttpSession httpSession;

    @PostMapping(value = "/authorize")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid MemberLoginReqDto loginReqVo,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        log.debug("LoginRequest Accepted");

        TokenDto vo = authenticationService.login(loginReqVo);

        return ResponseEntity.ok(vo);
    }

    @GetMapping("/user")
    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/authcheck")
    public ResponseEntity<Long> getLoginMember() {
        return ResponseEntity.ok(SecurityUtil.getCurrentMemberId());
    }
}
