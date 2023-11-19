package co.com.onneq.model.user.gateways;

import reactor.core.publisher.Mono;

public interface UserBrokerRepository {
    Mono<String> sendUserId(String id);
}
