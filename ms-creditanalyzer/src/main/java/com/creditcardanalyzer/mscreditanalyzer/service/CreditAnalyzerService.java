package com.creditcardanalyzer.mscreditanalyzer.service;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientCard;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientData;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatus;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.infra.clients.CardResourceClient;
import com.creditcardanalyzer.mscreditanalyzer.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAnalyzerService {

    private final ClientResourceClient clientResource;
    private final CardResourceClient cardResource;
    public ClientStatusResponse getClientStatus(String cpf){
        ResponseEntity<ClientData> clientData = clientResource.getClientByCpf(cpf);
        ResponseEntity<List<ClientCard>> clientCards = cardResource.getCardsByClientList(cpf);
        var clientStatus = ClientStatus
                .builder()
                .client(clientData.getBody())
                .cards(clientCards.getBody())
                .build();
        return new ClientStatusResponse(clientStatus);



    }
}
