package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Api extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 25)
    private String apiName;

    @Column(unique = true, length = 25)
    private String providerName;

    private String apiKey;

    @Column(length = 50)
    private String apiUser;

    private String apiPassword;

    //0 for REST, 1 for WSDL, 2 for both
    private int apiType;


}
