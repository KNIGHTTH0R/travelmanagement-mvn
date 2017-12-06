package com.mauwahid.tm.travelmgt.repository.database.log;

import com.mauwahid.tm.travelmgt.entity.log.LogFlightBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFlightBookRepository extends CrudRepository<LogFlightBook,Long> {

    LogFlightBook findByPnrId(String pnrId);

}
