package com.creditcardanalyzer.mscreditanalyzer.domain;


import com.creditcardanalyzer.mscreditanalyzer.domain.model.CardClient;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatus;

import java.util.List;

public record ClientStatusResponse(Long id, String name, List<CardClient> cards) {

    public ClientStatusResponse(ClientStatus clientStatus) {
        this(clientStatus.getClient().getId(), clientStatus.getClient().getName(), clientStatus.getCards());

    }

}
