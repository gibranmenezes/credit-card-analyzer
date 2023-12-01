package com.creditcardanalyzer.msclient.domain.client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record ClientRegisterDto(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        Integer age,
        @Email
        String email) {
}
