package com.creditcardanalyzer.mscreditcard.domain.dtos;

import com.creditcardanalyzer.mscreditcard.domain.enums.CardBrand;

import java.math.BigDecimal;

public record CreditCardSaveRequest(String name, CardBrand brand, BigDecimal income, BigDecimal baseLimit) {
}
