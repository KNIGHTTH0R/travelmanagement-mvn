package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelPolicyReq;
import com.mauwahid.tm.travelmgt.domain.apimodel.hotel.HotelCancelPolicy;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class AstriHotelCancelPolicy {

    private Map params = new HashMap<String,String>();

    private String url;


    @Autowired
    private AstriApiCaller astriApiCaller;


    public HotelCancelPolicy cancelPolicy(Map params) {

        String jsonData;

        url = AstriApiCaller.uri+"HotelCancellationPolicies.aspx";
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


    private HotelCancelPolicy exceptionHandling(Exception ex){

        return null;
    };

    //Translator From JSON

    private HotelCancelPolicy translateToObject(String jsonData) throws JSONException {
        HotelCancelPolicy hotelCancelPolicy = new HotelCancelPolicy();

        JSONObject objDat = new JSONObject(jsonData);

        hotelCancelPolicy.setHotelKey(objDat.optString("HotelKey"));
        hotelCancelPolicy.setSessionID(objDat.optString("SessionID"));
        hotelCancelPolicy.setMerchantID(objDat.optString("MerchantID"));
        hotelCancelPolicy.setRoomID(objDat.optString("RoomID"));
        hotelCancelPolicy.setDescription(objDat.optString("Description"));



        return hotelCancelPolicy;


    }


    //static method
    public static Map translateToParam(HotelCancelPolicyReq hotelCancelPolicyReq){
        Map param = new HashMap();

        param.put("username",AstriApiCaller.USERNAME);
        param.put("password", AstriApiCaller.PASSWORD);
        param.put("orgCode", AstriApiCaller.ORGCODE);
        param.put("merchantID", AstriApiCaller.MERCHANTID);

        param.put("sessionID", hotelCancelPolicyReq.getSessionID());
        param.put("hotelKey", hotelCancelPolicyReq.getHotelKey());
        param.put("roomID", hotelCancelPolicyReq.getRoomID());
   //     param.put("sessionID", AstriApiCaller.generateSessionID());


        return param;
    }

}
