package com.mauwahid.tm.travelmgt.domain.api.request.hotel.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
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


    @JsonProperty("destination_code")
    @ApiModelProperty(example = "f6cc2187dea984c9f16a1c948c3c5222")
    private String destinationKey;

  //  private String room;

   /* private String limit;

    private String sort;

    @JsonProperty("total_adult")
    private String totalAdult;

    @JsonProperty("total_child")
    private String totalChild;*/

    @JsonProperty("api_source")
    private String[] apiSource;

    @JsonProperty("bed_type")
    @ApiModelProperty(example = "0")
    private String bedType;

    @JsonProperty("num_of_result")
    private int numberOfResult;

    @JsonProperty("page")
    private int page;






}
