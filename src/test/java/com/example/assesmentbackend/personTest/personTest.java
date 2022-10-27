package com.example.assesmentbackend.personTest;

import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest
public class personTest{

    // OUR DATABASE IS GOING TO HAVE ALWAYS THE DEFAULT USER

    @Autowired
    private PersonService personService;

    @Test
    public void login_whenCorrectPerson_returnsTrue(){
        //given
        Person person = new Person(null, null, null, "solera@solera.com", null, "bootcamp2", new ArrayList<>());

        //when
        //then
        Assertions.assertTrue(personService.login(person));
    }

    @Test
    public void login_whenNonCorrectPassword_returnsFalse(){
        //given
        Person person = new Person(null, null, null, "solera@solera.com", null, "bcamp2", new ArrayList<>());

        //when
        //then
        Assertions.assertFalse(personService.login(person));
    }

    @Test
    public void login_whenNonCorrectUsername_returnsFalse(){
        //given
        Person person = new Person(null, null, null, "username", null, "bootcamp2", new ArrayList<>());

        //when
        //then
        Assertions.assertFalse(personService.login(person));
    }

    @Test
    public void createPerson_whenPersonCreated_returnsPerson(){
        //given
        Person person = new Person(null, "Jorge", "Ruiz", "Jorge R", "jorge.ruiz@solera.com", "pass", new ArrayList<>());

        //when
        Person result = personService.addPerson(person);

        //then
        // By getting the id means that the user is stored in the database
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(person.getUsername(), result.getUsername());
        Assertions.assertEquals(person.getPassword(), result.getPassword());
        Assertions.assertEquals(person.getEmail(), result.getEmail());
        Assertions.assertEquals(person.getFirstName(), result.getFirstName());
        Assertions.assertEquals(person.getLastName(), result.getLastName());
        Assertions.assertEquals(person.getBankAccount().size(), result.getBankAccount().size());

    }

    @Test
    public void createPerson_withExistingEmail_returnNull(){
        //given
        Person person = new Person(null, "Jorge", "Ruiz", "jorge@solera.com", "jorge@solera.com", "pass", new ArrayList<>());
        Person person1 = new Person(null, "Carlos", "Perez", "jorge@solera.com", "jorge@solera.com", "pass", new ArrayList<>());

        //when
        Person personCorrect = personService.addPerson(person);
        Person personNotCorrect = personService.addPerson(person1);

        //then
        Assertions.assertNull(personNotCorrect);

        Assertions.assertNotNull(personCorrect.getId());
        Assertions.assertEquals(person.getUsername(), personCorrect.getUsername());
        Assertions.assertEquals(person.getPassword(), personCorrect.getPassword());
        Assertions.assertEquals(person.getEmail(), personCorrect.getEmail());
        Assertions.assertEquals(person.getFirstName(), personCorrect.getFirstName());
        Assertions.assertEquals(person.getLastName(), personCorrect.getLastName());
        Assertions.assertEquals(person.getBankAccount().size(), personCorrect.getBankAccount().size());

    }

    @Test
    public void getPerson_withExistingIdSoleraUser_returnPerson(){
        Assertions.assertNotNull(personService.getPerson(Long.valueOf(1)));
    }

    @Test
    public void getPerson_withNotExistingId_returnNull(){
        Person person1 = personService.addPerson(new Person(null, "Carlos", "Perez", "Carlos P", "jorge@solera.com", "pass", new ArrayList<>()));
        Assertions.assertNull(personService.getPerson(Long.valueOf(person1.getId()+1)));
    }

    @Test
    public void getPersons_returnList(){
        Assertions.assertNotNull(personService.getPersons());
        Assertions.assertTrue(personService.getPersons().size() != 0);
    }

    @Test
    public void updatePerson_whenUpdate_returnsUpdatedPerson(){

    }

    @Test
    public void updatePerson_whenUpdateError_returnsOldPerson(){

    }

    @Test
    public void deletePerson_whenDeletePerson_deleteCorrectly(){

    }

    @Test
    public void deletePerson_whenDeleteNonExistingPerson_deleteNothing(){

    }
}
