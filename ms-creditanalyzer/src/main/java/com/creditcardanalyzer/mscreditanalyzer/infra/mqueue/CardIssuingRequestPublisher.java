package com.creditcardanalyzer.mscreditanalyzer.infra.mqueue;

import com.creditcardanalyzer.mscreditanalyzer.domain.CardIssuingRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardIssuingRequestPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardIssuing;

    public void requestCard(CardIssuingRequestData data) throws JsonProcessingException {
        var json = this.convertToJson(data);
        rabbitTemplate.convertAndSend(queueCardIssuing.getName(), json);

    }

    private String convertToJson(CardIssuingRequestData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
       return mapper.writeValueAsString(data);
    }

}
