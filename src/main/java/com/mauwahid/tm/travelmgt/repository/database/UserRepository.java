package com.mauwahid.tm.travelmgt.repository.database;

import com.mauwahid.tm.travelmgt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

   // @Modifying
    //@Query("select u.id from User u where u.apiKey = :apiKey")
    User findByApiKey(String apiKey);

}