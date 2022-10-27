package com.example.assesmentbackend.services;

import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public boolean login(Person person){
        Optional<Person> person1 = personRepository.findByUsername(person.getUsername());
        if(person1.isEmpty()) {
            return false;
        }
        else{
            if(person1.get().getPassword().equals(person.getPassword())){
                return true;
            }
            else {
                return false;
            }
        }
    }

    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        try {
            return personRepository.save(person);
        }
        catch (DataIntegrityViolationException exception){
            exception.getMessage();
        }
        return null;
    }

    public Person updatePerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Long id){
        personRepository.delete(personRepository.findById(id).orElse(null));
    }

}
