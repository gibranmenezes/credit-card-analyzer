package com.creditcardanalyzer.mscreditcard.domain;

import java.math.BigDecimal;

public record CreditCardRegistrationData(String name, CardBrand brand, BigDecimal income, BigDecimal baseLimit) {
}
