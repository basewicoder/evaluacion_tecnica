package com.examen.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

    @GetMapping({"/", "/passport/**", "/cuenta/**", "/transaccion/**", "/apidoc/**", "/sys/**"})
    public String index() {
        return "portal/index";
    }
}
