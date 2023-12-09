package com.creditcardanalyzer.mscreditcard.domain.dtos;

import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;

import java.math.BigDecimal;

public record CardsByClientListResponse(String name, String brand, BigDecimal approvedLimit) {

    public CardsByClientListResponse(CreditCard card, ClientCard client) {
        this(client.getName(), card.getBrand().toString(), card.getBaseLimit());
    }
}
