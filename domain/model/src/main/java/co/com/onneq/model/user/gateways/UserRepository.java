package co.com.onneq.model.user.gateways;

import co.com.onneq.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> getById(Integer id);

    Mono<User> saveUser(User user);

    Flux<User> getByName(String name);
    Flux<User> getAll();
}
