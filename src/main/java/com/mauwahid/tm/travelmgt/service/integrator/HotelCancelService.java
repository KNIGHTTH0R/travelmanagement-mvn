package com.mauwahid.tm.travelmgt.service.integrator;

import com.mauwahid.tm.travelmgt.domain.api.request.HotelCancelReq;
import com.mauwahid.tm.travelmgt.domain.api.response.HotelCancelResponse;
import com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.HotelCancelResult;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.AstriHotelCancel;
import com.mauwahid.tm.travelmgt.repository.api.trevohub.TrevoHotelBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HotelCancelService {

    @Autowired
    private TrevoHotelBook trevoHotelBook;

    @Autowired
    private AstriHotelCancel astriHotelCancel;


    public HotelCancelResponse cancelHotel(HotelCancelReq hotelCancelReq){

        HotelCancelResponse hotelCancelResponse = new HotelCancelResponse();
        hotelCancelResponse.setStatus("2");
        hotelCancelResponse.setMessage("Not Implemented");

        if(hotelCancelReq.getApiSource().equalsIgnoreCase("astrindo")){
            HotelCancelResult hotelCancelResult = cancelAsri(hotelCancelReq);
            return translateResponse(hotelCancelResult);

        }


        return hotelCancelResponse;

    }



    private HotelCancelResult cancelAsri(HotelCancelReq hotelCancelReq){

        Map param = AstriHotelCancel.translateToParam(hotelCancelReq);


        HotelCancelResult hotelCancelResult = astriHotelCancel.cancelHotel(param);


        return hotelCancelResult;

    }


    private HotelCancelResponse translateResponse(HotelCancelResult hotelCancelResult){
        HotelCancelResponse hotelCancelResponse = new HotelCancelResponse();

        hotelCancelResponse.setHotelCancelResult(hotelCancelResult);

        return hotelCancelResponse;
    }
}
