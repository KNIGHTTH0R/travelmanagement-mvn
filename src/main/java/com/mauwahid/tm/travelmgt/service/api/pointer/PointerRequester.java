package com.mauwahid.tm.travelmgt.service.api.pointer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PointerRequester {

    @Value("${pointer.url}")
    public String uri;


    @Value("${pointer.token}")
    public String authToken;




    public Map<String,String> getMapParams(){
        Map<String,String> params = new HashMap<>();


        return params;
    }

    public Map<String,String> getHeaderParams(){
        Map<String,String> headers = new HashMap<>();

        headers.put("token",authToken);

        return headers;
    }




}
