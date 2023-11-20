package co.com.onneq.model.user.gateways;

import reactor.core.publisher.Mono;

public interface UserPublisherRepository {
    Mono<String> sendUserId(String id);
}
