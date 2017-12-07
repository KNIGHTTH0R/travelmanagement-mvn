package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class AstriApiCaller {


    public static String BASE_URI = "http://202.129.226.202:10007/";

    public static String uri = BASE_URI + "B2B/";

    // public static String ORGCODE = "TEST";
    public static String ORGCODE = "DHWJddDxgJtZXcpYqA5uTg==";

   // public static String MERCHANTID = "1G";
    public static String MERCHANTID = "D8qwkNHW07RWHcS1ParYFg==";

    public static String USERNAME = "DHWJddDxgJtZXcpYqA5uTg==";

    public static String PASSWORD = "hszlQuXQgZJvVJpHtGLMBg==";

    public static String callApiPost(String uri, Map<String, String> params) throws IOException {
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }

    public static String callApiGet(String uri, Map<String, String> params) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();

        return  getWithHeaderRequester.sendRequest(uri,params);
    }



}
