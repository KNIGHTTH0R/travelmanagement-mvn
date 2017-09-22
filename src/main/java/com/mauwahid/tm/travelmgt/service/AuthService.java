package com.mauwahid.tm.travelmgt.service;

import com.mauwahid.tm.travelmgt.domain.api.response.Response;
import com.mauwahid.tm.travelmgt.domain.apimodel.Status;
import com.mauwahid.tm.travelmgt.entity.User;
import com.mauwahid.tm.travelmgt.entity.log.error.LogErrorAuth;
import com.mauwahid.tm.travelmgt.repository.UserRepository;
import com.mauwahid.tm.travelmgt.repository.log.error.LogErrorAuthRepository;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogErrorAuthRepository logErrorAuthRepository;

    public int isAuthenticated(String apiKey){
        int id = 0;
        List<User> users = userRepository.findByApiKey(apiKey);
        id =  users.isEmpty()?0: users.stream().findFirst().get().getId();
        return id;
    }


    public void logError(String apiKey, String ipAddress, String apiType, String apiName, String description){
        LogErrorAuth logErrorAuth = new LogErrorAuth(apiKey,ipAddress, apiType, apiName,description);
        logErrorAuthRepository.save(logErrorAuth);
    }

    public static Response authFailed(){
        Status status = new Status(StatusCode.AUTH_FAILED, "Auth Failed");
        Response response = new Response(status);
        return response;
    }


}
