package com.stackroute.query.repository;

import com.stackroute.query.model.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends MongoRepository<Query, String> {
}
