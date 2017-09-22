package com.mauwahid.tm.travelmgt.domain.apimodel;

import com.mauwahid.tm.travelmgt.domain.api.response.IData;
import org.springframework.stereotype.Component;

@Component
public class Airline implements IData {

    private String airlinesId;
    private String airlinesCode;
    private String airlinesName;
    private String connectionStatus;

    public String getAirlinesId() {
        return airlinesId;
    }

    public void setAirlinesId(String airlinesId) {
        this.airlinesId = airlinesId;
    }

    public String getAirlinesCode() {
        return airlinesCode;
    }

    public void setAirlinesCode(String airlinesCode) {
        this.airlinesCode = airlinesCode;
    }

    public String getAirlinesName() {
        return airlinesName;
    }

    public void  setAirlinesName(String airlinesName) {
        this.airlinesName = airlinesName;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }
}
