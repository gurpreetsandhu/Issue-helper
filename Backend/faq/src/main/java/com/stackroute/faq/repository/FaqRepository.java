package com.stackroute.faq.repository;

import com.stackroute.faq.model.Faq;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends MongoRepository<Faq, String> {
}
