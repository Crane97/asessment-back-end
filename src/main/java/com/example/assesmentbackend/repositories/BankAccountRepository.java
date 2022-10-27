package com.example.assesmentbackend.repositories;

import com.example.assesmentbackend.models.BankAccount;
import com.example.assesmentbackend.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<List<BankAccount>> findByPersonId(Long id);

}
