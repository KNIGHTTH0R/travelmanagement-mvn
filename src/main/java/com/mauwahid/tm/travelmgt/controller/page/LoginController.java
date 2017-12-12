package com.mauwahid.tm.travelmgt.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class LoginController {


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
