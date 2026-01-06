package com.example.myBankApp.Controllers;

import com.example.myBankApp.DTOs.CreateAccountRequest;
import com.example.myBankApp.Enums.TransactionType;
import com.example.myBankApp.Models.Transaction;
import com.example.myBankApp.Services.AccountService;
import com.example.myBankApp.Services.TransactionService;
import org.springframework.web.bind.annotation.*;
import com.example.myBankApp.Models.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users/{userid}/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public Account createAccount(@PathVariable Long userid,
                                 @RequestBody CreateAccountRequest request) {
        Account account = new Account();
        account.setBalance(request.getBalance());
        return accountService.createAccount(userid, account);
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    public List<Account> getAllAccountsByUser(@PathVariable Long userid) {
        return accountService.getAllAccountsByUser(userid);
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/{accountId}/withdraw")
    public Map<String, Object> withdraw(@PathVariable("accountId") Long accountId, @RequestParam double amount) {

        // обновляем баланс аккаунта
        double newBalance = accountService.withdraw(accountId, amount);

        // создаём транзакцию
        Transaction transaction = new Transaction();
        transaction.setAccount(accountService.getAccountById(accountId));
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAW);

        transactionService.createTransaction(transaction);

        // возвращаем баланс и транзакцию
        Map<String, Object> response = new HashMap<>();
        response.put("transaction", transaction);
        response.put("balance", newBalance);

        return response;
    }

    @PostMapping("/{accountId}/deposit")
    public Map<String, Object> deposit(@PathVariable("accountId") Long accountId, @RequestParam double amount) {

        // обновляем баланс аккаунта
        double newBalance = accountService.deposit(accountId, amount);

        // создаём транзакцию
        Transaction transaction = new Transaction();
        transaction.setAccount(accountService.getAccountById(accountId));
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);

        transactionService.createTransaction(transaction);

        // возвращаем баланс и транзакцию
        Map<String, Object> response = new HashMap<>();
        response.put("transaction", transaction);
        response.put("balance", newBalance);

        return response;
    }

    @GetMapping("/{accountId}/balance")
    public double showBalance(@PathVariable Long id) {
        return accountService.showBalance(id);
    }


}
