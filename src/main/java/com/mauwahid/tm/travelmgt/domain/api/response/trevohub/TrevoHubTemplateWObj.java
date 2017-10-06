package com.mauwahid.tm.travelmgt.domain.api.response.trevohub;

import com.mauwahid.tm.travelmgt.domain.api.old.IData;
import org.springframework.stereotype.Component;

@Component
public class TrevoHubTemplateWObj {

    private String errorCode;
    private String errorDesc;
    IData data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public IData getData() {
        return data;
    }

    public void setData(IData data) {
        this.data = data;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
