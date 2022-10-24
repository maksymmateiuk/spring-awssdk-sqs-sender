package com.mmateiuk.springawssdksqssender.consumer;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Component
@Slf4j
public class MessageConsumer {

    private static final String QUEUE_NAME = "demoQueue";

    public List<Message> receivedMessage() {
        SqsClient sqsClient = getSqsClient();

        String queueUrl = getQueueUrl(sqsClient);

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        log.info("{} Received messages from amazon SQS: {}", this.getClass().getSimpleName(), messages);
        return messages;
    }

    private String getQueueUrl(SqsClient sqsClient) {
        GetQueueUrlRequest getQueueRequest = GetQueueUrlRequest.builder()
                .queueName(QUEUE_NAME)
                .build();

        return sqsClient.getQueueUrl(getQueueRequest).queueUrl();
    }

    private SqsClient getSqsClient() {
        return SqsClient.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
