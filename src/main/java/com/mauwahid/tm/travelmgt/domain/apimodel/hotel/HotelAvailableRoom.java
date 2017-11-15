package com.mauwahid.tm.travelmgt.domain.apimodel.hotel;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiKeyAuthDefinition;
import lombok.Data;

@Data
public class HotelAvailableRoom {

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("supplier_code")
    private String supplierCode;

    @JsonProperty("supp_hotel_code")
    private String suppHotelCode;

    @JsonProperty("board_name")
    private String boardName;

    @JsonProperty("room_type_code")
    private String roomTypeCode;

    @JsonProperty("total_room")
    private String totalRoom;

    @JsonProperty("ori_curr_code")
    private String oriCurrCode;

    @JsonProperty("sell_curr_code")
    private String sellCurrCode;

    @JsonProperty("price_converted")
    private String priceConverted;

    @JsonProperty("discount_value")
    private String discountValue;

    @JsonProperty("service_fee")
    private String serviceFee;

    @JsonProperty("is_prome")
    private String isPromo;

    @JsonProperty("voucher_code")
    private String voucherCode;

    @JsonProperty("voucher_value")
    private String voucherValue;

    @JsonProperty("night_price")
    private String nightPrice;

    @JsonProperty("night_price_discounted")
    private String nightPriceDiscounted;

    @JsonProperty("total_price")
    private String totalPrice;

    @JsonProperty("total_price_discounted")
    private String totalPriceDiscounted;

    @JsonProperty("room_type_name")
    private String roomTypeName;

    @JsonProperty("original_price")
    private String originalPrice;
}
