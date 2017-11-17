package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AstriCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String countryCode;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
