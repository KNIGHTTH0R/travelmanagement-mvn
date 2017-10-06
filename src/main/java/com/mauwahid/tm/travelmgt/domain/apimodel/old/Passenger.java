package com.mauwahid.tm.travelmgt.domain.apimodel.old;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Passenger {


    @JsonProperty("psgr_type")
    private String passanggerType;
    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("id_passager")
    private String idPassanger;

    @JsonProperty("birth_date")
    private String birthDate;
    private String passport;

    @JsonProperty("expire_passport")
    private String expirePassport;

    @JsonProperty("country_passport")
    private String countryPassport;
    private String baggage;


}
