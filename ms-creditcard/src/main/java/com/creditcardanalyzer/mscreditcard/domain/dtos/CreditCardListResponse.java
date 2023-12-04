package com.creditcardanalyzer.mscreditcard.domain.dtos;

import com.creditcardanalyzer.mscreditcard.domain.enums.CardBrand;
import com.creditcardanalyzer.mscreditcard.domain.models.CreditCard;

import java.math.BigDecimal;

public record CreditCardListResponse(Long id, String name, CardBrand brand, BigDecimal income, BigDecimal baseLimit) {
    public CreditCardListResponse(CreditCard card) {
        this(card.getId(), card.getName(), card.getBrand(), card.getIncome(), card.getBaseLimit());
    }
}
