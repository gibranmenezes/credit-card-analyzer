package com.creditcardanalyzer.mscreditanalyzer.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ApprovedCard {
    String card;
    String brand;
    BigDecimal approvedLimit;

    public ApprovedCard(CreditCard creditCard, BigDecimal approvedLimit) {
        this.card = creditCard.getName();
        this.brand = creditCard.getBrand();
        this.approvedLimit  = approvedLimit;

    }
}
