package com.mauwahid.tm.travelmgt.repository.api.pointer;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PointerApiCaller  {

    @Value("${pointer.url}")
    public static String uri = "http://54.169.218.195:8989/sandbox/";


    @Value("${pointer.token}")
    public String authToken = "7cbe43b2721111e7ab0106a69b186ee1";


    public Map<String,String> getHeaderParams(){
        Map<String,String> headers = new HashMap<>();

        headers.put("token",authToken);

        return headers;
    }

    public String callApiGet(String uri, Map<String, String> params) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();

        return  getWithHeaderRequester.sendRequest(uri,getHeaderParams(),params);
    }

    public String callApiPost(String uri, Map<String, String> params) throws IOException{
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,getHeaderParams(),params);
    }

}
