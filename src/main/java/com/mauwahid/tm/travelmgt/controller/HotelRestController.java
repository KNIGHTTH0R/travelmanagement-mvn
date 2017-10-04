package com.mauwahid.tm.travelmgt.controller;

import com.mauwahid.tm.travelmgt.service.agent.HotelCityService;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.hotel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("hotel")
public class HotelRestController{


    @Autowired
    private HotelCityService hotelCityService;

    @Autowired
    private ApiGetCityList apiGetCityList;

    @Autowired
    private ApiSearchHotel apiSearchHotel;

    @Autowired
    private ApiDetail apiDetail;

    @Autowired
    private ApiBook apiBook;

    @Autowired
    private ApiIssue apiIssue;

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

    @RequestMapping("/book")
    public String bookHotel(
            @RequestParam(value = "session_id")String sessionId,
            @RequestParam(value = "room_id")String roomId,
            @RequestParam(value = "bed_type_id")String bedTypeId,
            @RequestParam(value = "smoking_pref")String smokingPref,
            @RequestParam(value = "customer_first_name")String customerFirstName,
            @RequestParam(value = "customer_last_name")String customerLastName,
            @RequestParam(value = "customer_phone")String customerPhone,
            @RequestParam(value = "custoemer_email")String customerEmail,
            @RequestParam(value = "customer_id_num")String customerIdNum,
            @RequestParam(value = "ext_service")String extService,
            @RequestParam(value = "total_margin")String totalMargin)
            throws IOException{
        return apiBook.callApi(sessionId,roomId,bedTypeId,smokingPref,customerFirstName,
                customerLastName,customerPhone, customerEmail,customerIdNum,extService,
                totalMargin);
    }

    @RequestMapping("/issue")
    public String issueHotel(@RequestParam(value = "hotel_data_id")String hotelDataId,
                             @RequestParam(value = "payment_type")String paymentType,
                             @RequestParam(value = "invoice_no")String invoiceNo)
            throws IOException{
        return apiIssue.callApi(hotelDataId,paymentType,invoiceNo);
    }





}
