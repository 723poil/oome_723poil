package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.infra.service.AuthenticationService;
import org.oome.infra.vo.LoginResVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/auth")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

    private final HttpSession httpSession;

//    @PostMapping(value = "/authorize", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<LoginResVo> login(@RequestBody @Valid LoginReqVo loginReqVo,
//                                            HttpServletRequest request,
//                                            HttpServletResponse response) throws Exception {
//        log.debug("LoginRequest Accepted");
//
//        LoginResVo vo = authenticationService.signin(response, loginReqVo);
//
//        return ResponseEntity.ok(vo);
//    }

    @GetMapping("/user")
    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/session")
    public LoginResVo getSessionUser() {
        return (LoginResVo) httpSession.getAttribute("user");
    }
}
