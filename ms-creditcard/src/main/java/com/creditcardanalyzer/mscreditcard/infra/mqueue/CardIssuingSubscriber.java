package com.creditcardanalyzer.mscreditcard.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuingSubscriber {

    @RabbitListener(queues = "${mq.queues.cards-issuing}")
    public void receiveIssuingRequest(@Payload String paylodad) {
        System.out.println(paylodad);
    }
}
