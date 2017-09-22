package com.mauwahid.tm.travelmgt.entity.log.error;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;

@Entity
public class LogErrorAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String apiKey;

    //@Column(length = 30)
    public String ipAddress;

    @Column(length = 30)
    public String apiType;

    @Column(length = 50)
    public String apiName;

    public String description;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date logDate;

    public LogErrorAuth(String apiKey, String ipAddress, String apiType, String apiName, String description) {
        this.apiKey = apiKey;
        this.ipAddress = ipAddress;
        this.apiName = apiName;
        this.description = description;
        this.apiType = apiType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }
}
