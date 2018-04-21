package com.example.service.impl;

import com.example.dao.repository.PersonRepository;
import com.example.model.PersonDocument;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

@EnableMongoRepositories(basePackages = "com.example.dao.repository")
@Service
public class PersonServiceImpl  implements PersonService{

	@Autowired
	private PersonRepository personRepo;

	public PersonDocument getPersonDocumentByName(String name) {
		PersonDocument doc = personRepo.findByName(name);
		return doc;
	}


	public PersonDocument create(PersonDocument personDoc) {
		personDoc = personRepo.save(personDoc);
		return personDoc;
	}

}
