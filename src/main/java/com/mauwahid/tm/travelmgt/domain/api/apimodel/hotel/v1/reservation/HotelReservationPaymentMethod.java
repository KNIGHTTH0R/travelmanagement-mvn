package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelReservationPaymentMethod {

    @JsonProperty("payment_method_code")
    private String paymentMethodCode;

    @JsonProperty("merchant_id")
    private String merchantID;

    @JsonProperty("payment_method_name")
    private String paymentMethodName;

    @JsonProperty("payment_curr_code")
    private String paymentCurrCode;

    private String active;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_time")
    private String createdTime;

    @JsonProperty("last_updated_by")
    private String lastUpdatedBy;

    @JsonProperty("last_updated_time")
    private String lastUpdatedTime;

    @JsonProperty("ref_org_id")
    private String refOrgId;


}
