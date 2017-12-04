package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelChangePriceReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelChangePriceResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelChangePriceResult;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelChangePrice;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HotelChangePriceService {

    @Autowired
    private TrevoHotelBook trevoHotelBook;

    @Autowired
    private AstriHotelChangePrice astriHotelChangePrice;


    public HotelChangePriceResponse changePrice(HotelChangePriceReq hotelCancelReq){

        HotelChangePriceResponse hotelCancelResponse = new HotelChangePriceResponse();
        hotelCancelResponse.setStatus("2");
        hotelCancelResponse.setMessage("Not Implemented");

        if(hotelCancelReq.getApiSource().equalsIgnoreCase("astrindo")){
            HotelChangePriceResult hotelChangePriceResult = changePriceAstri(hotelCancelReq);
            return translateResponse(hotelChangePriceResult);

        }


        return hotelCancelResponse;

    }



    private HotelChangePriceResult changePriceAstri(HotelChangePriceReq hotelChangePriceReq){

        Map param = AstriHotelChangePrice.translateToParam(hotelChangePriceReq);


        return astriHotelChangePrice.changePrice(param);

    }


    private HotelChangePriceResponse translateResponse(HotelChangePriceResult hotelChangePriceResult){
        HotelChangePriceResponse hotelChangePriceResponse = new HotelChangePriceResponse();

        hotelChangePriceResponse.setHotelChangePriceResult(hotelChangePriceResult);

        return hotelChangePriceResponse;
    }
}
