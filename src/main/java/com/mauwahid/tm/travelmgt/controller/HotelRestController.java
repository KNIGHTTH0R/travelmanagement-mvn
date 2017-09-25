package com.mauwahid.tm.travelmgt.controller;

import com.mauwahid.tm.travelmgt.domain.api.request.ReqSearch;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.SearchInfo;
import com.mauwahid.tm.travelmgt.service.api.HotelCityService;
import com.mauwahid.tm.travelmgt.service.api.trevohub.hotel.ApiDetail;
import com.mauwahid.tm.travelmgt.service.api.trevohub.hotel.ApiGetCityList;
import com.mauwahid.tm.travelmgt.service.api.trevohub.hotel.ApiSearchHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelRestController{


    @Autowired
    HotelCityService hotelCityService;

    @Autowired
    ApiGetCityList apiGetCityList;

    @Autowired
    ApiSearchHotel apiSearchHotel;

    @Autowired
    ApiDetail apiDetail;

    @RequestMapping("/city")
    public String searchCity(@RequestParam(value = "city")String city)
    throws IOException{
        return apiGetCityList.callApi(city);
    }

    @RequestMapping("/search")
    public String searchHotel(
            @RequestParam(value = "city")String city,
            @RequestParam(value = "sort_by")String sortBy,
            @RequestParam(value = "destination_key")String destinationKey,
            @RequestParam(value = "country_code")String countryCode,
            @RequestParam(value = "check_in")String checkIn,
            @RequestParam(value = "check_out")String checkOut,
            @RequestParam(value = "adult")String totalAdult,
            @RequestParam(value = "child")String totalChild,
            @RequestParam(value = "limit")String limit
    )
            throws IOException{
        return apiSearchHotel.callApi(city,sortBy,destinationKey
                ,countryCode,checkIn,checkOut,totalAdult,totalChild,limit);
    }

    @RequestMapping("/detail")
    public String detailHotel(@RequestParam(value = "session_id")String sessionId,
                              @RequestParam(value = "hotel_key")String hotelKey,
                              @RequestParam(value = "hotel_id")String hotelId,
                              @RequestParam(value = "service_id")String serviceId)
            throws IOException{
        return apiDetail.callApi(sessionId,hotelKey,hotelId,serviceId);
    }





}
