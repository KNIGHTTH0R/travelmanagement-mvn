package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String type;
    private String message;
}
