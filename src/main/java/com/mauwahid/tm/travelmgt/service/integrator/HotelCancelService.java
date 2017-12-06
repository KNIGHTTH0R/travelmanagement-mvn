package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelResult;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelCancel;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelCancel;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelCancelInterface;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelCancelRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HotelCancelService {

    private HotelCancelInterface hotelCancelInterface;

    @Autowired
    private LogHotelCancelRepository logHotelCancelRepository;

    public HotelCancelResponse cancelHotel(long userId, HotelCancelReq hotelCancelReq){

        HotelCancelResponse hotelCancelResponse = new HotelCancelResponse();
        hotelCancelResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
        hotelCancelResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        if(hotelCancelReq.getApiSource().equalsIgnoreCase(Common.API_ASTRINDO)){
            HotelCancelResult hotelCancelResult = cancelAsri(hotelCancelReq);
            return translateResponse(hotelCancelResult);
        }

        saveToLog(userId, hotelCancelResponse);


        return hotelCancelResponse;

    }



    private HotelCancelResult cancelAsri(HotelCancelReq hotelCancelReq){

        Map param = AstriHotelCancel.translateToParam(hotelCancelReq);

        hotelCancelInterface = new AstriHotelCancel();

        HotelCancelResult hotelCancelResult = hotelCancelInterface.cancelHotel(param);


        return hotelCancelResult;

    }


    private HotelCancelResponse translateResponse(HotelCancelResult hotelCancelResult){
        HotelCancelResponse hotelCancelResponse = new HotelCancelResponse();

        hotelCancelResponse.setHotelCancelResult(hotelCancelResult);

        return hotelCancelResponse;
    }

    private void saveToLog(long userId, HotelCancelResponse hotelCancelResponse){

        //todo : log save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelCancelResponse);

        LogHotelCancel logData = new LogHotelCancel();
        logData.setUserId(userId);
        logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setMessage(hotelCancelResponse.getMessage());
       // logData.setApiSessionKey(hotelCancelResponse.getS);

        logHotelCancelRepository.save(logData);
    }
}
