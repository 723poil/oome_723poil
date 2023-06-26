package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.core.api.http.OomeResponse;
import org.oome.infra.service.AuthenticationService;
import org.oome.infra.utils.SecurityUtil;
import org.oome.infra.vo.MemberLoginReqDto;
import org.oome.infra.vo.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/auth")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

    private final SecurityUtil securityUtil;

    @PostMapping(value = "/authorize")
    public OomeResponse<TokenDto> login(@RequestBody @Valid MemberLoginReqDto loginReqVo,
                                            HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        log.debug("LoginRequest Accepted");

        TokenDto vo = authenticationService.login(loginReqVo);

        return new OomeResponse<>(vo, HttpStatus.OK);
    }

    @GetMapping("/user")
    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/authcheck")
    public OomeResponse<Long> getLoginMember() {
        return new OomeResponse<>(securityUtil.getCurrentMemberId(), HttpStatus.OK);
    }
}
