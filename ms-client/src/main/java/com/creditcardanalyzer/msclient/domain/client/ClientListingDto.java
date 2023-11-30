package com.creditcardanalyzer.msclient.domain.client;

public record ClientListingDto(Long id, String name, String email, String cpf, Integer age) {
    public ClientListingDto(Client client){
        this(client.getId(), client.getName(), client.getEmail(), client.getCpf(), client.getAge());
    }
}
