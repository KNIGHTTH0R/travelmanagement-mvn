package com.mauwahid.tm.travelmgt.repository.api.resthub;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class GetWithHeaderRequester {




    public  String sendRequest(String uri, Map<String, String> headerParam, Map<String, String> params)
    throws IOException{

        OkHttpClient client = new OkHttpClient();


       // uri = uri+"lion/search/best_price";


        log.debug("data uri : "+uri);
        log.debug("map header : "+headerParam.toString());
        log.debug("map params : "+params.toString());

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

    public  String sendRequest(String uri, Map<String, String> params)
            throws IOException{

        OkHttpClient client = new OkHttpClient();


        // uri = uri+"lion/search/best_price";




        HttpUrl.Builder urlBuilder = HttpUrl.parse(uri).newBuilder();

        params.forEach((k,v)->
                {
                    urlBuilder.addQueryParameter(k,v);


                }
        );

        String url = urlBuilder.build().toString();

        log.debug("url : "+url);

        Request request = new Request.Builder().
                url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
