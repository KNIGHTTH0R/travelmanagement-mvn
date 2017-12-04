package com.mauwahid.tm.travelmgt.utils;

import com.mauwahid.tm.travelmgt.entity.log.error.LogErrorAuth;
import com.mauwahid.tm.travelmgt.entity.log.error.LogErrorHotelSearch;
import com.mauwahid.tm.travelmgt.repository.database.log.error.LogErrorAuthRepository;
import com.mauwahid.tm.travelmgt.repository.database.log.error.LogErrorHotelSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LogErrorHelper {


    @Autowired
    private LogErrorAuthRepository logErrorAuthRepository;

    @Autowired
    private LogErrorHotelSearchRepository logErrorHotelSearchRepository;





    public static LogErrorHelper getInstance(){
        return new LogErrorHelper();
    }


    public void saveAuthError(String authHeader){

        LogErrorAuth logErrorAuth = new LogErrorAuth();
        logErrorAuth.setApiKey(authHeader);

        logErrorAuthRepository.save(logErrorAuth);
    }


    public void saveErrorExc(String apiType, String ex, String params, String jsonData ){
        switch (apiType){
            case ApiStatic.API_HOTEL_SEARCH :

                LogErrorHotelSearch logErrorHotelSearch = new LogErrorHotelSearch();
                logErrorHotelSearch.setExceptionData(ex);
                logErrorHotelSearch.setJsonData(jsonData);
                logErrorHotelSearch.setParams(params);

                logErrorHotelSearchRepository.save(logErrorHotelSearch);
                break;
            default:
                    break;
        }


    }


}
