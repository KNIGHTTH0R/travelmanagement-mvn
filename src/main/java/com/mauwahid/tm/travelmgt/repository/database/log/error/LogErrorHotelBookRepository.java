package com.mauwahid.tm.travelmgt.repository.database.log.error;

import com.mauwahid.tm.travelmgt.entity.log.error.LogErrorHotelBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErrorHotelBookRepository extends CrudRepository<LogErrorHotelBook,Long> {
}
