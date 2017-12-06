package com.mauwahid.tm.travelmgt.repository.database.log;

import com.mauwahid.tm.travelmgt.entity.log.LogFlightIssue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogFlightIssueRepository extends CrudRepository<LogFlightIssue,Long> {

    LogFlightIssue findByPnrId(String pnrId);

}
