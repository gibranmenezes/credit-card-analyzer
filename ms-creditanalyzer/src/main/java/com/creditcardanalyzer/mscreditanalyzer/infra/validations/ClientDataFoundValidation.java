package com.creditcardanalyzer.mscreditanalyzer.infra.validations;

import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ClientDataFoundValidation implements ClientDataValidator{
    @Override
    public boolean validate(int status)  {
        return HttpStatus.NOT_FOUND.value() == status;
    }
}
