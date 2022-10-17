package com.challengeme.provider.repository;

import com.challengeme.provider.entity.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProviderRepository extends MongoRepository<Provider, String> {
}
