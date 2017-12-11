package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelHotel;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelDetailReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelDetailResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelDetail;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelDetail;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelDetailInterface;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelDetail;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelDetailRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelDetailService {


    private HotelDetailInterface hotelDetailInterface;

    @Autowired
    private LogHotelDetailRepository logHotelDetailRepository;

    @Autowired
    private ApplicationContext context;


    public HotelDetailResponse getDetailHotel(long userId, HotelDetailReq hotelDetailReq){

        HotelDetailResponse response = new HotelDetailResponse();
        response.setStatus(StatusCode.NOT_IMPLEMENTED);
        response.setMessage(StatusCode.S_NOT_IMPLEMENTED);
        HotelHotel hotel;

        if(hotelDetailReq.getApiSource().equalsIgnoreCase(Common.API_TREVOHUB)){

            hotel = getTrevohub(hotelDetailReq);
            response = translateResponse(response, hotel);
        }

        if(hotelDetailReq.getApiSource().equalsIgnoreCase(Common.API_ASTRINDO)){
            hotel = getAstrindo(hotelDetailReq);
            response = translateResponse(response, hotel);
        }

        saveToLog(userId,response);

        return response;

    }

    private HotelHotel getTrevohub(HotelDetailReq hotelDetailReq){

        Map param = TrevoHotelDetail.translateToParam(hotelDetailReq);

        hotelDetailInterface = context.getBean(TrevoHotelDetail.class);
        HotelHotel trevoHubHotel = hotelDetailInterface.getDetailHotel(param);

        return trevoHubHotel;

    }

    private HotelHotel getAstrindo(HotelDetailReq hotelDetailReq){

        Map param = AstriHotelDetail.translateToParam(hotelDetailReq);

        hotelDetailInterface =  context.getBean(AstriHotelDetail.class);
        HotelHotel astriHotel = hotelDetailInterface.getDetailHotel(param);

        return astriHotel;

    }


    private HotelDetailResponse translateResponse(HotelDetailResponse hotelDetailResponse, HotelHotel hotel){
        String sessionId = Common.generateSessionID();

        hotelDetailResponse.setStatus(StatusCode.SUCCESS);
        hotelDetailResponse.setMessage(StatusCode.S_SUCCESS);

        hotelDetailResponse.setSessionKey(sessionId);
        hotelDetailResponse.setHotelResult(hotel);

        return hotelDetailResponse;
    }



    private void saveToLog(long userId, HotelDetailResponse hotelDetailResponse){

        //todo : log_hotel_search -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelDetailResponse);

        LogHotelDetail logHotelDetail = new LogHotelDetail();
        logHotelDetail.setUserId(userId);
        logHotelDetail.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logHotelDetail.setMessage(hotelDetailResponse.getMessage());
        logHotelDetail.setApiSessionKey(hotelDetailResponse.getSessionKey());

        logHotelDetailRepository.save(logHotelDetail);
    }
}
