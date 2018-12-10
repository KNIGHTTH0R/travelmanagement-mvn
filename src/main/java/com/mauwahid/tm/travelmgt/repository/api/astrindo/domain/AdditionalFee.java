package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class AdditionalFee {
    private String type;
    private String amount;
    private String isTotal;
    private String tax;
    private String fees;
    private String id;
}
