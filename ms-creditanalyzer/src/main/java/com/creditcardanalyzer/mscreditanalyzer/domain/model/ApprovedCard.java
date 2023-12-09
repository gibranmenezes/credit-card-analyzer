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

    public ApprovedCard(CreditCard data, BigDecimal approvedLimit) {
        this.card = data.name();
        this.brand = data.brand();
        this.approvedLimit  = approvedLimit;

    }
}
