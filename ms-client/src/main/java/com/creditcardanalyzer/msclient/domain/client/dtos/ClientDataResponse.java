package com.creditcardanalyzer.msclient.domain.client.dtos;

import com.creditcardanalyzer.msclient.domain.client.Client;

import java.util.Optional;

public record ClientDataResponse(Long id, String name) {
    public ClientDataResponse(Client client){
        this(client.getId(), client.getName());
    }

    public ClientDataResponse(Optional<Client> client) {
        this(client.get().getId(), client.get().getName());    }
}
