package com.mauwahid.tm.travelmgt.service.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DestinationServiceTest {

    @Autowired
    DestinationService destinationService;

    @Test
    public void getAstrindo() throws Exception {

       // Destination destination = destinationService.getAstrindo("f6cc2187dea984c9f16a1c948c3c5222");

       // log.debug("Destination "+destination.getLocalCode()+", name "+destination.getLocalName());
       // log.debug("City "+destination.getCityCode()+", name "+destination.getLocalName());


    }

}