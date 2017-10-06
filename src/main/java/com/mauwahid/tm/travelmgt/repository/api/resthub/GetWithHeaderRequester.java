package com.mauwahid.tm.travelmgt.repository.api.resthub;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class GetWithHeaderRequester {


    Logger logger = LoggerFactory.getLogger(GetWithHeaderRequester.class);


    public  String sendRequest(String uri, Map<String, String> headerParam, Map<String, String> params)
    throws IOException{

        OkHttpClient client = new OkHttpClient();


       // uri = uri+"lion/search/best_price";


        logger.debug("data uri : "+uri);
        logger.debug("map header : "+headerParam.toString());
        logger.debug("map params : "+params.toString());

        Headers.Builder headerBuilder = new Headers.Builder();

        headerParam.forEach((k,v)->
                {
                    headerBuilder.add(k,v);
                }
        );

        HttpUrl.Builder urlBuilder = HttpUrl.parse(uri).newBuilder();

        params.forEach((k,v)->
                {
                    urlBuilder.addQueryParameter(k,v);


                }
        );

        String url = urlBuilder.build().toString();
        Headers headers = headerBuilder.build();

        Request request = new Request.Builder().
                url(url).headers(headers).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
