package com.mmateiuk.springawssdksqssender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmateiuk.springawssdksqssender.consumer.MessageConsumer;
import com.mmateiuk.springawssdksqssender.model.common.Event;
import com.mmateiuk.springawssdksqssender.model.common.TnDetail;
import com.mmateiuk.springawssdksqssender.producer.MessageProducer;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
@Slf4j
public class ProvisionTnService {

    private final MessageProducer messageProducer;
    private final MessageConsumer messageConsumer;

    public ProvisionTnService(MessageProducer messageProducer, MessageConsumer messageConsumer) {
        this.messageProducer = messageProducer;
        this.messageConsumer = messageConsumer;
    }

    public TnDetail provision(TnDetail tnDetail) throws JsonProcessingException {

        log.info("{} Sent message to amazon SQS: {}", this.getClass().getSimpleName(), tnDetail);

        messageProducer.sendMessage(tnDetail);


        return tnDetail;
    }

    public List<Event> getProvisionedTns() {
        List<Event> tnDetails = messageConsumer.receivedMessage()
                .stream()
                .map(this::getTnDetail)
                .collect(Collectors.toList());

        log.info("{} Received messages from amazon SQS: {}", this.getClass().getSimpleName(), tnDetails);

        return tnDetails;
    }

    private Event getTnDetail(Message message){
        try {
            return new ObjectMapper().readValue(message.body(), Event.class);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            log.info("Not parceble: {}", message.body());
        }
        return new Event();
    }
}
