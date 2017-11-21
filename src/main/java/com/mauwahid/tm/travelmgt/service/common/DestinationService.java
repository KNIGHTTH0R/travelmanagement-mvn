package com.mauwahid.tm.travelmgt.service.common;

import com.mauwahid.tm.travelmgt.domain.apimodel.common.Destination;
import com.mauwahid.tm.travelmgt.entity.City;
import com.mauwahid.tm.travelmgt.entity.Country;
import com.mauwahid.tm.travelmgt.entity.LocalDestination;
import com.mauwahid.tm.travelmgt.repository.database.CityRepository;
import com.mauwahid.tm.travelmgt.repository.database.CountryRepository;
import com.mauwahid.tm.travelmgt.repository.database.LocalDestinationRepository;
import com.mauwahid.tm.travelmgt.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class DestinationService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private LocalDestinationRepository localDestinationRepository;



    public Set<City> fetchCity(String countryCode){

        Country country = countryRepository.findByStdCountryCode(countryCode).stream().findFirst().get();
        Set<City> cities = cityRepository.findByCountry(country);

        return cities;

    }

    public Iterable<Country> fetchCountry(){

        Iterable<Country> countries = countryRepository.findAll();

        return countries;

    }

    public Set<LocalDestination> fetchLocalDestination(String cityCode){
        City city = cityRepository.findByStdCityCode(cityCode).stream().findFirst().get();

        Set<LocalDestination> localDestinations = localDestinationRepository.findByCity(city);

        return localDestinations;

    }



    public Destination getDestinationForAPI(String apiName, String destinationKey){

        Destination destination = null;


        switch (apiName){
            case Common.API_ASTRINDO :
                destination = getAstrindo(destinationKey);
                break;
        }

        return destination;


    }


    private Destination getAstrindo(String destinationKey){

        Destination destination = new Destination();

        LocalDestination localDestination = localDestinationRepository.
                findByStdLocalCode(destinationKey).stream().findFirst().get();

        String astriLocalCode = localDestination.getLocalAstrindoCode();
        String astriLocalName = localDestination.getLocalName();

        City city = localDestination.getCity();

        Country country = city.getCountry();

        if(astriLocalCode==null || astriLocalCode.equalsIgnoreCase("")){

            astriLocalCode = city.getCityAstrindoCode();
            astriLocalName = city.getCityName();

        }

        destination.setCountryCode(country.getCountryAstrindoCode());
        destination.setCountryName(country.getCountryName());
        destination.setLocalCode(astriLocalCode);
        destination.setLocalName(astriLocalName);
        destination.setCityCode(city.getCityAstrindoCode());
        destination.setCityName(city.getCityName());

        return  destination;
    }









}
