package com.example.assesmentbackend.services;

import com.example.assesmentbackend.models.BankAccount;
import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.repositories.BankAccountRepository;
import com.example.assesmentbackend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    public PersonRepository personRepository;

    @Autowired
    public BankAccountRepository bankAccountRepository;

    public BankAccount getAccount(Long id){
        return bankAccountRepository.findById(id).orElse(null);
    }

    public List<BankAccount> getAccounts(){
        return bankAccountRepository.findAll();
    }

    public BankAccount addAccount(BankAccount bankAccount){
        try {
            Optional<Person> person = personRepository.findById(bankAccount.getPerson().getId());
            if (person.isEmpty()) {
                return null;
            }
            return bankAccountRepository.save(bankAccount);
        }
        catch (DataIntegrityViolationException exception){
            exception.getMessage();
        }
        return null;
    }

    public List<BankAccount> getUserBankAccounts(Long id){
        return bankAccountRepository.findByPersonId(id).orElse(new ArrayList<>());
    }

    public BankAccount updateBankAccount(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id){
        bankAccountRepository.delete(bankAccountRepository.findById(id).orElse(null));
    }

}
