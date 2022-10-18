package com.challengeme.provider.repository;

import com.challengeme.provider.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {

    //@Query("SELECT u FROM user u WHERE u.email = ?1")
    User findByEmail(String email);
}
