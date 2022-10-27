package com.example.assesmentbackend;

import com.example.assesmentbackend.models.BankAccount;
import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.services.BankAccountService;
import com.example.assesmentbackend.services.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class AssesmentbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssesmentbackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PersonService personService, BankAccountService bankAccountService){
		return args -> {
			Person person = new Person(null, "Solera", "Solera", "solera@solera.com", "solera@solera.com", "bootcamp2", new ArrayList<>());
			Person person1 = personService.addPerson(person);

			BankAccount bankAccount = new BankAccount(null, 1500.0, "123-123-123", person1);
			bankAccountService.addAccount(bankAccount);
		};
	}

}
