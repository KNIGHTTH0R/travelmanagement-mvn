package com.mauwahid.tm.travelmgt.service.api;

import com.mauwahid.tm.travelmgt.domain.api.response.ResGetAirlines;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.entity.log.LogGetRoutes;
import com.mauwahid.tm.travelmgt.repository.log.LogGetRoutesRepo;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiGetRoutes;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RouteService {
    @Autowired
    ApiGetRoutes apiGetRoutes;

    @Autowired
    Status status;

    LogGetRoutes logGetRoutes;

    @Autowired
    LogGetRoutesRepo logGetRoutesRepo;


    @Autowired
    AuthService authService;

    TrevoHubTemplateWithArray dTemplate;


    public Response load(HttpServletRequest request, String apiKey, String airportCode, int type){

        Response response = null;

        // authService = new AuthService();
        int idUser = authService.isAuthenticated(apiKey);


        if(idUser==0){
            authService.logError(apiKey,request.getRemoteAddr(), ApiStatic.API_FLIGHT,ApiStatic.FLIGHT_GET_ROUTES,"");
            return AuthService.authFailed();
        }


        response = getResponse(airportCode,type);

        if(type == ApiStatic.FLIGHT_DOMESTIC){
            logGetRoutes = new LogGetRoutes(apiKey,idUser,"DOMESTIC",request.getRemoteHost());

        }else if(type == ApiStatic.FLIGHT_INTERNATIONAL)
        {
            logGetRoutes = new LogGetRoutes(apiKey,idUser,"INTERNATIONAL",request.getRemoteHost());
        }else if(type == ApiStatic.FLIGHT_ALL){
            logGetRoutes = new LogGetRoutes(apiKey,idUser,"ALL",request.getRemoteHost());

        }else
        {
            response = ApiStatic.GenErrorParam();
            logGetRoutes = new LogGetRoutes(apiKey,idUser,"WRONG_PARAM",request.getRemoteHost());

        }

        logGetRoutesRepo.save(logGetRoutes);
        return response;

    }

    private TrevoHubTemplateWithArray getData(String airportCode, int type){
        TrevoHubTemplateWithArray dTemplate = null;

        switch (type){
            case ApiStatic.FLIGHT_DOMESTIC :
                dTemplate = apiGetRoutes.getDomestics(airportCode);
                break;
            case ApiStatic.FLIGHT_INTERNATIONAL :
                dTemplate = apiGetRoutes.getInternational(airportCode);
                break;
            case ApiStatic.FLIGHT_ALL:
                dTemplate = apiGetRoutes.getAllRoutes(airportCode);
                break;

        }

        return dTemplate;
    }

    private Response getResponse(String airportCode,int type){
        Response Response = null;

        dTemplate = getData(airportCode, type);

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



    private Response getInternationalApi(String airportCode){

        Response Response = null;

        dTemplate = apiGetRoutes.getInternational(airportCode);

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
