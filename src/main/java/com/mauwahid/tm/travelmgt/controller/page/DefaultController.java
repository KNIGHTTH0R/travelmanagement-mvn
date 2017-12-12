package com.mauwahid.tm.travelmgt.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class DefaultController {


    @GetMapping("/home")
    public String home() {
        return "home";
    }



}

