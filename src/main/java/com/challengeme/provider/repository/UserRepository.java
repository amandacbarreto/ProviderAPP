package com.challenge.challenge.repository;

import com.challenge.challenge.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, Long> {
}
