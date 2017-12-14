package com.mauwahid.tm.travelmgt.repository.api.astrindo;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class AstriApiCaller {

    // development
   // public static String BASE_URI = "http://202.129.226.202:10007/";

    // production
    public static String BASE_URI = "http://203.153.216.34:1612/";

    public static String uri = BASE_URI + "B2B/";

    // development
    //public static String ORGCODE = "DHWJddDxgJtZXcpYqA5uTg==";

    //production
    public static String ORGCODE = "wrOjkduaDaoQQ07t1lVgJw==";

    // development
    // public static String MERCHANTID = "D8qwkNHW07RWHcS1ParYFg==";

    //production
    public static String MERCHANTID = "PiOw9Qi92FX0rVEPKZvuZA==";

    //development
    //public static String USERNAME = "DHWJddDxgJtZXcpYqA5uTg==";

    public static String USERNAME = "+qggfittARzhpcW5pZW83A==";

    public static String PASSWORD = "wqg/LsX2Z+Ok/8zaKLXKNg==";

    public static String callApiPost(String uri, Map<String, String> params) throws IOException {
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }

    public static String callApiGet(String uri, Map<String, String> params) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();

        if(uri==null)
            log.debug("URI NULL");

        return  getWithHeaderRequester.sendRequest(uri,params);
    }



}
