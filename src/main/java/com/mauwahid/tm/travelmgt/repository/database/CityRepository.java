package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.City;
import com.mauwahid.tm.travelmgt.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CityRepository extends CrudRepository<City,Long> {

    Set<City> findByCountry(Country country);


    Set<City> findByStdCityCode(String cityCode);

}
