package com.creditcardanalyzer.mscreditanalyzer.domain.model;


public record ClientStatusResponse(Long id, String name) {

    public ClientStatusResponse(ClientStatus clientStatus) {
        this(clientStatus.getClient().getId(), clientStatus.getClient().getName());

    }

}
