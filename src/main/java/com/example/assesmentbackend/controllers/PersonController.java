package com.example.assesmentbackend.controllers;

import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/person")
public class PersonController {

    @Autowired
    public PersonService personService;

    @PostMapping(path = "/login")
    @CrossOrigin
    public boolean login(@RequestBody Person person){
        return personService.login(person);
    }

    @GetMapping(path = "/{id}")
    @CrossOrigin
    public Person getPerson(@PathVariable(name = "id") Long id){
        return personService.getPerson(id);
    }

    @GetMapping(path = "/all")
    @CrossOrigin
    public List<Person> getAllPersons(){
        return personService.getPersons();
    }

    @PostMapping(path = "/add")
    @CrossOrigin
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PostMapping(path = "/update")
    @CrossOrigin
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @DeleteMapping(path = "/delete/{id}")
    @CrossOrigin
    public void deletePerson(@PathVariable(value = "id") Long id){
        personService.deletePerson(id);
    }

}
