package com.mauwahid.tm.travelmgt.utils;

import com.mauwahid.tm.travelmgt.entity.log.error.*;
import com.mauwahid.tm.travelmgt.repository.database.log.error.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

@Component
public class LogErrorHelper {


    @Autowired
    private LogErrorAuthRepository logErrorAuthRepository;

    @Autowired
    private LogErrorHotelSearchRepository logErrorHotelSearchRepository;

    @Autowired
    private LogErrorHotelDetailRepository logErrorHotelDetailRepository;

    @Autowired
    private LogErrorHotelBookRepository logErrorHotelBookRepository;

    @Autowired
    private LogErrorHotelCancelRepository logErrorHotelCancelRepository;


    public static LogErrorHelper getInstance(){
        return new LogErrorHelper();
    }


    public void saveAuthError(String authHeader, String apiName, String apiType, String description, String ipAddress){

        LogErrorAuth logErrorAuth = new LogErrorAuth();
        logErrorAuth.setApiKey(authHeader);
        logErrorAuth.setApiName(apiName);
        logErrorAuth.setApiType(apiType);
        logErrorAuth.setDescription(LogErrorHelper.convertStringToBlob(description));
        logErrorAuth.setIpAddress(ipAddress);

        logErrorAuthRepository.save(logErrorAuth);
    }


    public void saveErrorExc(String apiType, String ex, String params, String jsonData ){
        switch (apiType){
            case ApiStatic.API_HOTEL_SEARCH :

                LogErrorHotelSearch logErrorHotelSearch = new LogErrorHotelSearch();
                logErrorHotelSearch.setExceptionData(ex);
                logErrorHotelSearch.setJsonData(convertStringToBlob(jsonData));
                logErrorHotelSearch.setParams(convertStringToBlob(params));

                logErrorHotelSearchRepository.save(logErrorHotelSearch);
                break;
            case ApiStatic.API_HOTEL_DETAIL :

                LogErrorHotelDetail logErrorHotelDetail = new LogErrorHotelDetail();
                logErrorHotelDetail.setExceptionData(ex);
                logErrorHotelDetail.setJsonData(convertStringToBlob(jsonData));
                logErrorHotelDetail.setParams(convertStringToBlob(params));

                logErrorHotelDetailRepository.save(logErrorHotelDetail);
                break;

            case ApiStatic.API_HOTEL_BOOK :

                LogErrorHotelBook logErrorHotelBook = new LogErrorHotelBook();
                logErrorHotelBook.setExceptionData(ex);
                logErrorHotelBook.setJsonData(convertStringToBlob(jsonData));
                logErrorHotelBook.setParams(convertStringToBlob(params));

                logErrorHotelBookRepository.save(logErrorHotelBook);
                break;

            case ApiStatic.API_HOTEL_CANCEL :

                LogErrorHotelCancel logErrorHotelCancel = new LogErrorHotelCancel();
                logErrorHotelCancel.setExceptionData(ex);
                logErrorHotelCancel.setJsonData(convertStringToBlob(jsonData));
                logErrorHotelCancel.setParams(convertStringToBlob(params));

                logErrorHotelCancelRepository.save(logErrorHotelCancel);
                break;


            default:
                    break;
        }


    }

    public static Blob convertStringToBlob(String data){
        Blob blob = null;

        try{
            blob = new SerialBlob(data.getBytes());
        }catch (Exception ex){
            //failed blob creation
        }

        return blob;
    }

}
