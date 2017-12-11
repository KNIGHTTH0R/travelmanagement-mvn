package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelChangePriceResult;
import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelChangePriceResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelChangePrice;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelChangePrice;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelChangePriceInterface;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelChangePriceRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HotelChangePriceService {


    private HotelChangePriceInterface hotelChangePriceInterface;


    @Autowired
    private LogHotelChangePriceRepository logHotelChangePriceRepository;

    @Autowired
    private ApplicationContext context;


    public HotelChangePriceResponse changePrice(long userId, HotelChangePriceReq hotelCancelReq){

        HotelChangePriceResponse hotelChangePriceResponse = new HotelChangePriceResponse();
        hotelChangePriceResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
        hotelChangePriceResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        if(hotelCancelReq.getApiSource().equalsIgnoreCase(Common.API_ASTRINDO)){
            HotelChangePriceResult hotelChangePriceResult = changePriceAstri(hotelCancelReq);
            return translateResponse(hotelChangePriceResult);

        }

        saveToLog(userId, hotelChangePriceResponse);
        return hotelChangePriceResponse;

    }



    private HotelChangePriceResult changePriceAstri(HotelChangePriceReq hotelChangePriceReq){

        Map param = AstriHotelChangePrice.translateToParam(hotelChangePriceReq);

        hotelChangePriceInterface = context.getBean(AstriHotelChangePrice.class);
        HotelChangePriceResult hotelChangePriceResult = hotelChangePriceInterface.changePrice(param);

        return hotelChangePriceResult;

    }


    private HotelChangePriceResponse translateResponse(HotelChangePriceResult hotelChangePriceResult){
        HotelChangePriceResponse hotelChangePriceResponse = new HotelChangePriceResponse();

        hotelChangePriceResponse.setHotelChangePriceResult(hotelChangePriceResult);

        return hotelChangePriceResponse;
    }

    private void saveToLog(long userId, HotelChangePriceResponse hotelChangePriceResponse){

        //todo : log save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelChangePriceResponse);

        LogHotelChangePrice logData = new LogHotelChangePrice();
        logData.setUserId(userId);
        logData.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logData.setMessage(hotelChangePriceResponse.getMessage());
        // logData.setApiSessionKey(hotelCancelResponse.getS);

        logHotelChangePriceRepository.save(logData);
    }
}
