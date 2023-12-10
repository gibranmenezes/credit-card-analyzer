package com.creditcardanalyzer.mscreditanalyzer.service;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientAnalysisResponse;
import com.creditcardanalyzer.mscreditanalyzer.domain.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.*;
import com.creditcardanalyzer.mscreditanalyzer.infra.resources.CardResourceClient;
import com.creditcardanalyzer.mscreditanalyzer.infra.resources.ClientResourceClient;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ClientDataNotFoundException;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.ServicesComunicationErrorException;
import com.creditcardanalyzer.mscreditanalyzer.infra.validations.ClientDataFoundValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public ClientAnalysisResponse doAnalysis(String cpf, Long income)
            throws ClientDataNotFoundException, ServicesComunicationErrorException{
        try {
            ResponseEntity<ClientData> clientData = clientResource.getClientByCpf(cpf);
            ResponseEntity<List<CreditCard>> cardResponse = cardResource.getCardsByIncomeRange(income);

            List<CreditCard> cards = cardResponse.getBody();
            var approvedCards = cards.stream().map(card -> {
                var  client = clientData.getBody();
                var approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setBrand(card.getBrand());
                var age = BigDecimal.valueOf(client.getAge());
                approvedCard.setApprovedLimit(calculateApprovedLimit(card.getBaseLimit(),age));

                return approvedCard;
            }).toList();
            return new ClientAnalysisResponse(approvedCards);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (clientDataValidator.validate(status)) {
                throw new ClientDataNotFoundException();
            }
            throw new ServicesComunicationErrorException(e.getMessage(), status);
        }
    }

    private BigDecimal calculateApprovedLimit(BigDecimal baseLimit, BigDecimal age){
        var factor = age.divide(BigDecimal.valueOf(10));
        return factor.multiply(baseLimit);

    }
}
