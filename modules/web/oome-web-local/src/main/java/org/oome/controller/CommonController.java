package org.oome.controller;

import lombok.extern.slf4j.Slf4j;
import org.oome.core.utils.S;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@Controller
public class CommonController {

    @GetMapping("/{menuType}/{menu}")
    public String moveToMenu(
            @Valid
            @NotEmpty
            @PathVariable("menuType")
            String menuType,
            @Valid
            @NotEmpty
            @PathVariable("menu")
            String menu
    ) {
        log.debug(S.f("/{0}/{1}", menuType, menu));
        return S.f("/{0}/{1}", menuType, menu);
    }
}
