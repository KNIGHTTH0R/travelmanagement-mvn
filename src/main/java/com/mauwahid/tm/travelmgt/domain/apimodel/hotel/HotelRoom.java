package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class HotelRoom {


    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("room_capacity")
    private String roomCapacity;

    @JsonProperty("room_information")
    private String roomInformation;

    private String description;

    private String currency;

  //  private Set<String> facilities;


    @JsonProperty("total_room")
    private String totalRoom;

    @JsonProperty("total_night")
    private String totalNight;

    @JsonProperty("room_type")
    private String roomType;


    @JsonProperty("price_per_rooom")
    private HotelPricePerRoom pricePerRoom;

    private HotelPriceTotal price;

    @JsonProperty("bed_types")
    private Set<HotelBedType> bedTypes;

    @JsonProperty("smooking_prefs")
    private Set<HotelSmookingPref> smookingPrefs;

    private Set<HotelImage> images;

}
