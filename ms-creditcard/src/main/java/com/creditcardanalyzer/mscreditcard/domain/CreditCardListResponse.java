package com.creditcardanalyzer.mscreditcard.domain;

import java.math.BigDecimal;

public record CreditCardListResponse(Long id, String name, CardBrand brand, BigDecimal income, BigDecimal baseLimit) {
    public CreditCardListResponse(CreditCard card) {
        this(card.getId(), card.getName(), card.getBrand(), card.getIncome(), card.getBaseLimit());
    }
}
