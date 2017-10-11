package com.mauwahid.tm.travelmgt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HomeController {

    @GetMapping("/")
    public RedirectView redirectToDoc() {
        return new RedirectView("swagger-ui.html");
    }
}
