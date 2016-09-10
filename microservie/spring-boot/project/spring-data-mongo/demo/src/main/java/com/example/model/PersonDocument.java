package com.example.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="personDocument")
public class PersonDocument {
	    @Id
	    String id;
		String name;
	    String description;
	  
	    public PersonDocument(String name, String description){
	    	this.id =UUID.randomUUID().toString();
	        this.name = name;
	        this.description = description;
	    }
	  
	    /**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		
	    @Override
	    public String toString() {
	        return String.format("[id = %s, name = %s, description = %s]", id, name, description);
	    }
	}
