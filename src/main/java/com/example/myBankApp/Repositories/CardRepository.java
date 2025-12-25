package com.example.myBankApp.Repositories;

import com.example.myBankApp.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.myBankApp.Models.Card;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findCardByCardNumber(long cardNumber);
    List<Card> findAllByAccountId(Long accountId);
}
