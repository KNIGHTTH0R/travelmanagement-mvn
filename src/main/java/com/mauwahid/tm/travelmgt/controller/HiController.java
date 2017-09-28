package com.mauwahid.tm.travelmgt.controller;


import com.mauwahid.tm.travelmgt.domain.apimodel.Airline;
import com.mauwahid.tm.travelmgt.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HiController {

    @Autowired
    HiService hiService;

    @RequestMapping("/hi")
    public String bilangHai(){
        return hiService.bilangHai();
    }


    @RequestMapping("test_airline")
    public ArrayList<Airline> getAIrline(){

        ArrayList<Airline> airlines = new ArrayList<>();

        Airline airline = new Airline();
        airline.setAirlinesId("200");
        airline.setAirlinesName("test_name");
        airline.setAirlinesCode("111");

        airlines.add(airline);
        airlines.add(airline);

        return airlines;


    }


}
