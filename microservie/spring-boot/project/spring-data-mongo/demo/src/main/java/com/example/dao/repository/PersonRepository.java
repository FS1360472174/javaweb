package com.example.dao.repository;

import com.example.model.PersonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonDocument,String>{
	PersonDocument findByName(String name);
}