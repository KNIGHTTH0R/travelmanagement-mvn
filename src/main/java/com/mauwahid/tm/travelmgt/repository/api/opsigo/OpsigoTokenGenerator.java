package com.mauwahid.tm.travelmgt.repository.api.opsigo;

import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OpsigoTokenGenerator {



    public static String GetToken(){

        String uri = "https://opsigo-auth.azurewebsites.net/core/connect/token";
        Map params = new HashMap<String,String>();
        params.put("grant_type","client_credentials");
        params.put("client_id","client-cre-opsitools-dtk1");
        params.put("client_secret","ktAf#B4/emr,YGsf");
        params.put("scope","scope-dtk1-opsitools");

        PostStdRequester postStdRequester = new PostStdRequester();

        String token = "";

        JSONObject jsonObject = new JSONObject();
        try{
           String response = postStdRequester.sendRequest(uri,params);
           jsonObject = new JSONObject(response);

           token = jsonObject.optString("access_token");

        }catch (Exception e){

        }
        return token;

    }
}
