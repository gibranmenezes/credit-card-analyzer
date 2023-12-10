package com.creditcardanalyzer.mscreditanalyzer.infra.exceptions;

public class CardIssuingRequestError extends RuntimeException{
    public CardIssuingRequestError(String message){
        super(message);
    }
}
