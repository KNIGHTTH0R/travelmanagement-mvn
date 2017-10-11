package com.mauwahid.tm.travelmgt.repository.api.trevohub;

import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TrevoApiCaller {

    @Value("${trevohub.url}")
    public static String uri = "https://partner.aeroaffiliate.com/api/";

    @Value("${trevohub.b2b_code}")
    public static String b2bCode = "PAR191216UYCKNP";

    @Value("${trevohub.h2h_code}")
    public static String h2hCode = "HIND160617GU2OS";

    @Value("${trevohub.ip}")
    public String authToken = "127.0.0.1";

    public String callApiPost(String uri, Map<String, String> params) throws IOException{
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,params);
    }
}
