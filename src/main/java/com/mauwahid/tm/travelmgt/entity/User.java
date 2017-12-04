package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
public class User extends AbstractEntity{

    @Column(unique = true, length = 50)
    private String name;

    @Column(unique = true, length = 30)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String apiKey;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;



}
