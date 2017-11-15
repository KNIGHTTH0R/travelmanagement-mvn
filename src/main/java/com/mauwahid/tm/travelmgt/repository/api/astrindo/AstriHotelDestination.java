package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelDestinationReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelBookResult;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelDestination;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.reservation.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class AstriHotelDestination {

    private Map params = new HashMap<String,String>();

    private String url;


    @Autowired
    private AstriApiCaller astriApiCaller;


    public Set<HotelDestination> getDestination(Map params) {

        String jsonData;

        url = AstriApiCaller.uri;
        log.debug("params : "+params);

        try{
            jsonData = astriApiCaller.callApiGet(url,params);
            log.debug("JSON RES : "+jsonData);
        }catch (IOException ex){
            log.error("searchTravel : "+ex.toString());
            return exceptionHandling(ex);
        }

        try{
            return translateToObject(jsonData);
        }catch (Exception ex) {
            log.error("searchHotel translateToObj : "+ex.toString());

            return exceptionHandling(ex);
        }
    }


    private Set<HotelDestination> exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private Set<HotelDestination> translateToObject(String jsonData) throws JSONException {
        HotelDestination hotelDestination;
        Set<HotelDestination> hotelDestinations = new HashSet<>();

        JSONArray arrHotelDest = new JSONArray(jsonData);
        JSONObject objHotelDest = null;

        for(int i=0;i<arrHotelDest.length();i++){
            hotelDestination = new HotelDestination();

            objHotelDest = arrHotelDest.getJSONObject(i);

            hotelDestination.setCityCode(objHotelDest.optString("CityCode"));
            hotelDestination.setLabel(objHotelDest.optString("Label"));
            hotelDestination.setCityName(objHotelDest.optString("CityName"));
            hotelDestination.setCountryCode(objHotelDest.optString("CountryCode"));
            hotelDestination.setCountryName(objHotelDest.optString("CountryName"));

            hotelDestinations.add(hotelDestination);
        }


        return hotelDestinations;

    }


    //static method
    public static Map translateToParam(HotelDestinationReq hotelDestinationReq){
        Map param = new HashMap();

        param.put("username",AstriApiCaller.USERNAME);
        param.put("password", AstriApiCaller.PASSWORD);
        param.put("orgCode", AstriApiCaller.ORGCODE);
        param.put("merchantID", AstriApiCaller.MERCHANTID);

        param.put("Type","CountryCode");
        param.put("Value",hotelDestinationReq.getCountryCode());


        return param;
    }
}
