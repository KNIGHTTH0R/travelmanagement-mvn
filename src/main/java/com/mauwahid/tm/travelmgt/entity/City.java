package com.mauwahid.tm.travelmgt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class City extends AbstractEntity {


    @Column
    @JsonProperty("city_code")
    private String stdCityCode;

    @Column
    @JsonProperty("city_name")
    private String cityName;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Country country;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<LocalDestination> localDestinations;

    @Column
    @JsonIgnore
    private String cityAstrindoCode;


}
