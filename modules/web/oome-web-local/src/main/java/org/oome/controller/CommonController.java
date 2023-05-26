package org.oome.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CommonController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "/index.html";
    }


}
