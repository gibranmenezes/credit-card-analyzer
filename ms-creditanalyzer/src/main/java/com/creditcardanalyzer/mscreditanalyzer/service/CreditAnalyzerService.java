package com.creditcardanalyzer.mscreditanalyzer.service;

import com.creditcardanalyzer.mscreditanalyzer.domain.CardIssuingProtocol;
import com.creditcardanalyzer.mscreditanalyzer.domain.CardIssuingRequestData;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.ClientAnalysisResponse;
import com.creditcardanalyzer.mscreditanalyzer.domain.ClientStatusResponse;
import com.creditcardanalyzer.mscreditanalyzer.domain.model.*;
import com.creditcardanalyzer.mscreditanalyzer.infra.exceptions.CardIssuingRequestError;
import com.creditcardanalyzer.mscreditanalyzer.infra.mqueue.CardIssuingRequestPublisher;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditAnalyzerService {

    private final ClientResourceClient clientResource;
    private final CardResourceClient cardResource;
    private final ClientDataFoundValidation clientDataValidator;
    private final CardIssuingRequestPublisher cardIssuingRequestPublisher;

    public ClientStatusResponse getClientStatus(String cpf)
            throws ClientDataNotFoundException, ServicesComunicationErrorException {
        try {
            ResponseEntity<ClientData> clientData = clientResource.getClientByCpf(cpf);
            ResponseEntity<List<CardClient>> cardsClientResponse = cardResource.getCardsByClient(cpf);
            var clientStatus = ClientStatus
                    .builder()
                    .client(clientData.getBody())
                    .cards(cardsClientResponse.getBody())
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
                approvedCard.setCardName(card.getName());
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

    public CardIssuingProtocol requestCardIssuing(CardIssuingRequestData data) {
        try {
            cardIssuingRequestPublisher.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new CardIssuingProtocol(protocol);

        } catch (Exception e) {
            throw new CardIssuingRequestError(e.getMessage());

        }
    }
    private BigDecimal calculateApprovedLimit(BigDecimal baseLimit, BigDecimal age){
        var factor = age.divide(BigDecimal.valueOf(10));
        return factor.multiply(baseLimit);

    }
}
