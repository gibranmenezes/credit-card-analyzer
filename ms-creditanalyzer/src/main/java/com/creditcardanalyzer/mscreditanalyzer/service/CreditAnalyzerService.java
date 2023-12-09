package com.creditcardanalyzer.mscreditanalyzer.service;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientData;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatus;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.infra.clients.ClientResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAnalyzerService {

    private final ClientResourceClient clientResource;
    public ClientStatusResponse getClientStatus(String cpf){
        ResponseEntity<ClientData> clientData = clientResource.getClientByCpf(cpf);
        var clientStatus = ClientStatus.builder().client(clientData.getBody()).build();
        return new ClientStatusResponse(clientStatus);



    }
}
