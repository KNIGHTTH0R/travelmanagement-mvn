package com.mauwahid.tm.travelmgt.controller;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.ContohService;
import com.mauwahid.tm.travelmgt.service.agent.AirlinesService;
import com.mauwahid.tm.travelmgt.service.agent.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    ContohService contohService;

    @Autowired
    AirlinesService airlinesService;


    @Autowired
    SearchService searchService;

    @RequestMapping("/testApi")
    public Response getResponse(){
        return contohService.getResponse();
    }


    @RequestMapping("/testApiDomestic")
    public Response getAirlines(@RequestParam(value = "apiKey")String apiKey,
                                   @RequestParam(value="type")int type, HttpServletRequest request){

         return airlinesService.load(request, apiKey,type);
    }

    @RequestMapping("/search")
    public List<SearchInfo> getSearch(
            @RequestParam(value = "airlineName")String airlineName,
            @RequestParam(value = "roundtrip") String roundtrip,
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "departDate") String departDate,
            @RequestParam(value = "returnDate") String returnDate,
            @RequestParam(value = "adultPax") String adultPax,
            @RequestParam(value = "childPax") String childPax,
            @RequestParam(value = "infantPax") String infantPax,
            @RequestParam(value = "airlineType") String airlineType
            , HttpServletRequest request){

        FlightSearchReq flightSearchReq = new FlightSearchReq();
        flightSearchReq.setAdultPax(adultPax);
        flightSearchReq.setAirlineName(airlineName);
        flightSearchReq.setAirlineType(airlineType);
        flightSearchReq.setChildPax(childPax);
        flightSearchReq.setDepartDate(departDate);
        flightSearchReq.setFrom(from);
        flightSearchReq.setTo(to);
        flightSearchReq.setRoundtrip(roundtrip);
        flightSearchReq.setReturnDate(returnDate);
        flightSearchReq.setInfantPax(infantPax);


        return searchService.searchAirline(flightSearchReq);
    }
}
