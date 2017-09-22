package com.mauwahid.tm.travelmgt.service;

import com.mauwahid.tm.travelmgt.domain.api.response.ContohData;
import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiGetAirlines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ContohService {

    Response response;

    @Autowired
    ContohData contohData;

    @Autowired
    Status status;

    @Autowired
    ApiGetAirlines getAirlines;

    public Response getResponse(){


        contohData.setNama("Maulana");
        contohData.setAlamat("Bandung");

        status.setStatusId(200);
        status.setStatusDesc("SUCCESS");

        ArrayList<IData> list = new ArrayList<>();

        list.add(contohData);

        response = new Response(status);

        return response;

    }


}
