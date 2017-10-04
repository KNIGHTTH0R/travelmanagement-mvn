package com.mauwahid.tm.travelmgt.service.agent;

import com.mauwahid.tm.travelmgt.domain.api.response.ResGetAirlines;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.entity.log.LogGetAirlines;
import com.mauwahid.tm.travelmgt.repository.log.LogGetAirlinesRepo;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.agent.trevohub.ApiGetAirlines;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AirlinesService {


    @Autowired
    ApiGetAirlines apiGetAirlines;

    @Autowired
    Status status;

    LogGetAirlines logGetAirlines;

    @Autowired
    LogGetAirlinesRepo logGetAirlinesRepo;


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
            logGetAirlines = new LogGetAirlines(apiKey,idUser,"DOMESTIC",request.getRemoteHost());

        }else if(type == ApiStatic.FLIGHT_INTERNATIONAL)
        {
            logGetAirlines = new LogGetAirlines(apiKey,idUser,"INTERNATIONAL",request.getRemoteHost());
        }else if(type == ApiStatic.FLIGHT_ALL){
            logGetAirlines = new LogGetAirlines(apiKey,idUser,"ALL",request.getRemoteHost());

        }else
        {
            response = ApiStatic.GenErrorParam();
            logGetAirlines = new LogGetAirlines(apiKey,idUser,"WRONG_PARAM",request.getRemoteHost());

        }

        logGetAirlinesRepo.save(logGetAirlines);
        return response;

    }

    private TrevoHubTemplateWithArray getData(int type){
        TrevoHubTemplateWithArray dTemplate = null;

       switch (type){
           case ApiStatic.FLIGHT_DOMESTIC :
                dTemplate = apiGetAirlines.getDomestics();
                break;
           case ApiStatic.FLIGHT_INTERNATIONAL :
                dTemplate = apiGetAirlines.getInternational();
                break;
           case ApiStatic.FLIGHT_ALL:
                dTemplate = apiGetAirlines.getAllAirlines();
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

            ResGetAirlines resGetAirlines = new ResGetAirlines(dTemplate.getData());

            Response = new Response(status,resGetAirlines);

        }

        return Response;
    }



    private Response getInternationalApi(){

        Response Response = null;

        dTemplate = apiGetAirlines.getInternational();

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");

            ResGetAirlines resGetAirlines = new ResGetAirlines(dTemplate.getData());

            Response = new Response(status,resGetAirlines);

        }

        return Response;

    }

    private Response getAllAirlines(String apiKey){

        Response Response = null;

        dTemplate = apiGetAirlines.getAllAirlines();

        if(!dTemplate.getErrorCode().equals("0")){
            status.setStatusId(StatusCode.API_ERROR);
            status.setStatusDesc("ERROR : "+dTemplate.getErrorDesc());

            Response = new Response(status);
        }else{

            status.setStatusId(StatusCode.SUCCESS);
            status.setStatusDesc("SUCCESS");
            ResGetAirlines resGetAirlines = new ResGetAirlines(dTemplate.getData());

            Response = new Response(status,resGetAirlines);

        }

        return Response;

    }

}
