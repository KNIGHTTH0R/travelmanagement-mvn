package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LocalDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String stdLocalCode;

    @Column
    private String localName;



}
