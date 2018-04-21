//http://spring-java4dev.com/spring-framework/spring-boot/mongodb-with-spring-boot-use-mongooperation-for-accessing
package com.example;

import com.example.model.PersonDocument;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import({ MongoConfig.class })
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		PersonDocument doc = new PersonDocument("tom", "student");
		personService.create(doc);
		PersonDocument doc2 = personService.getPersonDocumentByName("tom");
		System.out.println("result:" + doc2.getName());

	}
}
