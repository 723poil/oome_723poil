package org.oome.api.common.controllers;

import org.oome.api.utils.OomeStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldCommonController {

    @GetMapping("/api/hello")
    public String helloWorld() {
        return OomeStringUtils.convertToCamelCase("hello_world");
    }
}
