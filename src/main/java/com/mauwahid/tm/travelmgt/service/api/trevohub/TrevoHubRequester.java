package com.mauwahid.tm.travelmgt.service.api.trevohub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrevoHubRequester {

    @Value("${trevohub.url}")
    public String url;

    @Value("${trevohub.b2b_code}")
    private String b2bCode;

    @Value("${trevohub.h2h_code}")
    private String h2hCode;

    @Value("${trevohub.ip")
    private String ip;


    public Map<String,String> getMapParams(){
        Map<String,String> params = new HashMap<>();

        params.put("b2b_code",b2bCode);
        params.put("h2h_code",h2hCode);


        return params;
    }

}
