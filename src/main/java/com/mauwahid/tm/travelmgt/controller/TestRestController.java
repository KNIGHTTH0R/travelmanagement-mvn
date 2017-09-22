package com.mauwahid.tm.travelmgt.controller;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.ContohService;
import com.mauwahid.tm.travelmgt.service.api.AirlinesService;
import com.mauwahid.tm.travelmgt.service.api.SearchService;
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

        ReqSearch reqSearch = new ReqSearch();
        reqSearch.setAdultPax(adultPax);
        reqSearch.setAirlineName(airlineName);
        reqSearch.setAirlineType(airlineType);
        reqSearch.setChildPax(childPax);
        reqSearch.setDepartDate(departDate);
        reqSearch.setFrom(from);
        reqSearch.setTo(to);
        reqSearch.setRoundtrip(roundtrip);
        reqSearch.setReturnDate(returnDate);
        reqSearch.setInfantPax(infantPax);


        return searchService.searchAirline(reqSearch);
    }
}
