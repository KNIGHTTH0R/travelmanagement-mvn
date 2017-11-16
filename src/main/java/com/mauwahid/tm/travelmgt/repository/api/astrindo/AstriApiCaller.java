package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Component
public class AstriApiCaller {


    public static String BASE_URI = "http://202.129.226.202:10007/";

    public static String uri = BASE_URI + "B2B/";

    // public static String ORGCODE = "TEST";
    public static String ORGCODE = "DHWJddDxgJtZXcpYqA5uTg==";

   // public static String MERCHANTID = "1G";
    public static String MERCHANTID = "D8qwkNHW07RWHcS1ParYFg==";

    public static String USERNAME = "hszlQuXQgZJvVJpHtGLMBg==";

    public static String PASSWORD = "mtTsoQWJuz5AOu+F+2Og1g9yInDvhxegiA7PiHgLyTzPI41kFjOVWNsFCU9tmtQ2";

  //  @Value("${trevohub.ip}")
    public String authToken = "127.0.0.1";

    public String callApiPost(String uri, Map<String, String> params) throws IOException {
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }

    public String callApiGet(String uri, Map<String, String> params) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();

        return  getWithHeaderRequester.sendRequest(uri,params);
    }


    public static String generateSessionID(){
        return UUID.randomUUID().toString();
    }
}
