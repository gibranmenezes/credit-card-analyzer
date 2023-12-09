package com.creditcardanalyzer.mscreditanalyzer.domain;


import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientCard;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatus;

import java.util.List;

public record ClientStatusResponse(Long id, String name, List<ClientCard> cards) {

    public ClientStatusResponse(ClientStatus clientStatus) {
        this(clientStatus.getClient().getId(), clientStatus.getClient().getName(), clientStatus.getCards());

    }

}
