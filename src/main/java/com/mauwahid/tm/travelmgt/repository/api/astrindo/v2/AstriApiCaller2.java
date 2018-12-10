package com.mauwahid.tm.travelmgt.repository.api.astrindo.v2;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class AstriApiCaller2 {

    // development
    //  public static String BASE_URI = "http://202.129.226.202:10007/";

    // production
    public static String BASE_URI = "http://203.153.216.34:1612/";


    public static String uri = BASE_URI + "B2B/";


    /* production */
    //production
    public static String ORGCODE = "wrOjkduaDaoQQ07t1lVgJw==";

    public static String MERCHANTID = "PiOw9Qi92FX0rVEPKZvuZA==";

    public static String USERNAME = "+qggfittARzhpcW5pZW83A==";

    public static String PASSWORD = "wqg/LsX2Z+Ok/8zaKLXKNg==";



   /* dev

    public static String ORGCODE = "DHWJddDxgJtZXcpYqA5uTg==";

    public static String USERNAME = "hszlQuXQgZJvVJpHtGLMBg==";

    public static String MERCHANTID = "D8qwkNHW07RWHcS1ParYFg==";


    public static String PASSWORD = "mtTsoQWJuz5AOu+F+2Og1g9yInDvhxegiA7PiHgLyTzPI41kFjOVWNsFCU9tmtQ2";
*/

    public static String callApiPost(String uri, Map<String, String> params) throws IOException {
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }

    public static String callApiGet(String uri, Map<String, String> params) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();

        return  getWithHeaderRequester.sendRequest(uri,params);
    }


}
