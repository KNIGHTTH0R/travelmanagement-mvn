package com.mauwahid.tm.travelmgt.repository.api.resthub;

import com.mauwahid.tm.travelmgt.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PostStdRequester implements IHttpRequester {



    @Override
    public String sendRequest(String uri, Map<String,String> params) throws IOException {

       // OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();

        FormBody.Builder formBuilder = new FormBody.Builder();

        params.forEach((k,v)->
                {
                   formBuilder.add(k,v);
                   // logger.debug("k "+k+", v ");
                }
        );

        log.debug("URI "+uri);

        Request request = new Request.Builder().url(uri).
                post(formBuilder.build()).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String sendRequest(String uri, Map<String,String> headerParam,Map<String,String> params) throws IOException {

       // OkHttpClient client = new OkHttpClient();

      /*  ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();
*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
      //  builder.connectionSpecs(Collections.singletonList(spec));

        OkHttpUtil.configureToIgnoreCertificate(builder);
//        OkHttpClient client = builder.build();

        OkHttpClient client = OkHttpUtil.configureToIgnoreCertificate(builder).build();

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

                            if(y!=null)
                                formDataBuilder.add(x,y);

                        }catch (Exception ex){
                            log.debug("exception "+ex.toString());
                        }

                    }
            );

        log.debug("URL Call "+uri);

        Request request = new Request.Builder().
                url(uri).
                headers(headers).
                post(formDataBuilder.build()).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String sendRequest(String uri, Map<String,String> headerParam,String jSONparams) throws IOException {

       // OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();


        Headers.Builder headerBuilder = new Headers.Builder();

        headerParam.forEach((k,v)->
                {
                    headerBuilder.add(k,v);
                }
        );

        Headers headers = headerBuilder.build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jSONparams);

        log.debug("URL Call "+uri);


        Request request = new Request.Builder().
                url(uri).
                headers(headers).
                post(requestBody).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public Map sendRequestMap(String uri, Map<String,String> params) throws IOException {

//        OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();


        FormBody.Builder formBuilder = new FormBody.Builder();

        params.forEach((k,v)->
                {
                    formBuilder.add(k,v);
                    // logger.debug("k "+k+", v ");
                }
        );

        log.debug("URI "+uri);
        Request request = new Request.Builder().url(uri).post(formBuilder.build()).build();


        Map map = new HashMap();


        try (Response response = client.newCall(request).execute()) {


            map.put("status",response.code());
            map.put("body", response.body().string());

            return map;
        }
    }

    public Map sendRequestJsonMap(String uri, Map<String,String> headerParam,String jSONparams) throws IOException {

       // OkHttpClient client = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);

        OkHttpClient client = builder.build();


        Headers.Builder headerBuilder = new Headers.Builder();

        headerParam.forEach((k,v)->
                {
                    headerBuilder.add(k,v);
                }
        );

        Headers headers = headerBuilder.build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jSONparams);

        log.debug("URL Call "+uri);


        Request request = new Request.Builder().
                url(uri).
                headers(headers).
                post(requestBody).build();

        Map map = new HashMap();

        try (Response response = client.newCall(request).execute()) {

            log.debug("Response code "+response.code());
            log.debug("Response body "+response.code());
            map.put("status",response.code());
            map.put("body", response.body().string());

            return map;

        }
    }


}
