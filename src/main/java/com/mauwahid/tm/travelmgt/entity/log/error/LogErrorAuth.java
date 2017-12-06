package com.mauwahid.tm.travelmgt.entity.log.error;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Blob;

@Data
@Entity
public class LogErrorAuth extends AbstractEntity{

    @Column
    private String apiKey;

    @Column
    private String ipAddress;

    @Column
    private String apiType;

    @Column
    private String apiName;

    @Column
    private Blob description;

}
