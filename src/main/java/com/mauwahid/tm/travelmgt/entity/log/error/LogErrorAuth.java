package com.mauwahid.tm.travelmgt.entity.log.error;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class LogErrorAuth extends AbstractEntity{

    private String apiKey;

    private String ipAddress;

    private String apiType;

    private String apiName;

    private String description;
}
