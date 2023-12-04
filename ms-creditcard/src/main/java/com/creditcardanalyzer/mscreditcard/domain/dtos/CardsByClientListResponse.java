package com.creditcardanalyzer.mscreditcard.domain.dtos;

import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;

import java.math.BigDecimal;

public record CardsByClientListResponse(String cpf, String brand, BigDecimal approvedLimit) {

    public CardsByClientListResponse(CreditCard card, ClientCard client) {
        this(client.getCpf(), card.getBrand().toString(), card.getBaseLimit());
    }
}
