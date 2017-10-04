package com.mauwahid.tm.travelmgt.service.agent.pointer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PointerRequester {

    @Value("${pointer.url}")
    public String uri = "http://54.169.218.195:8989/sandbox/";


    @Value("${pointer.token}")
    public String authToken = "7cbe43b2721111e7ab0106a69b186ee1";




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
