package com.challengeme.provider.repository;

import com.challengeme.provider.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
