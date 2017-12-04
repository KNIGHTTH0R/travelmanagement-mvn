package com.mauwahid.tm.travelmgt.entity.log.error;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LogErrorApi extends AbstractEntity {

    private String exceptionData;

    private String jsonData;

    private String params;
}
