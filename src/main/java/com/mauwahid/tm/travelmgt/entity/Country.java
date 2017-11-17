package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Country {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 5)
    private String stdCountryCode;

    @Column(length = 20)
    private String countryName;

  //  @OneToOne(mappedBy = "country")
  //  private AstriCountry astriCountry;



}
