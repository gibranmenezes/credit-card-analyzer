package com.creditcardanalyzer.msclient.domain.client.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record ClientSaveRequest(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        Integer age,
        @Email
        String email) {
}
