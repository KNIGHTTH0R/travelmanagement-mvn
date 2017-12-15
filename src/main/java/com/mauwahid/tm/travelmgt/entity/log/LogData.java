package com.mauwahid.tm.travelmgt.entity.log;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Blob;

@MappedSuperclass
@Data
public class LogData extends AbstractEntity {

    //todo : log_hotel_search -> user_id, api_date, statusCode, message, jsonOf HotelSearchResponse

    @Column
    private Long userId;

    @Column
    private int statusCode;

    @Column
    private String message;

    @Column
    private String apiSessionKey;

    @Column(columnDefinition = "TEXT")
    private String jsonData;

}
