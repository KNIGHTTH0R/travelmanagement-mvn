package com.mauwahid.tm.travelmgt.service.integrator.hotel.v2;

import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelBookInfo;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.HotelBookResult;
import com.mauwahid.tm.travelmgt.domain.api.request.hotel.v1.HotelBookReq;
import com.mauwahid.tm.travelmgt.domain.api.response.hotel.v1.HotelBookResponse;
import com.mauwahid.tm.travelmgt.entity.log.LogHotelBook;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.v1.AstriHotelBook;
import com.mauwahid.tm.travelmgt.repository.api.interfaces.HotelBookInterface;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import com.mauwahid.tm.travelmgt.repository.database.log.LogHotelBookRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HotelBookService {


    private HotelBookInterface hotelBookInterface;

    @Autowired
    private LogHotelBookRepository logHotelBookRepository;

    @Autowired
    private ApplicationContext context;


    public HotelBookResponse bookHotel(long userId, HotelBookReq hotelBookReq){

        HotelBookResponse hotelBookResponse = new HotelBookResponse();
        hotelBookResponse.setStatus(StatusCode.NOT_IMPLEMENTED);
        hotelBookResponse.setMessage(StatusCode.S_NOT_IMPLEMENTED);

        if(hotelBookReq.getApiSource().equalsIgnoreCase(Common.API_ASTRINDO)){
            HotelBookResult hotelBookResult = bookAstri(hotelBookReq);
            hotelBookResponse = translateResponse(hotelBookResponse, hotelBookResult);
        }

        saveToLog(userId, hotelBookResponse);

        return hotelBookResponse;

    }

    private HotelBookInfo bookTrevo(HotelBookReq hotelBookReq){

        Map param = TrevoHotelBook.translateToParam(hotelBookReq);

       // hotelBookInterface = new TrevoHotelBook();

      //  HotelBookInfo hotelBookInfo = hotelBookInterface.bookHotel(param);

        return null;

    }

    private HotelBookResult bookAstri(HotelBookReq hotelBookReq){

        Map param = AstriHotelBook.translateToParam(hotelBookReq);

       // hotelBookInterface = new AstriHotelBook();

        hotelBookInterface = context.getBean(AstriHotelBook.class);
        HotelBookResult hotelBookResult = hotelBookInterface.bookHotel(param);

        return hotelBookResult;

    }


    private HotelBookResponse translateResponse(HotelBookResponse hotelBookResponse, HotelBookResult hotelBookResult){



        hotelBookResponse.setStatus(StatusCode.SUCCESS);
        hotelBookResponse.setMessage(StatusCode.S_SUCCESS);

        if(hotelBookResult.getMessageCode().equalsIgnoreCase(StatusCode.STATUS_BOOK_ERROR_NULL_OR_FAILED_POLICY)){
            hotelBookResponse.setStatus(StatusCode.ERROR_API);
            hotelBookResponse.setMessage(StatusCode.S_ERROR_API);
        }

        hotelBookResponse.setBookResult(hotelBookResult);

        return hotelBookResponse;
    }

    private void saveToLog(long userId, HotelBookResponse hotelBookResponse){

        //todo : log save -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

        String jsonData = Common.generateJSONFromObject(hotelBookResponse);

        LogHotelBook logHotelBook = new LogHotelBook();
        logHotelBook.setUserId(userId);
      //  logHotelBook.setJsonData(LogErrorHelper.convertStringToBlob(jsonData));
        logHotelBook.setJsonData(jsonData);
        logHotelBook.setMessage(hotelBookResponse.getMessage());
      //  logHotelBook.setApiSessionKey(hotelBookResponse.get());

        logHotelBookRepository.save(logHotelBook);
    }
}
