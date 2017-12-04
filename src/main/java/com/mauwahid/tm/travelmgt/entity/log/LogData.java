package com.mauwahid.tm.travelmgt.entity.log;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LogData extends AbstractEntity {

    //todo : log_hotel_search -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

    private Long userId;

    private String statusCode;

    private String message;

    private String jsonData;

}
