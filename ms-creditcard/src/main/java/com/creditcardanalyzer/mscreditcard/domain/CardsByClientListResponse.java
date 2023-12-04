package com.creditcardanalyzer.mscreditcard.domain;

import ch.qos.logback.core.net.server.Client;

import java.math.BigDecimal;
import java.util.List;

public record CardsByClientListResponse(String cpf, String brand, BigDecimal approvedLimit) {

    public CardsByClientListResponse(CreditCard card, ClientCard client) {
        this(client.getCpf(), card.getBrand().toString(), card.getBaseLimit());
    }
}
