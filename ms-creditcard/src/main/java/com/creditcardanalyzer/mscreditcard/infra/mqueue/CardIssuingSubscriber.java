package com.creditcardanalyzer.mscreditcard.infra.mqueue;

import com.creditcardanalyzer.mscreditcard.domain.dtos.CardIssuingRequestData;
import com.creditcardanalyzer.mscreditcard.domain.models.ClientCard;
import com.creditcardanalyzer.mscreditcard.infra.repositories.ClientCardRepository;
import com.creditcardanalyzer.mscreditcard.infra.repositories.CreditCardRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuingSubscriber {

    private final CreditCardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.cards-issuing}")
    public void receiveIssuingRequest(@Payload String paylodad) {
       try {
           var mapper = new ObjectMapper();
           var data = mapper.readValue(paylodad, CardIssuingRequestData.class);
           var card = cardRepository.findById(data.cardId()).orElseThrow();

           var clientCard = new ClientCard();
           clientCard.setCards(card);
           clientCard.setCpf(data.cpf());
           clientCard.setApprovedLimit(data.approvedLimit());
           clientCardRepository.save(clientCard);
       } catch (Exception e){
           e.printStackTrace();

        }
    }
}
