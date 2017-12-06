package com.mauwahid.tm.travelmgt.repository.database.log;

import com.mauwahid.tm.travelmgt.entity.log.LogFlightReservationStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFlightReservationStatusRepository extends CrudRepository<LogFlightReservationStatus,Long> {
}
