package com.creditcardanalyzer.mscreditcard.domain.dtos;

import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;

import java.math.BigDecimal;


/**
 * CreditCard data reponse'
 * */
    public record CardsByClientResponse(String name, String brand, BigDecimal approvedLimit) {

    public CardsByClientResponse(CreditCard card) {
        this(card.getName(), card.getBrand().toString(), card.getBaseLimit());
    }
}