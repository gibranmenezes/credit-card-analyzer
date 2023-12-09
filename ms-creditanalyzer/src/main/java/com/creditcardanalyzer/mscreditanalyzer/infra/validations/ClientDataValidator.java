package com.creditcardanalyzer.mscreditanalyzer.infra.validations;

import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;

public interface ClientDataValidator {
    boolean validate(int status) throws ClientDataNotFoundException;
}
