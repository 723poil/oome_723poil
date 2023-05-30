package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oome.infra.service.AuthenticationService;
import org.oome.infra.vo.LoginReqVo;
import org.oome.infra.vo.LoginResVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}/auth")
@RestController
public class AuthenticationApiController {

    private final AuthenticationService authenticationService;

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
}
