package com.mauwahid.tm.travelmgt.entity.log.error;

import com.mauwahid.tm.travelmgt.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Blob;

@MappedSuperclass
@Data
public class LogErrorApi extends AbstractEntity {

    @Column
    private String exceptionData;

    @Column
    private Blob jsonData;

    @Column
    private String params;
}
