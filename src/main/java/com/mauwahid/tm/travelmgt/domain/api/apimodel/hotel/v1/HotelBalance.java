package com.mauwahid.tm.travelmgt.domain.api.apimodel.hotel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelBalance {

    @JsonProperty("last_deposit")
    private String lastDeposit;

    @JsonProperty("current_deposit")
    private String currentDeposit;

    public String getLastDeposit() {
        return lastDeposit;
    }

    public void setLastDeposit(String lastDeposit) {
        this.lastDeposit = lastDeposit;
    }

    public String getCurrentDeposit() {
        return currentDeposit;
    }

    public void setCurrentDeposit(String currentDeposit) {
        this.currentDeposit = currentDeposit;
    }
}
