package com.challenge.challenge.repository;

import com.challenge.challenge.entity.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProviderRepository extends MongoRepository<Provider, Long> {
}
