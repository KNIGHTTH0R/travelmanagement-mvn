package com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RqLogin {

    private String username;
    private String password;
}

