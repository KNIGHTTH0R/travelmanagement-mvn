package com.mauwahid.tm.travelmgt.controller.common;

import com.mauwahid.tm.travelmgt.entity.City;
import com.mauwahid.tm.travelmgt.entity.Country;
import com.mauwahid.tm.travelmgt.entity.LocalDestination;
import com.mauwahid.tm.travelmgt.service.common.DestinationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/destination")
@Api(tags = "Destination",  description="Operations for Hotel's booking")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;


    @PostMapping("/local")
    public ResponseEntity local(@RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody String cityCode){

        Set<LocalDestination> destinations = destinationService.fetchLocalDestination(cityCode);

        return  new ResponseEntity(destinations, HttpStatus.OK);
    }

    @PostMapping("/city")
    public ResponseEntity city(@RequestHeader(name = "api-key") String apiKey,
                                 @RequestBody String countryCode){

        Set<City> cities = destinationService.fetchCity(countryCode);

        return  new ResponseEntity(cities, HttpStatus.OK);
    }

    @PostMapping("/country")
    public ResponseEntity search(@RequestHeader(name = "api-key") String apiKey){

        Iterable<Country> countries = destinationService.fetchCountry();

        return  new ResponseEntity(countries, HttpStatus.OK);
    }
}
