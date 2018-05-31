package com.mauwahid.tm.travelmgt.repository.api.antavaya;

import com.mauwahid.tm.travelmgt.repository.api.resthub.GetWithHeaderRequester;
import com.mauwahid.tm.travelmgt.repository.api.resthub.PostStdRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AntavayaApiCaller {

   // @Value("${opsigo.url}")
  //  public static String uri = "https://opsigo-vayaapi.azurewebsites.net";
  // public static String uri = "https://opsitools-vac.azurewebsites.net";
   public static String uri = "https://opsitools-vac-staging.azurewebsites.net";


    public String authToken = "";


    public String getTemplate(){
        RestTemplate restTemplate = new RestTemplate();



        return "";
    }


    public Map<String,String> getHeaderParams(){
        Map<String,String> headers = new HashMap<>();

        authToken = AntavayaTokenGenerator.GetToken();

        headers.put("authorization","bearer "+authToken);
        return headers;
    }



    public String callApiPost(String uri, String jsonParams) throws IOException{
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequest(uri,getHeaderParams(),jsonParams);
    }


    public String callApiGet(String uri, Map param) throws IOException{
        GetWithHeaderRequester getWithHeaderRequester = new GetWithHeaderRequester();
        return getWithHeaderRequester.sendRequest(uri,getHeaderParams(),param);
    }

    public Map callApiPostJsonMap(String uri,String jsonParams) throws IOException{
        PostStdRequester postStdRequester = new PostStdRequester();
        return postStdRequester.sendRequestJsonMap(uri,getHeaderParams(), jsonParams);

    }

}
