package com.mauwahid.tm.travelmgt.service.api.trevohub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrevoHubRequester {

    @Value("${trevohub.url}")
    public String url = "https://astrindotravel.astindohub.co.id/api/";

    @Value("${trevohub.b2b_code}")
    private String b2bCode = "AST100717ICANVO";

    @Value("${trevohub.h2h_code}")
    private String h2hCode = "HINF210717FUWX0";

    @Value("${trevohub.ip")
    private String ip = "127.0.0.1";


    public Map<String,String> getMapParams(){
        Map<String,String> params = new HashMap<>();

        params.put("b2b_code",b2bCode);
        params.put("h2h_code",h2hCode);


        return params;
    }

}
