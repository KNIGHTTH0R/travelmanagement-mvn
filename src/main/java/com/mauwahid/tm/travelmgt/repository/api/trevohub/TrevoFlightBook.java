package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.request.FlightBookReq;
import com.mauwahid.tm.travelmgt.domain.api.request.FlightSearchReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.flight.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
public class TrevoFlightBook {

    private String url;

    private Logger logger = LoggerFactory.getLogger(TrevoFlightSearch.class);

    @Autowired
    private TrevoApiCaller trevoApiCaller;

    private FlightBook bookTravel(Map params) {

        String jsonData;

        url = TrevoApiCaller.uri;
        logger.debug("params : " + params);

        try {
            jsonData = trevoApiCaller.callApiPost(url, params);
            logger.debug("JSON RES : " + jsonData);
        } catch (IOException ex) {
            logger.error("searchTravel : " + ex.toString());
            return exceptionHandling(ex);
        }

        try {
            return translateToObject(jsonData);
        } catch (Exception ex) {
            logger.error("translateToObj : " + ex.toString());

            return exceptionHandling(ex);
        }
    }


    private FlightBook exceptionHandling(Exception ex) {

        return null;
    }


    private FlightBook translateToObject(String jsonData) throws JSONException {


        return null;

    }


    //static method
    public static Map translateParam(FlightBookReq flightBookReq) {
        Map param = new HashMap();


        param.put("b2b_code", TrevoApiCaller.b2bCode);
        param.put("h2h_code", TrevoApiCaller.h2hCode);
        param.put("action", "book");
        param.put("app", flightBookReq.getAirlineType());

        param.put("session_id", flightBookReq.getTravelId());
        param.put("customer[name]", flightBookReq.getContactName());
        param.put("customer[phone]", flightBookReq.getContactPhone());
        param.put("customer[email]",flightBookReq.getContactEmail());

        param.put("pax[0][type]","adult");
        param.put("pax[0][title]",flightBookReq.getAdultTitle1());

        String[] adult1Name = flightBookReq.getAdultName1().split(" ");

        param.put("pax[0][firstname]",adult1Name[0]);
        param.put("pax[0][lastname]",adult1Name[1]!=null?adult1Name[1]:adult1Name[0]);
        param.put("pax[0][id_number]",flightBookReq.getAdultIDNumber1());
        param.put("pax[0][birthdate]", flightBookReq.getAdultBirthDate1());
        param.put("pax[0][passport]", flightBookReq.getAdultPassport1());
        param.put("pax[0][passport_expired]", flightBookReq.getAdultPassportExpired1());

        if(flightBookReq.getAdultIDNumber2()!=null || !flightBookReq.getAdultIDNumber2().equalsIgnoreCase("")){

            String[] adultName2 = flightBookReq.getAdultName2().split(" ");

            param.put("pax[1][type]","adult");
            param.put("pax[1][firstname]", adultName2[0]);
            param.put("pax[1][lastname]", adultName2[1]!=null?adultName2[1]:adultName2[0]);
            param.put("pax[1][id_number]", flightBookReq.getAdultIDNumber2());
            param.put("pax[1][birthdate]", flightBookReq.getAdultBirthDate2());
            param.put("pax[1][passport]", flightBookReq.getAdultPassport2());
            param.put("pax[1][passport_expired]", flightBookReq.getAdultPassportExpired2());

        }
        return param;
    }


}
