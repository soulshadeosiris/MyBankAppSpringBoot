package com.example.myBankApp.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private int ccv;

    @Column(nullable = false)
    private int pinCode;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false, unique = true)
    private long cardNumber;

    @Column(nullable = false)
    @Transient
    private String cardHolderName;

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

    public Card() {

    }

    public Card(Account account, int ccv, int pinCode, LocalDate expirationDate, String cardType, long cardNumber, String cardHolderName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.account = account;
        this.ccv = ccv;
        this.pinCode = pinCode;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
