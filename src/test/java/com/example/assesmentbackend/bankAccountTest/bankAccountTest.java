package com.example.assesmentbackend.bankAccountTest;

import com.example.assesmentbackend.models.BankAccount;
import com.example.assesmentbackend.models.Person;
import com.example.assesmentbackend.services.BankAccountService;
import com.example.assesmentbackend.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class bankAccountTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    public PersonService personService;

    @Test
    public void createBankAccount_withPerson_returnsBankAccount(){
        BankAccount bankAccount = new BankAccount(null, 1200.0, "321-321-321", personService.getPerson(Long.valueOf(1)));
        BankAccount bankAccount1 = bankAccountService.addAccount(bankAccount);

        Assertions.assertNotNull(bankAccount1);
    }

    @Test
    public void createBankAccount_withNonExistingPerson_returnsNull(){
        BankAccount bankAccount = new BankAccount(null, 1200.0, "321-321-321", new Person(Long.valueOf(0), null, null, null, null, null, new ArrayList<>()));
        BankAccount bankAccount1 = bankAccountService.addAccount(bankAccount);

        Assertions.assertNull(bankAccount1);
    }

    @Test
    public void createBankAccount_withExistingBankNumber_returnsNull(){
        BankAccount bankAccount = new BankAccount(null, 1200.0, "321-321-321", new Person(Long.valueOf(1), null, null, null, null, null, new ArrayList<>()));
        BankAccount bankIncorrect = new BankAccount(null, 1100.0, "321-321-321", new Person(Long.valueOf(1), null, null, null, null, null, new ArrayList<>()));

        BankAccount bankAccount1 = bankAccountService.addAccount(bankAccount);

        BankAccount bankIncorrect1 = bankAccountService.addAccount(bankIncorrect);

        Assertions.assertNull(bankIncorrect1);
        Assertions.assertNotNull(bankAccount1);
        Assertions.assertEquals(bankAccount1.getNumber(), bankAccount.getNumber());
    }

    @Test
    public void getBankAccount_withExistingId_returnBankAccount(){
        Assertions.assertNotNull(bankAccountService.getAccount(Long.valueOf(1)));
    }

    @Test
    public void getBankAccount_withNotExistingId_returnBankAccount(){
        BankAccount bankAccount = bankAccountService.addAccount(new BankAccount(null, 1200.0, "321-321-321", new Person(Long.valueOf(1), null, null, null, null, null, new ArrayList<>())));
        Assertions.assertNull(bankAccountService.getAccount(Long.valueOf(bankAccount.getId()+1)));
    }

    @Test
    public void getBankAccounts_returnList(){
        Assertions.assertNotNull(bankAccountService.getAccounts());
        Assertions.assertTrue(bankAccountService.getAccounts().size() != 0);
    }
}
