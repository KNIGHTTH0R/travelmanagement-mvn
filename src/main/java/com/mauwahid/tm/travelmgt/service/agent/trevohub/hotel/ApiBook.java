package com.mauwahid.tm.travelmgt.service.agent.trevohub.hotel;

import com.mauwahid.tm.travelmgt.service.agent.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.agent.PostStdRequester;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.TrevoHubRequester;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ApiBook extends TrevoHubRequester {

    private Map<String,String> params;

    public String callApi(String sessionId,
                          String roomId,
                          String bedTypeId,
                          String smokingPref,
                          String customerFirstName,
                          String customerLastName,
                          String customerPhone,
                          String customerEmail,
                          String customerIdNum,
                          String extService,
                          String totalMargin) throws IOException {
        String jsonResponse = "";

        params = getMapParams();

        params.put("app","hotel");
        params.put("action","book");
        params.put("session_id",sessionId);
        params.put("room_id",roomId);
        params.put("bed_type_id",bedTypeId);
        params.put("smoking_pref",smokingPref );
        params.put("customer[first_name]", customerFirstName);
        params.put("customer[last_name]", customerLastName);
        params.put("customer[phone]",customerPhone);
        params.put("customer[email]", customerEmail);
        params.put("customer[id_number]", customerIdNum);
        params.put("external_service", extService);
        params.put("margin", totalMargin);


        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }

}
