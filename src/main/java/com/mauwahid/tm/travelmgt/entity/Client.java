package com.mauwahid.tm.travelmgt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Client extends AbstractEntity {


    @Column(unique = true, length = 30)
    private String clientName;

    @Column(unique = true, length = 30)
    private String companyName;

    @Column
    private String companyAddress;

    @Column
    private String companyEmail;

    @Column
    private String companyPhone;

    @OneToMany(mappedBy = "client")
    private Set<User> user;

    @Column
    private boolean status;

}
