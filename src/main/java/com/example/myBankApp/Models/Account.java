package com.example.myBankApp.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, unique = false)
    private long accountNumber;

    @Column(nullable = false)
    private double balance;

    private LocalDateTime createdAt;

    @PrePersist
    public void wasCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    private LocalDateTime updatedAt;

    @PreUpdate
    public void wasUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public Account() {

    }

    public Account(User user, long accountNumber, double balance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
