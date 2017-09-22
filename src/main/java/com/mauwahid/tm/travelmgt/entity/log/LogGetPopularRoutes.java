package com.mauwahid.tm.travelmgt.entity.log;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LogGetPopularRoutes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String apiKey;

    private int idUser;

    @Column(length = 10)
    private String type;


    private String response;

    @Column(length = 30)
    private String ipAddress;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;



    public LogGetPopularRoutes(String apiKey, int idUser, String type, String ipAddress) {
        this.apiKey = apiKey;
        this.type = type;
        this.ipAddress = ipAddress;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
