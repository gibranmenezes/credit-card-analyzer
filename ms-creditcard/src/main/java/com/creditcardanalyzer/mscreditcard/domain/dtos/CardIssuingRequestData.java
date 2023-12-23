package com.creditcardanalyzer.mscreditcard.domain.dtos;

import java.math.BigDecimal;

public record CardIssuingRequestData(Long cardId, String cpf, String address, BigDecimal approvedLimit) {
}
