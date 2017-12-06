package com.mauwahid.tm.travelmgt.entity.log;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LogDataWCallback extends LogData {

    @Column
    private String pnrId;

    @Column
    private double progress;

    @Column
    private String text;

    @Column
    private String jobType;

    @Column
    private String callBackUri;

    @Column
    private int callbackStatus;
}
