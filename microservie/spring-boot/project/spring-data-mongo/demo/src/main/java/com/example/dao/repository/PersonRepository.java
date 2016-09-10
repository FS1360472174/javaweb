package com.example.dao.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.PersonDocument;

@Repository
public interface PersonRepository extends MongoRepository<PersonDocument,String>{
	PersonDocument findByName(String name);
}