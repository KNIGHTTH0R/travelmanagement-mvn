package com.mauwahid.tm.travelmgt.controller;


import com.mauwahid.tm.travelmgt.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    HiService hiService;

    @RequestMapping("/hi")
    public String bilangHai(){
        return hiService.bilangHai();
    }



}
