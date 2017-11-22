package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OpsigoTokenGenerator {



    public static String GetToken(){

        String uri = "https://opsigo-auth.azurewebsites.net/core/connect/token";
        Map params = new HashMap<String,String>();
        params.put("grant_type","client_credentials");
        params.put("client_id","client-cre-opsitools-timn");
        params.put("client_secret","6vJKDn8wQJFewX#Z");
        params.put("scope","scope-gr-timn-opsitools");

        PostStdRequester postStdRequester = new PostStdRequester();

        String token = "";

        JSONObject jsonObject;
        try{
           String response = postStdRequester.sendRequest(uri,params);
           jsonObject = new JSONObject(response);

           token = jsonObject.optString("access_token");
           log.debug("token opsigo "+token);

        }catch (Exception e){

            log.error("ex "+e.toString());
        }
        return token;

    }
}
