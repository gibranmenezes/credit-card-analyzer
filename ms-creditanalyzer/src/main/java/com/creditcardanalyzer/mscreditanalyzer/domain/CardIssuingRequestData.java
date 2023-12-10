package com.creditcardanalyzer.mscreditanalyzer.domain;

import com.ctc.wstx.io.BaseInputSource;

import java.math.BigDecimal;

public record CardIssuingRequestData(Long cardId, String cpf, String address, BigDecimal approvedLimit) {
}
