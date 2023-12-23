package com.creditcardanalyzer.mscreditanalyzer.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Receives data from msCreditCard: CardsByClientResponse */
@Data
public class CardClient {

    private String name;
    private String brand;
    private BigDecimal approvedLimit;

}
