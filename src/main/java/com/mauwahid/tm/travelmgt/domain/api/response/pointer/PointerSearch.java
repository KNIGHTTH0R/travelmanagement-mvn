package com.mauwahid.tm.travelmgt.domain.api.response.pointer;


import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import org.springframework.boot.jackson.JsonComponent;


@JsonComponent
public class PointerSearch{

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

}
