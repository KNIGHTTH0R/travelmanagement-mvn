package com.mauwahid.tm.travelmgt.controller.page;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class DefaultController {


    @GetMapping("/test")
    public String home() {
        return "/home";
    }


}
