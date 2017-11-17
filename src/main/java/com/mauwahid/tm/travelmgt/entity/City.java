package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String stdCityCode;

    @Column
    private String cityName;

}
