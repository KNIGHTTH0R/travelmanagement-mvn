package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class CityList {

    private String cityCode;
    private Object mapCityCode;
    private String cityName;
    private String countryCode;
    private String countryName;
    private Integer latitude;
    private Integer longitude;
    private Object areas;
}
