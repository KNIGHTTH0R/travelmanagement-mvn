package com.mauwahid.tm.travelmgt.repository.api.opsigo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact {

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("HomePhone")
    private String homePhone;

    @JsonProperty("MobilePhone")
    private String mobilePhone;


}
