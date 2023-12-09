package com.creditcardanalyzer.msclient.domain.client.dtos;

import com.creditcardanalyzer.msclient.domain.client.Client;

public record ClientSaveResponse(Long id, String name, String email, String cpf, Integer age) {
    public ClientSaveResponse(Client client){
        this(client.getId(), client.getName(), client.getEmail(), client.getCpf(), client.getAge());
    }
}
