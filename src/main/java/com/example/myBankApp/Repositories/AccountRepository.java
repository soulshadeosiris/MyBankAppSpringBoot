package com.example.myBankApp.Repositories;

import com.example.myBankApp.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
