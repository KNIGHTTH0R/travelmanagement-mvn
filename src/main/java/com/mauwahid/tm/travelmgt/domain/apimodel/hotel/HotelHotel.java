package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.Set;

@Data
public class HotelHotel {

   /* @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "status_code",
            "status_desc",
            "hotel_session_id",
            "api_source",
            "hotel_name",
            "city",
            "address",
            "description",
            "hotel_key",
            "hotel_id",
            "hotel_rate",
            "stars",
            "rating",
            ""

    }) */

    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("status_desc")
    private String statusDesc;

    @JsonProperty("hotel_session_id")
    private String sessionId;


    @JsonProperty("api_source")
    private String hotelAPI;

    @JsonProperty("hotel_name")
    private String hotelName;

    private String city;

    private String address;

    private String description;

    @JsonProperty("add_description")
    private String addDescription;

    @JsonProperty("hotel_key")
    private String hotelKey;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("hotel_id")
    private String hotelId;

    @JsonProperty("price_rate")
    private String priceRate;

    private String stars;

    private String rating;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("check_in")
    private String checkIn;

    @JsonProperty("check_out")
    private String checkOut;

    private String telephone;

    private String fax;

    private String email;

    private String website;

    private Set<HotelAvailableRoom> rooms;

    private HotelLocation location;

    private Set<HotelImage> images;

    @JsonProperty("hotel_facilities")
    private Set<HotelFacility> hotelFacilities;


    @JsonProperty("mg_rooms")
    private Set<MGRoom> mgRooms;



    @JsonProperty("additional_info")
    private HotelAddInfo addInfo;

}
