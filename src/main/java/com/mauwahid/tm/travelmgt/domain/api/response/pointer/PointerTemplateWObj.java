package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

import com.mauwahid.tm.travelmgt.domain.api.response.IData;

import java.util.ArrayList;

public class PointerTemplateWObj {

    private String errorCode;
    private String errorDesc;
    private IData data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public IData getData() {
        return data;
    }

    public void setData(IData data) {
        this.data = data;
    }
}
