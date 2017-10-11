package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TrevoApiCaller {

    @Value("${trevohub.url}")
    public static String uri = "https://astrindotravel.astindohub.co.id/api/";

    @Value("${trevohub.b2b_code}")
    public static String b2bCode = "AST100717ICANVO";

    @Value("${trevohub.h2h_code}")
    public static String h2hCode = "HINF210717FUWX0";

    @Value("${pointer.ip}")
    public String authToken = "127.0.0.1";

    public String callApiPost(String uri, Map<String, String> params) throws IOException{
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }
}
