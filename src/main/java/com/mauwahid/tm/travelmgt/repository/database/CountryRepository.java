package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CountryRepository extends CrudRepository<Country,Long> {

    Set<Country> findByStdCountryCode(String countryCode);
}
