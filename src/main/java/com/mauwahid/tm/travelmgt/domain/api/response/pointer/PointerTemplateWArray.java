package com.mauwahid.tm.travelmgt.domain.api.response.pointer;

import com.mauwahid.tm.travelmgt.domain.api.old.IData;

import java.util.ArrayList;

public class PointerTemplateWArray<T extends IData> {

    private String errorCode;
    private String errorDesc;
    private ArrayList<T> data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
