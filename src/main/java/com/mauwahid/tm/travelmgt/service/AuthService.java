package com.mauwahid.tm.travelmgt.service;

import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import com.mauwahid.tm.travelmgt.entity.User;
import com.mauwahid.tm.travelmgt.repository.database.UserRepository;
import com.mauwahid.tm.travelmgt.repository.database.log.error.LogErrorAuthRepository;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;


public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogErrorAuthRepository logErrorAuthRepository;


    public AuthService getInstance() {
        return new AuthService();
    }

    public Long authKey(String apiKey,String ipAddress, String apiName){
       // int id = 0;
        //List<User> users = userRepository.findByApiKey(apiKey);
        User user = userRepository.findByApiKey(apiKey);

        if(user==null){
            logError(apiKey,ipAddress,StatusCode.S_FORBIDDEN, apiName,StatusCode.S_FORBIDDEN);

            return 0L;
        }

        return user.getId();
    }


    public void logError(String apiKey, String ipAddress, String apiType, String apiName, String description){
      //  LogErrorAuth logErrorAuth = new LogErrorAuth(apiKey,ipAddress, apiType, apiName,description);
      //  logErrorAuthRepository.save(logErrorAuth);
    }

    public DefaultResponse respAuthFailed(){
        return new DefaultResponse(StatusCode.FORBIDDEN,StatusCode.S_FORBIDDEN);
    }


}
