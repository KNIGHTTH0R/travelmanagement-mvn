package com.mauwahid.tm.travelmgt.domain.api.response.trevohub;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TrevoHubTemplateWithArray {

    private String errorCode;
    private String errorDesc;
    private ArrayList data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
