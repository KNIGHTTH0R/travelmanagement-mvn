package com.mauwahid.tm.travelmgt.service.agent;

import com.mauwahid.tm.travelmgt.domain.api.old.ResGetCityHotel;
import com.mauwahid.tm.travelmgt.domain.api.old.Response;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.old.Status;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.hotel.ApiGetCityList;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelCityService {

    @Autowired
    ApiGetCityList apiGetCityList;

    @Autowired
    Status status;

    @Autowired
    AuthService authService;

    TrevoHubTemplateWithArray dTemplate;



    public Response load(String city){

        Response response = null;


        response = getResponse(city);


        return response;

    }

    private Response getResponse(String city){
        Response Response = null;

        dTemplate = getData(city);

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");

            ResGetCityHotel resGetCityHotel = new ResGetCityHotel(dTemplate.getData());

            Response = new Response(status,resGetCityHotel);

        }

        return Response;
    }


    private TrevoHubTemplateWithArray getData(String city){
        TrevoHubTemplateWithArray dTemplate = null;
        dTemplate = apiGetCityList.getCityList(city);

        return dTemplate;
    }



}
