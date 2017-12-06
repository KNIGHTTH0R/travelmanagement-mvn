package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelSearchReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelSearchResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelSearch;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelAvailability;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelSearchInterface;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelSearchRepo;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class HotelSearchService {



    @Autowired
    private LogHotelSearchRepo logHotelSearchRepo;


    private HotelSearchInterface hotelSearchInterface;



    public HotelSearchResponse searchHotel(long userId, HotelSearchReq hotelSearchReq){

        Set<HotelHotel> hotels = agregate(hotelSearchReq);

        HotelSearchResponse response = translateResponse(hotels);

        saveToLog(userId,response);

        return response;

    }

    private Set<HotelHotel> agregate(HotelSearchReq hotelSearchReq){


        Map param;
        Set<String> apiSources = new HashSet<>(Arrays.asList(hotelSearchReq.getApiSource()));

        Set<HotelHotel> hotels = new HashSet<>();

        if(apiSources.contains(Common.API_ASTRINDO)){
            hotelSearchInterface = new AstriHotelAvailability();
            param = AstriHotelAvailability.translateToParam(hotelSearchReq);
            hotels = hotelSearchInterface.searchHotel(param);
        }


        return hotels;

    }



    private HotelSearchResponse translateResponse(Set<HotelHotel> hotels){
        HotelSearchResponse hotelSearchResponse = new HotelSearchResponse();
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
        logHotelSearch.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logHotelSearch.setMessage(hotelSearchResponse.getMessage());
        logHotelSearch.setStatusCode(hotelSearchResponse.getStatus());
        logHotelSearch.setApiSessionKey(hotelSearchResponse.getSessionKey());

        logHotelSearchRepo.save(logHotelSearch);
    }
}
