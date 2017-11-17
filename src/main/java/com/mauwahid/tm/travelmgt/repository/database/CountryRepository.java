package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,Long> {
}
