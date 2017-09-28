package com.mauwahid.tm.travelmgt.service.api.trevohub.hotel;

import com.mauwahid.tm.travelmgt.service.api.IHttpRequester;
import com.mauwahid.tm.travelmgt.service.api.PostStdRequester;
import com.mauwahid.tm.travelmgt.service.api.trevohub.TrevoHubRequester;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ApiIssue extends TrevoHubRequester {

    private Map<String,String> params;

    public String callApi(String hotelDataId,
                          String paymentType,
                          String invoiceNo) throws IOException {
        String jsonResponse = "";

        params = getMapParams();

        params.put("app","hotel");
        params.put("action","issue");
        params.put("hotel_data_id",hotelDataId);
        params.put("payment_type",paymentType);
        params.put("invoice_no",invoiceNo);

        IHttpRequester requester = new PostStdRequester();
        jsonResponse = requester.sendRequest(url,params);
        return jsonResponse;
    }
}
