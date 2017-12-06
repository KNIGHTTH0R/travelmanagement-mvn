package com.mauwahid.tm.travelmgt.repository.database.log;

import com.mauwahid.tm.travelmgt.entity.log.LogHotelDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogHotelDetailRepository extends CrudRepository<LogHotelDetail,Long> {
}
