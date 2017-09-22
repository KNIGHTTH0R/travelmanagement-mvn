package com.mauwahid.tm.travelmgt.controller;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.api.HotelCityService;
import com.mauwahid.tm.travelmgt.service.api.trevohub.hotel.ApiGetCityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HotelRestController{


    @Autowired
    HotelCityService hotelCityService;

    @RequestMapping("/hotelcity")
    public Response getSearch(
            @RequestParam(value = "city")String city){
        return hotelCityService.load(city);
    }
}
