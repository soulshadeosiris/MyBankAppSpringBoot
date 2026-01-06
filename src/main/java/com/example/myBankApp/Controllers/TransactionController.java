package com.example.myBankApp.Controllers;

import com.example.myBankApp.Enums.TransactionType;
import com.example.myBankApp.Models.Account;
import com.example.myBankApp.Models.Transaction;
import com.example.myBankApp.Repositories.AccountRepository;
import com.example.myBankApp.Services.AccountService;
import com.example.myBankApp.Services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("accounts/{accountId}/transactions")

public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public TransactionController(TransactionService transactionService, AccountService accountService, AccountRepository accountRepository) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public Transaction createTransaction(@PathVariable Long accountId, @RequestBody Transaction transaction){
        Account account = accountService.getAccountById(accountId);
        transaction.setAccount(account);

        if (transaction.getType() == TransactionType.DEPOSIT) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if (transaction.getType() == TransactionType.WITHDRAW) {
            if (account.getBalance() < transaction.getAmount()) {
                throw new RuntimeException("Insufficient funds");
            }
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
        accountRepository.save(account);

        return transactionService.createTransaction(transaction);
    }

    @GetMapping()
    public List<Transaction> getAllTransactionsByAccount(@PathVariable Long accountId){
        return transactionService.getAllTransactionsByAccount(accountId);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }
}
