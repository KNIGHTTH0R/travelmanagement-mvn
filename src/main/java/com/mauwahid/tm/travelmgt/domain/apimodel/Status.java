package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.mauwahid.tm.travelmgt.domain.api.response.IStatus;
import org.springframework.stereotype.Component;

@Component
public class Status implements IStatus {

    private int statusId;
    private String statusDesc;

    public Status(){

    }

    public Status(int statusId, String statusDesc){
        this.statusId = statusId;
        this.statusDesc = statusDesc;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
