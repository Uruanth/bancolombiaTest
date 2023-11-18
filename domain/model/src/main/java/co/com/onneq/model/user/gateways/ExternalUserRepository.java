package co.com.onneq.model.user.gateways;

import co.com.onneq.model.user.User;
import reactor.core.publisher.Mono;

public interface ExternalUserRepository {

    Mono<User> getUserById(String id);

}
