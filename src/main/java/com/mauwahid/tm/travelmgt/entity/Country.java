package com.mauwahid.tm.travelmgt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Country extends AbstractEntity {


  /*  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id; */

    @Column(length = 5)
    @JsonProperty("country_code")
    private String stdCountryCode;

    @Column(length = 20)
    @JsonProperty("country_name")
    private String countryName;


    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<City> cities;

  //  @OneToOne(mappedBy = "country")
  //  private AstriCountry astriCountry;

    @Column
    @JsonIgnore
    private String countryAstrindoCode;

}
