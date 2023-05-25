package org.oome.api.common.controllers;

import lombok.RequiredArgsConstructor;
import org.oome.api.common.services.HelloWorldService;
import org.oome.core.api.utils.OomeStringUtils;
import org.oome.core.utils.S;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@RequestMapping("${org.oome.api.context-path.common-url}")
@RestController
public class HelloWorldCommonApiController {

    private final HelloWorldService helloWorldService;

    @GetMapping("/hello")
    public String helloWorld() {
        return OomeStringUtils.convertToCamelCase(helloWorldService.returnHelloWorld());
    }

    @GetMapping("/hello/{username}")
    public String helloUser(
            @PathVariable("username")
            @Valid
            @NotEmpty String username) {
        return S.f("hello {0} and {1}", username, username + "2");
    }
}
