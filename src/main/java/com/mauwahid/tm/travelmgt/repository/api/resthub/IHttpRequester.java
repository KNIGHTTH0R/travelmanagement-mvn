package com.mauwahid.tm.travelmgt.repository.api.resthub;

import java.io.IOException;
import java.util.Map;

public interface IHttpRequester {

    public String sendRequest(String uri, Map<String,String> params) throws IOException;
}
