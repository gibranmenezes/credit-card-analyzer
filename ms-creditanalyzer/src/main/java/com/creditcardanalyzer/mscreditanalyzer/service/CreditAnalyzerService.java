package com.creditcardanalyzer.mscreditanalyzer.service;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientCard;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientData;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatus;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.infra.clients.CardResourceClient;
import com.creditcardanalyzer.mscreditanalyzer.infra.clients.ClientResourceClient;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ServicesComunicationErrorException;
import com.creditcardanalyzer.mscreditanalyzer.infra.validations.ClientDataFoundValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAnalyzerService {

    private final ClientResourceClient clientResource;
    private final CardResourceClient cardResource;
    private final ClientDataFoundValidation clientDataValidator;

    public ClientStatusResponse getClientStatus(String cpf)
            throws ClientDataNotFoundException, ServicesComunicationErrorException {
        try {
            ResponseEntity<ClientData> clientData = clientResource.getClientByCpf(cpf);
            ResponseEntity<List<ClientCard>> clientCards = cardResource.getCardsByClientList(cpf);
            var clientStatus = ClientStatus
                    .builder()
                    .client(clientData.getBody())
                    .cards(clientCards.getBody())
                    .build();

            return new ClientStatusResponse(clientStatus);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (clientDataValidator.validate(status)) {
                throw new ClientDataNotFoundException();
            }
            throw new ServicesComunicationErrorException(e.getMessage(), status);
        }
    }
}
