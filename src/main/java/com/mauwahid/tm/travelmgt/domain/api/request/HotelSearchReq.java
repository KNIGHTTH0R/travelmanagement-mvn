package com.mauwahid.tm.travelmgt.domain.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class HotelSearchReq {

    @ApiModelProperty(example = "BKK")
    private String city;

   // @JsonProperty("destination_key")
  //  private String destinationKey = "";

    @JsonProperty("country_code")
    @ApiModelProperty(example = "TH")
    private String countryCode;

    @JsonProperty("nationality_code")
    @ApiModelProperty(example = "ID")
    private String bookerNationalityCode;

    @JsonProperty("check_in")
    @ApiModelProperty(example = "2017-12-01")
    private String checkIn;

    @JsonProperty("check_out")
    @ApiModelProperty(example = "2017-12-03")
    private String checkOut;

  //  private String room;

   /* private String limit;

    private String sort;

    @JsonProperty("total_adult")
    private String totalAdult;

    @JsonProperty("total_child")
    private String totalChild;*/

    @JsonProperty("api_source")
    private String[] apiSource;

 /*   @JsonProperty("booker_Nationality")
    private String bookerNationality;

    @JsonProperty("dest_country_code")
    private String destCountryCode; */

    @JsonProperty("single_room")
    @ApiModelProperty(example = "0")
    private String singleRoom;

    @JsonProperty("double_room")
    @ApiModelProperty(example = "1")
    private String doubleRoom;

    @JsonProperty("twin_room")
    @ApiModelProperty(example = "0")
    private String twinRoom;

    @JsonProperty("triple_room")
    @ApiModelProperty(example = "0")
    private String tripleRoom;

    @JsonProperty("quad_room")
    @ApiModelProperty(example = "0")
    private String quadRoom;




}
