package com.example.myBankApp.Repositories;

import com.example.myBankApp.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
    boolean existsByAccountNumber(long accountNumber);

}
