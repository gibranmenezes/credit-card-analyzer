package com.creditcardanalyzer.mscreditanalyzer.infra.exceptions;

import lombok.Getter;

public class ServicesComunicationErrorException extends Exception {

    @Getter
    private Integer status;

    public ServicesComunicationErrorException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
