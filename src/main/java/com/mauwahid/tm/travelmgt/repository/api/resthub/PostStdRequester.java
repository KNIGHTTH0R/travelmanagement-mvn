package com.mauwahid.tm.travelmgt.repository.api.resthub;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class PostStdRequester implements IHttpRequester {


    Logger logger = LoggerFactory.getLogger(PostStdRequester.class);

    @Override
    public String sendRequest(String uri, Map<String,String> params) throws IOException {

        OkHttpClient client = new OkHttpClient();

        FormBody.Builder formBuilder = new FormBody.Builder();

        params.forEach((k,v)->
                {
                    formBuilder.add(k,v);
                }
        );

        Request request = new Request.Builder().url(uri).post(formBuilder.build()).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String sendRequest(String uri, Map<String,String> headerParam,Map<String,String> params) throws IOException {

        OkHttpClient client = new OkHttpClient();


        Headers.Builder headerBuilder = new Headers.Builder();

        headerParam.forEach((k,v)->
                {
                    headerBuilder.add(k,v);
                }
        );

        Headers headers = headerBuilder.build();

        FormBody.Builder formDataBuilder = new FormBody.Builder();
        //logger.debug("formBuilder "+formDataBuilder);

        //logger.debug("header param : "+headerParam);
            params.forEach((x,y)->
                    {
                        try{
              //              logger.debug("data : "+x+", "+y);
                            formDataBuilder.add(x,y);

                        }catch (Exception ex){
                            logger.debug("exception "+ex.toString());
                        }

                    }
            );


        Request request = new Request.Builder().
                url(uri).
                headers(headers).
                post(formDataBuilder.build()).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
