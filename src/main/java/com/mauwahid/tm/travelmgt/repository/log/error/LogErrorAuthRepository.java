package com.mauwahid.tm.travelmgt.repository.log.error;

import com.mauwahid.tm.travelmgt.entity.log.error.LogErrorAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LogErrorAuthRepository extends CrudRepository<LogErrorAuth,Long> {
}
