package com.mauwahid.tm.travelmgt.domain.apimodel.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightContact {

    private String phone;

    @JsonProperty("full_name")
    private String fullName;

    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
