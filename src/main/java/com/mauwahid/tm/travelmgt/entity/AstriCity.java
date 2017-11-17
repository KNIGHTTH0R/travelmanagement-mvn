package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AstriCity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cityCode;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;
}
