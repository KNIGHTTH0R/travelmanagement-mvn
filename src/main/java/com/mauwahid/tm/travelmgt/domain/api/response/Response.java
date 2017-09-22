package com.mauwahid.tm.travelmgt.domain.api.response;

public class Response {

    private IStatus status;
    private IData data;


    public Response(IStatus status){
        this.status = status;
    }

    public Response(IStatus status,IData data){
        this.status = status;
        this.data = data;
    }
    public IStatus getStatus() {
        return status;
    }

    public void setStatus(IStatus status) {
        this.status = status;
    }

    public IData getData() {
        return data;
    }

    public void setData(IData data) {
        this.data = data;
    }
}
