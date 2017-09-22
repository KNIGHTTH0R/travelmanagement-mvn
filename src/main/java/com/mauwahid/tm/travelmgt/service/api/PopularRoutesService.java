package com.mauwahid.tm.travelmgt.service.api;

import com.mauwahid.tm.travelmgt.domain.api.response.ResGetAirlines;
import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.api.response.trevohub.TrevoHubTemplateWithArray;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.entity.log.LogGetPopularRoutes;
import com.mauwahid.tm.travelmgt.repository.log.LogGetPopularRoutesRepo;
import com.mauwahid.tm.travelmgt.service.AuthService;
import com.mauwahid.tm.travelmgt.service.api.trevohub.ApiGetPopularRoutes;
import com.mauwahid.tm.travelmgt.utils.ApiStatic;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PopularRoutesService {

    @Autowired
    ApiGetPopularRoutes apiGetPopularRoutes;

    @Autowired
    Status status;

    LogGetPopularRoutes logGetPopularRoutes;

    @Autowired
    LogGetPopularRoutesRepo logGetPopularRoutesRepo;


    @Autowired
    AuthService authService;

    TrevoHubTemplateWithArray dTemplate;


    public Response load(HttpServletRequest request, String apiKey, int type){

        Response response = null;

        // authService = new AuthService();
        int idUser = authService.isAuthenticated(apiKey);


        if(idUser==0){
            authService.logError(apiKey,request.getRemoteAddr(), ApiStatic.API_FLIGHT,ApiStatic.FLIGHT_GET_POPULAR_ROUTES,"");
            return AuthService.authFailed();
        }


        response = getResponse(type);

        if(type == ApiStatic.FLIGHT_DOMESTIC){
            logGetPopularRoutes = new LogGetPopularRoutes(apiKey,idUser,"DOMESTIC",request.getRemoteHost());

        }else if(type == ApiStatic.FLIGHT_INTERNATIONAL)
        {
            logGetPopularRoutes = new LogGetPopularRoutes(apiKey,idUser,"INTERNATIONAL",request.getRemoteHost());
        }else if(type == ApiStatic.FLIGHT_ALL){
            logGetPopularRoutes = new LogGetPopularRoutes(apiKey,idUser,"ALL",request.getRemoteHost());

        }else
        {
            response = ApiStatic.GenErrorParam();
            logGetPopularRoutes = new LogGetPopularRoutes(apiKey,idUser,"WRONG_PARAM",request.getRemoteHost());

        }

        logGetPopularRoutesRepo.save(logGetPopularRoutes);
        return response;

    }

    private TrevoHubTemplateWithArray getData(int type){
        TrevoHubTemplateWithArray dTemplate = null;

        switch (type){
            case ApiStatic.FLIGHT_DOMESTIC :
                dTemplate = apiGetPopularRoutes.getDomestics();
                break;
            case ApiStatic.FLIGHT_INTERNATIONAL :
                dTemplate = apiGetPopularRoutes.getInternational();
                break;
            case ApiStatic.FLIGHT_ALL:
                dTemplate = apiGetPopularRoutes.getAllRoutes();
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




}
