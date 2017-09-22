package com.mauwahid.tm.travelmgt.domain.api.response.pointer;


import com.mauwahid.tm.travelmgt.domain.api.response.IData;

public class PointerSearch implements IData {

    private String code;
    private PointerResult pointerResult;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PointerResult getPointerResult() {
        return pointerResult;
    }

    public void setPointerResult(PointerResult pointerResult) {
        this.pointerResult = pointerResult;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
