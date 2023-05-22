package org.oome.api.common.controllers;

import org.oome.core.api.utils.OomeStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("${org.oome.api.context-path.common-url}")
@RestController
public class HelloWorldCommonApiController {

    @GetMapping("/hello")
    public String helloWorld() {
        return OomeStringUtils.convertToCamelCase("hello_world");
    }
}
