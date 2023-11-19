package co.com.onneq.sqs.sender;

import co.com.onneq.model.user.gateways.UserBrokerRepository;
import co.com.onneq.sqs.sender.config.SQSSenderProperties;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Service
@Log4j2
@AllArgsConstructor
public class SQSSender implements UserBrokerRepository {
    private final SQSSenderProperties properties;
    private final SqsAsyncClient client;

    public Mono<String> send(String message) {
        return Mono.fromCallable(() -> buildRequest(message))
                .flatMap(request -> Mono.fromFuture(client.sendMessage(request)))
                .doOnNext(response -> log.debug("Message sent {}", response.messageId()))
                .map(SendMessageResponse::messageId);
    }

    private SendMessageRequest buildRequest(String message) {
        return SendMessageRequest.builder()
                .queueUrl(properties.getQueueUrl())
                .messageBody(message)
                .build();
    }

    @Override
    public Mono<String> sendUserId(String id) {
        return send(id);
    }
}
