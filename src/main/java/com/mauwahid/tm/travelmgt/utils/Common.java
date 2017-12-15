package com.mauwahid.tm.travelmgt.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauwahid.tm.travelmgt.domain.api.response.ResponseInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Common {



    public static final String API_ASTRINDO = "astrindo";

    public static final String API_POINTER = "pointer";

    public static final String API_TREVOHUB = "trevohub";

    public static final String API_OPSIGO = "opsigo";

    public static final String FLIGHT_DOMESTIC = "domestic";

    public static final String FLIGHT_INTERNATIONAL = "international";


    public static final String CALLBACK_BOOK = "/flight/book-callback";

    public static final String CALLBACK_ISSUE = "/flight/issue-callback";

    public static final String CALLBACK_CANCEL = "/flight/cancel-callback";



    //dd-MM-yyyy
    public static String toPointerStandar(String dateWindoFormat) throws ParseException{
        String NEW_FORMAT = "dd-MM-yyy";
        String OLD_FORMAT = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date date = null;

        date = sdf.parse(dateWindoFormat);


        sdf.applyPattern(NEW_FORMAT);
        return sdf.format(date);
    }

    public static String opsTranslateAirline(String airlineName){

        airlineName = airlineName.toLowerCase();

        switch (airlineName){
            case "lion" :
                return "2";
            case "sriwijaya":
                return "3";
            case "citilink":
                return "4";
            case "airasia":
                return "5";
            case "intair" : //sabre
                return "6";
            case "sabre" : //sabre
                return "6";
            case "garuda":
                return "7";
            case "jetstar":
                return "8";
            case "kalstar":
                return "11";
            default:
                return "2";
        }
    }

    public static String generateSessionID(){
        return UUID.randomUUID().toString();
    }


    public static String generateJSONFromObject(ResponseInterface responseInterface){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = "";

        try{
            jsonData = objectMapper.writeValueAsString(responseInterface);
        }catch (Exception ex){

        }

        return jsonData;
    }


    public static boolean isApiListChecked(String... params){

        int count = 0;

        for(String api : params){

            if(api.equalsIgnoreCase(Common.API_ASTRINDO)){
                count++;
            }

        }

        if(count>0)
            return true;

        return false;
    }
}
