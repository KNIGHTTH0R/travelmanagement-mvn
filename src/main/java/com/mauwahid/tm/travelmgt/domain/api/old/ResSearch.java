package com.mauwahid.tm.travelmgt.domain.api.old;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.apimodel.old.SearchInfo;

import java.util.ArrayList;

public class ResSearch extends StdResponse {


    @JsonProperty("error_no")
    private String errorNo;

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("search_info")
    private ArrayList<SearchInfo> searchInfos;

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ArrayList<SearchInfo> getSearchInfos() {
        return searchInfos;
    }

    public void setSearchInfos(ArrayList<SearchInfo> searchInfos) {
        this.searchInfos = searchInfos;
    }
}
