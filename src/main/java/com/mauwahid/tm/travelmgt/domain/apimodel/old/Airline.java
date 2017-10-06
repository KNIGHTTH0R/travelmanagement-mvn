package com.mauwahid.tm.travelmgt.domain.apimodel.old;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mauwahid.tm.travelmgt.domain.api.old.IData;
import org.springframework.stereotype.Component;

@Component
public class Airline implements IData {


    @JsonProperty("airlines_id")
    private String airlinesId;

    @JsonProperty("airlines_code")
    private String airlinesCode;

    @JsonProperty("airlines_name")
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
