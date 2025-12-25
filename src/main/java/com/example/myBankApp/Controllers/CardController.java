package com.example.myBankApp.Controllers;

import com.example.myBankApp.Models.Account;
import com.example.myBankApp.Models.Card;
import com.example.myBankApp.Services.AccountService;
import com.example.myBankApp.Services.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts/{accountId}/cards")
public class CardController {

    private final CardService cardService;
    private final AccountService accountService;

    public CardController(CardService cardService, AccountService accountService) {
        this.cardService = cardService;
        this.accountService = accountService;
    }

    @PostMapping
    public Card createCard(@PathVariable Long accountId, @RequestBody Card card) {
        Account account = accountService.getAccountById(accountId);
        card.setAccount(account);
        return cardService.createCard(card);
    }

    @GetMapping
    public List<Card> getAllCards(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return cardService.getAllCardsByAccount(accountId);
    }

    @GetMapping("/{cardId}")
    public Card getCardById(@PathVariable Long accountId, @PathVariable Long cardId) {
        return cardService.getCardById(cardId);
    }

    @PutMapping("/{cardId}")
    public Card updatedCard(
            @PathVariable Long accountId,
            @PathVariable Long cardId,
            @RequestBody Card card) {

        return cardService.updateCard(accountId, cardId, card);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable Long accountId, @PathVariable Long cardId) {
        cardService.deleteCard(cardId);
    }

    @GetMapping("/by-number/{cardNumber}")
    public Card findCardByNumber(@PathVariable long cardNumber) {
        return cardService.findCardByNumber(cardNumber);
    }
}

