package com.mmateiuk.springawssdksqssender.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Component
@Slf4j
public class MessageProducer {

    private static final String QUEUE_NAME = "demoQueue";

    public <T> void sendMessage(T message) throws JsonProcessingException {
        SqsClient sqsClient = getSqsClient();
        String messageBody = new ObjectMapper().writeValueAsString(message);
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(QUEUE_NAME)
                .messageBody(messageBody)
                .delaySeconds(5)
                .build();

        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(messageRequest);
        log.info("{} Response from amazon SQS: {}", this.getClass().getSimpleName(), sendMessageResponse);
    }

    private SqsClient getSqsClient() {
        return SqsClient.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
