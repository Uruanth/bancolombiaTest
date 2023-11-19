package co.com.onneq.model.user.gateways;

import co.com.onneq.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserCacheRepository {
    Flux<User> getAll();
    Mono<Long> save(User user);

    Mono<Boolean> deleteAll();
}
