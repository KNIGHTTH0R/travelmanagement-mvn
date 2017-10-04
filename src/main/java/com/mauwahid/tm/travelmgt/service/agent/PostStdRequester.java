package com.mauwahid.tm.travelmgt.service.agent;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class PostStdRequester implements IHttpRequester {


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
}
