package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightPayments {

    private String code;

    private String title;

    private String amount;

    private String currency;

    @JsonProperty("foreign_amount")
    private String foreignAmount;

    @JsonProperty("foreign_currency")
    private String foreignCurrency;
}
