package com.mauwahid.tm.travelmgt.service;

import com.mauwahid.tm.travelmgt.domain.api.response.DefaultResponse;
import com.mauwahid.tm.travelmgt.entity.User;
import com.mauwahid.tm.travelmgt.repository.database.UserRepository;
import com.mauwahid.tm.travelmgt.repository.database.log.error.LogErrorAuthRepository;
import com.mauwahid.tm.travelmgt.utils.LogErrorHelper;
import com.mauwahid.tm.travelmgt.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogErrorAuthRepository logErrorAuthRepository;

    @Autowired
    private LogErrorHelper logErrorHelper;


    public AuthService getInstance() {
        return new AuthService();
    }

    public Long authKey(String apiKey,String apiName, String apiType, String description, String ipAddress){
        User user = userRepository.findByApiKey(apiKey);

        if(user==null){
            logErrorHelper.saveAuthError(apiKey, apiName,apiType,description,ipAddress);
            return 0L;
        }

        return user.getId();
    }


    public DefaultResponse respAuthFailed(){
        return new DefaultResponse(StatusCode.UNAUTORIZED,StatusCode.S_UNAUTHORIZED);
    }


}
