package com.mauwahid.tm.travelmgt.repository.api.resthub;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class GetWithHeaderRequester {




    public  String sendRequest(String uri, Map<String, String> headerParam, Map<String, String> params)
    throws IOException{

       // OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();


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

      //  OkHttpClient client = new OkHttpClient();
        log.debug("URI "+uri.toString());
        // uri = uri+"lion/search/best_price";
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

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
