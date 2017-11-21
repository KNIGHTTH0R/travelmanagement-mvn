package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.City;
import com.mauwahid.tm.travelmgt.entity.LocalDestination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LocalDestinationRepository extends CrudRepository<LocalDestination,Long> {

    Set<LocalDestination> findByStdLocalCode(String code);

    Set<LocalDestination> findByCity(City city);
}
