package com.mauwahid.tm.travelmgt.service.integrator.hotel.v1;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelSearch;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.v1.AstriHotelAvailability;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelSearchInterface;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelSearchRepo;
import com.mauwahid.tm.travelmgt.service.integrator.hotel.iface.HotelSearchServiceInterface;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@Qualifier("v1")
public class HotelSearchService{



    @Autowired
    private LogHotelSearchRepo logHotelSearchRepo;


    private HotelSearchInterface hotelSearchInterface;

    @Autowired
    private ApplicationContext context;



    public HotelSearchResponse searchHotel(long userId, HotelSearchReq hotelSearchReq){

        HotelSearchResponse response = new HotelSearchResponse();

        if(!Common.isApiListChecked(hotelSearchReq.getApiSource())){
            response.setStatus(StatusCode.BAD_REQUEST);
            response.setMessage(StatusCode.S_API_NOT_FOUND);

            saveToLog(userId,response);

            return response;
        }

        Set<HotelHotel> hotels = agregate(hotelSearchReq);

        response = translateResponse(response, hotels);

        saveToLog(userId,response);

        return response;

    }

    private Set<HotelHotel> agregate(HotelSearchReq hotelSearchReq){


        Map param;
        Set<String> apiSources = new HashSet<>(Arrays.asList(hotelSearchReq.getApiSource()));

        Set<HotelHotel> hotels = new HashSet<>();

        if(apiSources.contains(Common.API_ASTRINDO)){
           // hotelSearchInterface = new AstriHotelAvailability();
            hotelSearchInterface = context.getBean(AstriHotelAvailability.class);
            param = AstriHotelAvailability.translateToParam(hotelSearchReq);
            hotels = hotelSearchInterface.searchHotel(param);
        }


        return hotels;

    }



    private HotelSearchResponse translateResponse(HotelSearchResponse hotelSearchResponse, Set<HotelHotel> hotels){
        String sessionId = Common.generateSessionID();

        hotelSearchResponse.setSessionKey(sessionId);
        hotelSearchResponse.setHotels(hotels);

        hotelSearchResponse.setStatus(StatusCode.SUCCESS);
        hotelSearchResponse.setMessage(StatusCode.S_SUCCESS);

        if(hotels.isEmpty()){
            hotelSearchResponse.setStatus(StatusCode.NO_CONTENT);
            hotelSearchResponse.setMessage(StatusCode.S_NO_CONTENT);
        }

        return hotelSearchResponse;
    }


    private void saveToLog(long userId, HotelSearchResponse hotelSearchResponse){

         //todo : log_save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelSearchResponse);

        LogHotelSearch logHotelSearch = new LogHotelSearch();
        logHotelSearch.setUserId(userId);
      //  logHotelSearch.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
     //   logHotelSearch.setJsonData(jsonData);
        logHotelSearch.setMessage(hotelSearchResponse.getMessage());
        logHotelSearch.setStatusCode(hotelSearchResponse.getStatus());
        logHotelSearch.setApiSessionKey(hotelSearchResponse.getSessionKey());

        logHotelSearchRepo.save(logHotelSearch);
    }
}
