package com.example.myBankApp.Services;

import com.example.myBankApp.Models.Account;
import com.example.myBankApp.Models.Card;
import com.example.myBankApp.Repositories.AccountRepository;
import com.example.myBankApp.Repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Card getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card updateCard(Long accountId, Long cardId, Card updatedCard) {
        Card currentCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (currentCard.getAccount() == null ||
                !accountId.equals(currentCard.getAccount().getId())) {
            throw new RuntimeException("Card does not belong to this account");
        }

        currentCard.setCcv(updatedCard.getCcv());
        currentCard.setPinCode(updatedCard.getPinCode());
        currentCard.setExpirationDate(updatedCard.getExpirationDate());
        currentCard.setCardType(updatedCard.getCardType());
        currentCard.setCardNumber(updatedCard.getCardNumber());
        currentCard.setCardHolderName(updatedCard.getCardHolderName());

        return cardRepository.save(currentCard);
    }

    public void deleteCard(Long id) {
        cardRepository.delete(getCardById(id));
    }

    public Card findCardByNumber(long cardNumber) {
        return cardRepository.findCardByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public List<Card> getAllCardsByAccount(Long accountId) {
        return cardRepository.findAllByAccountId(accountId);
    }
}
