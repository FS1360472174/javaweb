package com.example.service;

import com.example.model.PersonDocument;

public interface PersonService {
   PersonDocument create(PersonDocument personDoc);
	
   PersonDocument getPersonDocumentByName(String name);
}
