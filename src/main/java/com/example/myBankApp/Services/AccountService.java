package com.example.myBankApp.Services;

import com.example.myBankApp.Models.Account;
import com.example.myBankApp.Repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Long id, Account updateedAccount) {
        Account existingAccount = getAccountById(id);
        existingAccount.setAccountNumber(updateedAccount.getAccountNumber());
        existingAccount.setUser(updateedAccount.getUser());
        existingAccount.setBalance(updateedAccount.getBalance());
        return accountRepository.save(existingAccount);
    }

    public double showBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public double deposit(Long accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double updatedBalance = account.getBalance() + amount;
        account.setBalance(updatedBalance);

        accountRepository.save(account); // сохраняем изменения в базе

        return updatedBalance;
    }

    public void deleteAccount(Long accountId) {
        Account account = getAccountById(accountId);
        accountRepository.delete(account);
    }

    public double withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Withdraw amount exceeds your balance");
        }

        double updatedBalance = account.getBalance() - amount;
        account.setBalance(updatedBalance);

        accountRepository.save(account);

        return updatedBalance;
    }


}
