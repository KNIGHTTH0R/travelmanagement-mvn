package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.log.LogHotelCancellationPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogHotelCancellationPolicyRepository extends CrudRepository<LogHotelCancellationPolicy,Long> {
}
