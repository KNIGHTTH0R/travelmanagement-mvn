package com.mauwahid.tm.travelmgt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LocalDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column
    @JsonProperty("local_code")
    private String stdLocalCode;

    @Column
    @JsonProperty("local_name")
    private String localName;

    @Column
    @JsonIgnore
    private String localAstrindoCode;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private City city;

    @Column
    @JsonIgnore
    private boolean isParent;




}
