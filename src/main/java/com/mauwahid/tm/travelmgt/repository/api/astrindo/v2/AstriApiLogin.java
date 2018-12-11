package com.mauwahid.tm.travelmgt.repository.api.astrindo.v2;

import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.request.RqLogin;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response.RsHotelLogin;
import com.mauwahid.tm.travelmgt.repository.api.astrindo.domain.response.RsLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AstriApiLogin {

    private static final String URL = " /AuthenticationService.svc/Login";
    private static final String username = "INFOMEDIAU00";
    private static final String password = "password123";

   // @Autowired
    private RestTemplate restTemplate;

    public String postAndGetSession(){

        restTemplate = new RestTemplate();

        RqLogin rqLogin = new RqLogin(username, password);
        HttpEntity<RqLogin> request = new HttpEntity<>(rqLogin);

        ResponseEntity<RsLogin> response = restTemplate.exchange(URL, HttpMethod.POST, request, RsLogin.class);

        log.debug("Response "+response.toString());

        return response.getBody().getSessionId();

    }
}
