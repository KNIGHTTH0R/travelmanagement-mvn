package com.mauwahid.tm.travelmgt.service.agent.trevohub.hotel;

import com.mauwahid.tm.travelmgt.service.agent.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.agent.PostStdRequester;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.TrevoHubRequester;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ApiDetail extends TrevoHubRequester{

    private Map<String,String> params;

    public String callApi(String sessionId,
                          String hotelKey,
                          String hotelId,
                          String serviceId) throws IOException {
        String jsonResponse = "--";

        params = getMapParams();

        params.put("app","hotel");
        params.put("service_name","all");
        params.put("action","detail");
        params.put("session_id",sessionId);
        params.put("hotel_key",hotelKey);
        params.put("hotel_id",hotelId);
        params.put("service_id",serviceId );


        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }

}


