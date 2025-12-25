package com.example.myBankApp.Controllers;

import com.example.myBankApp.Services.AccountService;
import org.springframework.web.bind.annotation.*;
import com.example.myBankApp.Models.Account;

import java.util.List;

@RestController
@RequestMapping("/users/{userid}/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/{accountId}/withdraw")
    public double withdraw(@PathVariable Long id, @RequestParam double amount) {
        return accountService.withdraw(id, amount);
    }

    @PostMapping("/{accountId}/deposit")
    public double deposit(@PathVariable Long id, @RequestParam double amount) {
        return accountService.deposit(id, amount);
    }

    @GetMapping("/{accountId}/balance")
    public double showBalance(@PathVariable Long id) {
        return accountService.showBalance(id);
    }


}
