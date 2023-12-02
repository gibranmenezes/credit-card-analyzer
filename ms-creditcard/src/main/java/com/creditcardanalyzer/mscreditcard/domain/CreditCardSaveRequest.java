package com.creditcardanalyzer.mscreditcard.domain;

import java.math.BigDecimal;

public record CreditCardSaveRequest(String name, CardBrand brand, BigDecimal income, BigDecimal baseLimit) {
}
