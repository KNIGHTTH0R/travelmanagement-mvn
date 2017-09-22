package com.mauwahid.tm.travelmgt.service.api;

import com.mauwahid.tm.travelmgt.domain.api.response.ResGetAirport;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.entity.log.LogGetAirports;
import com.mauwahid.tm.travelmgt.repository.log.LogGetAirportsRepo;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiGetAirlines;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AirportService {

    @Autowired
    ApiGetAirlines apiGetAirport;

    @Autowired
    Status status;

    LogGetAirports logGetAirports;

    @Autowired
    LogGetAirportsRepo logGetAirportsRepo;


    @Autowired
    AuthService authService;

    TrevoHubTemplateWithArray dTemplate;


    public Response load(HttpServletRequest request, String apiKey, int type){

        Response response = null;

        // authService = new AuthService();
        int idUser = authService.isAuthenticated(apiKey);


        if(idUser==0){
            authService.logError(apiKey,request.getRemoteAddr(), ApiStatic.API_FLIGHT,ApiStatic.FLIGHT_GET_AIRLINES,"");
            return AuthService.authFailed();
        }


        response = getResponse(type);

        if(type == ApiStatic.FLIGHT_DOMESTIC){
            logGetAirports = new LogGetAirports(apiKey,idUser,"DOMESTIC",request.getRemoteHost());

        }else if(type == ApiStatic.FLIGHT_INTERNATIONAL)
        {
            logGetAirports = new LogGetAirports(apiKey,idUser,"INTERNATIONAL",request.getRemoteHost());
        }else if(type == ApiStatic.FLIGHT_ALL){
            logGetAirports = new LogGetAirports(apiKey,idUser,"ALL",request.getRemoteHost());

        }else
        {
            response = ApiStatic.GenErrorParam();
            logGetAirports = new LogGetAirports(apiKey,idUser,"WRONG_PARAM",request.getRemoteHost());

        }

        logGetAirportsRepo.save(logGetAirports);
        return response;

    }

    private TrevoHubTemplateWithArray getData(int type){
        TrevoHubTemplateWithArray dTemplate = null;

        switch (type){
            case ApiStatic.FLIGHT_DOMESTIC :
                dTemplate = apiGetAirport.getDomestics();
                break;
            case ApiStatic.FLIGHT_INTERNATIONAL :
                dTemplate = apiGetAirport.getInternational();
                break;
            case ApiStatic.FLIGHT_ALL:
                dTemplate = apiGetAirport.getAllAirlines();
                break;

        }

        return dTemplate;
    }

    private Response getResponse(int type){
        Response Response = null;

        dTemplate = getData(type);

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");

            ResGetAirport resGetAirport = new ResGetAirport(dTemplate.getData());

            Response = new Response(status,resGetAirport);

        }

        return Response;
    }



    private Response getInternationalApi(){

        Response Response = null;

        dTemplate = apiGetAirport.getInternational();

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");

            ResGetAirport resGetAirport = new ResGetAirport(dTemplate.getData());

            Response = new Response(status,resGetAirport);

        }

        return Response;

    }

    private Response getAllAirlines(String apiKey){

        Response Response = null;

        dTemplate = apiGetAirport.getAllAirlines();

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");
            ResGetAirport resGetAirport = new ResGetAirport(dTemplate.getData());

            Response = new Response(status,resGetAirport);

        }

        return Response;

    }

}
