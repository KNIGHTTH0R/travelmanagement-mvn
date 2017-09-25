package com.mauwahid.tm.travelmgt.service.api.trevohub.hotel;

import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.service.api.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.api.PostStdRequester;
import com.mauwahid.tm.travelmgt.service.api.trevohub.TrevoHubRequester;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ApiSearchHotel extends TrevoHubRequester {


    private Map<String,String> params;

    public String callApi(String city,
                          String sortBy,
                          String destinationKey,
                          String countryCode,
                          String checkIn,
                          String checkOut,
                          String totalAdult,
                          String totalChild,
                          String limit) throws IOException {
        String jsonResponse = "--";

        params = getMapParams();

        params.put("app","hotel");
        params.put("service_name","all");
        params.put("action","search");
        params.put("city",city);
        params.put("sort",sortBy);
        params.put("destination_key",destinationKey);
        params.put("country_code",countryCode );
        params.put("check_in", checkIn);
        params.put("check_out",checkOut);
        params.put("room[0][total_adult]",totalAdult);
        params.put("room[0][total_child]",totalChild);
        params.put("limit", limit);


        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }




}
