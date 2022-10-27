package com.example.assesmentbackend.controllers;

import com.example.assesmentbackend.models.BankAccount;
import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.services.BankAccountService;
import com.example.assesmentbackend.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/bankaccount")
public class BankAccountController {

    @Autowired
    public BankAccountService bankAccountService;

    @GetMapping(path = "/{id}")
    public BankAccount getAccount(@PathVariable(name = "id") Long id){
        return bankAccountService.getAccount(id);
    }

    @GetMapping(path = "/all")
    public List<BankAccount> getAccounts(){
        return bankAccountService.getAccounts();
    }

    @GetMapping(path = "/all/{id}")
    public List<BankAccount> getPersonAccounts(@PathVariable(name = "id") Long id){
        return bankAccountService.getUserBankAccounts(id);
    }

    @PostMapping(path = "/add")
    public BankAccount addAccounts(@RequestBody BankAccount bankAccount){
        return bankAccountService.addAccount(bankAccount);
    }

    @PostMapping(path = "/update")
    public BankAccount updateNote(@RequestBody BankAccount bankAccount){
        return bankAccountService.updateBankAccount(bankAccount);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteNote(@PathVariable(value = "id") Long id){
        bankAccountService.deleteBankAccount(id);
    }
}
