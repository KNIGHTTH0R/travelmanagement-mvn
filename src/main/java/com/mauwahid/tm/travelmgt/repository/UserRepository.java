package com.mauwahid.tm.travelmgt.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.mauwahid.tm.travelmgt.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

   // @Modifying
    //@Query("select u.id from User u where u.apiKey = :apiKey")
    List<User> findByApiKey(String apiKey);

}